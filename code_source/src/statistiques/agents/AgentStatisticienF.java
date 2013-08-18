package statistiques.agents;



import java.util.ArrayList;
import statistiques.statisticienF.comportements.FinStatisticienFComportement;
import statistiques.statisticienF.comportements.InitialisationStatisticienFComportement;
import statistiques.statisticienF.comportements.TraitementStatisticienFComportement;
import utilitaires.Preferences;

import agents.AgentControleur;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

/**
 * Classe : AgentStatisticien Foyer
 * Description : Agent Statisticien Foyer , il est responsable de :
 * 				<ul>
 * 				<li> Il reçoit le débit de la consommation de chaque foyer  </li>

 * 				<li> Dès le remplissage de la file, il calcule le débit moyen  </li>
 * 				<li> ET communique le résultat à l'agent Interface Graphique  </li>
 * 				</ul>
 * @author Mohamed IMLI - Sihame AARAB
 * @version 2.0
 * 
 */

public class AgentStatisticienF extends Agent {


	private static final long serialVersionUID = -7963319685772619427L;
	/**
	 * 
	 */

	// Membres
	private ArrayList<ACLMessage> donneesFoyers;// données foyers
	private ArrayList<ACLMessage> fileAttentedonneesFoyers;// file d'attente des données foyers 
	
	
	public void setup(){


		 // Comportement de Statisticien :
		 FSMBehaviour behaviourStatisticien = new FSMBehaviour(this);
			
			//Etats
		 behaviourStatisticien.registerFirstState(new InitialisationStatisticienFComportement(this),"initialisationStatisticienF");
		 behaviourStatisticien.registerState(new TraitementStatisticienFComportement(this),"traitementStatisticienF");
		 behaviourStatisticien.registerLastState(new FinStatisticienFComportement(this),"finStatisticienF");
			
			//Transitions
		 behaviourStatisticien.registerDefaultTransition("initialisationStatisticienF","traitementStatisticienF");
		 behaviourStatisticien.registerTransition("traitementStatisticienF","traitementStatisticienF",1);
		 behaviourStatisticien.registerTransition("traitementStatisticienF","finStatisticienF",0);
			
			addBehaviour(behaviourStatisticien);



	}
	
	public void takeDown(){

	}

	// Totaux ( Depuis lancement de la simulation ) :



	
	/** Calcul total des consommations depuis le lancement de la simulation : */
	public double calculerTotalConsommation(){
		double total =0;
		for(int i=0;i<this.donneesFoyers.size();i++){
			total+=Double.parseDouble(this.donneesFoyers.get(i).getContent());
		}
		return total;
	}

	// Totaux ( Par rapport à une file d'attente  ) :



	/** Calcul total des consommations depuis le lancement de la simulation : */
	public double calculerTotalFileConsommation(){
		double total =0;
		for(int i=0;i<this.fileAttentedonneesFoyers.size();i++){
			total+=Double.parseDouble(this.fileAttentedonneesFoyers.get(i).getContent());
		}
		return total;
	}
	// Moyennes ( depuis lancement de la simulation ) :

	



	/** Calcul total des consommations depuis le lancement de la simulation : */
	public double calculerMoynneConsommation(){
		return (this.calculerTotalConsommation()/this.donneesFoyers.size());
	}

	// Moyennes ( Par rapport à la file d'attente ) :



	/** Calcul moyenne de la file d'attente des consommations : */
	public double calculerMoynneFileConsommation(){
		return (this.calculerTotalFileConsommation()/this.fileAttentedonneesFoyers.size());
	}
	
	// Pertes
	

	
	// Les MINs et les Max ..
	
	/** Calcul du débit max produit par l'agent producteur */
	

	
/** Calcul du débit min produit par l'agent producteur */
	

	
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

	/** Verifier si la file d'attente des messages de foyers est remplie */
	public boolean fileAttenteFoyersRemplie(){
		// requête au DF pour récupérer la liste des foyers :
		/*DFAgentDescription[] listeFoyers = null;
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd  = new ServiceDescription();
		sd.setType(Preferences.FOYER_SERVICE);
		dfd.addServices(sd);

		try {
			listeFoyers = DFService.search(this, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return (this.fileAttentedonneesFoyers.size() == listeFoyers.length);
*/
		return (this.fileAttentedonneesFoyers.size() >= (Preferences.getNOMBRE_DISTRIBUTEURS_CONTROLEUR()*Preferences.getNOMBRE_TRANSFORMATEURS_DISTRIBUTEUR()*Preferences.getNOMBRE_FOYERS_TRANSFORMATEUR()));
	}

	// Vider les files d'attentes ..



	/** cette fonction vide la file d'attente des messages de foyers */
	public void viderfileAttenteMessageFoyers(){
		this.fileAttentedonneesFoyers.clear();
	}




	public void ajouterDonneeFoyer(ACLMessage message){
		this.donneesFoyers.add(message);
	}

	//




	// Getters et Setters ..
	


	


	




	public ArrayList<ACLMessage> getDonneesFoyers() {
		return donneesFoyers;
	}


	public void setDonneesFoyers(ArrayList<ACLMessage> donneesFoyers) {
		this.donneesFoyers = donneesFoyers;
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
