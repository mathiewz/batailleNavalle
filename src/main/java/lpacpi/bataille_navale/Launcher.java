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
			} if(menu == 1){
				new Partie(true);
			}else if(menu == 2){
				new Partie(false);
			}else{
				new TimeTrial();
			}
		}
	}

	private static int menu(){
		System.out.println("\nMENU");
		System.out.println("====\n");
		System.out.println("1-Partie classique (Joueur vs IA)");
		System.out.println("2-Partie deux joueur");
		System.out.println("5-Partie Time Trial");
		System.out.println("3-Credits");
		System.out.println("4-Quitter");
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un numéro(1,2,3,4) :");
		int ret = 0;
		String valeurClavier=sc.nextLine();
		if(Board.isNumeric(valeurClavier))
		{	
			int str = Integer.valueOf(valeurClavier);

			switch(str){
			case 1:
				ret = 1;
				break;
			case 2:
				ret = 2;
				break;
			case 3:
				System.out.println("\nCREDITS");
				System.out.println("=======\n");
				System.out.println("Développé par:");
				System.out.println("LUCAS Mathieu");
				System.out.println("LE QUERE Ronan");
				System.out.println("CHEVESSIER Pierrick");
				System.out.println("DANIEL Steven\n\n");
				ret = -1;
				break;
			case 4:
				System.exit(0);
				break;
			case 5:
				ret = 3;
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
