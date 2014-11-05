package lpacpi.bataille_navale;

import java.util.Scanner;

public class Partie {

	private String nomJoueur1;
	private String nomJoueur2;

	private Board bj1;
	private Board bj2;

	public Partie(){
		nomJoueur1 = "j1";
		nomJoueur1 = "j2";
		bj1 = new Board();
		bj2 = new Board();
		while(!bj1.isBoardGameOver() && !bj2.isBoardGameOver()){
			System.out.println("\nTour du Joueur 1\n================\n\n");
			tour(bj1, bj2,true);
			if(!bj2.isBoardGameOver()){
				System.out.println("\nTour du Joueur 2\n================\n\n");
				tour(bj2, bj1,true);
			}
		}
		System.out.println("Game Over !!!\n"+nomJoueur1+" à gagné !");
	}

	private void tour(Board boardJoueur, Board boardEnnemy, boolean variable){
		int tir=0;
		int histo=0;
		String XY = "";
		if(variable)
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
			String coordonnee=ia();
			tir = boardEnnemy.tir(coordonnee);
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
				String coordonnee=ia();
				tir = boardEnnemy.tir(coordonnee);

			}
		}       

		if(tir == Board.CASE_TOUCHE){
			System.out.println("Un bateau ennemi à été touché  !!");

		}
		else if (tir == Board.BATEAU_COULE){
			System.out.println("Le bateau ennemi à coulé  !!");
			if(!boardEnnemy.isBoardGameOver()){
				tour(boardJoueur, boardEnnemy,variable);
			}
		}

	}
	public String ia(){
		String coordonnees;
		String[] position= new String[]{"A","B","C","D","E","F","G","H","I","J"};
		int lower = 0;
		int higher = 9;

		int random1 = (int)(Math.random() * (higher-lower)) + lower;
		int random2 = (int)(Math.random() * (higher-lower)) + lower;
		int i = random1;
		int Y= random2;
		coordonnees=position[i]+Y;
		return coordonnees;
	}

}
