package statistiques.statisticienPL.comportements;

import statistiques.agents.AgentStatisticienPL;
import utilitaires.Preferences;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Classe : TraitementStatisticienPLComportement
 * Description : Comportement principal de l'agent Statisticien Producteur Local
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class TraitementStatisticienPLComportement extends CyclicBehaviour {

	
	private static final long serialVersionUID = -3416668879645608723L;
	// Membres :
	AgentStatisticienPL monAgent;

	/** Constructeur principal */
	public TraitementStatisticienPLComportement(AgentStatisticienPL statisticienPL) {
		super(statisticienPL);
		this.monAgent=statisticienPL;
	}		

	@Override
	public void action() {

		
		this.monAgent.doWait();
		// Reception des messages du compteur intelligent :
		ACLMessage message = this.monAgent.receive();
		 if(message.getOntology().equals(Preferences.PRODUCTION_LOCAL)){
			
			// message de l'Agent producteur local
			this.monAgent.ajouterDonneeProducteurLocal(message);
			// Verifier si la file d'attente n'est pas remplie :
			if(this.monAgent.fileAttentePLRemplie()){
				// communiquer la moyenne de la file d'attente à l'agent Interface Graphique :
				ACLMessage reponseStatisticien = new ACLMessage(ACLMessage.INFORM);
				reponseStatisticien.setOntology(Preferences.MOYENNE_DEBIT_FILE_PRODUCTEUR_LOCAL);
				reponseStatisticien.setContent(""+this.monAgent.calculerTotalFileProductionLocale());
				this.monAgent.informerAgentInterfaceGraphique(reponseStatisticien);
				this.monAgent.viderfileAttenteMessagePL();



			}else{
				this.monAgent.ajouterMessageFilePL(message);
			}
			
			//			
		}


	}

}
