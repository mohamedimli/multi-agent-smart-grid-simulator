package agents;

import java.util.ArrayList;

import comportements.transformateur.FinTComportement;
import comportements.transformateur.InitialisationTComportement;
import comportements.transformateur.TraitementTComportement;

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
 * Classe : Agent Transformateurs
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 * 
 */
public class AgentTranformateur extends Agent {
	
	private static final long serialVersionUID = -3839461314452834161L;
	// Membres
	private AID monDistributeur; 		// Mon distributeur, pour lui communiquer le r�sultat du calcul
	private ArrayList<AID> mesFoyers; 		// Mes foyers, � renseigner pendant leur cr�ation, utile pour v�rifier l'identit� de l'�metteur du message et pour savoir si la file d'attente est remplie !
	private ArrayList<AID> mesCompteursIntelligents;	// mes compteurs intelligents
	private ArrayList<ACLMessage> fileAttenteMessagesCompteurs ; // file d'attente des messages des compteurs intelligents
	
	/** Comportement g�n�rale du compteur intelligent */
	public void setup(){
		
		FSMBehaviour behaviourTransformateur = new FSMBehaviour(this);
				
				//Etats
		behaviourTransformateur.registerFirstState(new InitialisationTComportement(this),"initialisationTransformateur");
		behaviourTransformateur.registerState(new TraitementTComportement(this),"traitementTransformateur");
		behaviourTransformateur.registerLastState(new FinTComportement(this),"finTransformateur");
				
				//Transitions
		behaviourTransformateur.registerDefaultTransition("initialisationTransformateur","traitementTransformateur");
		behaviourTransformateur.registerTransition("traitementTransformateur","traitementTransformateur",1);
		behaviourTransformateur.registerTransition("traitementTransformateur","finTransformateur",0);
				
				addBehaviour(behaviourTransformateur);
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
	
	// Responsabilit�s :

	
	/** cr�e une instance de l'agent Foyer */
	public void creerAgentFoyer(){
		// r�cuperer le nombre de mes Agents Distributeurs :
		int nombreFoyers=0;
		if(this.mesFoyers != null){
			nombreFoyers=this.mesFoyers.size();
		}
		// Incr�mentation 
		nombreFoyers++;
		String nomNouveauFoyer=this.getAID().getLocalName() + "F"+nombreFoyers;
		String monIdentite = this.getAID().getLocalName();
		createAgent(nomNouveauFoyer, AgentFoyer.class.getName(), new Object[] {monIdentite});
		  AID  identifiantNouveauFoyer = new AID(nomNouveauFoyer,AID.ISLOCALNAME);
		  this.mesFoyers.add(identifiantNouveauFoyer);

	}
	
	/** @return retourne vrai si la file d'attente des messages est remplie */
	public boolean fileAttenteRemplie(){
		// On suppose ici que le nombre des compteurs intelligents est �gal au nombre des foyers !
		return (this.fileAttenteMessagesCompteurs.size() == this.mesFoyers.size());
	}
	
	/** cette fonction vide la file d'attente des messages */
	public void viderfileAttenteMessage(){
		this.fileAttenteMessagesCompteurs.clear();
	}
	/** cette fonction ajoute un message � la file d'attente des messages */
	public void ajouterMessageFileAttente(ACLMessage message){
		// V�rifier si un message de l'emetteur n'existe d�ja dans la file d'attente
		boolean existance = false;
		AID emetteur = message.getSender();
		for(int i=0;i<this.fileAttenteMessagesCompteurs.size();i++){
			
				if (this.fileAttenteMessagesCompteurs.get(i).getSender() == emetteur) {
					existance = true;
			}
		}
		if(! existance){
			this.fileAttenteMessagesCompteurs.add(message);
		}
	}
	
	
	/** Calculer la somme des r�sultats de la diff�rence de tous les compteurs int�lligents de tous les foyers li�s au Transformateur
	 * @return : la somme des r�sultats de la diff�rence de tous les compteurs int�lligents de tous les foyers li�s au Transformateur 
	 * */
	public double calculerSommeResCompteurs(){
		double somme =0;
		if(fileAttenteRemplie()){
			for(int i=0;i<this.fileAttenteMessagesCompteurs.size();i++){
				if(this.fileAttenteMessagesCompteurs.get(i) != null){
					somme+=Double.parseDouble(this.fileAttenteMessagesCompteurs.get(i).getContent());
				}
			}
		}
		return somme;
	}
	/** Communiquer le r�sultat de traitement du Transformateur au distributeur */
	public void informerDistributeur(){
		// Envoie du message au Distributeur :
			ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
			reponse.setContent(this.calculerSommeResCompteurs()+"");
			reponse.addReceiver(this.monDistributeur);
			this.send(reponse); 
	   }
		
	/**
	 * Fonction qui instancie un agent.
	 * @param agentName nom de l'agent
	 * @param agentClass Chemin complet de la classe de l'agent � instancier (package + nom de la classe).
	 * @param argumets Liste des atguments � passer � l'agent (peut �tre "null")
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
	
// Setters and getters .. 
	
	public AID getMonDistributeur() {
		return monDistributeur;
	}

	public void setMonDistributeur(AID monDistributeur) {
		this.monDistributeur = monDistributeur;
	}

	public ArrayList<AID> getMesFoyers() {
		return mesFoyers;
	}

	public void setMesFoyers(ArrayList<AID> mesFoyers) {
		this.mesFoyers = mesFoyers;
	}

	public ArrayList<AID> getMesCompteursIntelligents() {
		return mesCompteursIntelligents;
	}

	public void setMesCompteursIntelligents(ArrayList<AID> mesCompteursIntelligents) {
		this.mesCompteursIntelligents = mesCompteursIntelligents;
	}

	public ArrayList<ACLMessage> getFileAttenteMessagesCompteurs() {
		return fileAttenteMessagesCompteurs;
	}

	public void setFileAttenteMessagesCompteurs(
			ArrayList<ACLMessage> fileAttenteMessagesCompteurs) {
		this.fileAttenteMessagesCompteurs = fileAttenteMessagesCompteurs;
	}


}
