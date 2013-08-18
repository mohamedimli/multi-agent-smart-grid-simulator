
package agents;

import java.util.GregorianCalendar;
import java.util.Random;

import utilitaires.Preferences;
import comportements.producteurlocal.EnvoiProductionComportement;
import comportements.producteurlocal.FinPLComportement;
import comportements.producteurlocal.InitialisationPLComportement;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

/**
 * Classe : Agent Producteur Local
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 * 
 */

public class AgentProducteurLocal extends Agent {
	
	private static final long serialVersionUID = 4934869975625400921L;
	

	// Membres :
	private AID monFoyer;
	private AID monCompteurIntelligent;
	private  double maxDebitProduction; 

	/** Comportement générale du Producteur Local */
	protected void setup() {
		FSMBehaviour behaviourProducteurLocal = new FSMBehaviour(this);
		
		//Etats
	behaviourProducteurLocal.registerFirstState(new InitialisationPLComportement(this),"initialisationPL");
	behaviourProducteurLocal.registerState(new EnvoiProductionComportement(this,1000),"envoiProductionPL");
	behaviourProducteurLocal.registerLastState(new FinPLComportement(this),"finProducteurLocal");
		
		//Transitions
	behaviourProducteurLocal.registerDefaultTransition("initialisationPL","envoiProductionPL");
	behaviourProducteurLocal.registerTransition("envoiProductionPL","envoiProductionPL",1);
	behaviourProducteurLocal.registerTransition("envoiProductionPL","finProducteurLocal",0);
		
		addBehaviour(behaviourProducteurLocal);



	}
	
	protected void takeDown() {
		
		try {
			DFService.deregister(this);
			}
			catch (FIPAException fe) {
			fe.printStackTrace();
			}
	
		
			}


