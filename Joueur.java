package jeux;

import java.util.Random;

public class Joueur {

	private String nom;
	private static int pointDeVie;
	private static int pointDeVieActuel;
	private boolean estVivant = true;
	private int estDansSalle;
	private int degatActuel = 0; // degat du joueur en combat initialisé a 0
	private Random r = new Random(); // valeur aléatoire qui est utilisé pendant le combat

	public Joueur(String nom, int pointDeVie, int dansSalle) {
		super();
		this.nom = nom;
		this.pointDeVie = pointDeVie;
		this.estVivant = true;
		this.setEstDansSalle(dansSalle);

		System.out.println("-----------------------------------------------");
		System.out.println("Création du personnage");
		System.out.println("\tNombre de points de vie : " + pointDeVie);
		System.out.println("\tApparition dans la salle : " + getEstDansSalle());
		MoteurJeu.pause();

	}

	@Override
	public String toString() {
		return "Un nouveau Chercheur de trésor apparait : " + nom
				+ "\n A vous de retrouvez le trésor dans la salle des coffres, attention aux différents pièges et créatures qui vivent dans ce donjon...\n";
	}

	public void combattre(Zombie unZombie) throws InterruptedException {

		if (unZombie.getPointDeVie() < 0) { // si le zombie est deja mort
			System.out.println("Vieux cadavre de zombie présent dans la salle " + unZombie.getNumZombie());
		} else { // demarage de la rencontre avec le zombie
			System.out.println("\nVous rencontrez un zombie dans cette salle,vous l'attaquez en premier, il a "
					+ unZombie.getPointDeVie() + "PV");

			while (this.pointDeVie > 0 && unZombie.getPointDeVie() > 0) { // tant que le joueur et le zombie sont pas
																			// mort
				// combat entre le zombie et le joueur, il est automatique.
				degatActuel = 1 + r.nextInt(50 - 10);// degat du joueur entre 50 et 10
				System.out.println("\tvous l'attaquez ! Vous lui infligez : " + degatActuel + " de dégâts \n");
				pointDeVieActuel = unZombie.getPointDeVie() - degatActuel;
				unZombie.setPointDeVie(pointDeVieActuel); //change les PV du zombie
				System.out.println("\tLes points de vie du zombie sont de: " + unZombie.getPointDeVie());
				Thread.sleep(3 * 1000); // met une pause de 3 secondes

				if (unZombie.getPointDeVie() > 0) { // si le zombie a plus de 0 points de vie
					degatActuel = 1 + r.nextInt(50 - 10); // choisi les dégâts du zombie entre 50 et 10
					System.out.println("\tle zombie riposte ! il vous inflige : " + degatActuel + " de dégâts \n");
					pointDeVieActuel = Joueur.getPointDeVie() - degatActuel;//enleve les PV du joueur
					this.setPointDeVie(pointDeVieActuel); // change les PV du joueur
					System.out.println("\tVos points de vie sont de: " + Joueur.getPointDeVie());
					Thread.sleep(3 * 1000); // met une pause de 3 secondes
				}
			}
			if (this.pointDeVie < 0) { // Verification des PV du Joueur
				this.estVivant = false; 
				System.out.println("vous êtes mort! ");
			} else {
				System.out.println("\tvous avez vaincu le zombie ! \n");
			}
		}
	}

	
	// getters et setters des différents attributs de cette classe
	private void setPointDeVie(int pointDeVieActuel) {
		pointDeVie = pointDeVieActuel;
	}

	static int getPointDeVie() {
		return pointDeVie;
	}

	public boolean isEstVivant() {
		return estVivant;
	}

	public void setEstVivant(boolean estVivant) {
		this.estVivant = estVivant;
	}

	public int getEstDansSalle() {
		return estDansSalle;
	}

	public void setEstDansSalle(int estDansSalle) {
		this.estDansSalle = estDansSalle;
	}

}
