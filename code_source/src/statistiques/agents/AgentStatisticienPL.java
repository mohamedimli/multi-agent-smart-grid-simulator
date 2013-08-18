package statistiques.agents;



import java.util.ArrayList;
import statistiques.statisticienPL.comportements.FinStatisticienPLComportement;
import statistiques.statisticienPL.comportements.InitialisationStatisticienPLComportement;
import statistiques.statisticienPL.comportements.TraitementStatisticienPLComportement;
import utilitaires.Preferences;

import agents.AgentControleur;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

/**
 * Classe : AgentStatisticienPL
 * Description : Agent Statisticien, il est responsable de
 * 				<ul>
 * 				<li> Création de l'agent Statisticien Producteur Local et l'agent Statisitien Foyer </li>
 * 				<li> Création de l'agent interface graphique </li>
 * 				<li> Du lancement de la simulation,( il crée l'agent Controleur)  </li>
 * 				<li> Il reçoit le débit de la production du producteur et le communique à l'agent Interface Graphique  </li>
 * 				</ul>
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 * 
 */

public class AgentStatisticienPL extends Agent {


	

	
	private static final long serialVersionUID = -3115097129912716000L;
	// Membres
	private ArrayList<ACLMessage> donneesProLocaux; // données producteurs locaux
	private ArrayList<ACLMessage> fileAttentedonneesProLocaux; // file d'attente des données de producteurs locaux
	
	
	public void setup(){


		 // Comportement de Statisticien :
		 FSMBehaviour behaviourStatisticien = new FSMBehaviour(this);
			
			//Etats
		 behaviourStatisticien.registerFirstState(new InitialisationStatisticienPLComportement(this),"initialisationStatisticienPL");
		 behaviourStatisticien.registerState(new TraitementStatisticienPLComportement(this),"traitementStatisticienPL");
		 behaviourStatisticien.registerLastState(new FinStatisticienPLComportement(this),"finStatisticienPL");
			
			//Transitions
		 behaviourStatisticien.registerDefaultTransition("initialisationStatisticienPL","traitementStatisticienPL");
		 behaviourStatisticien.registerTransition("traitementStatisticienPL","traitementStatisticienPL",1);
		 behaviourStatisticien.registerTransition("traitementStatisticienPL","finStatisticienPL",0);
			
			addBehaviour(behaviourStatisticien);



	}
	
	public void takeDown(){

	}

	// Totaux ( Depuis lancement de la simulation ) :



	/** Calcul total des productions locales depuis le lancement de la simulation : */
	public double calculerTotalProductionLocale(){
		double total =0;
		for(int i=0;i<this.donneesProLocaux.size();i++){
			total+=Double.parseDouble(this.donneesProLocaux.get(i).getContent());
		}
		return total;
	}


	// Totaux ( Par rapport à une file d'attente  ) :


	/** Calcul total de la file d'attente des productions locales : */
	public double calculerTotalFileProductionLocale(){
		double total =0;
		for(int i=0;i<this.fileAttentedonneesProLocaux.size();i++){
			total+=Double.parseDouble(this.fileAttentedonneesProLocaux.get(i).getContent());
		}
		return total;
	}

	// Moyennes ( depuis lancement de la simulation ) :



	/** Calcul total des productions locales depuis le lancement de la simulation : */
	public double calculerMoyenneProductionLocale(){
		return (this.calculerTotalProductionLocale()/this.donneesProLocaux.size());
	}


	// Moyennes ( Par rapport à la file d'attente ) :


	/** Calcul moyenne de la file d'attente des productions locales  : */
	public double calculerMoyenneFileProductionLocale(){
		return (this.calculerTotalFileProductionLocale()/this.fileAttentedonneesProLocaux.size());
	}

	
	
	
	
	
	// Les MINs et les Max ..
	

	

	


	
	/** Calcul du maximum absolue de la production locale */
	
	public double calculerMaxProductionLocale(){
		double maxPL=Double.parseDouble(this.donneesProLocaux.get(0).getContent());
		for(int i=0;i<this.donneesProLocaux.size();i++){
			if (Double.parseDouble(this.donneesProLocaux.get(0).getContent())>maxPL){
				maxPL = Double.parseDouble(this.donneesProLocaux.get(0).getContent());
			}
		}
		return maxPL;
	}
	
/** Calcul du minimum absolue de la production locale */
	
	public double calculerMinProductionLocale(){
		double minPL=Double.parseDouble(this.donneesProLocaux.get(0).getContent());
		for(int i=0;i<this.donneesProLocaux.size();i++){
			if (Double.parseDouble(this.donneesProLocaux.get(0).getContent())<minPL){
				minPL = Double.parseDouble(this.donneesProLocaux.get(0).getContent());
			}
		}
		return minPL;
	}
	
	/** Calcul du debit maximum de la production locale */
	
	public double calculerMaxDebitProductionLocale(){
		double maxDebitPL=Double.parseDouble(this.fileAttentedonneesProLocaux.get(0).getContent());
		for(int i=0;i<this.fileAttentedonneesProLocaux.size();i++){
			if (Double.parseDouble(this.fileAttentedonneesProLocaux.get(0).getContent())>maxDebitPL){
				maxDebitPL = Double.parseDouble(this.fileAttentedonneesProLocaux.get(0).getContent());
			}
		}
		return maxDebitPL;
	}
	
/** Calcul du debit minimum de la production locale */
	
