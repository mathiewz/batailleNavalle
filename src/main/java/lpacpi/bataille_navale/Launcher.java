package lpacpi.bataille_navale;

import java.io.File;
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
			}if (menu == 1){
				new Partie(true);
			}else if(menu == 2){
				new Partie(false);
			}else if(menu == 3){
				new TimeTrial();
			}else if(menu == 4){
				new Partie21();
			}else if(menu == 5){
				new BatailleNavalePlus();
			}else if(menu == 6){
				new EnnemiCommun();
			}
		}
	}

	private static int menu(){
		System.out.println("\nMENU");
		System.out.println("====\n");
		System.out.println("1-Partie classique (Joueur vs IA)");
		System.out.println("2-Partie deux joueurs");
		System.out.println("3-Partie Time Trial");
		System.out.println("4-Partie 21x21");
		System.out.println("5-Bataille Navale plus");
		System.out.println("6-Partie Ennemi Commun");
		System.out.println("7-Credits");
		System.out.println("8-Quitter");
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un numéro(1,2,3,4,5,6,7,8) :");
		int ret = 0;
		String valeurClavier=sc.nextLine();
		sc.close();
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
				ret = 3;
				break;
			case 4:
				ret = 4;
				break;
			case 5:
				ret = 5;
				break;
			case 6:
				ret = 6;
				break;
			case 7:
				System.out.println("\nCREDITS");
				System.out.println("=======\n");
				System.out.println("Développé par:");
				System.out.println("LUCAS Mathieu");
				System.out.println("LE QUERE Ronan");
				System.out.println("CHEVESSIER Pierrick");
				System.out.println("DANIEL Steven\n\n");
				ret = -1;
				
				break;
			case 8:
				System.exit(0);
				break;
			case 22:	
				try{
					String filePath = "./boat";

					Scanner scanner=new Scanner(new File(filePath));


					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();

						System.out.println(line);

					}
					scanner.close();
				} catch(Exception e){
					e.printStackTrace();
				}
				ret = -1;
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
