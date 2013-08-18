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
		
		
		this.consommationElectrique = 1139;  // débit de la consommation éléctrique
		
		
		this.MaxdureeMarche = (1*60*60); // Durée maximale pendant laquelle l'appreil peut être en marche
		this.MindureeMarche = (30); // durée minimale pendant laquelle l'appareil peut être en marche
		
		
		this.MaxdureeArret = (30); //  Durée maximale pendant laquelle l'appreil peut être en arrêt
		this.MindureeArret = (20*60*60); // Durée minimale pendant laquelle l'appreil peut être en arrêt
		
	}

}
