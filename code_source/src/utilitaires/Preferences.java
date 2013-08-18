package utilitaires;

import jade.core.AID;

/**
 * Classe : Preferences
 * Description : Cette classe contient :
 * 				<ul>
 * 				<li> Parametres de la simulation </li>
 * 				<li> Nom des événements de l'interface graphique </li>
 * 				<li> AID ( identité ) des agents de la partie Controleur d l'application  </li>
 * 				<li> L'ontologie de tous les messages échangés entre les agents </li>
 * 				</ul>
 * Remarque : les membres de cette classe sont statiques, nous n'aurons pas besoin de l'instancer.
 * @author Mohamed IMLI - Sihame AARAB
 * @version 3.0
 * 
 */

public class Preferences {

	// Membres :

	// Paramètres de la simulation par défault :

	private static int NOMBRE_MESSAGES_SECONDE=1; 	// règler le nombre de messages par séconde 
	private static int NOMBRE_DISTRIBUTEURS_CONTROLEUR=1; // nombre de distributeurs par controleur
	private static int NOMBRE_TRANSFORMATEURS_DISTRIBUTEUR=1; // nombre de transformateurs par distributeur
	private static int NOMBRE_FOYERS_TRANSFORMATEUR=1; // nombre de foyers par transformateur

	// Parametres du producteur local :

	private static double MAX_DEBIT_PRODUCTEUR_LOCAL=3000; // débit max du producteur local
	private static double MIN_DEBIT_PRODUCTEUR_LOCAL=30;   // débit min du producteur local


	// Parametres du producteur :

	private static double MAX_DEBIT_PRODUCTEUR=3000; // débit max du producteur local

	// Nom de l'agent Statisticien :

	public static final AID agentStatisticien = new AID("statisticien",AID.ISLOCALNAME); // "statisticien" par défaut
	public static final AID agentStatisticienPL = new AID("statisticienPL",AID.ISLOCALNAME); // "statisticienPL" par défaut
	public static final AID agentStatisticienF = new AID("statisticienF",AID.ISLOCALNAME); // "agentStatisticienF" par défaut
	public static final AID agentInterfaceGraphique = new AID("interfaceGraphique",AID.ISLOCALNAME); // "interfaceGraphique" par défaut




	// Gestion des événement de l'interface graphique :

	public static final int LANCER_SIMULATION_EVENEMENT = 1000;

	// Ontologie des messages échangés :

	// Entre les agents du simulateur

	public static final String PRODUCTION_LOCAL = "PRODUCTION_LOCAL"; // production local
	public static final String CONSOMMATION_FOYER = "CONSOMMATION_FOYER"; // consommation du foyer
	public static final String PRODUCTION_PRODUCTEUR = "PRODUCTION_PRODUCTEUR"; // production du poducteur
	public static final String DIFFERENCE_COMPTEUR_INTELLIGENT = "DIFFERENCE_COMPTEUR_INTELLIGENT"; // résultat calcul d'un compteur intelligent ( Conso - Prod )
	public static final String RESULTAT_SOMME_TRANSFORMATEUR = "RESULTAT_SOMME_TRANSFORMATEUR";
	public static final String RESULTAT_SOMME_DISTRIBUTEUR = "RESULTAT_SOMME_DISTRIBUTEUR";
	public static final String DEMANDE_AJUSTEMENT_PRODUCTION = "DEMANDE_AJUSTEMENT_PRODUCTION";

	// Entre l'agent Statisticien et l'Agent Interface Graphique :

	// Totaux et totaux des files d'attentes
	public static final String TOTAUX = "TOTAUX";
	public static final String TOTAUX_FILEATTENTE = "TOTAUX_FILEATTENTE";
	// moyennes et moyennes des files d'attentes

	// public static final String MOYENNES_DEBITS = "MOYENNES_DEBITS";
	// public static final String MOYENNES_TOTAUX = "MOYENNES_TOTAUX";
	public static final String MOYENNE_DEBIT_FILE_PRODUCTEUR_LOCAL = "MOYENNE_DEBIT_FILE_PRODUCTEUR_LOCAL";
	public static final String MOYENNE_DEBIT_FILE_PRODUCTEUR = "MOYENNE_DEBIT_FILE_PRODUCTEUR";
	public static final String MOYENNE_DEBIT_FILE_FOYER = "MOYENNE_DEBIT_FILE_FOYER";


