package comportements.compteurIntelligent;


import java.util.ArrayList;

import agents.AgentCompteurIntelligent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

/**
 * Classe : InitialisationCIComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class InitialisationCIComportement extends OneShotBehaviour {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2290426702462646632L;
	// Membres
	private AgentCompteurIntelligent monAgent;
	
	// Constructeur
	public InitialisationCIComportement(AgentCompteurIntelligent compteurIntelligent){
		super(compteurIntelligent);
		this.monAgent = compteurIntelligent;
	}

	@Override
	public void action() {
		this.monAgent.setFileAttenteMessages(new ArrayList<ACLMessage>());
		Object[] arg = this.monAgent.getArguments(); // Récupérer les arguments ..
		String monFoyer = (String) arg[0];
		String monTransformateur = (String) arg[1];
		String monProducteurLocal = (String) arg[2];
		
		// initialiser les membres de mon agent
		this.monAgent.setMonTransformateur(new AID(monTransformateur,AID.ISLOCALNAME));
		this.monAgent.setMonFoyer(new AID(monFoyer,AID.ISLOCALNAME));
			if(monProducteurLocal.length() !=0){
				this.monAgent.setMonProducteurLocal(new AID(monProducteurLocal,AID.ISGUID));
			}
		
		try {
			// Création de description l'agent [CompteurIntelligent]
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(this.monAgent.getAID());
			// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
			DFService.register(this.monAgent, dfd);
			} catch (FIPAException e) {
			e.printStackTrace();
			}
		



	}
	

}
