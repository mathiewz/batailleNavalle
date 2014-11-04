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
    	try {
			j2.placerBateau(new Bateau(2, "bla", 4, 7, Board.SENS_VERTICAL));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        while(isNotGameOver){
        	System.out.println("\nTour du Joueur 1\n================\n\n");
        	tour(j1);
        	if(isNotGameOver){
        		System.out.println("\nTour du Joueur 2\n================\n\n");
        		tour(j2);
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
			board.placerBateau(new Bateau(2, "torpilleur", 0, 0, Board.SENS_HORIZONTAL));
	    	board.placerBateau(new Bateau(3, "sous-marin", 0, 1, Board.SENS_HORIZONTAL));
	    	board.placerBateau(new Bateau(3, "contre-torpilleur", 0, 2, Board.SENS_HORIZONTAL));
	    	board.placerBateau(new Bateau(4, "croiseur", 0, 3, Board.SENS_HORIZONTAL));
	    	board.placerBateau(new Bateau(5, "porte-avions", 0, 4, Board.SENS_HORIZONTAL));
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
    private static void tour(Board board){
    	System.out.println(board.toString());
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Saisir les coordonnées du tir  ");
    	String XY = "";
    	XY = sc.nextLine();
    	
    	int tir = board.tir(XY); 
        while (tir == -1 ){
        	System.out.println("Saisie non valable !!");
        	sc = new Scanner(System.in);
        	System.out.println("Saisir les coordonnées du tir  ");
        	XY = "";
        	XY = sc.nextLine();
        	
        	tir = board.tir(XY); 
        
        }       
    	if (tir == Board.BATEAU_COULE){
    		tour(board);    		
    	}
    }

}