	public double calculerMinDebitProductionLocale(){
		double minDebitPL=Double.parseDouble(this.fileAttentedonneesProLocaux.get(0).getContent());
		for(int i=0;i<this.fileAttentedonneesProLocaux.size();i++){
			if (Double.parseDouble(this.fileAttentedonneesProLocaux.get(0).getContent())<minDebitPL){
				minDebitPL = Double.parseDouble(this.fileAttentedonneesProLocaux.get(0).getContent());
			}
		}
		return minDebitPL;
	}
	

	

	
	// Ajout des messages .. 

	/** Ajouter un message à la file d'attente des donnees des producteurs locaux */
	public void ajouterMessageFilePL(ACLMessage message){

		// Vérifier si un message de l'emetteur n'existe déja dans la file d'attente
		/*boolean existance = false;
		AID emetteur = message.getSender();
		for(int i=0;i<this.fileAttentedonneesProLocaux.size();i++){

			if (this.fileAttentedonneesProLocaux.get(i).getSender() == emetteur) {
				existance = true;
			}
		}
		if((! existance) && (! ! this.fileAttentePLRemplie())){
			this.fileAttentedonneesProLocaux.add(message);
		}*/
		this.fileAttentedonneesProLocaux.add(message);
	}


	// Remplissge des files d'attente des messages
	/** Verifier si la file d'attente des messages de producteur locaux est remplie */
	public boolean fileAttentePLRemplie(){
		// requête au DF pour récupérer la liste des producteur locaux :
		
		
		
		/*DFAgentDescription[] listePL = null;
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd  = new ServiceDescription();
		sd.setType(Preferences.PRODUCTEUR_LOCAL_SERVICE);
		dfd.addServices(sd);

		try {
			listePL = DFService.search(this, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return ((this.fileAttentedonneesProLocaux.size()*2) >= (Preferences.getNOMBRE_DISTRIBUTEURS_CONTROLEUR()*Preferences.getNOMBRE_TRANSFORMATEURS_DISTRIBUTEUR()*Preferences.getNOMBRE_FOYERS_TRANSFORMATEUR()));

		
		//return(this.fileAttentedonneesProLocaux.size() == (Preferences.getNOMBRE_DISTRIBUTEURS_CONTROLEUR()*Preferences.getNOMBRE_MESSAGES_SECONDE()*Preferences.getNOMBRE_FOYERS_TRANSFORMATEUR()/2));

		// return true;
	}


	// Vider les files d'attentes ..

	/** cette fonction vide la file d'attente des messages de producteurs locaux */
	public void viderfileAttenteMessagePL(){
		this.fileAttentedonneesProLocaux.clear();
	}

	



	public void ajouterDonneeProducteurLocal(ACLMessage message){
		this.donneesProLocaux.add(message);
	}

	
	

	//




	// Getters et Setters ..
	public ArrayList<ACLMessage> getDonneesProLocaux() {
		return donneesProLocaux;
	}


	public void setDonneesProLocaux(ArrayList<ACLMessage> donneesProLocaux) {
		this.donneesProLocaux = donneesProLocaux;
	}







	




	public ArrayList<ACLMessage> getFileAttentedonneesProLocaux() {
		return fileAttentedonneesProLocaux;
	}


	public void setFileAttentedonneesProLocaux(
			ArrayList<ACLMessage> fileAttentedonneesProLocaux) {
		this.fileAttentedonneesProLocaux = fileAttentedonneesProLocaux;
	}

	
	/** Lancer la simulation */
	public void lancerSimulation(){
		this.creerAgentControleur();
	}

	/** crée une instance de l'agent Distributeur */
	public void creerAgentControleur(){
		String monIdentite = this.getAID().getLocalName();
		createAgent("C", AgentControleur.class.getName(), new Object[] {monIdentite});
	}
	
	
	/** crée une instance de l'agent Interface Graphique */
	public void creerAgentInterfaceGraphique(){
		String monIdentite = this.getAID().getLocalName();
		createAgent(Preferences.getAgentInterfaceGraphique().getLocalName(), interfaceGraphique.agents.AgentInterfaceGraphique.class.getName(), new Object[] {monIdentite});
	}

	/**
	 * Fonction qui instancie un agent.
	 * @param agentName nom de l'agent
	 * @param agentClass Chemin complet de la classe de l'agent à instancier (package + nom de la classe).
	 * @param argumets Liste des atguments à passer à l'agent (peut être "null")
	 */
	private void createAgent(String agentName, String agentClass, Object[] argumets) {
		ContainerController containetController = getContainerController();
		try {
			AgentController newAgent = containetController.createNewAgent(agentName, agentClass, argumets);
			newAgent.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}

	public void informerAgentInterfaceGraphique(ACLMessage message) {
		message.addReceiver(Preferences.getAgentInterfaceGraphique());
		this.send(message);
	}

	






}
