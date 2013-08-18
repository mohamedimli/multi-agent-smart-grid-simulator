package comportements.controleur;


import java.util.ArrayList;

import utilitaires.Preferences;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import agents.AgentControleur;

/**
 * Classe : InitialisationCComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class InitialisationCComportement extends OneShotBehaviour {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2225263239490783276L;
	// Membres
	private AgentControleur monAgent;
	
	// Constructeur
	public InitialisationCComportement(AgentControleur controleur){
		super(controleur);
		this.monAgent = controleur;	
	}

	@Override
	public void action() {
		this.monAgent.setMesDistributeurs( new ArrayList<AID>());
		this.monAgent.setFileAttenteMessagesDistributeurs(new ArrayList<ACLMessage>());
		this.monAgent.creerAgentproducteur();
		for(int i=0;i<Preferences.getNOMBRE_DISTRIBUTEURS_CONTROLEUR();i++){
			this.monAgent.creerAgentDistributeur();
		}
		
		try {
			// Création de desciprion de l'agent [Controleur]
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(this.monAgent.getAID());
			// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
			DFService.register(this.monAgent, dfd);
			} catch (FIPAException e) {
			e.printStackTrace();
			}		

	}
	

}
