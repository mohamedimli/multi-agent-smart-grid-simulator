package appareils;


/**
 * Classe : Aspirateur
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class Aspirateur extends AppareilElectrique {

	public Aspirateur(){
		super();
		
		
		this.consommationElectrique = 1874; // débit de la consommation éléctrique
		
		
		this.MaxdureeMarche = (1*60*60); // Durée maximale pendant laquelle l'appreil peut être en marche
		this.MindureeMarche = (30); // durée minimale pendant laquelle l'appareil peut être en marche
		
		
		this.MaxdureeArret = (30); //  Durée maximale pendant laquelle l'appreil peut être en arrêt
		this.MindureeArret = (20*60*60); // durée minimale pendant laquelle l'appareil peut être en marche
		
		
	}
}
