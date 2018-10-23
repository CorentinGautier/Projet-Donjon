package jeux;

import java.util.Random;

public class Salle {

	private int numeroSalle;
	private int iSalleNord; // indice salle nord
	private int iSalleSud; // indice salle Sud
	private int iSalleEst;// indice salle Est
	private int iSalleOuest;// indice salle Ouest
	public final static int PORTE_A_CALCULER = -1;
	// constante qui determine si on peux y ajouter un salle mais pas
	// obligatoire d'avoir une salle, si pas de salle alors la porte devient une
	// porte absente
	private final static int PORTE_ABSENTE = -1; 
	//constante qui reprensente le mur d'une salle
	private final static int PORTE_ENTREE_DU_JEUX = -2; 
	//porte d'entrée du jeux qui est tout le temps la meme et qui ne peux etre modifier
	private final static int SALLE_ENTREE_DU_JEUX = 0;
	//Salle d'entrée et spawn du personnage dans le jeux
	private static int nbTotalZombies = 0;
	private Zombie leZombie = null;

	public Salle(int numSalle) {
		super();
		this.numeroSalle = numSalle;

		Random hazard = new Random();

		if (numeroSalle != SALLE_ENTREE_DU_JEUX) {
			if (hazard.nextInt(100) < 40) // 40% de chance d'avoir un zombie
											// dans chaque salle
			{
				nbTotalZombies++;
				leZombie = new Zombie(40 + hazard.nextInt(10), numeroSalle); 
				// création  du zombie et choix de ses PV aléatoirement entre 40 et 5O HP
			}
		}
	}

	public void affiche() { // affiche les portes disponible dans la console et propose leur utilisation au joueur
		String affichage = "Accès possible(s)";
		if (iSalleNord != PORTE_A_CALCULER && iSalleNord != this.PORTE_ENTREE_DU_JEUX) {
			affichage = (affichage + "\n\t n: porteNord vers salle n°" + iSalleNord);
		}
		if (iSalleSud != PORTE_A_CALCULER) {
			affichage = (affichage + "\n\t s: porteSud vers salle n°" + iSalleSud);
		}
		if (iSalleEst != PORTE_A_CALCULER) {
			affichage = (affichage + "\n\t e: porteEst vers salle n°" + iSalleEst);
		}
		if (iSalleOuest != PORTE_A_CALCULER) {
			affichage = (affichage + "\n\t o: porteOuest vers salle n°" + iSalleOuest);
		}
		System.out.println(affichage);
	}

	public String afficheLaSalle(String porte) { //affichage de la salle dans le plan de la console
		String aff;
		aff = porte + "[" + numeroSalle;
		if (leZombie != null) {
			aff = aff + "!";
		}
		if (Donjon.getSalleFin() == numeroSalle) {
			aff = aff + "*";
		}
		aff = aff + "]";
		return (aff);
	}
	
	
	// getters et setters des différents attributs de cette classe

	public int getNumeroSalle() {
		return numeroSalle;
	}

	public void setNumeroSalle(int numeroSalle) {
		this.numeroSalle = numeroSalle;
	}

	public int getiSalleNord() {
		return iSalleNord;
	}

	public void setiSalleNord(int iSalleNord) {
		this.iSalleNord = iSalleNord;
	}

	public int getiSalleSud() {
		return iSalleSud;
	}

	public void setiSalleSud(int iSalleSud) {
		this.iSalleSud = iSalleSud;
	}

	public int getiSalleEst() {
		return iSalleEst;
	}

	public void setiSalleEst(int iSalleEst) {
		this.iSalleEst = iSalleEst;
	}

	public int getiSalleOuest() {
		return iSalleOuest;
	}

	public void setiSalleOuest(int iSalleOuest) {
		this.iSalleOuest = iSalleOuest;
	}

	public static int getNbTotalZombies() {
		return nbTotalZombies;
	}

	public static void setNbTotalZombies(int nbTotalZombies) {
		Salle.nbTotalZombies = nbTotalZombies;
	}

	public Zombie getLeZombie() {
		return leZombie;
	}

	public void setLeZombie(Zombie leZombie) {
		this.leZombie = leZombie;
	}

	public static int getPorteACalculer() {
		return PORTE_A_CALCULER;
	}

	public static int getPorteAbsente() {
		return PORTE_ABSENTE;
	}

	public static int getPorteEntreeDuJeux() {
		return PORTE_ENTREE_DU_JEUX;
	}

	public static int getSalleEntreeDuJeux() {
		return SALLE_ENTREE_DU_JEUX;
	}
	
	
}


