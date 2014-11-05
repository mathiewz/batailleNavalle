package lpacpi.bataille_navale;

import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Launcher{

	public static void main( String[] args ){
		while(true){
			System.out.println( "Bienvenue dans la bataille navale!" );
			int menu = -1;
			while (menu==-1){
				menu = menu();
			}
			new Partie();
		}
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

}
