package interfaceGraphique.comportements;

import interfaceGraphique.agents.AgentInterfaceGraphique;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Classe : FinInterfaceGraphiqueComprtement
 * Description : Comportement de fin de l'agent interface graphique
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class FinInterfaceGraphiqueComprtement extends OneShotBehaviour {
	
	
	
	private static final long serialVersionUID = 4356543057326001175L;
	AgentInterfaceGraphique monAgent;

	public FinInterfaceGraphiqueComprtement(AgentInterfaceGraphique interfaceGraphique) {
		super(interfaceGraphique);
		this.monAgent=interfaceGraphique;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

}
