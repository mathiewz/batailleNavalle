package lpacpi.bataille_navale;

import java.util.Scanner;

public class EnnemiCommun extends Partie {

	private int nbTour;

	public EnnemiCommun(){
		System.out.println("Saisir votre nom de joueur");
		Scanner sc = new Scanner(System.in);
		String nomJoueur1=sc.nextLine();
		System.out.println("Saisir le nom de l'adversaire");
		Scanner sc1 = new Scanner(System.in);
		String nomJoueur2=sc1.nextLine();		
		bj1 = new Board(nomJoueur1);
		bj2 = new IA();
		nbTour=0;

		while(!bj1.isBoardGameOver() && !bj2.isBoardGameOver()){
			System.out.println("\nA "+nomJoueur1+ " de jouer !!\n================\n\n");
			tour(bj1, bj2,true);
			if(!bj2.isBoardGameOver()){
				System.out.println("\nA "+ nomJoueur2+ " de jouer !!\n================\n\n");
				tour(bj2, bj1,true);
			}
			tourTiers();
		}
		if(bj2.isBoardGameOver()){
			System.out.println("Partie terminée !!!"+nomJoueur1+" à gagné !\n");
		}else{
			System.out.println("Partie terminée !!!"+nomJoueur2+" à gagné !\n");
		}
	}

	protected void tourTiers() {
		if (nbTour%2==1){
		System.out.println("\nL'armée tierce vous attaque !!\n================\n\n");
			bj1.tirIA();
		} else {
			System.out.println("\nL'armée tierce attaque l'ennemi !!\n================\n\n");
			bj2.tirIA();
		}
		nbTour++;
	}
}