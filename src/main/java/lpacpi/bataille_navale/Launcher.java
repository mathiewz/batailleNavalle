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
        menu();
        Board j1 = new Board();
        Board j2 = new Board();
    	placerBateau(j1);
    	placerBateau(j2);
        while(isNotGameOver){
        	tour(j1);
        	if(isNotGameOver){
        		tour(j2);
        	}
        }
        System.out.println("Game Over !!!");
    }
    private static void menu(){
        System.out.println("1-jeux");
        System.out.println("2-credit");
        System.out.println("3-Quitter");
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un numéro(1,2,3) :");
        int str = Integer.valueOf(sc.nextLine());
       
    	switch(str){
	    	case 1:
	    		
	    		break;
	    	
	    	case 2:
	    		
	    		break;
	    	
	    	case 3:
	    		System.exit(0);
	    		break;
	    	default:
	            System.out.println("ERREUR");
	            break;
    	}
    }
    
    private static void placerBateau(Board board){
    	try {
			board.placerBateau(0,0, new Bateau(2, "torpilleur"),Board.SENS_HORIZONTAL);
	    	board.placerBateau(0,1, new Bateau(3, "sous-marin"),Board.SENS_HORIZONTAL);
	    	board.placerBateau(0,2, new Bateau(3, "contre-torpilleur"),Board.SENS_HORIZONTAL);
	    	board.placerBateau(0,3, new Bateau(4, "croiseur"),Board.SENS_HORIZONTAL);
	    	board.placerBateau(0,4, new Bateau(5, "porte-avions"),Board.SENS_HORIZONTAL);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
    private static void tour(Board board){
//    	board.tir(); 
    }
}
