package appareils;


/**
 * Classe : Ampoule
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class Ampoule extends AppareilElectrique {

	public Ampoule() {
		super();
		this.consommationElectrique = 40; // débit de la consommation éléctrique 
		
		this.MaxdureeMarche = (5*60*60); // Durée maximale pendant laquelle l'appreil peut être en marche
		this.MindureeMarche = (30);  // durée minimale pendant laquelle l'appareil peut être en marche
		
		this.MaxdureeArret = (18*60*60); //  Durée maximale pendant laquelle l'appreil peut être en arrêt
		this.MindureeArret = (30); // Durée minimale pendant laquelle l'appreil peut être en arrêt
	}

}
