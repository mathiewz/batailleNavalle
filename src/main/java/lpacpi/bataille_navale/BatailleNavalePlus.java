package lpacpi.bataille_navale;

import java.util.Scanner;

public class BatailleNavalePlus extends Partie { 
	public BatailleNavalePlus(){
		
	System.out.println("Saisir votre nom de joueur");
	Scanner sc = new Scanner(System.in);
	String nomJoueur1=sc.nextLine();
	System.out.println("Saisir le nom de l'adversaire");
	Scanner sc1 = new Scanner(System.in);
	String nomJoueur2=sc1.nextLine();		
	bj1 = new Board(nomJoueur1);	
	bj2 = new IA();
	while(!bj1.isBoardGameOver() && !bj2.isBoardGameOver()){
		int nbBateau=bj1.NbBoat();
		for(int j=0; j<nbBateau;j++){
		System.out.println("\nA "+nomJoueur1+ " de jouer !!\n================\n\n");
		tour(bj1, bj2,true);
		}
		if(!bj2.isBoardGameOver()){
			nbBateau=bj2.NbBoat();
			for(int i=0; i<nbBateau;i++){
				System.out.println("\nA "+nomJoueur1+ " de jouer !!\n================\n\n");
				tour(bj2, bj1,true);
				}
		}
	}
	if(bj2.isBoardGameOver()){
		System.out.println("Partie terminée !!!"+nomJoueur1+" à gagné !\n");
	}else{
		System.out.println("Partie terminée !!!"+nomJoueur2+" à gagné !\n");
	}
}
}
