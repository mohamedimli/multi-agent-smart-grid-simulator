package appareils;

/**
 * Classe : Four
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class Four extends AppareilElectrique {

	public Four() {
		// Appel du constructeur de la classe mère !
		super();
		
		// Rensigner la puissance éléctrique :
		
		this.consommationElectrique = 2300; // Watt/heure à vérifier !
		
		// Rensigner la durée Maximale/Minimale d'utilisation :
		
		this.MaxdureeMarche = (3*60*60); // 3 heures par exemple !
		this.MindureeMarche = (30); // 30 secondes par exemple !
		
		// Rensigner la durée Maximale/Minimale d'utilisation :
		
		this.MaxdureeArret = (30); // 30 secondes par exemple !
		this.MindureeArret = (20*60*60); // 3 heures par exemple !
		
	}

}
