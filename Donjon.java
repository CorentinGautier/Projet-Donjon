package jeux;

import java.util.Random;
import java.util.Scanner;

public class Donjon {

	private boolean estVivant = true; // valeur booleen : true = joueur est
										// vivant et false = est mort
	private static Salle[] lesSalles;
	private static int nbSalles;
	private static int profondeur; // nombre d'étage du donjon
	private static int iProchaineSalle; // indice de la prochaine salle
	private static int salleFin;
	private final static String LISIBILITER_TIRRET_PLAN = "  | ";
	private final static String LISIBILITER_ESPACE_PLAN = "      ";
	private final static String POSITION_JOUEUR = " <------ vous êtes ici";
	private Random r = new Random(); // valeur aléatoire

	public void constuire() {
		// construction du donjon aléatoirmement

		System.out.println("-----------------------------------------------");
		Scanner sc = new Scanner(System.in); // permet de prendre la valeur ici String écrite dans le clavier par le
												// joueur
		System.out.println("Combien d'étages voulez-vous pour votre donjon ?");
		profondeur = sc.nextInt();
		profondeur--;

		nbSalles = profondeur * 10;
		salleFin = -1;

		// creation salle de départ
		iProchaineSalle = 0;
		lesSalles = new Salle[nbSalles + 1];
		lesSalles[0] = new Salle(0);
		// création des autres salles
		iProchaineSalle++;
		generationSalle(0, Salle.getPorteEntreeDuJeux(), Salle.PORTE_A_CALCULER, Salle.PORTE_A_CALCULER,
				Salle.PORTE_A_CALCULER, 0); // initilisation de la porte de depart (le nord) qui est bloqué et mettre a
											// calculer pour peut etre crée d'autres portes pour la salle de depart

		System.out.println("Nombre de salles créées : " + (Donjon.salleFin + 1));
		System.out.println("Nombre de zombies qui peuplent le donjon : " + Salle.getNbTotalZombies());
		System.out.println("Numéro de la salle aux trésors : " + Donjon.salleFin);
		textLegendePlan();

		MoteurJeu.pause();

	}

