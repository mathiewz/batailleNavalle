package lpacpi.bataille_navale;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Launcher{

	public static void main( String[] args ){
		boolean isNotGameOver = true;
		System.out.println( "Bienvenue dans la bataille navale!" );
		int menu = -1;
		while (menu==-1){
			menu = menu();
		}
		Board j1 = new Board();
		Board j2 = new Board();
		placerBateau(j1);
		placerBateau(j2);
		
		isNotGameOver = finJeu();
		while(isNotGameOver){
			System.out.println("\nTour du Joueur 1\n================\n\n");
			tour(j1, j2);
			if(isNotGameOver){
				System.out.println("\nTour du Joueur 2\n================\n\n");
				tour(j2, j1);
			}
		}
		System.out.println("Game Over !!!");
		
	}
	


	private static int menu(){
		System.out.println("1-jeux");
		System.out.println("2-credit");
		System.out.println("3-Quitter");
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un numéro(1,2,3) :");
		int str = Integer.valueOf(sc.nextLine());
		int ret = 0;
		switch(str){
		case 1:
			ret = 0;
			break;
		case 2:
			System.out.println("blabla les credits.....");
			ret = -1;
			break;
		case 3:
			System.exit(0);
			break;
		default:
			System.out.println("Saisie non valide");
			ret = -1;
			break;
		}
		return ret;
	}

	private static void placerBateau(Board board){
		try {
			String[] nomBateau=new String[]{"torpilleur","sous-marin","contre-torpilleur","croiseur","porte-avions"};
			int[] dimBateau=new int[]{2,3,3,4,5};
			boolean test;
			Scanner sc = new Scanner(System.in);
		
			for(int i=0;i<5;i++){
				test=false;
				while (!test)
				{
				System.out.println("Veuillez saisir coordonnées du "+nomBateau[i]+" :");
				int[] coordonee = Board.parseStringCoordonnee(sc.nextLine());
				sc = new Scanner(System.in);
				System.out.println("Veuillez saisir sens "+nomBateau[i]+" (horizontal=1 et vertical=2):");
				int sens = Integer.valueOf(sc.nextLine());
				if(sens==Board.SENS_HORIZONTAL){
					if(coordonee[0]+dimBateau[i]<Board.DIMENSION) {
				board.placerBateau(new Bateau(dimBateau[i], nomBateau[i], coordonee[0], coordonee[1], sens));
				test=true;
					}
					else
					{
						test=false;
						System.out.println("hors limite");
					}
					
				}
				else
				{
					if(coordonee[1]+dimBateau[i]<Board.DIMENSION) {
						board.placerBateau(new Bateau(dimBateau[i], nomBateau[i], coordonee[0], coordonee[1], sens));
						test=true;
							}
							else
							{
								test=false;
								System.out.println("hors limite");
							}
				
				
		}}}}
		 catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void tour(Board boardJoueur, Board boardEnnemy){
		System.out.println(boardJoueur.afficheAllie());
		System.out.println(boardEnnemy.afficheEnnemy());
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir les coordonnées du tir  ");
		String XY = "";
		XY = sc.nextLine();

		int tir = boardEnnemy.tir(XY); 
		while (tir == -1 ){
			System.out.println("Saisie non valable !!");
			sc = new Scanner(System.in);
			System.out.println("Saisir les coordonnées du tir  ");
			XY = "";
			XY = sc.nextLine();

			tir = boardEnnemy.tir(XY); 

		}       
		if (tir == Board.BATEAU_COULE){

			if(tir == Board.CASE_TOUCHE){
				System.out.println("Un bateau ennemi à été touché  !!");

			}
			else if (tir == Board.BATEAU_COULE){
				System.out.println("Le bateau ennemi à coulé  !!");
				tour(boardJoueur, boardEnnemy);    		
			}
		}
	}
	
	private static boolean finJeu() {		
		if(Board.nbBateauCoule() != 0){		
		return true;
		}
		return false;
	}
	
	
	
	
	
}
