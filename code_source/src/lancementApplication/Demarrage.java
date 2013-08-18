package lancementApplication;

import jade.Boot;


/**
 * Classe : Demarrage
 * Description : Classe principale pour démarrer l'application
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class Demarrage {

	
	public static void main(String[] args) {
		
		String[] param = new String[ 2 ];
		param[ 0 ] = "-gui";
		param[ 1 ] = "statisticien:statistiques.agents.AgentStatisticien()";


		Boot.main( param ); 

	}
	
	

}