	public void generationSalle(int iSalleEnCours, int PN, int PE, int PS, int PO, int profSalle) {
		// PN = porte nord
		// PS = porte Sud
		// PE = Porte Est
		// PO = Porte Ouest
		// génaration de toute les Salles du Donjon apres la création de la première
		Random existePorte = new Random();
		int iProf = profSalle;

		iProf++;
		lesSalles[iSalleEnCours].setiSalleNord(PN);
		lesSalles[iSalleEnCours].setiSalleSud(PS);
		lesSalles[iSalleEnCours].setiSalleEst(PE);
		lesSalles[iSalleEnCours].setiSalleOuest(PO);

		// tant que toute les portes ne sont pas calculées
		while ((lesSalles[iSalleEnCours].getiSalleSud() == Salle.PORTE_A_CALCULER || PS != Salle.PORTE_A_CALCULER)
				&& (lesSalles[iSalleEnCours].getiSalleEst() == Salle.PORTE_A_CALCULER || PE != Salle.PORTE_A_CALCULER)
				&& (lesSalles[iSalleEnCours].getiSalleOuest() == Salle.PORTE_A_CALCULER || PO != Salle.PORTE_A_CALCULER)
				&& (lesSalles[iSalleEnCours].getiSalleNord() == Salle.PORTE_A_CALCULER || PN != Salle.PORTE_A_CALCULER)
				&& iProchaineSalle < nbSalles && iProf <= profondeur) {

			if (iProchaineSalle < nbSalles && lesSalles[iSalleEnCours].getiSalleSud() == Salle.PORTE_A_CALCULER) {
				// determine si oui ou non il y a une salle de crée au Sud de
				// la salle précedente

				if (existePorte.nextInt(100) > 60) // 60% de chance d'avoir une
													// porte vers la salle
													// suivante
				{
					lesSalles[iSalleEnCours].setiSalleSud(iProchaineSalle); // si une porte au sud est crée alors crée
																			// une salle, qu'on peut accèder avec la
																			// porte sud de celle-ci
					iProchaineSalle++;
				}

			}
			if (iProchaineSalle < nbSalles && lesSalles[iSalleEnCours].getiSalleEst() == Salle.PORTE_A_CALCULER) {
				// determine si oui ou non il y a une salle de crée a l'Est de
				// la salle précedente

				if (existePorte.nextInt(100) > 60) // 60% de chance d'avoir une
													// porte vers la salle
													// suivante
				{
					lesSalles[iSalleEnCours].setiSalleEst(iProchaineSalle); // si une porte au Est est crée alors crée
					// une salle, qu'on peut accèder avec la
					// porte Est de celle-ci
					iProchaineSalle++;
				}
				;
			}
			if (iProchaineSalle < nbSalles && lesSalles[iSalleEnCours].getiSalleOuest() == Salle.PORTE_A_CALCULER) {
				// determine si oui ou non il y a une salle de crée a l'ouest de
				// la salle précedente

				if (existePorte.nextInt(100) > 60) // 60% de chance d'avoir une
													// porte vers la salle
													// suivante
				{
					lesSalles[iSalleEnCours].setiSalleOuest(iProchaineSalle);// si une porte au Ouest est crée alors crée
					// une salle, qu'on peut accèder avec la
					// porte ouest de celle-ci
					iProchaineSalle++;
				}
			}

			if (iProchaineSalle < nbSalles && lesSalles[iSalleEnCours].getiSalleNord() == Salle.PORTE_A_CALCULER) {
				// determine si oui ou non il y a une salle de crée au Nord de
				// la salle précedente

				if (existePorte.nextInt(100) > 60) // 60% de chance d'avoir une
													// porte vers la salle
													// suivante
				{
					lesSalles[iSalleEnCours].setiSalleNord(iProchaineSalle);// si une porte au nord est crée alors crée
					// une salle, qu'on peut accèder avec la
					// porte nord de celle-ci
					iProchaineSalle++;
				}
			}

		}
		// verifie si y'a pas deja une pote de calculer aléatoirement et lui
		// assigne une salle pour ne pas crée une porte nord qui donne sur
		// aucune salle
		if (lesSalles[iSalleEnCours].getiSalleNord() != Salle.PORTE_A_CALCULER && PN == Salle.PORTE_A_CALCULER) {
			lesSalles[lesSalles[iSalleEnCours].getiSalleNord()] = new Salle(lesSalles[iSalleEnCours].getiSalleNord());

			generationSalle(lesSalles[iSalleEnCours].getiSalleNord(), Salle.PORTE_A_CALCULER, Salle.PORTE_A_CALCULER,
					iSalleEnCours, Salle.PORTE_A_CALCULER, iProf);
		}

		// verifie si y'a pas deja une pote de calculer aléatoirement et lui
		// assigne une salle pour ne pas crée une porte Sud qui donne sur aucune
		// salle
		if (lesSalles[iSalleEnCours].getiSalleSud() != Salle.PORTE_A_CALCULER && PS == Salle.PORTE_A_CALCULER) {
			lesSalles[lesSalles[iSalleEnCours].getiSalleSud()] = new Salle(lesSalles[iSalleEnCours].getiSalleSud());

			generationSalle(lesSalles[iSalleEnCours].getiSalleSud(), iSalleEnCours, Salle.PORTE_A_CALCULER,
					Salle.PORTE_A_CALCULER, Salle.PORTE_A_CALCULER, iProf);
		}
		// verifie si y'a pas deja une pote de calculer aléatoirement et lui
		// assigne une salle pour ne pas crée une porte Est qui donne sur aucune
		// salle
		if (lesSalles[iSalleEnCours].getiSalleEst() != Salle.PORTE_A_CALCULER && PE == Salle.PORTE_A_CALCULER) {
			lesSalles[lesSalles[iSalleEnCours].getiSalleEst()] = new Salle(lesSalles[iSalleEnCours].getiSalleEst());

			generationSalle(lesSalles[iSalleEnCours].getiSalleEst(), Salle.PORTE_A_CALCULER, Salle.PORTE_A_CALCULER,
					Salle.PORTE_A_CALCULER, iSalleEnCours, iProf);
		}
		// verifie si y'a pas deja une pote de calculer aléatoirement et lui
		// assigne une salle pour ne pas crée une porte ouest qui donne sur
		// aucune salle
		if (lesSalles[iSalleEnCours].getiSalleOuest() != Salle.PORTE_A_CALCULER && PO == Salle.PORTE_A_CALCULER) {
			lesSalles[lesSalles[iSalleEnCours].getiSalleOuest()] = new Salle(lesSalles[iSalleEnCours].getiSalleOuest());

			generationSalle(lesSalles[iSalleEnCours].getiSalleOuest(), Salle.PORTE_A_CALCULER, iSalleEnCours,
					Salle.PORTE_A_CALCULER, Salle.PORTE_A_CALCULER, iProf);
		}

		if (salleFin < iSalleEnCours) {
			salleFin = iSalleEnCours;
		}

	}

