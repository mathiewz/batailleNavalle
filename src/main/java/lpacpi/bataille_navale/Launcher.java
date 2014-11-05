package lpacpi.bataille_navale;

import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Launcher{

	public static void main( String[] args ){
		boolean isNotGameOver = true;
		boolean variable=true;
		System.out.println( "Bienvenue dans la bataille navale!" );
		int menu = -1;
		while (menu==-1){
			menu = menu();
		}
		Board j1 = new Board();
		Board j2 = new Board();
		placerBateau(j1);
		placerBateau(j2);
		while(isNotGameOver){
			variable=true;
			System.out.println("\nTour du Joueur 1\n================\n\n");
			tour(j1, j2,variable);
			if(isNotGameOver){
				System.out.println("\nTour du Joueur 2\n================\n\n");
				variable=false;
				tour(j2, j1,variable);
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
		int ret = 0;
		String valeurClavier=sc.nextLine();
		if(Board.isNumeric(valeurClavier))
		{	
			int str = Integer.valueOf(valeurClavier);

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
		}
		else
		{
			System.out.println("Saisie non valide");
			ret=-1;
		}
		return ret;
	}

	private static void placerBateau(Board board){
		try {
			String[] nomBateau=new String[]{"torpilleur","sous-marin","contre-torpilleur","croiseur","porte-avions"};
			int[] dimBateau=new int[]{2,3,3,4,5};
			for(int i=0;i<5;i++){
				boolean isBateauPlace = false;
				do{
					boolean isPLacementValide;
					int[] coordonee;
					int sens = -1;
					String err;
					do{
						err = "";
						isPLacementValide = false;
						Scanner sc = new Scanner(System.in);
						System.out.println("Veuillez saisir coordonnées du "+nomBateau[i]+" :");
						coordonee = Board.parseStringCoordonnee(sc.nextLine());
						if(coordonee[0] == -1){
							err += "Les coordonées du bateau ne sont pas valides";
						} else {
							sc = new Scanner(System.in);
							System.out.println("Veuillez saisir sens "+nomBateau[i]+" (horizontal=1 et vertical=2):");
							String value = sc.nextLine();
							if(Board.isNumeric(value)){
								sens = Integer.valueOf(value);
								if(sens == 1 || sens ==2){isPLacementValide = true;}
							} else {
								err += "La saisie du sens n'est pas valide\n";
							}
						}
						System.out.println(err);
					}while(!isPLacementValide);

					int placementErr = board.placerBateau(new Bateau(dimBateau[i], nomBateau[i], coordonee[0], coordonee[1], sens));
					if(placementErr == -1){
						System.out.println("Case déjà occupée par un autre bateau");
					} else if(placementErr == -2){
						System.out.println("Le bateau dépasse du plateau");
					} else {
						isBateauPlace = true;
					}
				}while(!isBateauPlace);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void tour(Board boardJoueur, Board boardEnnemy, boolean variable){
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
			tour(boardJoueur, boardEnnemy,variable);    		
		}

	}
	public static String ia(){
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
