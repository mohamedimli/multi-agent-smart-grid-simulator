package appareils;

/**
 * Classe : FerARepasser
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */
public class FerARepasser extends AppareilElectrique {

	public FerARepasser() {

		super();


		this.consommationElectrique = 1960;

		this.MaxdureeMarche = (2*60*60); 
		this.MindureeMarche = (30); 


		this.MaxdureeArret = (30); 
		this.MindureeArret = (20*60*60);

	}

}
