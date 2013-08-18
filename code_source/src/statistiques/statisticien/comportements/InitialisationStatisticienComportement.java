package statistiques.statisticien.comportements;

import java.util.ArrayList;

import statistiques.agents.AgentStatisticien;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;


/**
 * Classe : InitialisationStatisticienComportement
 * Description : Comportement d'initialisation de l'agent Statisticien.
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 * 
 */
public class InitialisationStatisticienComportement extends OneShotBehaviour {
	
	
	private static final long serialVersionUID = -1203367462220118512L;
	// Membres :
	AgentStatisticien monAgent;


	public InitialisationStatisticienComportement(AgentStatisticien statisticien) {
		super(statisticien);
		this.monAgent=statisticien;
		
		
		
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		
		// Initialisation des membres de mon agent :
				this.monAgent.setDonneesFoyers(new ArrayList<ACLMessage>());
				this.monAgent.setDonneesProducteur(new ArrayList<ACLMessage>());
				this.monAgent.setDonneesProLocaux(new ArrayList<ACLMessage>());
				this.monAgent.setFileAttentedonneesFoyers(new ArrayList<ACLMessage>());
				this.monAgent.setFileAttentedonneesProLocaux(new ArrayList<ACLMessage>());
		// Enregistrement dans le DF :
				try {
					// Création de description l'agent [Statisticien]
					DFAgentDescription dfd = new DFAgentDescription();
					dfd.setName(this.monAgent.getAID());
					// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
					DFService.register(this.monAgent, dfd);
					System.out.println(this.monAgent.getLocalName()+" REGISTERED WITH THE DF");
					} catch (FIPAException e) {
					e.printStackTrace();
					}
		
		// création de l'agent Interface Graphique :

				this.monAgent.creerAgentInterfaceGraphique();
				this.monAgent.creerAgentStatisticienPL();
				this.monAgent.creerAgentStatisticienF();

				
				System.out.println("Initialisation Statisticien ..");

	}


}
