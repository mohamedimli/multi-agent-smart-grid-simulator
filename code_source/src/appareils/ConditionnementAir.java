package appareils;

/**
 * Classe : ConditionnementAir
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class ConditionnementAir extends AppareilElectrique {

	public ConditionnementAir() {
		// Appel du constructeur de la classe mère !
		super();
		
		// Rensigner la puissance éléctrique :
		
		this.consommationElectrique = 2600; // Watt/heure à vérifier !
		
		// Rensigner la durée Maximale/Minimale d'utilisation :
		
		this.MaxdureeMarche = (10*60*60); // 3 heures par exemple !
		this.MindureeMarche = (30); // 30 secondes par exemple !
		
		// Rensigner la durée Maximale/Minimale d'utilisation :
		
		this.MaxdureeArret = (30); // 30 secondes par exemple !
		this.MindureeArret = (10*60*60); // 3 heures par exemple !
		
	}

}
