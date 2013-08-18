package comportements.distributeur;


import java.util.ArrayList;

import utilitaires.Preferences;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import agents.AgentDistributeur;

/**
 * Classe : InitialisationDComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class InitialisationDComportement extends OneShotBehaviour {

	private static final long serialVersionUID = 3439302790940089059L;
	// Membres
	private AgentDistributeur monAgent;
	
	// Constructeur
	public InitialisationDComportement(AgentDistributeur distributeur){
		super(distributeur);
		this.monAgent = distributeur;	
	}

	@Override
	public void action() {
		
		Object[] arg = this.monAgent.getArguments(); // liste des arguments
		String monControleur = (String) arg[0];		
		// initialiser les membres de mon agent
		this.monAgent.setMonControleur(new AID(monControleur,AID.ISLOCALNAME));
		//System.out.println("J'ai reçu l'argument : "+monControleur + " de la part de "+this.monAgent.getMonControleur().getLocalName());
		//System.out.println("Initialisation Distributeur .."); 	// pour débuguer ..
		this.monAgent.setMesTransformateurs(new ArrayList<AID>());
		this.monAgent.setFileAttenteMessagesTransformateurs(new ArrayList<ACLMessage>());

		
		// crŽer autant de transformateur que le nombre entrŽ par l'utilisateur
		for(int i=0;i<Preferences.getNOMBRE_TRANSFORMATEURS_DISTRIBUTEUR();i++){
			this.monAgent.creerAgentTransformateur();
		}
		
		// Enregistrement dans DF :
		try {
			// Création de desciprion de l'agent [Distributeur]
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(this.monAgent.getAID());
			// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
			DFService.register(this.monAgent, dfd);
			//System.out.println(this.monAgent.getLocalName()+" REGISTERED WITH THE DF");
			} catch (FIPAException e) {
			e.printStackTrace();
			}


	



	}
	

}
