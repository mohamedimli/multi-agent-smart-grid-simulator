package comportements.producteurlocal;

import agents.AgentProducteurLocal;
import jade.core.behaviours.TickerBehaviour;

/**
 * Classe : EnvoiProductionComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class EnvoiProductionComportement extends TickerBehaviour {
	
	private static final long serialVersionUID = 1149427695644416618L;
	
	// membres
	private AgentProducteurLocal monAgent;

	// constructeur
	public EnvoiProductionComportement(AgentProducteurLocal panneauSolaire, long periode) {
		super(panneauSolaire, periode); 
		this.monAgent=panneauSolaire;
	}

	@Override
	protected void onTick() {
		//System.out.println(myAgent.getLocalName()+ " je produis : " + this.monAgent.produireEnergie());
		// Envoie du message au compteur intelligent :
		this.monAgent.informerCompteurIntelligent();
		//this.monAgent.informerAgentStatisticien();


	}

}
