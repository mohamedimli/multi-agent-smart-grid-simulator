package appareils;

/**
 * Classe : RadioReveil
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class RadioReveil extends AppareilElectrique {

	public RadioReveil() {
		super();
		
		
		this.consommationElectrique = 10;
		
		
		this.MaxdureeMarche = (23*60*60); 
		this.MindureeMarche = (30);
		
		this.MaxdureeArret = (30); 
		this.MindureeArret = (1*60*60); 
	}

}
