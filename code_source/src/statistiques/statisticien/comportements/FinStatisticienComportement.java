package statistiques.statisticien.comportements;

import statistiques.agents.AgentStatisticien;
import jade.core.behaviours.OneShotBehaviour;


/**
 * Classe : TraitementStatisticienPLComportement
 * Description : Comportement principal de l'agent Statisticien Producteur Local
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 * 
 */
public class FinStatisticienComportement extends OneShotBehaviour {
	
	
	private static final long serialVersionUID = 5552684953133439775L;
		// Membres :
		AgentStatisticien monAgent;


		public FinStatisticienComportement(AgentStatisticien statisticien) {
			super(statisticien);
			this.monAgent=statisticien;
		}		


	@Override
	public void action() {
		this.monAgent.doDelete();
	}

}
