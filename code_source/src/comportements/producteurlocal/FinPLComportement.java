package comportements.producteurlocal;

import agents.AgentProducteurLocal;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Classe : FinPLComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class FinPLComportement extends OneShotBehaviour {

	private static final long serialVersionUID = -4678189912406688717L;
	
	// membres
	private AgentProducteurLocal monAgent;

	// constructeur
	public FinPLComportement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FinPLComportement(AgentProducteurLocal producteurLocal) {
		super(producteurLocal);
		this.monAgent=producteurLocal;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

}
