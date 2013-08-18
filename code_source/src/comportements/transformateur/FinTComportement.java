package comportements.transformateur;

import agents.AgentProducteurLocal;
import agents.AgentTranformateur;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Classe : FinTComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class FinTComportement extends OneShotBehaviour {

	private static final long serialVersionUID = -4678189912406688717L;
	
	// membres
	private AgentTranformateur monAgent;

	// constructeur
	public FinTComportement() {
		super();
	}

	public FinTComportement(AgentTranformateur transformateur) {
		super(transformateur);
		this.monAgent=transformateur;
	}
	
	@Override
	public void action() {
		this.monAgent.doDelete();
	}

}
