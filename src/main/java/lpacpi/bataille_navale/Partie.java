package lpacpi.bataille_navale;

import java.util.Scanner;

public class Partie {

	private String nomJoueur1;
	private String nomJoueur2;

	private Board bj1;
	private Board bj2;

	
	public Partie(){
		boolean vsIA = true;
		System.out.println("saisir votre nom de Joueur");
		Scanner sc = new Scanner(System.in);
		String nomJoueur1=sc.nextLine();
		System.out.println("saisir le nom de l'adversaire");
		Scanner sc1 = new Scanner(System.in);
		String nomJoueur2=sc1.nextLine();
		
		bj1 = new Board();
		if(!vsIA){bj2 = new Board();}
		else{bj2 = new IA();}
		while(!bj1.isBoardGameOver() && !bj2.isBoardGameOver()){
			System.out.println("\nA "+nomJoueur1+ " de jouer !!\n================\n\n");
			tour(bj1, bj2,true);
			if(!bj2.isBoardGameOver()){
				System.out.println("\nA "+ nomJoueur2+ " de jouer !!\n================\n\n");
				tour(bj2, bj1,true);
			}
		}
		if(bj2.isBoardGameOver()){
			System.out.println("Partie terminée !!!"+nomJoueur1+" à gagné !\n");
		}else{
			System.out.println("Partie terminée !!!"+nomJoueur1+" à gagné !\n");
		}
	}

	private void tour(Board boardJoueur, Board boardEnnemy, boolean variable){

		int tir=0;
		String XY = "";
		if(!(boardJoueur instanceof IA))
		{
			System.out.println(boardJoueur.afficheAllie());
			System.out.println(boardEnnemy.afficheEnnemy());
			Scanner sc = new Scanner(System.in);
			System.out.println("Saisir les coordonnées du tir  ");

			XY = sc.nextLine();

			tir = boardEnnemy.tir(XY); 
		}
		else
		{
			tir = boardEnnemy.tirIA();
		}
		while (tir == -1 ){
			if(variable)
			{
				System.out.println("Saisie non valable !!");
				Scanner sc = new Scanner(System.in);
				System.out.println("Saisir les coordonnées du tir  ");
				XY = "";
				XY = sc.nextLine();

				tir = boardEnnemy.tir(XY); 
			}
			else
			{
				tir = boardEnnemy.tirIA();

			}
		}       

		if(tir == Board.CASE_TOUCHE){
			System.out.println("Un bateau ennemi à été touché  !!");
			if((boardJoueur instanceof IA))
			{
			
				Board.addCaseTouche();
			}
			

		}
		else if (tir == Board.BATEAU_COULE){
			System.out.println("Le bateau ennemi à coulé  !!");
			if(!boardEnnemy.isBoardGameOver()){
				tour(boardJoueur, boardEnnemy,variable);
			}
		}

	}
	
	
}
