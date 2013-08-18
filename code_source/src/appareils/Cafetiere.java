package appareils;

/**
 * Classe : Cafetiere
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class Cafetiere extends AppareilElectrique {

	public Cafetiere() {
		super();
		
		
		this.consommationElectrique = 1139;  // d�bit de la consommation �l�ctrique
		
		
		this.MaxdureeMarche = (1*60*60); // Dur�e maximale pendant laquelle l'appreil peut �tre en marche
		this.MindureeMarche = (30); // dur�e minimale pendant laquelle l'appareil peut �tre en marche
		
		
		this.MaxdureeArret = (30); //  Dur�e maximale pendant laquelle l'appreil peut �tre en arr�t
		this.MindureeArret = (20*60*60); // Dur�e minimale pendant laquelle l'appreil peut �tre en arr�t
		
	}

}
