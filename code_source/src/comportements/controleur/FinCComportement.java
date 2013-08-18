package comportements.controleur;

import agents.AgentControleur;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Classe : FinCComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class FinCComportement extends OneShotBehaviour {

	private static final long serialVersionUID = 3629410283940262729L;
	// Membres
	private AgentControleur monAgent;
	//  Constructeurs
	public FinCComportement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FinCComportement(AgentControleur controleur) {
		super(controleur);
		this.monAgent=controleur;
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

}
