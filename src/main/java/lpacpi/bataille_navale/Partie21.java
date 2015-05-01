package lpacpi.bataille_navale;

import java.util.Scanner;

public class Partie21 extends Partie {

	public Partie21(){
		System.out.println("Saisir votre nom de joueur");
		Scanner sc = new Scanner(System.in);
		String nomJoueur1=sc.nextLine();
		sc.close();
		System.out.println("Saisir le nom de l'adversaire");
		Scanner sc1 = new Scanner(System.in);
		String nomJoueur2=sc1.nextLine();
		sc1.close();
		bj1 = new Board(21, nomJoueur1);
		bj2 = new IA(21);
		while(!bj2.isBoardGameOver()){
			System.out.println("\nA "+nomJoueur1+ " de jouer !!\n================\n\n");
			tour(bj1, bj2,true);
		}
		if(bj2.isBoardGameOver()){
			System.out.println("Partie terminée !!!"+nomJoueur1+" à gagné !\n");
		}else{
			System.out.println("Partie terminée !!!"+nomJoueur2+" à gagné !\n");
		}
	}

}
