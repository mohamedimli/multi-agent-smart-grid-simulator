package statistiques.statisticienF.comportements;

import statistiques.agents.AgentStatisticienF;
import utilitaires.Preferences;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


/**
 * Classe : TraitementStatisticienFComportement
 * Description : Comportement principal de l'agent Statisticien Foyer
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class TraitementStatisticienFComportement extends CyclicBehaviour {


	private static final long serialVersionUID = -3416668879645608723L;
	// Membres :
	AgentStatisticienF monAgent;


	public TraitementStatisticienFComportement(AgentStatisticienF statisticienF) {
		super(statisticienF);
		this.monAgent=statisticienF;
	}		

	@Override
	public void action() {


		this.monAgent.doWait();
		// Reception des messages du compteur intelligent :
		ACLMessage message = this.monAgent.receive();
		if(message.getOntology().equals(Preferences.CONSOMMATION_FOYER)){

			// message de l'Agent producteur local
			this.monAgent.ajouterDonneeFoyer(message);
			// Verifier si la file d'attente n'est pas remplie :
			if(this.monAgent.fileAttenteFoyersRemplie()){
				// communiquer la moyenne de la file d'attente à l'agent Interface Graphique :
				ACLMessage reponseStatisticien = new ACLMessage(ACLMessage.INFORM);
				reponseStatisticien.setOntology(Preferences.MOYENNE_DEBIT_FILE_FOYER);
				reponseStatisticien.setContent(""+this.monAgent.calculerTotalFileConsommation());
				this.monAgent.informerAgentInterfaceGraphique(reponseStatisticien);
				this.monAgent.viderfileAttenteMessageFoyers();	



			}else{
				this.monAgent.ajouterMessageFileFoyers(message);
			}

		}


	}

}