	/** Cette fonction simule le comportement de la production d'un panneau solaire 
	 *  @return : débit de la production du panneau solaire par seconde
	 * */
	public double produireEnergie(){

		// Donnees de réfèrence pour calculer la production d'un panneau solaire :

		double[][] TableauDonnees = new double[25][2];
		// heure : 00h00
		TableauDonnees[0][0]=0.0; // min production
		TableauDonnees[0][1]=0.0; // max production
		// heure : 01h00
		TableauDonnees[1][0]=0.0;
		TableauDonnees[1][1]=0.0;
		// heure : 02h00
		TableauDonnees[2][0]=0.0;
		TableauDonnees[2][1]=0.0;
		// heure : 03h00
		TableauDonnees[3][0]=0.0;
		TableauDonnees[3][1]=0.0;
		// heure : 04h00
		TableauDonnees[4][0]=0.0;
		TableauDonnees[4][1]=0.0;
		// heure : 05h00
		TableauDonnees[5][0]=0.0;
		TableauDonnees[5][1]=0.0;
		// heure : 06h00
		TableauDonnees[6][0]=0.0; 
		TableauDonnees[6][1]=0.0; 
		// heure : 07h00
		TableauDonnees[7][0]=0.0;
		TableauDonnees[7][1]=0.0;
		// heure : 08h00
		TableauDonnees[8][0]=0.10;
		TableauDonnees[8][1]=0.20;
		// heure : 09h00
		TableauDonnees[9][0]=0.20;
		TableauDonnees[9][1]=0.25;
		// heure : 10h00
		TableauDonnees[10][0]=0.25;
		TableauDonnees[10][1]=0.45;
		// heure : 11h00
		TableauDonnees[11][0]=0.45;
		TableauDonnees[11][1]=0.50;
		// heure : 12h00
		TableauDonnees[12][0]=0.50; 
		TableauDonnees[12][1]=0.80;
		// heure : 13h00
		TableauDonnees[13][0]=0.80;
		TableauDonnees[13][1]=0.85;
		// heure : 14h00
		TableauDonnees[14][0]=0.85;
		TableauDonnees[14][1]=0.90;
		// heure : 15h00
		TableauDonnees[15][0]=0.90;
		TableauDonnees[15][1]=0.95;
		// heure : 16h00
		TableauDonnees[16][0]=0.95;
		TableauDonnees[16][1]=0.80;
		// heure : 17h00
		TableauDonnees[17][0]=0.80;
		TableauDonnees[17][1]=0.40;
		// heure : 18h00
		TableauDonnees[18][0]=0.40;
		TableauDonnees[18][1]=0.20; 
		// heure : 19h00
		TableauDonnees[19][0]=0.20;
		TableauDonnees[19][1]=0.10;
		// heure : 20h00
		TableauDonnees[20][0]=0.10;
		TableauDonnees[20][1]=0.05;
		// heure : 21h00
		TableauDonnees[21][0]=0.05;
		TableauDonnees[21][1]=0;
		// heure : 22h00
		TableauDonnees[22][0]=0;
		TableauDonnees[22][1]=0;
		// heure : 23h00
		TableauDonnees[23][0]=0.40;//0;
		TableauDonnees[23][1]=0.55;//0;
		// heure : 24h00 == 00h00 ==> utile pour calculer TableauDonnees[heure+1] facilement ! 
		TableauDonnees[24][0]=0;
		TableauDonnees[24][1]=0;

		// Récupération de l'heure actuelle du systeme

		java.util.GregorianCalendar calendrier = new GregorianCalendar();
		int heure = calendrier.get(java.util.Calendar.HOUR_OF_DAY);
		@SuppressWarnings("unused")
		int minutes = calendrier.get(java.util.Calendar.MINUTE);

		// Augmentation ou diminution production entre 2 créneaux horaires 

		boolean isAugmentation = (((TableauDonnees[heure+1][0]+TableauDonnees[heure+1][1])/2)- ((TableauDonnees[heure][0]+TableauDonnees[heure][1])/2)>0);
		// Calcul des nouvelles bornes MAX et MIN	                          
		double longueurIntervalle = (TableauDonnees[heure][1]-TableauDonnees[heure][0])*this.maxDebitProduction;
		double nouveauMin=0.0;
		double nouveauMax=0.0;
		int nombreIntervalles = 0;
		if (isAugmentation){
			nombreIntervalles=minutes;	
			nouveauMin= (this.maxDebitProduction*TableauDonnees[heure][0])+((longueurIntervalle/60) * nombreIntervalles);
			nouveauMax= (this.maxDebitProduction*TableauDonnees[heure][0])+((longueurIntervalle/60) * (nombreIntervalles+1));
		}else{
			nouveauMin= (this.maxDebitProduction*TableauDonnees[heure][1])-((longueurIntervalle/60) * (nombreIntervalles+1));
			nouveauMax= (this.maxDebitProduction*TableauDonnees[heure][1])-((longueurIntervalle/60) * (nombreIntervalles));
		}


		// Calcul de la nouvelle production :

		Random rn = new Random();
		double diffMinMax = nouveauMax - nouveauMin;
		double production = nouveauMin + (rn.nextDouble() * diffMinMax );
		return production;
	}

	public void setMaxDebitProduction(double maxDebitProduction) {
		this.maxDebitProduction = maxDebitProduction;
	}

	/** Communiquer le débit de la production au compteur intelligent */
	public void informerCompteurIntelligent(){
		// Envoie du message au compteur intelligent :
		ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
		reponse.setContent(produireEnergie()+"");
		reponse.setOntology(Preferences.PRODUCTION_LOCAL);
		reponse.addReceiver(this.monCompteurIntelligent);
		this.send(reponse); // myAgent est remplacé par this !
	}
	

	// Setters and Getters :

	public AID getMonFoyer() {
		return monFoyer;
	}

	public void setMonFoyer(AID monFoyer) {
		this.monFoyer = monFoyer;
	}

	public AID getMonCompteurIntelligent() {
		return monCompteurIntelligent;
	}

	public void setMonCompteurIntelligent(AID monCompteurIntelligent) {
		this.monCompteurIntelligent = monCompteurIntelligent;
	}

	public double getMaxDebitProduction() {
		return maxDebitProduction;
	}

}