	// Pertes :
	public static final String TOTAL_DEBIT_PERTES = "TOTAL_DEBIT_PERTES";
	// Min et Max du debit de la production local
	public static final String MIN_MAX_DEBIT_PRODUCTION_LOCAL = "MIN_MAX_DEBIT_PRODUCTION_LOCAL";
	// Min et Max de la production local
	public static final String MIN_MAX_PRODUCTION_LOCAL = "MIN_MAX_PRODUCTION_LOCAL";
	// Min et Max du debit de consommation
	public static final String MIN_MAX_DEBIT_CONSOMMATION = "MIN_MAX_DEBIT_CONSOMMATION";
	// Min et Max de la consommation
	public static final String MIN_MAX_CONSOMMATION = "MIN_MAX_CONSOMMATION";
	// Min et Max de la consommation
	public static final String MIN_MAX_PRODUCTION = "MIN_MAX_PRODUCTION";















	public static final String DEMANDE_LANCEMENT_SIMULATION ="DEMANDE_LANCEMENT_SIMULATION";

	// Services :

	public static final String PRODUCTEUR_LOCAL_SERVICE ="PRODUCTEUR_LOCAL_SERVICE";
	public static final String FOYER_SERVICE ="FOYER_SERVICE";






	public Preferences() {
		// Constructeur par défaut, vide
	}

	// Setters and Getters ..

	public static int getNOMBRE_MESSAGES_SECONDE() {
		return NOMBRE_MESSAGES_SECONDE;
	}


	public static void setNOMBRE_MESSAGES_SECONDE(int nOMBRE_MESSAGES_SECONDE) {
		NOMBRE_MESSAGES_SECONDE = nOMBRE_MESSAGES_SECONDE;
	}


	public static int getNOMBRE_DISTRIBUTEURS_CONTROLEUR() {
		return NOMBRE_DISTRIBUTEURS_CONTROLEUR;
	}


	public static void setNOMBRE_DISTRIBUTEURS_CONTROLEUR(
			int nOMBRE_DISTRIBUTEURS_CONTROLEUR) {
		NOMBRE_DISTRIBUTEURS_CONTROLEUR = nOMBRE_DISTRIBUTEURS_CONTROLEUR;
	}


	public static int getNOMBRE_TRANSFORMATEURS_DISTRIBUTEUR() {
		return NOMBRE_TRANSFORMATEURS_DISTRIBUTEUR;
	}


	public static void setNOMBRE_TRANSFORMATEURS_DISTRIBUTEUR(
			int nOMBRE_TRANSFORMATEURS_DISTRIBUTEUR) {
		NOMBRE_TRANSFORMATEURS_DISTRIBUTEUR = nOMBRE_TRANSFORMATEURS_DISTRIBUTEUR;
	}


	public static int getNOMBRE_FOYERS_TRANSFORMATEUR() {
		return NOMBRE_FOYERS_TRANSFORMATEUR;
	}


	public static void setNOMBRE_FOYERS_TRANSFORMATEUR(
			int nOMBRE_FOYERS_TRANSFORMATEUR) {
		NOMBRE_FOYERS_TRANSFORMATEUR = nOMBRE_FOYERS_TRANSFORMATEUR;
	}


	public static double getMAX_DEBIT_PRODUCTEUR_LOCAL() {
		return MAX_DEBIT_PRODUCTEUR_LOCAL;
	}


	public static void setMAX_DEBIT_PRODUCTEUR_LOCAL(
			double mAX_DEBIT_PRODUCTEUR_LOCAL) {
		MAX_DEBIT_PRODUCTEUR_LOCAL = mAX_DEBIT_PRODUCTEUR_LOCAL;
	}


	public static double getMIN_DEBIT_PRODUCTEUR_LOCAL() {
		return MIN_DEBIT_PRODUCTEUR_LOCAL;
	}


	public static void setMIN_DEBIT_PRODUCTEUR_LOCAL(
			double mIN_DEBIT_PRODUCTEUR_LOCAL) {
		MIN_DEBIT_PRODUCTEUR_LOCAL = mIN_DEBIT_PRODUCTEUR_LOCAL;
	}


	public static double getMAX_DEBIT_PRODUCTEUR() {
		return MAX_DEBIT_PRODUCTEUR;
	}


	public static void setMAX_DEBIT_PRODUCTEUR(double mAX_DEBIT_PRODUCTEUR) {
		MAX_DEBIT_PRODUCTEUR = mAX_DEBIT_PRODUCTEUR;
	}


	public static AID getAgentStatisticien() {
		return agentStatisticien;
	}





	public static AID getAgentInterfaceGraphique() {
		return agentInterfaceGraphique;
	}


}
