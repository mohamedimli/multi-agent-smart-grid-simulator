package comportements.controleur;

import agents.AgentControleur;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Classe : TraitementCComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class TraitementCComportement extends CyclicBehaviour {
	
	// Membres
	
	private static final long serialVersionUID = -1903067591839204688L;
	private AgentControleur monAgent;

	
	// Constructeurs
	public TraitementCComportement() {
		super();
	}

	public TraitementCComportement( AgentControleur controleur) {
		super(controleur);
		this.monAgent=controleur;
		
	}
	
	@Override
	public void action() {
		// Reception du message :
		myAgent.doWait();
		ACLMessage message = this.monAgent.receive();
		if (message != null){
			this.monAgent.ajouterMessageFileAttente(message);
			if(this.monAgent.fileAttenteRemplie()){
				this.monAgent.demanderAjousterProduction();
				this.monAgent.viderfileAttenteMessage();
			}
		}

	}

}
