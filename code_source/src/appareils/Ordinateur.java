package appareils;

/**
 * Classe : Ordinateur
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class Ordinateur extends AppareilElectrique {

	public Ordinateur() {
		
		super();
		
		
		this.consommationElectrique = 120;
		
		this.MaxdureeMarche = (8*60*60);
		this.MindureeMarche = (30); 
		
		
		this.MaxdureeArret = (30); 
		this.MindureeArret = (10*60*60); 
		
	}

}
