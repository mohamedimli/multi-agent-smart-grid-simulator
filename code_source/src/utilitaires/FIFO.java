package utilitaires;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe : FIFO
 * 	Description : cette classe implémente une file, et permet de retourner son contenu sous forme d'un tableau
 * 				  utile, pour la gestion des données en entrée des graphes du FrameWork JFREECHART
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */
public class FIFO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -562417112353753502L;
	// Membres
	Queue<Double> queue;
	int taille;



	public FIFO(int taille) {
		super();
		this.taille=taille;
		this.queue=new LinkedList<Double>();
		for(int i=0;i<taille;i++){
			queue.add((double) 0);
		}

	}

	// Ajouter un élémement à la file

	public void ajouter(double element){
		this.queue.remove();
		this.queue.add(element);

	}
	/** @return Contenu de la file sous forme d'un tableau */
	public double[][] retournerTableau(){
		double[][] tab = new double[2][this.taille];
		int k=1;

		for(int i=0;i<this.queue.size();i++){
			Iterator<Double> it = this.queue.iterator();
			tab[0][i]=(double) k;
			for(int h=0;h<i;h++){
				tab[1][i]=(double) it.next();
			}

			k++;
		}
		return tab;
	}





}