	public void AffichePlan(int salleJoueur) { // fonction d'affichage du
												// plan du Donjon

		System.out.println("Voici le plan de votre Donjon : \n");
		System.out.println(".......-------.......-------.......-------.......-------.......");
		AfficheUneSalle(Salle.getSalleEntreeDuJeux(), "N", "ENTREE.", "      ", salleJoueur);
		System.out.println(".......-------.......-------.......-------.......-------.......\n");
	}

	// affiche la salle dans le plan
	public void AfficheUneSalle(int iSalle, String porteEntree, String CheminSalle, String CheminSousSalle,
			int salleJoueur) {
		String LigneAff;
		String cheminDeLaSalle;

		if (iSalle != 0) {
			System.out.println(CheminSalle.substring(0, CheminSalle.length() - 3) + "|");
		}

		LigneAff = CheminSalle + lesSalles[iSalle].afficheLaSalle(porteEntree);
		if (salleJoueur == iSalle) {
			LigneAff += POSITION_JOUEUR;
		}
		System.out.println(LigneAff);

		// sur le plan affiche les sortie de la salle avec des un syntaxe pour
		// améliorer la compréhension du plan il faut le faire pour le nord en
		// premier puis les autres car toutes les cardinalités peuvent être la
		// première sortie représenté dans le plan
		if (lesSalles[iSalle].getiSalleNord() != Salle.getPorteAbsente() && "N" != porteEntree
				&& iSalle != Salle.getPorteEntreeDuJeux()) {
			cheminDeLaSalle = LISIBILITER_TIRRET_PLAN;
			if ((lesSalles[iSalle].getiSalleOuest() == Salle.getPorteAbsente() || "O" == porteEntree)
					&& (lesSalles[iSalle].getiSalleEst() == Salle.getPorteAbsente() || "E" == porteEntree)
					&& (lesSalles[iSalle].getiSalleSud() == Salle.getPorteAbsente() || "S" == porteEntree)) {

				cheminDeLaSalle = LISIBILITER_ESPACE_PLAN;

			}
			AfficheUneSalle(lesSalles[iSalle].getiSalleNord(), "S", CheminSousSalle + "  N--",
					CheminSousSalle + cheminDeLaSalle, salleJoueur);
		}

		// sur le plan affiche les sortie de la salle avec des un syntaxe pour
		// améliorer la compréhension du plan il faut le faire pour le Sud en
		// premier puis les autres car toutes les cardinalités peuvent être la
		// première sortie représenté dans le plan

		if (lesSalles[iSalle].getiSalleSud() != Salle.getPorteAbsente() && "S" != porteEntree) {
			cheminDeLaSalle = LISIBILITER_TIRRET_PLAN;
			if ((lesSalles[iSalle].getiSalleOuest() == Salle.getPorteAbsente() || "O" == porteEntree)
					&& (lesSalles[iSalle].getiSalleEst() == Salle.getPorteAbsente() || "E" == porteEntree)) {
				cheminDeLaSalle = LISIBILITER_ESPACE_PLAN;

			}
			AfficheUneSalle(lesSalles[iSalle].getiSalleSud(), "N", CheminSousSalle + "  S--",
					CheminSousSalle + cheminDeLaSalle, salleJoueur);
		}

		// sur le plan affiche les sortie de la salle avec des un syntaxe pour
		// améliorer la compréhension du plan il faut le faire pour l'Est en
		// premier puis les autres car toutes les cardinalités peuvent être la
		// première sortie représenté dans le plan

		if (lesSalles[iSalle].getiSalleEst() != Salle.getPorteAbsente() && "E" != porteEntree) {
			cheminDeLaSalle = LISIBILITER_TIRRET_PLAN;
			if (lesSalles[iSalle].getiSalleOuest() == Salle.getPorteAbsente() || "O" == porteEntree) {
				cheminDeLaSalle = LISIBILITER_ESPACE_PLAN;

			}

			AfficheUneSalle(lesSalles[iSalle].getiSalleEst(), "O", CheminSousSalle + "  E--",
					CheminSousSalle + cheminDeLaSalle, salleJoueur);
		}
		// sur le plan affiche les sortie de la salle avec des un syntaxe pour
		// améliorer la compréhension du plan il faut le faire pour l'Ouest en
		// premier puis les autres car toutes les cardinalités peuvent être la
		// première sortie représenté dans le plan

		if (lesSalles[iSalle].getiSalleOuest() != Salle.getPorteAbsente() && "O" != porteEntree) {
			cheminDeLaSalle = LISIBILITER_ESPACE_PLAN;
			AfficheUneSalle(lesSalles[iSalle].getiSalleOuest(), "E", CheminSousSalle + "  O--",
					CheminSousSalle + cheminDeLaSalle, salleJoueur);
		}

	}

