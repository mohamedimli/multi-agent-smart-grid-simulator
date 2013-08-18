package interfaceGraphique.comportements;


import utilitaires.FIFO;
import utilitaires.Preferences;
import interfaceGraphique.agents.AgentInterfaceGraphique;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Classe : TraitementInterfaceGraphiqueComportement
 * Description : Comportement principal de l'agent Interface graphique
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class TraitementInterfaceGraphiqueComportement extends CyclicBehaviour {

	
	private static final long serialVersionUID = -537142470868458277L;
	AgentInterfaceGraphique monAgent;

	public TraitementInterfaceGraphiqueComportement(AgentInterfaceGraphique interfaceGraphique) {
		super(interfaceGraphique);
		this.monAgent=interfaceGraphique;
	}

	@Override
	public void action() {

		ACLMessage message = myAgent.receive();
		if(message != null ){
			if( message.getOntology().equals(Preferences.PRODUCTION_PRODUCTEUR)){


				FIFO temp =((AgentInterfaceGraphique) this.myAgent).getFileDebitProducteur();
				temp.ajouter(Double.parseDouble(message.getContent()));
				((AgentInterfaceGraphique) this.myAgent).setFileDebitProducteur(temp);
				
				this.monAgent.getDonneesDebitProducteur().addSeries("Production", ((AgentInterfaceGraphique) this.myAgent).getFileDebitProducteur().retournerTableau());
				this.monAgent.getDonneesDebitsTotaux().addSeries("Production", this.monAgent.getFileDebitProducteur().retournerTableau());



			} else if (message.getOntology().equals(Preferences.MOYENNE_DEBIT_FILE_PRODUCTEUR_LOCAL)){
				FIFO temp =((AgentInterfaceGraphique) this.myAgent).getFileDebitProducteurLocal();
				System.out.println("Production locale ..");
				temp.ajouter(Double.parseDouble(message.getContent()));
				((AgentInterfaceGraphique) this.myAgent).setFileDebitProducteurLocal(temp);
				this.monAgent.getDonneesDebitProducteurLocal().addSeries("Production locale", ((AgentInterfaceGraphique) this.myAgent).getFileDebitProducteurLocal().retournerTableau());
				this.monAgent.getDonneesDebitsTotaux().addSeries("Production locale", this.monAgent.getFileDebitProducteurLocal().retournerTableau());

				
			} else if (message.getOntology().equals(Preferences.MOYENNE_DEBIT_FILE_FOYER)){
				FIFO temp =((AgentInterfaceGraphique) this.myAgent).getFileDebitFoyer();
				temp.ajouter(Double.parseDouble(message.getContent()));
				((AgentInterfaceGraphique) this.myAgent).setFileDebitFoyer(temp);
				this.monAgent.getDonneesDebitFoyer().addSeries("Consommation", ((AgentInterfaceGraphique) this.myAgent).getFileDebitFoyer().retournerTableau());
				this.monAgent.getDonneesDebitsTotaux().addSeries("Consommation", ((AgentInterfaceGraphique) this.myAgent).getFileDebitFoyer().retournerTableau());
				
				
				
				
				
				
				
				
				
			}
		} // fin si message != null

	}

}
