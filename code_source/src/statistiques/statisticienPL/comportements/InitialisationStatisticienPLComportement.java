package statistiques.statisticienPL.comportements;

import java.util.ArrayList;

import statistiques.agents.AgentStatisticienPL;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

/**
 * Classe : InitialisationStatisticienPLComportement
 * Description : Comportement d'initialisation de l'agent Statisticien Producteur Local
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class InitialisationStatisticienPLComportement extends OneShotBehaviour {
	

	private static final long serialVersionUID = -1203367462220118512L;
	// Membres :
	AgentStatisticienPL monAgent;

	/** Constructeur principal */
	public InitialisationStatisticienPLComportement(AgentStatisticienPL statisticienPL) {
		super(statisticienPL);
		this.monAgent=statisticienPL;
		
	}
	
	@Override
	public void action() {		
		// Initialisation des membres de mon agent :
				this.monAgent.setDonneesProLocaux(new ArrayList<ACLMessage>());
				this.monAgent.setFileAttentedonneesProLocaux(new ArrayList<ACLMessage>());
		// Enregistrement dans le DF :
				try {
					// Création de description l'agent [StatisticienPL]
					DFAgentDescription dfd = new DFAgentDescription();
					dfd.setName(this.monAgent.getAID());
					// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
					DFService.register(this.monAgent, dfd);
					} catch (FIPAException e) {
					e.printStackTrace();
					}
	}


}
