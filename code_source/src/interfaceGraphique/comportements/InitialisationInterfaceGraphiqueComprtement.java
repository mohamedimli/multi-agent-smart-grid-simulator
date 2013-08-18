package interfaceGraphique.comportements;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import utilitaires.FIFO;
import interfaceGraphique.Principale;
import interfaceGraphique.agents.AgentInterfaceGraphique;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

/**
 * Classe : InitialisationInterfaceGraphiqueComprtement
 * Description : Comportement d'initialisation de l'agent interface graphique
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class InitialisationInterfaceGraphiqueComprtement extends OneShotBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8493753790482136932L;
	
	AgentInterfaceGraphique monAgent;
	

	public InitialisationInterfaceGraphiqueComprtement(AgentInterfaceGraphique interfaceGraphique) {
		super(interfaceGraphique);
		this.monAgent=interfaceGraphique;
	}


	@Override
	public void action() {
		this.monAgent.setmaPrincipale(new Principale(this.monAgent)); // cr�er la fen�tre principale
		this.monAgent.getmaPrincipale().setVisible(true);
		

		try {
			// Cr�ation de description l'agent [InterfaceGraphique]
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(this.monAgent.getAID());
			// Enregistrement de la description de l'agent dans DF (Directory Facilitator)
			DFService.register(this.monAgent, dfd);
			System.out.println(this.monAgent.getLocalName()+" REGISTERED WITH THE DF");
			} catch (FIPAException e) {
			e.printStackTrace();
			}
		
		// Initialisation graphes :
		
		//----  d�bit de production du producteur :
		
				this.monAgent.setDonneesDebitProducteur(new DefaultXYDataset());
			
				this.monAgent.setFileDebitProducteur(new FIFO(10));
				//this.monAgent.getDonneesDebitProducteur().addSeries("Production", this.monAgent.getFileDebitProducteur().retournerTableau());

				JFreeChart grapheProducteur = ChartFactory.createXYLineChart("Production de l'Agent Producteur","Temps","Production en Watt/Heure",this.monAgent.getDonneesDebitProducteur(), PlotOrientation.VERTICAL, true,true,false);
				ChartPanel grapheProducteurPane=new ChartPanel(grapheProducteur);
				this.monAgent.getmaPrincipale().getGraphePane().add(grapheProducteurPane);
	   
		//----  D�bit total de production des producteurs locaux :
				
				this.monAgent.setDonneesDebitProducteurLocal(new DefaultXYDataset());
			
				this.monAgent.setFileDebitProducteurLocal(new FIFO(10));
				//this.monAgent.getDonneesDebitProducteurLocal().addSeries("Production locale", this.monAgent.getFileDebitProducteurLocal().retournerTableau());
				JFreeChart grapheProducteurLocal = ChartFactory.createXYLineChart("D�bit de production des producteurs locaux","Temps","Production en Watt/Heure",this.monAgent.getDonneesDebitProducteurLocal(), PlotOrientation.VERTICAL, true,true,false);
				ChartPanel grapheProducteurLocalPane=new ChartPanel(grapheProducteurLocal);
				this.monAgent.getmaPrincipale().getGraphePane().add(grapheProducteurLocalPane);
				
	   //----  D�bit total de la consommation des Foyers :
				
				this.monAgent.setDonneesDebitFoyer(new DefaultXYDataset());
			
				this.monAgent.setFileDebitFoyer(new FIFO(10));
				//this.monAgent.getDonneesDebitFoyer().addSeries("Consommation", this.monAgent.getFileDebitFoyer().retournerTableau());
				JFreeChart grapheFoyer = ChartFactory.createXYLineChart("D�bit de la consommation des Foyers","Temps","Consommation en Watt/Heure",this.monAgent.getDonneesDebitFoyer(), PlotOrientation.VERTICAL, true,true,false);
				ChartPanel grapheFoyerPane=new ChartPanel(grapheFoyer);
				this.monAgent.getmaPrincipale().getGraphePane().add(grapheFoyerPane);
				
	   //---- D�bit total consommation/production/production locale :
				
				this.monAgent.setDonneesDebitsTotaux(new DefaultXYDataset());
				
				this.monAgent.setFileDebitsTotaux(new FIFO(10));
				//this.monAgent.getDonneesDebitsTotaux().addSeries("Production", this.monAgent.getFileDebitProducteur().retournerTableau());
				//this.monAgent.getDonneesDebitsTotaux().addSeries("Production locale", this.monAgent.getFileDebitProducteurLocal().retournerTableau());
				// this.monAgent.getDonneesDebitsTotaux().addSeries("Consommation", this.monAgent.getFileDebitFoyer().retournerTableau());
				JFreeChart grapheDebitsTotaux = ChartFactory.createXYLineChart("D�bit Production/Consommation/Production Locale","Temps","Consommation en Watt/Heure",this.monAgent.getDonneesDebitsTotaux(), PlotOrientation.VERTICAL, true,true,false);
				ChartPanel grapheDebitsTotauxPane=new ChartPanel(grapheDebitsTotaux);
				this.monAgent.getmaPrincipale().getGraphePane().add(grapheDebitsTotauxPane);
				
				
				
		// redimentionner la f�netre principale :
				
				this.monAgent.getMaPrincipale().pack();
	   
					
	}

}
