package appareils;


/**
 * Classe : ChauffageElectrique
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */
public class ChauffageElectrique extends AppareilElectrique {

	public ChauffageElectrique() {
		super();


		this.consommationElectrique = 1000; 


		this.MaxdureeMarche = (20*60*60);
		this.MindureeMarche = (30); 


		this.MaxdureeArret = (30); 
		this.MindureeArret = (3*60*60);

	}

}
