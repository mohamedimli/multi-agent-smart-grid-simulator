package comportements.foyer;

import java.util.ArrayList;

import utilitaires.Preferences;

import agents.AgentFoyer;
import appareils.AppareilElectrique;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Classe : InitialisationFComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class InitialisationFComportement extends OneShotBehaviour {

	private static final long serialVersionUID = 7377262706940861747L;
	// Membres
	private AgentFoyer monAgent;
	
	// Constructeur
	public InitialisationFComportement(AgentFoyer foyer){
		super(foyer);
		this.monAgent = foyer;	
	}

	@Override
	public void action() {		
		Object[] arg = this.monAgent.getArguments(); // Récupérer les arguments .. 
		String monTransformateur = (String) arg[0];
		
		
		// initialiser les membres de mon agent
		this.monAgent.setMonTransformateur(new AID(monTransformateur,AID.ISLOCALNAME));
		ArrayList<AppareilElectrique> listeAppareils = new ArrayList<AppareilElectrique>();
		this.monAgent.setListeAppareils(listeAppareils); // important 		
		// Test création agent producteur local :
		this.monAgent.creerAgentProducteurLocal();
	   	// Test création agent compteur intelligent :
		this.monAgent.creerAgentCompteurIntelligent();
	   	
	

		monAgent.initialiserListeAppareils(); //
		
		// Enregistrement dans DF :
				try {
					// Création de desciprion de l'agent [Foyer]
					DFAgentDescription dfd = new DFAgentDescription();
					dfd.setName(this.monAgent.getAID());
					// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
					DFService.register(this.monAgent, dfd);
				//  Enregistrer le service ProducteurLocal
		            ServiceDescription foyerService  = new ServiceDescription();
		            foyerService.setType( Preferences.FOYER_SERVICE );
		            dfd.addServices(foyerService);
					//System.out.println(this.monAgent.getLocalName()+" REGISTERED WITH THE DF");
					} catch (FIPAException e) {
					e.printStackTrace();
					}


		
		//System.out.println("Initialisation Foyer ..");



	}
	

}
