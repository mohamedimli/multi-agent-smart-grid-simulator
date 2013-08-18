package comportements.foyer;

import agents.AgentFoyer;
import jade.core.behaviours.TickerBehaviour;

/**
 * Classe : EnvoiConsommationComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class EnvoiConsommationComportement extends TickerBehaviour {
	
	
	//membres
	private static final long serialVersionUID = 1038359605672598986L;
	private AgentFoyer monAgent;

	//Constructeurs
	public EnvoiConsommationComportement(AgentFoyer foyer, long periode) {
		super(foyer, periode); 
		this.monAgent=foyer;
	}

	@Override
	protected void onTick() {
		// this.monAgent.arreterDemarrerAppareils();
		//System.out.println(myAgent.getLocalName()+ " je consomme : " + this.monAgent.calculerDebitConsommationFoyer());
		// Envoie du message au compteur intelligent :
		this.monAgent.informerCompteurIntelligent();

	}

}
