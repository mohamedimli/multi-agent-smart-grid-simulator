package statistiques.statisticien.comportements;

import statistiques.agents.AgentStatisticien;
import utilitaires.Preferences;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


/**
 * Classe : TraitementStatisticienComportement
 * Description : Comportement principal de l'agent Statisticien			
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 * 
 */
public class TraitementStatisticienComportement extends CyclicBehaviour {


	private static final long serialVersionUID = 8520389639213835313L;
	// Membres :
	AgentStatisticien monAgent;


	public TraitementStatisticienComportement(AgentStatisticien statisticien) {
		super(statisticien);
		this.monAgent=statisticien;
	}		

	@Override
	public void action() {

		this.monAgent.doWait();
		// Reception des messages du compteur intelligent :
		ACLMessage message = this.monAgent.receive();
		if(message.getOntology().equals(Preferences.DEMANDE_LANCEMENT_SIMULATION)){
			this.monAgent.creerAgentControleur(); // créer l'agent controleur ce qui permet de lancer la simulation

		}else if(message.getOntology().equals(Preferences.PRODUCTION_LOCAL)){

			// message de l'Agent producteur local
			this.monAgent.ajouterDonneeProducteurLocal(message);
			// Verifier si la file d'attente n'est pas remplie :
			if(this.monAgent.fileAttentePLRemplie()){
				// communiquer la moyenne de la file d'attente à l'agent Interface Graphique :
				ACLMessage reponseStatisticien = new ACLMessage(ACLMessage.INFORM);
				reponseStatisticien.setOntology(Preferences.MOYENNE_DEBIT_FILE_PRODUCTEUR_LOCAL);
				reponseStatisticien.setContent(""+this.monAgent.calculerMoyenneFileProductionLocale());

				this.monAgent.informerAgentInterfaceGraphique(reponseStatisticien);
				this.monAgent.viderfileAttenteMessagePL();	



			}else{
				this.monAgent.ajouterMessageFilePL(message);
			}


		}else if (message.getOntology().equals(Preferences.CONSOMMATION_FOYER)){
			// message de l'Agent Foyer
			this.monAgent.ajouterDonneeFoyer(message);

			// Verifier si la file d'attente n'est pas remplie :
			if(this.monAgent.fileAttenteFoyersRemplie()){
				// communiquer la moyenne de la file d'attente à l'agent Interface Graphique :
				ACLMessage reponseStatisticien = new ACLMessage(ACLMessage.INFORM);
				reponseStatisticien.setOntology(Preferences.MOYENNE_DEBIT_FILE_FOYER);
				reponseStatisticien.setContent(""+this.monAgent.calculerMoynneFileConsommation());

				this.monAgent.informerAgentInterfaceGraphique(reponseStatisticien);
				this.monAgent.viderfileAttenteMessagePL();	



			}else{
				this.monAgent.ajouterMessageFileFoyers(message);
			}

			//

		}else if (message.getOntology().equals(Preferences.PRODUCTION_PRODUCTEUR)){
			// message de l'Agent producteur
			this.monAgent.ajouterDonneeProducteur(message);
			// communiquer production :
			ACLMessage  reponseTest = new ACLMessage(ACLMessage.INFORM);
			reponseTest.setContent(message.getContent());
			reponseTest.setOntology(Preferences.PRODUCTION_PRODUCTEUR);
			this.monAgent.informerAgentInterfaceGraphique(reponseTest);

		}




	}

}
