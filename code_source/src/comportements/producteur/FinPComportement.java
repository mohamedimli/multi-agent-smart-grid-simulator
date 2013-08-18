package comportements.producteur;

import agents.AgentProducteur;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Classe : FinPComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class FinPComportement extends OneShotBehaviour {

	
	private static final long serialVersionUID = -4678189912406688717L;
	
	// membres
	private AgentProducteur monAgent;

	// constructeurs
	public FinPComportement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FinPComportement(AgentProducteur producteur) {
		super(producteur);
		this.monAgent=producteur;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

}
