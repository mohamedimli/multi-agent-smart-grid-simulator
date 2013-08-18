package comportements.transformateur;


import java.util.ArrayList;

import utilitaires.Preferences;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import agents.AgentTranformateur;

/**
 * Classe : InitialisationTComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class InitialisationTComportement extends OneShotBehaviour {

	private static final long serialVersionUID = -4347856828495428803L;
	// Membres
	private AgentTranformateur monAgent;
	
	// Constructeur
	public InitialisationTComportement(AgentTranformateur transformateur){
		super(transformateur);
		this.monAgent = transformateur;	
	}

	@Override
	public void action() {
		Object[] arg = this.monAgent.getArguments(); // Récupérer les arguments 
		String monDistributeur = (String) arg[0];
		
		// initialiser les membrs de mon agent
		this.monAgent.setMonDistributeur(new AID(monDistributeur,AID.ISLOCALNAME));
		//System.out.println("J'ai reçu l'argument : "+monDistributeur + " de la part de "+this.monAgent.getMonDistributeur().getLocalName());		
		this.monAgent.setMesFoyers(new ArrayList<AID>());
		
		// crŽe autant de foyers que le nombre entrŽ par l'utilisateur
		for(int i=0;i<Preferences.getNOMBRE_FOYERS_TRANSFORMATEUR();i++){
			this.monAgent.creerAgentFoyer();
			
		this.monAgent.setFileAttenteMessagesCompteurs(new ArrayList<ACLMessage>());
		}
		
		
		// Enregistrement dans DF :
		try {
			// Création de desciprion de l'agent [Producteur Local]
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(this.monAgent.getAID());
			// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
			DFService.register(this.monAgent, dfd);
			//System.out.println(this.monAgent.getLocalName()+" REGISTERED WITH THE DF");
			} catch (FIPAException e) {
			e.printStackTrace();
			}
		//System.out.println("Initialisation Transformateur ..");


	}
	

}
