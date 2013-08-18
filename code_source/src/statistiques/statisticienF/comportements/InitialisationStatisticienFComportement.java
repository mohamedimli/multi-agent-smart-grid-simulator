package statistiques.statisticienF.comportements;

import java.util.ArrayList;

import statistiques.agents.AgentStatisticienF;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

/**
 * Classe : InitialisationStatisticienFComportement
 * Description : Comportement d'initialisation de l'agent Statisticien Producteur Local
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */
public class InitialisationStatisticienFComportement extends OneShotBehaviour {
	
	
	private static final long serialVersionUID = -1203367462220118512L;
	// Membres :
	AgentStatisticienF monAgent;


	public InitialisationStatisticienFComportement(AgentStatisticienF statisticienF) {
		super(statisticienF);
		this.monAgent=statisticienF;
		
		
		
	}
	
	@Override
	public void action() {		
		// Initialisation des membres de mon agent :
				this.monAgent.setDonneesFoyers(new ArrayList<ACLMessage>());
				this.monAgent.setFileAttentedonneesFoyers(new ArrayList<ACLMessage>());
		// Enregistrement dans le DF :
				try {
					// Création de description l'agent [StatisticienF]
					DFAgentDescription dfd = new DFAgentDescription();
					dfd.setName(this.monAgent.getAID());
					// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
					DFService.register(this.monAgent, dfd);
					} catch (FIPAException e) {
					e.printStackTrace();
					}
    		
				

	}


}
