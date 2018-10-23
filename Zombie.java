package jeux;

public class Zombie {

	private int pointDeVie;
	private int numZombie; //numéro de la salle ou est le Zombie 

	
	//constructeur du zombie
	public Zombie(int pointDeVie, int numZombie) {
		super();
		this.pointDeVie = pointDeVie;
		this.setNumZombie(numZombie);
	}

	
	// getters et setters des différents attributs de cette classe
	public int getPointDeVie() {
		return pointDeVie;
	}

	public void setPointDeVie(int pointDeVie) {
		this.pointDeVie = pointDeVie;
	}

	public int getNumZombie() {
		return numZombie;
	}

	public void setNumZombie(int numZombie) {
		this.numZombie = numZombie;
	}
}
