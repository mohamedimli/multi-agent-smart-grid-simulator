package agents;

import java.util.ArrayList;

import utilitaires.Preferences;

import comportements.compteurIntelligent.FinCIComportement;
import comportements.compteurIntelligent.InitialisationCIComportement;
import comportements.compteurIntelligent.TraitementCIComportement;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;


/**
 * Classe : Agent Compteur Intelligent
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 * 
 */

public class AgentCompteurIntelligent extends Agent {

	
	
	private static final long serialVersionUID = 1389369427493929755L;
	
	// Membres
	ArrayList<ACLMessage> fileAttenteMessages; // pour stocker les données reçues du foyer et du producteur local
	AID monFoyer; // adresse de mon foyer
	AID monProducteurLocal; // adresse de mon producteur local
	AID monTransformateur; //  // adresse de mon transformateur
	
	
	/** Comportement générale du compteur intelligent */
	protected void setup(){
		 FSMBehaviour behaviourCompteurIntelligent = new FSMBehaviour(this);
			
			//Etats
		 behaviourCompteurIntelligent.registerFirstState(new InitialisationCIComportement(this),"initialisationCI");
		 behaviourCompteurIntelligent.registerState(new TraitementCIComportement(this),"traitementCI");
		 behaviourCompteurIntelligent.registerLastState(new FinCIComportement(this),"finCI");
			
			//Transitions
		 behaviourCompteurIntelligent.registerDefaultTransition("initialisationCI","traitementCI");
		 behaviourCompteurIntelligent.registerTransition("traitementCI","traitementCI",1);
		 behaviourCompteurIntelligent.registerTransition("traitementCI","finCI",0);
			
			addBehaviour(behaviourCompteurIntelligent);	    
		   
		
		
	}
	
	/** Pour desenregistrer du DF avant de tuer l'agent */
	protected void takeDown() {
		
		try {
			DFService.deregister(this);
			}
			catch (FIPAException fe) {
			fe.printStackTrace();
			}
		
			}
	
	public boolean fileAttenteRemplie(){
		if(this.monProducteurLocal != null){
			return (this.fileAttenteMessages.size() == 2);
		}else{
			return (this.fileAttenteMessages.size() == 1);
		}		
	}
	public int signeCoefDifference(){
		if(this.fileAttenteMessages.get(0).getSender() ==this.monFoyer){
			return 1;
		}else{
			return -1;
		}
	}
	
	
	/** cette fonction vide la file d'attente des messages */
	
	public void viderfileAttenteMessage(){
		this.fileAttenteMessages.clear();
	}
	
	/** @return  la différence entre la consommation et la production du foyer */
	public double calculerDifferenceConsoProd(){
		double difference =0;
		if(this.fileAttenteMessages.size() == 2 ){
			 difference = Double.parseDouble(this.fileAttenteMessages.get(1).getContent()) - Double.parseDouble(this.fileAttenteMessages.get(0).getContent()); 
		}else if(this.fileAttenteMessages.size() == 1){
			difference = Double.parseDouble(this.fileAttenteMessages.get(0).getContent());
		}
		return (difference * signeCoefDifference() );
	}
	/** informer le Transformateur */
	   public void informerTransformateur(){
				ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
				reponse.setContent(this.calculerDifferenceConsoProd()+"");
				reponse.setOntology(Preferences.DIFFERENCE_COMPTEUR_INTELLIGENT);
				reponse.addReceiver(this.monTransformateur);
				this.send(reponse);
		   }
	   
	   /** informer le Statisticien 
	    * 		@param agentstatisticien : AID de l'agent statisticien destinataire
	    * 		@param ontologie : L'ontoogie du message à envoyer
	    *		@param message : Contenu du message à envoyer
	    */
	   public void informerStatisticien(String message,String ontologie, AID agentstatisticienDestinataire){
				ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
				reponse.setContent(message);
				reponse.setOntology(ontologie);
				reponse.addReceiver(agentstatisticienDestinataire);
				this.send(reponse); 
		   }
	   
	 /** Ajouter un message à la file d'attente 
	  * @param : message : Message ACL à ajouter à la file 
	  * */
	   public void ajouterMessage(ACLMessage message){
		   
		// Vérifier si un message de l'emetteur n'existe déja dans la file d'attente
			boolean existance = false;
			AID emetteur = message.getSender();
			for(int i=0;i<this.fileAttenteMessages.size();i++){
					if (this.fileAttenteMessages.get(i).getSender() == emetteur) {
						existance = true;
				}
			}
			if((! existance) && (! this.fileAttenteRemplie())){
				this.fileAttenteMessages.add(message);
			}
		   	   }
	
	// Getters and Setters ..

	public AID getMonTransformateur() {
		return monTransformateur;
	}

	public void setMonTransformateur(AID monTransformateur) {
		this.monTransformateur = monTransformateur;
	}

	public ArrayList<ACLMessage> getFileAttenteMessages() {
		return fileAttenteMessages;
	}

	public void setFileAttenteMessages(ArrayList<ACLMessage> fileAttenteMessages) {
		this.fileAttenteMessages = fileAttenteMessages;
	}

	public AID getMonFoyer() {
		return monFoyer;
	}

	public void setMonFoyer(AID monFoyer) {
		this.monFoyer = monFoyer;
	}

	public AID getMonProducteurLocal() {
		return monProducteurLocal;
	}

	public void setMonProducteurLocal(AID monProducteurLocal) {
		this.monProducteurLocal = monProducteurLocal;
	}
	
	
	
} 


