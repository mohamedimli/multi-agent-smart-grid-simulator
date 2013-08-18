package statistiques.statisticienPL.comportements;

import statistiques.agents.AgentStatisticienPL;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Classe : FinStatisticienPLComportement
 * Description : Comportement de fin de l'agent Statisticien Producteur Local
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */
public class FinStatisticienPLComportement extends OneShotBehaviour {



	private static final long serialVersionUID = -9093303556804756963L;
	// Membres :
	AgentStatisticienPL monAgent;

	/** Constructeur principal */
	public FinStatisticienPLComportement(AgentStatisticienPL statisticienPL) {
		super(statisticienPL);
		this.monAgent=statisticienPL;
	}		


	@Override
	public void action() {
		this.monAgent.doDelete(); // mission accomplie, tuer l'agent !
	}

}
