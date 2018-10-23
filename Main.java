package jeux;

public class Main {

	 private int SalleOuEstJoueur = Salle.getSalleEntreeDuJeux();
	 private static int nbSalles;
	 private static int profondeur;
	

	public static void main(String[] args) throws InterruptedException {
		
		Joueur leJoueur = new Joueur("Nom", 100, Salle.getSalleEntreeDuJeux());//création du joueur
		Donjon leDonjon = new Donjon(); 
		MoteurJeu leMoteurJeu = new MoteurJeu(leJoueur, leDonjon); 
		leMoteurJeu.textIntro();		
		leDonjon.constuire();
		System.out.println("-----------------------------------------------");
		System.out.println("|    L'histoire commence,  bonne chance !      |");
		System.out.println("-----------------------------------------------");
		leMoteurJeu.pause();
		leMoteurJeu.lancerLeJeu();
	}
}
