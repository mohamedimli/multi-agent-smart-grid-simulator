package appareils;

import java.util.Date;
import java.util.Random;


/**
 * Classe : AppareilElectrique, une classe abstraite qui sera implement�e par les appareils �l�ctriques
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public  class AppareilElectrique {


	protected double consommationElectrique; 	// puissance electrique de l'appareil en Watt par heure !
	protected Date heureDemarrage; 				// heure de d�marrage de l'appareil �l�ctrique
	protected Date heureArret; 					// heure de d�marrage de l'appareil �l�ctrique
	protected long MindureeMarche; 			// Dur�e pendant laquelle l'appareil est en marche
	protected long MaxdureeMarche;			 // Dur�e pendant laquelle l'appareil est en marche
	protected long MindureeArret;   		   	// Dur�e pendant laquelle l'appreil est en arr�t
	protected long MaxdureeArret;				 // Dur�e pendant laquelle l'appreil est en arr�t
	protected boolean isDemarre;				 // Pour savoir si l'appareil est d�marr�e
	
	
	/** Constructeur par d�fault */ 
	
	public AppareilElectrique(){
		this.MaxdureeArret=60*60*5; // 5 heures pour tester
		this.MindureeArret=1; // une seconde pour tester 
		
		this.MaxdureeMarche=60*60*10; // 10 heures pour test 
		this.MindureeMarche=1; // une seconde pour tester 
	}

	// getters 
	
	/** @return la consommation electrique de l'appareil */
	public  double getConsommationElectrique(){
		return this.consommationElectrique;
	}

	/** @return le d�bit de consommation de l'appareil */
	public  double getDebitConsommation(){
		return (this.consommationElectrique/ (60*60)); // Attention d�bit par s�conde !
	}

	/** @return : heure de d�marrage de l'appareil �l�ctrique. */
	public Date getHeureDemarrage(){
		return this.heureDemarrage;
	}
	
	/** @return la dur�e du marche minimale.  */
	public double getMindureeMarche(){
		return this.MindureeMarche;
	}
	
	/** @return la dur�e du marche maximale. */
	public double getMaxdureeMarche(){
		return this.MaxdureeMarche;
	}
	
	/** @return Calcule de la dur�e de marche. */
	public double calculerDureeMarche(){
		Random rn = new Random();
		return (this.MindureeMarche+(rn.nextDouble()*(this.MaxdureeMarche- this.MindureeMarche)));
	}
	
	/** @return Calcule de la dur�e d'arr�t. */
	public double calculerDureeArret(){
		Random rn = new Random();
		return (this.MindureeArret+(rn.nextDouble()*(this.MaxdureeArret- this.MindureeArret)));
	}
	
	/** D�marrer l'appareil �l�ctrique */
	public  void demarrer(){
		if (this.demarrable()){
			this.isDemarre=true;
		}
	}
	
	/** arreter l'appareil �l�ctrique	*/
	public  void arreter(){
		if (this.arretable()){
			this.isDemarre=false;
		}
	}
	/** @return  vrai si nous pouvons d�marrer l'appareil */
	public  boolean demarrable(){
		return ((System.currentTimeMillis()-this.heureArret.getTime())> this.calculerDureeArret() );
	}
	/** @return  vrai si on   peut arr�ter l'appareil */
	public boolean arretable(){
		return ((System.currentTimeMillis()-this.heureDemarrage.getTime())> this.calculerDureeMarche() );
	}
	/** D�marrer ou arreter appareil suivant son comportement/�tat  */
	public  void demarrerArreterAppareil(){
		Random rnDemarrageArret = new Random();
		if (rnDemarrageArret.nextInt()>=0.5){
			this.demarrer();
		}else{
			this.arreter();
		}

	}
}
