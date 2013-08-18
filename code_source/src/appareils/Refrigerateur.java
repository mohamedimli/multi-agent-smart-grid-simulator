package appareils;

/**
 * Classe : Refrigerateur
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */
public class Refrigerateur extends AppareilElectrique {

	public Refrigerateur() {
		super();
		this.consommationElectrique = 300;

		this.MaxdureeMarche = (3*60*60); 
		this.MindureeMarche = (30);


		this.MaxdureeArret = (3*60*60); 
		this.MindureeArret = (30); 


	}

}
