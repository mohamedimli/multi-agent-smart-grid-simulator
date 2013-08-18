package comportements.transformateur;

import agents.AgentTranformateur;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Classe : TraitementTComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class TraitementTComportement extends CyclicBehaviour {
	
	// Membres
	private static final long serialVersionUID = -937928509568702299L;
	private AgentTranformateur monAgent;

	
	// Constructeur par défaut 
	public TraitementTComportement() {
		super();
	}
	// Constructeur avec paramètre
	public TraitementTComportement( AgentTranformateur transformateur) {
		super(transformateur);
		this.monAgent=transformateur;
		
	}
	
	@Override
	public void action() {
		// Reception du message :
		myAgent.doWait();
		ACLMessage message = this.monAgent.receive();
		if (message != null){
			this.monAgent.ajouterMessageFileAttente(message);
			//System.out.println("J'ai reçu le message suivant : "+message.getContent() +" de la part de : " +message.getSender());
			if(this.monAgent.fileAttenteRemplie()){
				// Communiquer le résultat à mon Distributeur
				this.monAgent.informerDistributeur();
				// et ne pas oublier de vider la file d'attente
				this.monAgent.viderfileAttenteMessage();
			}
		}

	}

}
