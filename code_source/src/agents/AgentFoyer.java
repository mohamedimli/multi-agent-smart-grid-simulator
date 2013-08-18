package agents;


import java.util.ArrayList;
import java.util.Random;

import utilitaires.Preferences;

import comportements.foyer.EnvoiConsommationComportement;
import comportements.foyer.FinFComportement;
import comportements.foyer.InitialisationFComportement;
import appareils.Ampoule;
import appareils.AppareilElectrique;
import appareils.Aspirateur;
import appareils.Cafetiere;
import appareils.ChauffageElectrique;
import appareils.ConditionnementAir;
import appareils.Cuisiniere;
import appareils.FerARepasser;
import appareils.Four;
import appareils.MachineALaver;
import appareils.MicoOnde;
import appareils.Ordinateur;
import appareils.RadioReveil;
import appareils.Refrigerateur;
import appareils.TV;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

/** Agent Foyer qui simule le comportement d'un foyer qui met en Marche/Arret des appareils éléctrique
* @author : Mohamed IMLI
* @version : 1.0
*
*/
  

public class AgentFoyer extends Agent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4168412046714323742L;
	// membres
	protected ArrayList<AppareilElectrique> listeAppareils; // Contient la liste des appareils dont dispose le foyer
	protected AID monCompteurIntelligent=null; // L'adresse AID du compteur intelligent du foyer
	protected AID monProducteurLocal=null; // L'adresse AID du producteur local du foyer
	protected AID monTransformateur; // pour le communiquer au compteur intelligent et au producteur local lors de leur création 
	
	/** Comportement générale du Foyer */
	   public void setup(){
		   // Comportement foyer :
		   FSMBehaviour behaviourProducteurLocal = new FSMBehaviour(this);
			
			//Etats
		behaviourProducteurLocal.registerFirstState(new InitialisationFComportement(this),"initialisationFoyer");
		behaviourProducteurLocal.registerState(new EnvoiConsommationComportement(this,1000),"envoiProductionFoyer");
		behaviourProducteurLocal.registerLastState(new FinFComportement(this),"finFoyer");
			
			//Transitions
		behaviourProducteurLocal.registerDefaultTransition("initialisationFoyer","envoiProductionFoyer");
		behaviourProducteurLocal.registerTransition("envoiProductionFoyer","envoiProduction",1);
		behaviourProducteurLocal.registerTransition("envoiProductionFoyer","finFoyer",0);
			
			addBehaviour(behaviourProducteurLocal);	    
		   
		   
	   }
	   
	   protected void takeDown() {
			// Deregister from the yellow pages
			
			try {
				DFService.deregister(this);
				}
				catch (FIPAException fe) {
				fe.printStackTrace();
				}
			//TODO
			
				}
	

		/** crée un SEUL producteur local lié au Foyer si la fonction pileFaceProba retourne VRAI */
		public void creerAgentProducteurLocal(){
			if(pileFaceProba(0.50)){
				if (this.monProducteurLocal == null){
					String nomNouveauProducteurLocal=this.getAID().getLocalName() + "PL";
					String monIdentite = this.getAID().getLocalName();
					  this.monProducteurLocal = new AID(nomNouveauProducteurLocal,AID.ISLOCALNAME);
					createAgent(nomNouveauProducteurLocal, AgentProducteurLocal.class.getName(), new Object[] {monIdentite, this.getLocalName()+"CI"}); // 2 ème argument est le nom du compteur intelligent !
					  
				
			}else{
				this.monProducteurLocal=null; // Le foyer ne devrait pas possèder un producteur local !
			}
			
		}
		}
	
	/** crée un SEUL compteur intélligent lié au Foyer. */
	public void creerAgentCompteurIntelligent(){
		if (this.monCompteurIntelligent == null){
			String nomNouveauCompteurIntelligent=this.getAID().getLocalName() + "CI";
			String monIdentite = this.getAID().getLocalName();
			String monProducteurLocal="";
			if(this.getMonProducteurLocal() != null){
				 monProducteurLocal = this.getMonProducteurLocal().getLocalName();
			}
			this.monCompteurIntelligent = new AID(nomNouveauCompteurIntelligent,AID.ISLOCALNAME);
			createAgent(nomNouveauCompteurIntelligent, AgentCompteurIntelligent.class.getName(), new Object[] {monIdentite, this.monTransformateur.getLocalName(),monProducteurLocal});
		}else{
			System.out.println("Je possède dèja un compteur intelligent ");
		}
		
	}
	
	/** Initialiser la liste des appareils que possède un Foyer ainsi que le nombre des ampules */
	public void initialiserListeAppareils(){
		
		// 1 ==> 97.1 des foyers Français possèdent un TV :
		if(pileFaceProba(0.971)){
			this.listeAppareils.add(new TV());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 2 ==> 99.8 des foyers Français possèdent un Réfrigérateur :
		if(pileFaceProba(99.8)){
			this.listeAppareils.add(new Refrigerateur());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 3 ==> .. des foyers Français possède un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new RadioReveil());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 4==> .. des foyers Français possèdent un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new Ordinateur());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 5 ==> ==> .. des foyers Français possèdent un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new MicoOnde());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 6 ==> .. des foyers Français possèdent un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new MachineALaver());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 7 ==> .. des foyers Français possèdent un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new Four());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 8 ==> .. des foyers Français possèdentnt un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new FerARepasser());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 9  ==> .. des foyers Français possèdent un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new Cuisiniere());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 10 ==> .. des foyers Français possèdent un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new ConditionnementAir());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 11 ==> .. des foyers Français possèdent un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new ChauffageElectrique());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 12 ==> .. des foyers Français possèdent un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new Cafetiere());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 13 ==> .. des foyers Français possèdent un TV ( Attention proba à changer ! ):
		if(pileFaceProba(0.50)){
			this.listeAppareils.add(new Aspirateur());
		}else{
			this.listeAppareils.add(null);
		}
		
		// 14 ==> Ajout d'un nombre entre min et max d'ampoules !
		// Entre 1 et 20 ! .. un foyer possèdent au moyen une ampoule !
		
		int nombreAmpoulesFoyer = this.getEntier(1, 20);
		for ( int i =1; i<nombreAmpoulesFoyer;i++ ){
			this.listeAppareils.add(new Ampoule());
			
		}
		
		
		
	}
	/** @return retourne aléatoirement VRAI ou FAUX  */
	public boolean pileFace(){
		Random rdPileFace = new Random();
		return rdPileFace.nextBoolean();
	}
	
	/** @return retourne aléatoirement un entier entre l'intervalle spécifié */
	
	public int getEntier(int min,int max){
		Random rdgetEntier = new Random();
		return (min +(rdgetEntier.nextInt(max-min)+min));
	}
	
	/** @return retourne VRAI si la valeur aléatoire générée est inférieur à la probabilité passée en paramètre  */
	public boolean pileFaceProba(double proba){
		Random pileFaceProba = new Random();
		return  (pileFaceProba.nextDouble()<=proba);
		}

		
	

	/** Cette fonction arrête ou démarre d'une manière aléatoire la liste des appareils éléctriques
	 * initialisée du foyer .. grâce à la magie du polymorphisme :) ! 
	 */

   public void arreterDemarrerAppareils(){
	  for(int i=0;i<this.listeAppareils.size();i++){
		  if(this.listeAppareils.get(i) != null) // très important !!
		  this.listeAppareils.get(i).demarrerArreterAppareil(); // .. et c'est tout lol !
	  }
   }
   
   /** @return retourne le débit de la consommation totale du foyer */
   public double calculerDebitConsommationFoyer(){
	   double debitsomme=0;
	   for(int i=0;i<this.listeAppareils.size();i++){
		   if(this.listeAppareils.get(i) != null) // très important !!
			  debitsomme+=this.listeAppareils.get(i).getDebitConsommation();
		  }
	   return debitsomme*1000;
   }
   
   /** Communiquer le débit de consommation total au compteur intelligent */
   public void informerCompteurIntelligent(){
	// Envoie du message au compteur intelligent :
		ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
		reponse.setContent(calculerDebitConsommationFoyer()+"");
		reponse.setOntology(Preferences.CONSOMMATION_FOYER);
		reponse.addReceiver(this.monCompteurIntelligent);
		this.send(reponse);
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
	
	// Setters

	public ArrayList<AppareilElectrique> getListeAppareils() {
		return listeAppareils;
	}

	public void setListeAppareils(ArrayList<AppareilElectrique> listeAppareils) {
		this.listeAppareils = listeAppareils;
	}

	public AID getMonCompteurIntelligent() {
		return monCompteurIntelligent;
	}

	public void setMonCompteurIntelligent(AID monCompteurIntelligent) {
		this.monCompteurIntelligent = monCompteurIntelligent;
	}

	public AID getMonProducteurLocal() {
		return monProducteurLocal;
	}

	public void setMonProducteurLocal(AID monProducteurLocal) {
		this.monProducteurLocal = monProducteurLocal;
	}

	public AID getMonTransformateur() {
		return monTransformateur;
	}

	public void setMonTransformateur(AID monTransformateur) {
		this.monTransformateur = monTransformateur;
	}
	
	 
	
    

} 
