package comportements.compteurIntelligent;

import utilitaires.Preferences;
import agents.AgentCompteurIntelligent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


/**
 * Classe : TraitementCIComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */


public class TraitementCIComportement extends CyclicBehaviour {
	
	private static final long serialVersionUID = -3772015411246970101L;
	// Membres
	private AgentCompteurIntelligent monAgent;

	// Constructeur
	public TraitementCIComportement() {
		super();
	}

	public TraitementCIComportement(AgentCompteurIntelligent compteurIntelligent) {
		super(compteurIntelligent);
		this.monAgent=compteurIntelligent;
		
	}
	
	@Override
	public void action() {
		// Reception du message :
		myAgent.doWait();
		ACLMessage message = this.monAgent.receive();
		if (message != null){
			this.monAgent.ajouterMessage(message);
			//System.out.println("J'ai reçu le message suivant : "+message.getContent() +" de la part de : " +message.getSender());
			if(this.monAgent.fileAttenteRemplie()){
				this.monAgent.informerTransformateur();
				this.monAgent.viderfileAttenteMessage();
				// informer le statisticien :
				String reponse="";
				String ontologie="";
				//if (message.getSender().getLocalName().equals(this.monAgent.getMonFoyer().getLocalName()) ){
				if (message.getOntology().equals(Preferences.CONSOMMATION_FOYER) ){
					 ontologie = Preferences.CONSOMMATION_FOYER;
					 reponse=message.getContent();
						this.monAgent.informerStatisticien(reponse,ontologie,Preferences.agentStatisticienF);
				}else if (message.getOntology().equals(Preferences.PRODUCTION_LOCAL)){
					 ontologie = Preferences.PRODUCTION_LOCAL;
					reponse=message.getContent();
					this.monAgent.informerStatisticien(reponse,ontologie,Preferences.agentStatisticienPL);
				}
				
				
			}
		}

	}

}
