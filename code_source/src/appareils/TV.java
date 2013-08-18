package appareils;


/**
 * Classe : TV
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class TV extends AppareilElectrique {

	public TV() {
		super();
		
		
		this.consommationElectrique = 300; 
		
		
		this.MaxdureeMarche = (4*60*60); 
		this.MindureeMarche = (30);
		
		
		this.MaxdureeArret = (10*60*60);
		this.MindureeArret = (30); 
	}
	
	

}
  