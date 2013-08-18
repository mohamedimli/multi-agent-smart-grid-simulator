package comportements.producteur;

import agents.AgentProducteur;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Classe : TraitementPComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class TraitementPComportement extends CyclicBehaviour {
	
	private static final long serialVersionUID = -1175966049234509973L;
	// Membres
	private AgentProducteur monAgent;

	// Constructeur par défaut
	public TraitementPComportement() {
		super();
	}
	// Constructeur avec paramètre
	public TraitementPComportement( AgentProducteur producteur) {
		super(producteur);
		this.monAgent=producteur; 
		
	}
	
	@Override
	public void action() {
		// Reception du message :
		myAgent.doWait();
		ACLMessage message = this.monAgent.receive();
		if (message != null){
			//System.out.println("J'ai reçu le message suivant : "+message.getContent() +" de la part de : " +message.getSender());
			double productionDemandee =Double.parseDouble(message.getContent());
			String reponseStatisticien="";
			if(productionDemandee<=this.monAgent.getMaxDebitProduction()){
				this.monAgent.ajusterMProduction(productionDemandee);
				//System.out.println("Production Ajustée !");
				reponseStatisticien+=productionDemandee;
			}else{
				this.monAgent.ajusterMProduction(this.monAgent.getMaxDebitProduction());
				//System.out.println("Production Ajustée au Max !");
				reponseStatisticien+=this.monAgent.getMaxDebitProduction();
				// informer le statisticien :
				}
			this.monAgent.informerStatisticien(reponseStatisticien);

		}

	}

}
