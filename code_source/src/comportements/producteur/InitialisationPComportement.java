package comportements.producteur;


import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import agents.AgentProducteur;

/**
 * Classe : InitialisationPComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class InitialisationPComportement extends OneShotBehaviour {

	private static final long serialVersionUID = -5429800667492784589L;
	// Membres
	
	private AgentProducteur monAgent;
	
	// Constructeur
	
	public InitialisationPComportement(AgentProducteur producteur){
		super(producteur);
		this.monAgent = producteur;	
	}

	@Override
	public void action() {
		this.monAgent.setMaxDebitProduction(10000000); // on suppose que 10000000 est le maximum d'energie que le producteur global peut produire
		this.monAgent.demarrer(); // on demarre le producteur
		
		// Enregistrement dans DF :
				try {
					// Création de desciprion de l'agent [Producteur]
					DFAgentDescription dfd = new DFAgentDescription();
					dfd.setName(this.monAgent.getAID());
					// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
					DFService.register(this.monAgent, dfd);
					//System.out.println(this.monAgent.getLocalName()+" REGISTERED WITH THE DF");
					} catch (FIPAException e) {
					e.printStackTrace();
					}


		//System.out.println("Initialisation producteur ..");
		

	}
	

}
