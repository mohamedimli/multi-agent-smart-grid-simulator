package appareils;


/**
 * Classe : MicroOnde
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */
public class MicoOnde extends AppareilElectrique {

	public MicoOnde() {
		super();
		
		
		this.consommationElectrique = 1350; 
		
		
		this.MaxdureeMarche = (3*60*60); 
		this.MindureeMarche = (30);
		
		
		this.MaxdureeArret = (30); 
		this.MindureeArret = (15*60*60); 
		
	}

}
