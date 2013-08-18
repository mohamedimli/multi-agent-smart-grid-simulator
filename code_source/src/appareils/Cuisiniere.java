package appareils;


/**
 * Classe : Cuisiere
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */
public class Cuisiniere extends AppareilElectrique {

	public Cuisiniere() {
		super();
		
		
		this.consommationElectrique = 8000; 
		
		
		this.MaxdureeMarche = (3*60*60); 
		this.MindureeMarche = (30);
		
		
		this.MaxdureeArret = (30); 
		this.MindureeArret = (15*60*60); 
		
	}

}
