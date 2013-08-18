package agents;


import java.util.ArrayList;

import comportements.distributeur.FinDComportement;
import comportements.distributeur.InitialisationDComportement;
import comportements.distributeur.TraitementDComportement;

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
 * Classe : Agent Distributeur
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 * 
 */ 

public class AgentDistributeur extends Agent {
	
	private static final long serialVersionUID = 4091691942166368944L;
	// Membres
	private AID monControleur; 		// pour lui communiquer le résultat du calcul
	private ArrayList<AID> mesTransformateurs; 		// à renseigner lors de leur création, utile pour vérifier l'identité de l'émetteur du message et pour savoir si la file d'attente est remplie !
	private ArrayList<ACLMessage> fileAttenteMessagesTransformateurs ; 		// file d'attente des messages de distributeurs

	/** Comportement générale du Distributeur */
	public void setup(){
		// Comportement Agent Distributeur :
		
		FSMBehaviour behaviourDistributeur = new FSMBehaviour(this);
		
		//Etats
	 behaviourDistributeur.registerFirstState(new InitialisationDComportement(this),"initialisationDistributeur");
	 behaviourDistributeur.registerState(new TraitementDComportement(this),"traitementDistributeur");
	 behaviourDistributeur.registerLastState(new FinDComportement(this),"finDistributeur");
		
		//Transitions
	 behaviourDistributeur.registerDefaultTransition("initialisationDistributeur","traitementDistributeur");
	 behaviourDistributeur.registerTransition("traitementProducteur","traitementDistributeur",1);
	 behaviourDistributeur.registerTransition("traitementProducteur","finDistributeur",0);
		
		addBehaviour(behaviourDistributeur);
	}
	
	protected void takeDown() {
		// Deregister from the yellow pages
		
		try {
			DFService.deregister(this);
			}
			catch (FIPAException fe) {
			fe.printStackTrace();
			}
		//TODO
		
			}
	
	
	// Responsabilités :	
	/** crée une instance de l'agent Distributeur */
	public void creerAgentTransformateur(){
		// récuperer le nombre de mes Agents Distributeurs :
		int nombreTransformateurs=0;
		if(this.mesTransformateurs != null){
			nombreTransformateurs=this.mesTransformateurs.size();
			
		} 
		nombreTransformateurs++; // Incrémentation
		String monIdentite = this.getAID().getLocalName();
		String nomNouveauTransformateur=this.getAID().getLocalName() + "T"+nombreTransformateurs;
		createAgent(nomNouveauTransformateur, AgentTranformateur.class.getName(), new Object[] {monIdentite});
		  AID  identifiantNouveauDistributeur = new AID(nomNouveauTransformateur,AID.ISLOCALNAME);
		  this.mesTransformateurs.add(identifiantNouveauDistributeur);

	}
	
	/** @return retourne vrai si la file d'attente des messages est remplie */
	public boolean fileAttenteRemplie(){
		return (this.fileAttenteMessagesTransformateurs.size() == this.mesTransformateurs.size());
	}
	
	/** cette fonction vide la file d'attente des messages */
	public void viderfileAttenteMessage(){
		this.fileAttenteMessagesTransformateurs.clear();
	}
	/** cette fonction ajoute un message à la file d'attente des messages */
	public void ajouterMessageFileAttente(ACLMessage message){
		// Vérifier si un message de l'emetteur n'existe déja dans la file d'attente
		boolean existance = false;
		AID emetteur = message.getSender();
		for(int i=0;i<this.fileAttenteMessagesTransformateurs.size();i++){
			
				if (this.fileAttenteMessagesTransformateurs.get(i).getSender() == emetteur) {
					existance = true;
			}
		}
		if(! existance){
			this.fileAttenteMessagesTransformateurs.add(message);
		}
	}
	
	
	
	/** Calculer la somme des résultats de la différence de tous les Transformateurs liés à ce Distributeur */
	public double calculerSommeResTransformateurs(){
		double somme =0;
		if(fileAttenteRemplie()){
			for(int i=0;i<this.fileAttenteMessagesTransformateurs.size();i++){
				if(this.fileAttenteMessagesTransformateurs.get(i) != null){
					somme+=Double.parseDouble(this.fileAttenteMessagesTransformateurs.get(i).getContent());
				}
			}
		}
		return somme;
	}
	/** Communiquer le résultat de traitement du Distributeur au Controleur */
	public void informerControleur(){
		// Envoie du message au Distributeur :
			ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
			reponse.setContent(this.calculerSommeResTransformateurs()+"");
			reponse.addReceiver(this.monControleur);
			this.send(reponse);
			System.out.println("Controleur informé ..");
	   }
		
	
	
	// Setters et Getters .. !
	
	public AID getMonControleur() {
		return monControleur;
	}

	public void setMonControleur(AID monControleur) {
		this.monControleur = monControleur;
	}

	public ArrayList<AID> getMesTransformateurs() {
		return mesTransformateurs;
	}

	public void setMesTransformateurs(ArrayList<AID> mesTransformateurs) {
		this.mesTransformateurs = mesTransformateurs;
	}

	public ArrayList<ACLMessage> getFileAttenteMessagesTransformateurs() {
		return fileAttenteMessagesTransformateurs;
	}

	public void setFileAttenteMessagesTransformateurs(
			ArrayList<ACLMessage> fileAttenteMessagesTransformateurs) {
		this.fileAttenteMessagesTransformateurs = fileAttenteMessagesTransformateurs;
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
