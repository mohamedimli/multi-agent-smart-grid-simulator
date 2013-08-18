package statistiques.agents;



import java.util.ArrayList;
import statistiques.statisticien.comportements.FinStatisticienComportement;
import statistiques.statisticien.comportements.InitialisationStatisticienComportement;
import statistiques.statisticien.comportements.TraitementStatisticienComportement;
import utilitaires.Preferences;

import agents.AgentControleur;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

/**
 * Classe : AgentStatisticien
 * Description : Agent Statisticien, il est responsable de :
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

public class AgentStatisticien extends Agent {


	private static final long serialVersionUID = -7963319685772619427L;
	

	// Membres
	private ArrayList<ACLMessage> donneesProLocaux; // données producteurs locaux
	private ArrayList<ACLMessage> donneesProducteur;// données producteurs 
	private ArrayList<ACLMessage> donneesFoyers;// données foyers
	private ArrayList<ACLMessage> fileAttentedonneesProLocaux; // file d'attente des données de producteurs locaux
	private ArrayList<ACLMessage> fileAttentedonneesFoyers;// file d'attente des données foyers 
	
	/** Le méta-comportement de l'agent Statisticien */
	public void setup(){

		 FSMBehaviour behaviourStatisticien = new FSMBehaviour(this);
			
			//Etats
		 behaviourStatisticien.registerFirstState(new InitialisationStatisticienComportement(this),"initialisationStatisticien");
		 behaviourStatisticien.registerState(new TraitementStatisticienComportement(this),"traitementStatisticien");
		 behaviourStatisticien.registerLastState(new FinStatisticienComportement(this),"finStatisticien");
			
			//Transitions
		 behaviourStatisticien.registerDefaultTransition("initialisationStatisticien","traitementStatisticien");
		 behaviourStatisticien.registerTransition("traitementStatisticien","traitementStatisticien",1);
		 behaviourStatisticien.registerTransition("traitementStatisticien","finStatisticien",0);
			
			addBehaviour(behaviourStatisticien);



	}
	
	public void takeDown(){

	}


	/** Calcul total des productions depuis le lancement de la simulation : */
	public double calculerTotalProduction(){
		double total =0;
		for(int i=0;i<this.donneesProducteur.size();i++){
			total+=Double.parseDouble(this.donneesProducteur.get(i).getContent());
		}
		return total;
	}

	/** Calcul total des productions locales depuis le lancement de la simulation : */
	public double calculerTotalProductionLocale(){
		double total =0;
		for(int i=0;i<this.donneesProLocaux.size();i++){
			total+=Double.parseDouble(this.donneesProLocaux.get(i).getContent());
		}
		return total;
	}
	/** Calcul total des consommations depuis le lancement de la simulation : */
	public double calculerTotalConsommation(){
		double total =0;
		for(int i=0;i<this.donneesFoyers.size();i++){
			total+=Double.parseDouble(this.donneesFoyers.get(i).getContent());
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
	/** Calcul total des consommations depuis le lancement de la simulation : */
	public double calculerTotalFileConsommation(){
		double total =0;
		for(int i=0;i<this.fileAttentedonneesFoyers.size();i++){
			total+=Double.parseDouble(this.fileAttentedonneesFoyers.get(i).getContent());
		}
		return total;
	}
	// Moyennes ( depuis lancement de la simulation ) :

	/** Calcul moyenne des productions depuis le lancement de la simulation : */
	public double calculerMoynneProduction(){
		return (this.calculerTotalProduction()/this.donneesProducteur.size());

	}

	/** Calcul total des productions locales depuis le lancement de la simulation : */
	public double calculerMoyenneProductionLocale(){
		return (this.calculerTotalProductionLocale()/this.donneesProLocaux.size());
	}

	/** Calcul total des consommations depuis le lancement de la simulation : */
	public double calculerMoynneConsommation(){
		return (this.calculerTotalConsommation()/this.donneesFoyers.size());
	}

	// Moyennes ( Par rapport à la file d'attente ) :


	/** Calcul moyenne de la file d'attente des productions locales  : */
	public double calculerMoyenneFileProductionLocale(){
		return (this.calculerTotalFileProductionLocale()/this.fileAttentedonneesProLocaux.size());
	}

	/** Calcul moyenne de la file d'attente des consommations : */
	public double calculerMoynneFileConsommation(){
		return (this.calculerTotalFileConsommation()/this.fileAttentedonneesFoyers.size());
	}
	
	// Pertes
	/** Calcul du total des pertes depuis le lancement la simulation */
	public double calculerTotalPertes(){
		return (this.calculerTotalProduction()+this.calculerTotalProductionLocale()-this.calculerTotalConsommation());
	}
	/** Calcul du débit des pertes */
	public double calculerDebitPertes(){
		// récuper le débit du producteur :
		double debitProducteur = Double.parseDouble(this.donneesProducteur.get(this.donneesProducteur.size()-1).getContent());
		return (debitProducteur+this.calculerTotalFileProductionLocale()-this.calculerTotalFileConsommation());
	}
	
	// Les MINs et les Max ..
	
	/** Calcul du débit max produit par l'agent producteur */
	
	public double calculerMaxProduction(){
		double maxProd=Double.parseDouble(this.donneesProducteur.get(0).getContent());
		for(int i=0;i<this.donneesProducteur.size();i++){
			if (Double.parseDouble(this.donneesProducteur.get(0).getContent())>maxProd){
				maxProd = Double.parseDouble(this.donneesProducteur.get(0).getContent());
			}
		}
		return maxProd;
	}
	
/** Calcul du débit min produit par l'agent producteur */
	
	public double calculerMinProduction(){
		double minProd=Double.parseDouble(this.donneesProducteur.get(0).getContent());
		for(int i=0;i<this.donneesProducteur.size();i++){
			if (Double.parseDouble(this.donneesProducteur.get(0).getContent())<minProd){
				minProd = Double.parseDouble(this.donneesProducteur.get(0).getContent());
			}
		}
		return minProd;
	}
	
	/** Calcul du maximum absolue de la consommation */
	
	public double calculerMaxConsommation(){
		double maxConso=Double.parseDouble(this.donneesFoyers.get(0).getContent());
		for(int i=0;i<this.donneesFoyers.size();i++){
			if (Double.parseDouble(this.donneesFoyers.get(0).getContent())>maxConso){
				maxConso = Double.parseDouble(this.donneesFoyers.get(0).getContent());
			}
		}
		return maxConso;
	}
	
/** Calcul du minimum absolue de la consommation */
	
	public double calculerMinConsommation(){
		double minConso=Double.parseDouble(this.donneesFoyers.get(0).getContent());
		for(int i=0;i<this.donneesFoyers.size();i++){
			if (Double.parseDouble(this.donneesFoyers.get(0).getContent())<minConso){
				minConso = Double.parseDouble(this.donneesFoyers.get(0).getContent());
			}
		}
		return minConso;
	}
	
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
	
/** Calcul du debit maximum de la consommation */
	
	public double calculerMaxDebitConsommation(){
		double maxDebitConso=Double.parseDouble(this.donneesFoyers.get(0).getContent());
		for(int i=0;i<this.donneesFoyers.size();i++){
			if (Double.parseDouble(this.donneesFoyers.get(0).getContent())>maxDebitConso){
				maxDebitConso = Double.parseDouble(this.donneesFoyers.get(0).getContent());
			}
		}
		return maxDebitConso;
	}
	
/** Calcul du debit minimum de la consommation */
	
	public double calculerMinDebitConsommation(){
		double minDebitConso=Double.parseDouble(this.donneesFoyers.get(0).getContent());
		for(int i=0;i<this.donneesFoyers.size();i++){
			if (Double.parseDouble(this.donneesFoyers.get(0).getContent())<minDebitConso){
				minDebitConso = Double.parseDouble(this.donneesFoyers.get(0).getContent());
			}
		}
		return minDebitConso;
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
	/** Ajouter un message à la file d'attente des donnees des producteurs locaux */
	public void ajouterMessageFileFoyers(ACLMessage message){

		// Vérifier si un message de l'emetteur n'existe déja dans la file d'attente
		
		
		/*boolean existance = false;
		AID emetteur = message.getSender();
		for(int i=0;i<this.fileAttentedonneesFoyers.size();i++){

			if (this.fileAttentedonneesFoyers.get(i).getSender() == emetteur) {
				existance = true;
			}
		}
		if((! existance) && (! ! this.fileAttenteFoyersRemplie())){
			this.fileAttentedonneesFoyers.add(message);
		}*/
		
		this.fileAttentedonneesFoyers.add(message);
	}

	// Remplissge des files d'attente des messages
//	/** Verifier si la file d'attente des messages de producteur locaux est remplie */
	public boolean fileAttentePLRemplie(){
//		// requête au DF pour récupérer la liste des producteur locaux :
//		
//		
//		DFAgentDescription[] listePL = null;
//		DFAgentDescription dfd = new DFAgentDescription();
//		ServiceDescription sd  = new ServiceDescription();
//		sd.setType(Preferences.PRODUCTEUR_LOCAL_SERVICE);
//		dfd.addServices(sd);
//
//		try {
//			listePL = DFService.search(this, dfd);
//		} catch (FIPAException e) {
//			e.printStackTrace();
//		}
//		
//		
		return(this.fileAttentedonneesProLocaux.size() == (Preferences.getNOMBRE_DISTRIBUTEURS_CONTROLEUR()*Preferences.getNOMBRE_MESSAGES_SECONDE()*Preferences.getNOMBRE_FOYERS_TRANSFORMATEUR()/2));
	}
	
	
	/** Verifier si la file d'attente des messages de foyers est remplie */
public boolean fileAttenteFoyersRemplie(){
//		// requête au DF pour récupérer la liste des foyers :
//		DFAgentDescription[] listeFoyers = null;
//		DFAgentDescription dfd = new DFAgentDescription();
//		ServiceDescription sd  = new ServiceDescription();
//		sd.setType(Preferences.FOYER_SERVICE);
//		dfd.addServices(sd);
//
//		try {
//			listeFoyers = DFService.search(this, dfd);
//		} catch (FIPAException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		return (this.fileAttentedonneesFoyers.size() == listeFoyers.length);
		return true; // L'agent statisiticien ne s'occupe plus de la reception du débit de consommation des foyers;
					// c'est la résponsabilité de l'agent Statisiticien Foyer
	}

	// Vider les files d'attentes ..

	/** cette fonction vide la file d'attente des messages de producteurs locaux */
	public void viderfileAttenteMessagePL(){
		this.fileAttentedonneesProLocaux.clear();
	}

	/** cette fonction vide la file d'attente des messages de foyers */
	public void viderfileAttenteMessageFoyers(){
		this.fileAttentedonneesFoyers.clear();
	}



	public void ajouterDonneeProducteurLocal(ACLMessage message){
		this.donneesProLocaux.add(message);
	}

	public void ajouterDonneeProducteur(ACLMessage message){
		this.donneesProducteur.add(message);
	}
	public void ajouterDonneeFoyer(ACLMessage message){
		this.donneesFoyers.add(message);
	}

	//




	// Getters et Setters ..
	public ArrayList<ACLMessage> getDonneesProLocaux() {
		return donneesProLocaux;
	}


	public void setDonneesProLocaux(ArrayList<ACLMessage> donneesProLocaux) {
		this.donneesProLocaux = donneesProLocaux;
	}


	public ArrayList<ACLMessage> getDonneesProducteur() {
		return donneesProducteur;
	}


	public void setDonneesProducteur(ArrayList<ACLMessage> donneesProducteur) {
		this.donneesProducteur = donneesProducteur;
	}


	public ArrayList<ACLMessage> getDonneesFoyers() {
		return donneesFoyers;
	}


	public void setDonneesFoyers(ArrayList<ACLMessage> donneesFoyers) {
		this.donneesFoyers = donneesFoyers;
	}


	public ArrayList<ACLMessage> getFileAttentedonneesProLocaux() {
		return fileAttentedonneesProLocaux;
	}


	public void setFileAttentedonneesProLocaux(
			ArrayList<ACLMessage> fileAttentedonneesProLocaux) {
		this.fileAttentedonneesProLocaux = fileAttentedonneesProLocaux;
	}


	public ArrayList<ACLMessage> getFileAttentedonneesFoyers() {
		return fileAttentedonneesFoyers;
	}


	public void setFileAttentedonneesFoyers(
			ArrayList<ACLMessage> fileAttentedonneesFoyers) {
		this.fileAttentedonneesFoyers = fileAttentedonneesFoyers;
	}

	
	/** Lancer la simulation */
	public void lancerSimulation(){
		this.creerAgentControleur();
	}

	/** crée une instance de l'agent Constroleur */
	public void creerAgentControleur(){
		String monIdentite = this.getAID().getLocalName();
		createAgent("C", AgentControleur.class.getName(), new Object[] {monIdentite});
	}
	
	/** créé une instance de l'agent statisticienPL */
	public void creerAgentStatisticienPL() {
		createAgent(Preferences.agentStatisticienPL.getLocalName(), AgentStatisticienPL.class.getName(), new Object[] {});
		
	}
	
	public void creerAgentStatisticienF() {
		createAgent(Preferences.agentStatisticienF.getLocalName(), AgentStatisticienF.class.getName(), new Object[] {});

		
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