	public void textLegendePlan() // affiche la légende du plan du donjon
	{

		System.out.println("Sur le Plan");
		System.out.println("\t'" + POSITION_JOUEUR + "' indique où est situé le personnage");
		System.out.println(" \t! indique la présence d'un zombie dans la salle");
		System.out.println(" \t* indique la salle aux trésors");
	}

	
	// getters et setters des différents attributs de cette classe
	public boolean isEstVivant() {
		return estVivant;
	}

	public void setEstVivant(boolean estVivant) {
		this.estVivant = estVivant;
	}

	public static Salle[] getLesSalles() {
		return lesSalles;
	}

	public static void setLesSalles(Salle[] lesSalles) {
		Donjon.lesSalles = lesSalles;
	}

	public static int getNbSalles() {
		return nbSalles;
	}

	public static void setNbSalles(int nbSalles) {
		Donjon.nbSalles = nbSalles;
	}

	public static int getProfondeur() {
		return profondeur;
	}

	public static void setProfondeur(int profondeur) {
		Donjon.profondeur = profondeur;
	}

	public static int getiProchaineSalle() {
		return iProchaineSalle;
	}

	public static void setiProchaineSalle(int iProchaineSalle) {
		Donjon.iProchaineSalle = iProchaineSalle;
	}

	public static int getSalleFin() {
		return salleFin;
	}

	public static void setSalleFin(int salleFin) {
		Donjon.salleFin = salleFin;
	}

	public Random getR() {
		return r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public static String getLisibiliterTirretPlan() {
		return LISIBILITER_TIRRET_PLAN;
	}

	public static String getLisibiliterEspacePlan() {
		return LISIBILITER_ESPACE_PLAN;
	}

	public static String getPositionJoueur() {
		return POSITION_JOUEUR;
	}

}
