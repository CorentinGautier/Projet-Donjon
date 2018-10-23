package jeux;

import java.util.Scanner;

public class MoteurJeu {
	
	private Joueur leJoueur ; 
	private Donjon leDonjon;

	public MoteurJeu( Joueur leJoueur, Donjon leDonjon) {
		this.leJoueur=leJoueur;
		this.leDonjon = leDonjon; 
		
	}
	public void lancerLeJeu() throws InterruptedException {
			boolean mouvement = true; //variable de verification si le joueur peut encore faire des mouvement 
			boolean continuer = true ;//variable de verification si le joueur peut encore continuer le jeu
			int numDeplacement = 0 ; //compteur de déplacement fait dans le jeux
			String key = "0"; //valeur initial de la commande rentré par le joueur lors du scanner.

			
			while (continuer==true) {
				if (mouvement == true) {
					numDeplacement++; //incrémente le compteur de Déplacement
					
					
					if (Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getLeZombie() != null) // si il y a un zombie dans la salle alors combat
					{
						System.out.println("-----------------------------------------------");
						System.out.println("COMBAT");

						leJoueur.combattre(Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getLeZombie()); 
						// lance le combat contre le zombie dans la salle ou est le joueur
						
						MoteurJeu.pause();
						
						if (leJoueur.isEstVivant() == false ) 
						{break;
						}
					}

					if (leJoueur.getEstDansSalle() == Donjon.getSalleFin()) { // si le joueur a trouvé la salle de trésor 
						System.out.println("-----------------------------------------------");
						System.out.println("SALLE AUX TRESORS");

						System.out.println("Bien Jouer !! \n Vous avez trouvé la salle aux trésors");
						continuer=false;
						
						MoteurJeu.pause();
						break;
					}

					leDonjon.AffichePlan(leJoueur.getEstDansSalle());
					

					System.out.println(" DEPLACEMENT N°" + numDeplacement);
					System.out.println("\n Il vous reste " + Joueur.getPointDeVie() + " point(s) de vie,  vous êtes dans la salle "+leJoueur.getEstDansSalle());
					Donjon.getLesSalles()[leJoueur.getEstDansSalle()].affiche();

				}

				System.out.print(" Votre choix ? :");
				Scanner sc = new Scanner(System.in);

				key = sc.nextLine();
				mouvement = false;

				switch (key) {
				case "n": // Pour avancer vers le Nord, tapez n.
					if (Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getiSalleNord() != Salle.getPorteAbsente()
							&& Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getiSalleNord() != Salle.getPorteEntreeDuJeux()) {
						leJoueur.setEstDansSalle(Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getiSalleNord());

						mouvement = true;
					}
					break;
				case "s": // Pour avancer vers le Sud, tapez s.
					if (Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getiSalleSud() != Salle.getPorteAbsente()) {
						leJoueur.setEstDansSalle(Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getiSalleSud());
						mouvement = true;
					}
					break;
				case "e": // Pour avancer vers le Est, tapez e.
					if (Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getiSalleEst() != Salle.getPorteAbsente()) {
						leJoueur.setEstDansSalle(Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getiSalleEst());
						mouvement = true;
					}
					break;
				case "o": // Pour avancer vers le Ouest, tapez o.
					if (Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getiSalleOuest() != Salle.getPorteAbsente()) {
						leJoueur.setEstDansSalle(Donjon.getLesSalles()[leJoueur.getEstDansSalle()].getiSalleOuest());
						mouvement = true;
					}
					break;
				case "x": // Pour avancer vers le Ouest, tapez o.
					continuer = false;
					break;
				default:
					System.out.println("\n cela ne correspond pas aux commandes proposées");

				}
			}
	}
	public void textIntro () 
	// introduction du jeux et presentation des commandes 
	
	{
		System.out.println("-----------------------------------------------");		
		System.out.println("|    Bienvenue dans le jeux du Donjon !       |");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Le but est de trouver la salle aux trésors. Attention aux zombies !");
		
		System.out.println("\n Pour avancer vers le nord , tapez n");
		System.out.println(" Pour avancer vers le sud , tapez s");
		System.out.println(" Pour avancer vers l'est, tapez e");
		System.out.println(" Pour avancer vers l'ouest, tapez o");
		
		MoteurJeu.pause();
		
	}
	
	public static void pause () 
	{
	Scanner sc = new Scanner(System.in);
	System.out.println("\n<Appuyez sur la touche ENTRER>");
	sc.nextLine();
	}

}
