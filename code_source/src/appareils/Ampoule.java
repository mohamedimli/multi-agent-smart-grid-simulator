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
		this.consommationElectrique = 40; // d�bit de la consommation �l�ctrique 
		
		this.MaxdureeMarche = (5*60*60); // Dur�e maximale pendant laquelle l'appreil peut �tre en marche
		this.MindureeMarche = (30);  // dur�e minimale pendant laquelle l'appareil peut �tre en marche
		
		this.MaxdureeArret = (18*60*60); //  Dur�e maximale pendant laquelle l'appreil peut �tre en arr�t
		this.MindureeArret = (30); // Dur�e minimale pendant laquelle l'appreil peut �tre en arr�t
	}

}
