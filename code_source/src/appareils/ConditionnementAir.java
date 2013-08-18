package appareils;

/**
 * Classe : ConditionnementAir
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class ConditionnementAir extends AppareilElectrique {

	public ConditionnementAir() {
		// Appel du constructeur de la classe m�re !
		super();
		
		// Rensigner la puissance �l�ctrique :
		
		this.consommationElectrique = 2600; // Watt/heure � v�rifier !
		
		// Rensigner la dur�e Maximale/Minimale d'utilisation :
		
		this.MaxdureeMarche = (10*60*60); // 3 heures par exemple !
		this.MindureeMarche = (30); // 30 secondes par exemple !
		
		// Rensigner la dur�e Maximale/Minimale d'utilisation :
		
		this.MaxdureeArret = (30); // 30 secondes par exemple !
		this.MindureeArret = (10*60*60); // 3 heures par exemple !
		
	}

}
