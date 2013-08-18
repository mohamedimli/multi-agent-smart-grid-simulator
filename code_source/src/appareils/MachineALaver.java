package appareils;

/**
 * Classe : MachineALaver
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class MachineALaver extends AppareilElectrique {

	public MachineALaver() {
		
		super();
		
		
		this.consommationElectrique = 1350; 
		
		this.MaxdureeMarche = (2*60*60);
		this.MindureeMarche = (30); 
		
		
		this.MaxdureeArret = (30); 
		this.MindureeArret = (20*60*60);
		
	}

}
