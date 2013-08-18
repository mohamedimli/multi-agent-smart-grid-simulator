package comportements.distributeur;

import agents.AgentDistributeur;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Classe : FinDComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class FinDComportement extends OneShotBehaviour {

	private static final long serialVersionUID = -5199458758846037515L;

	private AgentDistributeur monAgent;

	public FinDComportement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FinDComportement(AgentDistributeur distributeur) {
		super(distributeur);
		this.monAgent=distributeur;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

}
