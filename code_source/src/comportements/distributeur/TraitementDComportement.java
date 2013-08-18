package comportements.distributeur;

import agents.AgentDistributeur;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Classe : TraitementDComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class TraitementDComportement extends CyclicBehaviour {
	
	private static final long serialVersionUID = 2834231074306184727L;
	// Membres
	private AgentDistributeur monAgent;

	// Constructeur par défault
	public TraitementDComportement() {
		super();
	}
	// Constructeur avec paramètre :
	public TraitementDComportement( AgentDistributeur distributeur) {
		super(distributeur);
		this.monAgent=distributeur;
		
	}
	
	@Override
	public void action() {
		// Reception du message :
		myAgent.doWait();
		ACLMessage message = this.monAgent.receive();
		if (message != null){
			//System.out.println("J'ai reçu le message suivant : "+message.getContent() +" de la part de : " +message.getSender());
			this.monAgent.ajouterMessageFileAttente(message);
			if(this.monAgent.fileAttenteRemplie()){
				// Communiquer le résultat a mon Controleur
				this.monAgent.informerControleur();
				// et ne pas oublier de vider la file d'attente
				this.monAgent.viderfileAttenteMessage();
			}
		}

	}

}
