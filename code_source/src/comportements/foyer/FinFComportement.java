package comportements.foyer;

import agents.AgentFoyer;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Classe : FinFComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class FinFComportement extends OneShotBehaviour {


	private static final long serialVersionUID = -5565221608872001958L;

	private AgentFoyer monAgent;

	public FinFComportement() {
		super();
	}

	public FinFComportement(AgentFoyer foyer) {
		super(foyer);
		this.monAgent=foyer;
	}
	
	@Override
	public void action() {

	}

}
