package statistiques.statisticienF.comportements;

import statistiques.agents.AgentStatisticienF;
import jade.core.behaviours.OneShotBehaviour;


/**
 * Classe : FinStatisticienFComportement
 * Description : Comportement de fin de l'agent Statisticien Foyer
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class FinStatisticienFComportement extends OneShotBehaviour {
	

	
	private static final long serialVersionUID = -9093303556804756963L;
		// Membres :
		AgentStatisticienF monAgent;


		public FinStatisticienFComportement(AgentStatisticienF statisticienF) {
			super(statisticienF);
			this.monAgent=statisticienF;
		}		


	@Override
	public void action() {
		this.monAgent.doDelete();
	}

}
