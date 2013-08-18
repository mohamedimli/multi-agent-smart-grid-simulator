package interfaceGraphique.agents;

import interfaceGraphique.Principale;
import interfaceGraphique.comportements.FinInterfaceGraphiqueComprtement;
import interfaceGraphique.comportements.InitialisationInterfaceGraphiqueComprtement;
import interfaceGraphique.comportements.TraitementInterfaceGraphiqueComportement;

import org.jfree.data.xy.DefaultXYDataset;

import jade.core.behaviours.FSMBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import utilitaires.FIFO;
import utilitaires.Preferences;

/**
 * Classe : AgentInterfaceGraphique
 * Description : Agent Interface Graphique, il crée et mets à jour l'interface graphique
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class AgentInterfaceGraphique extends GuiAgent {

	
	private static final long serialVersionUID = 5291978990355056613L;
	// 
	transient protected Principale maPrincipale;  // La fênetre principale
	
	// Producteur
	private FIFO fileDebitProducteur;
	private DefaultXYDataset donneesDebitProducteur;
	// Producteur local
	private FIFO fileDebitProducteurLocal;
	private DefaultXYDataset donneesDebitProducteurLocal;
	// Foyer :
	private FIFO fileDebitFoyer;
	private DefaultXYDataset donneesDebitFoyer;
	// Les trois ..
	private FIFO fileDebitsTotaux;
	private DefaultXYDataset donneesDebitsTotaux;
	
	
	
	
	
		
	
	public Principale getMaPrincipale() {
		return maPrincipale;
	}



	public void setMaPrincipale(Principale maPrincipale) {
		this.maPrincipale = maPrincipale;
	}

	
	


	











	public DefaultXYDataset getDataset3() {
		return dataset3;
	}


	public void setDataset3(DefaultXYDataset dataset3) {
		this.dataset3 = dataset3;
	}

	private DefaultXYDataset dataset3;





	


	public Principale getmaPrincipale() {
		return maPrincipale;
	}


	public void setmaPrincipale(Principale principale) {
		this.maPrincipale = principale;
	}


	public void setup(){
		
		 // Comportement de Interface Graphique :
		 FSMBehaviour behaviourInterfaceGraphique = new FSMBehaviour(this);
			
			//Etats
		 behaviourInterfaceGraphique.registerFirstState(new InitialisationInterfaceGraphiqueComprtement(this),"initialisationInterfaceGraphique");
		 behaviourInterfaceGraphique.registerState(new TraitementInterfaceGraphiqueComportement(this),"traitementInterfaceGraphique");
		 behaviourInterfaceGraphique.registerLastState(new FinInterfaceGraphiqueComprtement(this),"finInterfaceGraphique");
			
			//Transitions
		 behaviourInterfaceGraphique.registerDefaultTransition("initialisationInterfaceGraphique","traitementInterfaceGraphique");
		 behaviourInterfaceGraphique.registerTransition("traitementInterfaceGraphique","traitementInterfaceGraphique",1);
		 behaviourInterfaceGraphique.registerTransition("traitementInterfaceGraphique","finInterfaceGraphique",0);
			
			addBehaviour(behaviourInterfaceGraphique);


		

		

		//---------------- graphe 1 ------------------------
		// this.dataset1 = new DefaultXYDataset();
		// this.dataset1.addSeries("serie 1", new double[][]{{1,2,3,4,5},{1,4, 3,6,5}});
		// JFreeChart chart1 = ChartFactory.createXYLineChart("Production Producteur","x","y",this.dataset1, PlotOrientation.VERTICAL, false,false,false);
		// ChartPanel chartPane1=new ChartPanel(chart1);
		//---------------- graphe 2 ------------------------
		//this.dataset2 = new DefaultXYDataset();
		//this.dataset2.addSeries("serie 2", new double[][]{{1,2,3,4,5},{1,4, 3,6,5}});
		//JFreeChart chart2 = ChartFactory.createXYLineChart("Consommation totale","x","y",this.dataset1, PlotOrientation.VERTICAL, false,false,false);
		//ChartPanel chartPane2=new ChartPanel(chart2);

		//---------------- graphe 3 ------------------------
		/*this.dataset3 = new DefaultXYDataset();
		        this.dataset3.addSeries("serie 3", new double[][]{{1,2,3,4,5},{1,4, 3,6,5}});
		        JFreeChart chart3 = ChartFactory.createXYLineChart("Production Locale","x","y",this.dataset1, PlotOrientation.VERTICAL, false,false,false);
				ChartPanel chartPane3=new ChartPanel(chart3);*/
		//---------------- graphe 4 ------------------------
		/*this.dataset4 = new DefaultXYDataset();
		        this.dataset4.addSeries("serie 3", new double[][]{{1,2,3,4,5},{1,4, 3,6,5}});
		        JFreeChart chart4 = ChartFactory.createXYLineChart("Production Locale","x","y",this.dataset1, PlotOrientation.VERTICAL, false,false,false);
				ChartPanel chartPane4=new ChartPanel(chart4);*/

		//--------------------------------------

		// this.maPrincipale.getGraphePane().add(chartPane1);
		//this.maPrincipale.getGraphePane().add(chartPane2);
		//this.maPrincipale.getGraphePane().add(chartPane3);
		//this.maPrincipale.getGraphePane().add(chartPane4);


		// ---------- TICKER BEHAVIOUR ------------
		/*TickerBehaviour behaviour = new TickerBehaviour(this, 1000){

					@Override
					protected void onTick() {
						// TODO Auto-generated method stub
						System.out.println("passage ..");
						Random rd1 = new Random();
						Random rd2 = new Random();
						Random rd3 = new Random();
						Random rd4 = new Random();
						 ((AgentInterfaceGraphique) myAgent).getDataset1().addSeries("serie 1", new double[][]{{1,2,3,4,5},{rd1.nextDouble()*1000,rd1.nextDouble()*1000,rd1.nextDouble()*1000,rd1.nextDouble()*1000,rd1.nextDouble()*1000}});
						 ((AgentInterfaceGraphique) myAgent).setDataset1(dataset1);

						 DefaultXYDataset dataset2 = new DefaultXYDataset();
						 ((AgentInterfaceGraphique) myAgent).getDataset2().addSeries("serie 2", new double[][]{{1,2,3,4,5},{rd2.nextDouble()*1000,rd2.nextDouble()*1000,rd2.nextDouble()*1000,rd2.nextDouble()*1000,rd2.nextDouble()*1000}});
						 ((AgentInterfaceGraphique) myAgent).setDataset2(dataset2);

						 DefaultXYDataset dataset3 = new DefaultXYDataset();
						 ((AgentInterfaceGraphique) myAgent).getDataset3().addSeries("serie 3", new double[][]{{1,2,3,4,5},{rd3.nextDouble()*1000,rd3.nextDouble()*1000,rd3.nextDouble()*1000,rd3.nextDouble()*1000,rd3.nextDouble()*1000}});
						 ((AgentInterfaceGraphique) myAgent).setDataset3(dataset3);
						 //DefaultXYDataset dataset4 = new DefaultXYDataset();
						 //((AgentInterfaceGraphique) myAgent).getDataset4().addSeries("serie 4", new double[][]{{1,2,3,4,5},{rd4.nextDouble()*1000,rd4.nextDouble()*1000,rd4.nextDouble()*1000,rd4.nextDouble()*1000,rd4.nextDouble()*1000}});
						 //((AgentInterfaceGraphique) myAgent).setDataset4(dataset3);
					}

				};

				this.addBehaviour(behaviour);*/







	}

	public void takeDown(){
		this.maPrincipale.setVisible(false);
	}



	@Override
	protected void onGuiEvent(GuiEvent evenement) {
		switch(evenement.getType())
		{
		case Preferences.LANCER_SIMULATION_EVENEMENT: 
			demanderLancerSimulation(); // Demander à l'agent statisticien de lancer la simulation
			break;

		}
	}




	@Override
	public void postGuiEvent(GuiEvent e) {
		// TODO Auto-generated method stub
		super.postGuiEvent(e);
	}

	/** Demander à l'agent Statisticien de lancer la simulation */
	
	
	public void demanderLancerSimulation(){

		// Envoie du message à l'agent Statisticien :
		ACLMessage reponse = new ACLMessage(ACLMessage.REQUEST);
		reponse.setContent(Preferences.DEMANDE_LANCEMENT_SIMULATION);
		reponse.setOntology(Preferences.DEMANDE_LANCEMENT_SIMULATION);
		reponse.addReceiver(Preferences.getAgentStatisticien());
		this.send(reponse); 
	}


	public FIFO getFileDebitProducteur() {
		return fileDebitProducteur;
	}


	public void setFileDebitProducteur(FIFO fileDebitProducteur) {
		this.fileDebitProducteur = fileDebitProducteur;
	}


	public DefaultXYDataset getDonneesDebitProducteur() {
		return donneesDebitProducteur;
	}


	public void setDonneesDebitProducteur(DefaultXYDataset donneesDebitProducteur) {
		this.donneesDebitProducteur = donneesDebitProducteur;
	}


	public FIFO getFileDebitProducteurLocal() {
		return fileDebitProducteurLocal;
	}


	public void setFileDebitProducteurLocal(FIFO fileDebitProducteurLocal) {
		this.fileDebitProducteurLocal = fileDebitProducteurLocal;
	}


	


	public FIFO getFileDebitFoyer() {
		return fileDebitFoyer;
	}


	public void setFileDebitFoyer(FIFO fileDebitFoyer) {
		this.fileDebitFoyer = fileDebitFoyer;
	}




	public DefaultXYDataset getDonneesDebitProducteurLocal() {
		return donneesDebitProducteurLocal;
	}


	public void setDonneesDebitProducteurLocal(
			DefaultXYDataset donneesDebitProducteurLocal) {
		this.donneesDebitProducteurLocal = donneesDebitProducteurLocal;
	}


	public DefaultXYDataset getDonneesDebitFoyer() {
		return donneesDebitFoyer;
	}


	public void setDonneesDebitFoyer(DefaultXYDataset donneesDebitFoyer) {
		this.donneesDebitFoyer = donneesDebitFoyer;
	}


	public FIFO getFileDebitsTotaux() {
		return fileDebitsTotaux;
	}


	public void setFileDebitsTotaux(FIFO fileDebitsTotaux) {
		this.fileDebitsTotaux = fileDebitsTotaux;
	}


	public DefaultXYDataset getDonneesDebitsTotaux() {
		return donneesDebitsTotaux;
	}


	public void setDonneesDebitsTotaux(DefaultXYDataset donneesDebitsTotaux) {
		this.donneesDebitsTotaux = donneesDebitsTotaux;
	}




}
