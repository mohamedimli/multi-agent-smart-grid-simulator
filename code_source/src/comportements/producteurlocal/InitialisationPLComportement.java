package comportements.producteurlocal;

import java.util.Random;

import utilitaires.Preferences;

import agents.AgentProducteurLocal;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Classe : InitialisationPLComportement
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class InitialisationPLComportement extends OneShotBehaviour {

	private static final long serialVersionUID = 5694063040161825841L;
	// Membres
	AgentProducteurLocal monAgent;
	
	// Constructeur
	
	public InitialisationPLComportement(AgentProducteurLocal panneauSolaire){
		this.monAgent = panneauSolaire;
		
	}

	@Override
	public void action() {

		
		// Récupérer les arguments .. juste pour tester .. 
		String monFoyer;
		String monCompteurIntelligent;
		Object[] arg = this.monAgent.getArguments(); //
		monFoyer = (String) arg[0];
		monCompteurIntelligent = (String) arg[1]; 
		
		
		// initialiser les membres de mon agent
		
		this.monAgent.setMonFoyer(new AID(monFoyer,AID.ISLOCALNAME));
		this.monAgent.setMonCompteurIntelligent(new AID(monCompteurIntelligent,AID.ISLOCALNAME));
		
		// Initialiser le max du débit de la production:
		Random rd = new Random();
		double diffMaxMinDebit = Preferences.getMAX_DEBIT_PRODUCTEUR_LOCAL() - Preferences.getMIN_DEBIT_PRODUCTEUR_LOCAL();
		double debitMax = Preferences.getMIN_DEBIT_PRODUCTEUR_LOCAL() + ( rd.nextDouble() * diffMaxMinDebit);
		this.monAgent.setMaxDebitProduction(debitMax);
		
		// Enregistrement dans DF :
				try {
					// Création de desciprion de l'agent [Producteur Local]
					DFAgentDescription dfd = new DFAgentDescription();
					dfd.setName(this.monAgent.getAID());
					// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
					DFService.register(this.monAgent, dfd);
					//  Enregistrer le service ProducteurLocal
		            ServiceDescription prodLocalService  = new ServiceDescription();
		            prodLocalService.setType( Preferences.PRODUCTEUR_LOCAL_SERVICE );
		            dfd.addServices(prodLocalService);
					//
					//System.out.println(this.monAgent.getLocalName()+" REGISTERED WITH THE DF");
					} catch (FIPAException e) {
					e.printStackTrace();
					}
				
				//System.out.println("Initialisation producteur local ..");



		
		

	}
	

}
