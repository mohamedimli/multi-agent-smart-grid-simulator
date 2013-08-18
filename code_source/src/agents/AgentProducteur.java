package agents;


import java.util.Random;

import utilitaires.Preferences;

import comportements.producteur.FinPComportement;
import comportements.producteur.InitialisationPComportement;
import comportements.producteur.TraitementPComportement;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;


public class AgentProducteur extends Agent {
	
	private static final long serialVersionUID = 3683311026607364328L;
	// Membres
	// private ArrayList<AID> mesDistributeurs; //  ça sera utile si on veut controller la distribution de l'éléctricité
	// private AID monControlleur;	// ça sera utile si on veut vérifier l'identité du demandeur d'ajustement de la production
	private double MaxDebitProduction; // Le débit Maximum que le producteur peut produire
	private double debitProduction; 	// débit de la production de l'énérgie  
	private boolean EnMarche; // EnMarche est à TRUE si le producteur est en marche
	
	/** Comportement générale du Producteur */
	 public void setup(){
		 
		 FSMBehaviour behaviourFoyer = new FSMBehaviour(this);
			
			//Etats
		 behaviourFoyer.registerFirstState(new InitialisationPComportement(this),"initialisationProducteur");
		 behaviourFoyer.registerState(new TraitementPComportement(this),"traitementProducteur");
		 behaviourFoyer.registerLastState(new FinPComportement(this),"finProducteur");
			
			//Transitions
		 behaviourFoyer.registerDefaultTransition("initialisationProducteur","traitementProducteur");
		 behaviourFoyer.registerTransition("traitementProducteur","traitementProducteur",1);
		 behaviourFoyer.registerTransition("traitementProducteur","finProducteur",0);
			
			addBehaviour(behaviourFoyer);	  
		 
	 }
	 
	 protected void takeDown() {
			// Deregister from the yellow pages
			
			try {
				DFService.deregister(this);
				}
				catch (FIPAException fe) {
				fe.printStackTrace();
				}
			//TODO
			
				}
	
/** pour démarrer le producteur */
	
	public void demarrer(){
		this.EnMarche=true;
		Random rdProducteur = new Random();
		this.debitProduction = rdProducteur.nextDouble() * this.MaxDebitProduction; // Attention !! à modifier ..
		
	}
	
/** pour arrêter le producteur */
	public void arreter(){
		this.EnMarche=false;
		this.debitProduction = 0; // parfait !
		
	}
// getters :	
	
/** @return retourne le	debit de la production du Producteur */
	public double getDebitProduction(){
		return this.debitProduction;
	}
	/** @return retourne le	debit maximum de la production du Producteur */
	public double getMaxDebitMProduction(){
		return this.MaxDebitProduction;
	}
	
	/**  Ajuste le debit de la production du Producteur
	 *  @param nouveauDebit : le nouveau débit de la production
	 *  */
	public void ajusterMProduction(double nouveauDebit ){
		this.debitProduction = nouveauDebit;
	}

public double getMaxDebitProduction() {
	return MaxDebitProduction;
}

public void setMaxDebitProduction(double maxDebitProduction) {
	MaxDebitProduction = maxDebitProduction;
}

public boolean isEnMarche() {
	return EnMarche;
}

public void setEnMarche(boolean enMarche) {
	EnMarche = enMarche;
}

public void setDebitProduction(double debitProduction) {
	this.debitProduction = debitProduction;
}

/** informer le Statisticien */
public void informerStatisticien(String message){
			ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
			reponse.setContent(message);
			reponse.setOntology(Preferences.PRODUCTION_PRODUCTEUR);
			reponse.addReceiver(Preferences.getAgentStatisticien());
			this.send(reponse); 
	   }

}
