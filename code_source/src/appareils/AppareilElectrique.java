package appareils;

import java.util.Date;
import java.util.Random;


/**
 * Classe : AppareilElectrique, une classe abstraite qui sera implementée par les appareils éléctriques
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public  class AppareilElectrique {


	protected double consommationElectrique; 	// puissance electrique de l'appareil en Watt par heure !
	protected Date heureDemarrage; 				// heure de démarrage de l'appareil éléctrique
	protected Date heureArret; 					// heure de démarrage de l'appareil éléctrique
	protected long MindureeMarche; 			// Durée pendant laquelle l'appareil est en marche
	protected long MaxdureeMarche;			 // Durée pendant laquelle l'appareil est en marche
	protected long MindureeArret;   		   	// Durée pendant laquelle l'appreil est en arrêt
	protected long MaxdureeArret;				 // Durée pendant laquelle l'appreil est en arrêt
	protected boolean isDemarre;				 // Pour savoir si l'appareil est démarrée
	
	
	/** Constructeur par défault */ 
	
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

	/** @return le débit de consommation de l'appareil */
	public  double getDebitConsommation(){
		return (this.consommationElectrique/ (60*60)); // Attention débit par séconde !
	}

	/** @return : heure de démarrage de l'appareil éléctrique. */
	public Date getHeureDemarrage(){
		return this.heureDemarrage;
	}
	
	/** @return la durée du marche minimale.  */
	public double getMindureeMarche(){
		return this.MindureeMarche;
	}
	
	/** @return la durée du marche maximale. */
	public double getMaxdureeMarche(){
		return this.MaxdureeMarche;
	}
	
	/** @return Calcule de la durée de marche. */
	public double calculerDureeMarche(){
		Random rn = new Random();
		return (this.MindureeMarche+(rn.nextDouble()*(this.MaxdureeMarche- this.MindureeMarche)));
	}
	
	/** @return Calcule de la durée d'arrêt. */
	public double calculerDureeArret(){
		Random rn = new Random();
		return (this.MindureeArret+(rn.nextDouble()*(this.MaxdureeArret- this.MindureeArret)));
	}
	
	/** Démarrer l'appareil éléctrique */
	public  void demarrer(){
		if (this.demarrable()){
			this.isDemarre=true;
		}
	}
	
	/** arreter l'appareil éléctrique	*/
	public  void arreter(){
		if (this.arretable()){
			this.isDemarre=false;
		}
	}
	/** @return  vrai si nous pouvons démarrer l'appareil */
	public  boolean demarrable(){
		return ((System.currentTimeMillis()-this.heureArret.getTime())> this.calculerDureeArret() );
	}
	/** @return  vrai si on   peut arrêter l'appareil */
	public boolean arretable(){
		return ((System.currentTimeMillis()-this.heureDemarrage.getTime())> this.calculerDureeMarche() );
	}
	/** Démarrer ou arreter appareil suivant son comportement/état  */
	public  void demarrerArreterAppareil(){
		Random rnDemarrageArret = new Random();
		if (rnDemarrageArret.nextInt()>=0.5){
			this.demarrer();
		}else{
			this.arreter();
		}

	}
}
