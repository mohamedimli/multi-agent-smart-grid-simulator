package agents;


import java.util.ArrayList;

import comportements.controleur.FinCComportement;
import comportements.controleur.InitialisationCComportement;
import comportements.controleur.TraitementCComportement;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;



/**
 * Classe : Agent Controleur
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 */

public class AgentControleur extends Agent{
	
	
	private static final long serialVersionUID = 1650891030544302833L;
	
	// Membres
	
	private AID monProducteur=null; 	// pour lui communiquer le résultat du calcul
	private ArrayList<AID> mesDistributeurs=null; 	// à renseigner lors de leur création, utile pour vérifier l'identité de l'émetteur du message et pour savoir si la file d'attente est remplie !
	private ArrayList<ACLMessage> fileAttenteMessagesDistributeurs=null ; // file d'attente des messages de distributeurs

	
	public void setup(){
		 
		
FSMBehaviour behaviourControleur = new FSMBehaviour(this);
		
		//Etats
	 behaviourControleur.registerFirstState(new InitialisationCComportement(this),"initialisationControleur");
	 behaviourControleur.registerState(new TraitementCComportement(this),"traitementControleur");
	 behaviourControleur.registerLastState(new FinCComportement(this),"finControleur");
		
		//Transitions
	 behaviourControleur.registerDefaultTransition("initialisationControleur","traitementControleur");
	 behaviourControleur.registerTransition("traitementProducteur","traitementControleur",1);
	 behaviourControleur.registerTransition("traitementControleur","finControleur",0);
		
		addBehaviour(behaviourControleur);	 
	}
	


	protected void takeDown() {
		
		try {
			DFService.deregister(this);
			}
			catch (FIPAException fe) {
			fe.printStackTrace();
			}
		//TODO
		
			}
	
		
	
	// Responsabilités :
	
	/** crée une instance de l'agent Producteur */
	public void creerAgentproducteur(){
		String monIdentite = this.getAID().getLocalName();
		createAgent("monProducteur", AgentProducteur.class.getName(), new Object[] {monIdentite});
		  this.monProducteur = new AID("monProducteur",AID.ISLOCALNAME);
	}
	
	
	/** crée une instance de l'agent Distributeur */
	public void creerAgentDistributeur(){
		// récuperer le nombre de mes Agents Distributeurs :
		int nombreDistributeurs=0;
		if(this.mesDistributeurs != null){
			nombreDistributeurs=this.mesDistributeurs.size();
		}
		nombreDistributeurs++;  // Incrémentation 
		String nomNouveauDistributeur=this.getAID().getLocalName() + "D"+nombreDistributeurs;
		String monIdentite = this.getAID().getLocalName();
		createAgent(nomNouveauDistributeur, AgentDistributeur.class.getName(), new Object[] {monIdentite});
		  AID  identifiantNouveauDistributeur = new AID(nomNouveauDistributeur,AID.ISLOCALNAME);
		  this.mesDistributeurs.add(identifiantNouveauDistributeur);

	}
	
	/** @return retourne vrai si la file d'attente des messages est remplie */
	public boolean fileAttenteRemplie(){
		return this.fileAttenteMessagesDistributeurs.size() == this.mesDistributeurs.size();
	}
	
	
	
	/** cette fonction vide la file d'attente des messages */
	public void viderfileAttenteMessage(){
		this.fileAttenteMessagesDistributeurs.clear();
	}
	
	/** cette fonction ajoute un message à la file d'attente des messages */
	public void ajouterMessageFileAttente(ACLMessage message){
		// Vérifier si un message de l'emetteur n'existe déja dans la file d'attente
		boolean existance = false;
		AID emetteur = message.getSender();
		for(int i=0;i<this.fileAttenteMessagesDistributeurs.size();i++){
			
				if (this.fileAttenteMessagesDistributeurs.get(i).getSender() == emetteur) {
					existance = true;
			}
		}
		if(! existance){
			this.fileAttenteMessagesDistributeurs.add(message);
		}
	}
	
	
	/** Calculer la somme des résultats reçues de tous les Distributeurs liés à ce Distributeur */
	public double calculerSommeResDistributeurs(){
		double somme =0;
		if(fileAttenteRemplie()){
			for(int i=0;i<this.fileAttenteMessagesDistributeurs.size();i++){
				if(this.fileAttenteMessagesDistributeurs.get(i) != null){ // très important !
					somme+=Double.parseDouble(this.fileAttenteMessagesDistributeurs.get(i).getContent());
				}
			}
		}
		return somme;
	}
	
	/** Communiquer le résultat de traitement du Distributeur au Controleur */
	public void demanderAjousterProduction(){
		// Envoie du message au Producteur :
			ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
			double nouvelleProduction = Math.abs(2*(1.05) * (this.calculerSommeResDistributeurs())); // 5% de plus par rapport au besoin ponctuel
			reponse.setContent(nouvelleProduction+"");
			reponse.addReceiver(this.monProducteur);
			this.send(reponse);
			System.out.println("Demande ajustement production envoyée ..");
	   }
	
	// Setters and getters .. !
	
	public AID getMonProducteur() {
		return monProducteur;
	}

	public void setMonProducteur(AID monProducteur) {
		this.monProducteur = monProducteur;
	}

	public ArrayList<AID> getMesDistributeurs() {
		return mesDistributeurs;
	}

	public void setMesDistributeurs(ArrayList<AID> mesDistributeurs) {
		this.mesDistributeurs = mesDistributeurs;
	}

	public ArrayList<ACLMessage> getFileAttenteMessagesDistributeurs() {
		return fileAttenteMessagesDistributeurs;
	}

	public void setFileAttenteMessagesDistributeurs(
			ArrayList<ACLMessage> fileAttenteMessagesDistributeurs) {
		this.fileAttenteMessagesDistributeurs = fileAttenteMessagesDistributeurs;
	}

	/**
	 * Fonction qui instancie un agent.
	 * @param agentName nom de l'agent
	 * @param agentClass Chemin complet de la classe de l'agent à instancier (package + nom de la classe).
	 * @param argumets Liste des atguments à passer à l'agent (peut être "null")
	 */
	private void createAgent(String agentName, String agentClass, Object[] argumets) {
		ContainerController containetController = getContainerController();
		try {
			AgentController newAgent = containetController.createNewAgent(agentName, agentClass, argumets);
			newAgent.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}

}
