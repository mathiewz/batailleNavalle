package lpacpi.bataille_navale;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	public static final int DIMENSION=10;
	protected int[][] plateau = new int[DIMENSION][DIMENSION];
	public static final int SENS_HORIZONTAL=1;
	public static final int SENS_VERTICAL=2;
	public static final int CASE_EAU=1;
	public static final int CASE_BATEAU=2;
	public static final int CASE_DANS_EAU=3;
	public static final int CASE_TOUCHE=4;

	public static int BATEAU_COULE = 9;

	protected ArrayList<Bateau> listBateaux;
	private ArrayList<String> listCaseToucheIA;

	public Board()	{
		for(int i=0; i<DIMENSION;i++){
			for(int j=0; j<DIMENSION;j++){
				plateau[i][j] = CASE_EAU;
			}
		}
		listBateaux = new ArrayList<Bateau>();
		initialiserBateaux();
		listCaseToucheIA = new ArrayList<String>();
	}
	public int placerBateau(Bateau bateau) throws Exception
	{
		int ret = 0;
		if(bateau.getSens()==SENS_HORIZONTAL){
			if(bateau.getX()+bateau.GetTaille()-1<DIMENSION && bateau.getX() >= 0) {
				boolean onPeutPlacer = true;
				for(int n = 0; n < bateau.GetTaille(); n++){
					if(plateau[bateau.getX()+n][bateau.getY()]==CASE_BATEAU){
						onPeutPlacer = false;
					}
				}
				if(onPeutPlacer){
					for(int n = 0; n < bateau.GetTaille(); n++){
						plateau[bateau.getX()+n][bateau.getY()]=CASE_BATEAU;
					}
					listBateaux.add(bateau);
				} else {
					ret = -1;
				}
			} else {
				ret = -2;
			}

		}

		if(bateau.getSens()==SENS_VERTICAL){
			if(bateau.getY()+bateau.GetTaille()-1<DIMENSION && bateau.getY() >= 0) {
				boolean onPeutPlacer = true;
				for(int n = 0; n < bateau.GetTaille(); n++){
					if(plateau[bateau.getX()][bateau.getY()+n]==CASE_BATEAU){
						onPeutPlacer = false;
					}
				}
				if(onPeutPlacer){
					for(int n = 0; n < bateau.GetTaille(); n++){
						plateau[bateau.getX()][bateau.getY()+n]=CASE_BATEAU;
					}
					listBateaux.add(bateau);
				} else {
					ret = -1;
				}
			} else {
				ret = -2;
			}
		}
		return ret;
	}

	public String afficheAllie(){
		String ret = new String();
		ret += "Etat de votre board\n";
		ret += "   A B C D E F G H I J\n";
		for(int i=0; i<DIMENSION;i++){
			ret += "   ------------------- \n"+(i+1);
			if(i<9){ret +=" |";}
			else{ret +="|";}
			for(int j=0; j<DIMENSION;j++){
				if(plateau[j][i] == CASE_EAU){
					ret += " ";
				} else if(plateau[j][i] == CASE_BATEAU){
					ret += "#";
				} else if(plateau[j][i] == CASE_DANS_EAU){
					ret += "O";
				} else if(plateau[j][i] == CASE_TOUCHE){
					boolean isCoule = false;
					for(Bateau b : listBateaux){
						String coordonees = getCharForNumber(j);
						coordonees += String.valueOf(i+1);
						if(b.isThisBateauAtThisPlace(coordonees) && b.estCoule()){isCoule= true;}
					}
					if(isCoule){ret += "+";}
					else{ret += "X";}
				}
				ret+= "|";
			}
			ret += "\n";
		}
		ret += "   ------------------- ";
		return ret;
	}

	public String afficheEnnemy(){
		String ret = new String();
		ret += "Etat du board ennemi\n";
		ret += "   A B C D E F G H I J\n";
		for(int i=0; i<DIMENSION;i++){
			ret += "   ------------------- \n"+(i+1);
			if(i<9){ret +=" |";}
			else{ret +="|";}
			for(int j=0; j<DIMENSION;j++){
				if(plateau[j][i] == CASE_EAU || plateau[j][i] == CASE_BATEAU){
					ret += " ";
				} else if(plateau[j][i] == CASE_DANS_EAU){
					ret += "O";
				} else if(plateau[j][i] == CASE_TOUCHE){
					ret += "X";
				}
				ret+= "|";
			}
			ret += "\n";
		}
		ret += "   ------------------- ";
		return ret;
	}

	public int tir(String coordonnees){
		System.out.println("tir en "+coordonnees);
		int[]coordonnee=parseStringCoordonnee(coordonnees);
		int ret = 0;
		if(coordonnee[0] == -1 || coordonnee[0]>=DIMENSION || coordonnee[1] >= DIMENSION){
			ret = -1;
		} else {
			switch(plateau[coordonnee[0]][coordonnee[1]]){
			case CASE_EAU:
				System.out.println("dans l'eau");
				plateau[coordonnee[0]][coordonnee[1]]=CASE_DANS_EAU;
				ret = CASE_DANS_EAU;
				break;
			case CASE_BATEAU:
				plateau[coordonnee[0]][coordonnee[1]]=CASE_TOUCHE;
				for(Bateau bateau : listBateaux){
					if(bateau.isThisBateauAtThisPlace(coordonnees)){
						bateau.retirerVie();
						if(!bateau.estCoule()){
							System.out.println("Touché !");
							ret = CASE_TOUCHE;
						} else {
							System.out.println(bateau.GetNom()+" coulé !");
							ret = BATEAU_COULE;
						}
					}
				}
				break;
			case CASE_DANS_EAU:
				System.out.println("case déjà visée");
				ret = -1;
				break;

			case CASE_TOUCHE:
				System.out.println("case déjà visée");
				ret = -1;
				break;
			default:
				System.out.println("Valeur du tableau improbable ô_O");
				ret = -1;
				break;
			}
		}
		return ret;
	}
	public static int[] parseStringCoordonnee(String coordonnees){
		int[] ret = new int[2];
		if(coordonnees.length() <= 0){
			ret[0] = -1;
		}else if(convertCharToIndex(coordonnees) < 0 || convertCharToIndex(coordonnees) > 9 || !isNumeric(coordonnees.substring(1))){
			ret[0] = -1;
		}else if(Integer.valueOf(coordonnees.substring(1))-1 > 9 || Integer.valueOf(coordonnees.substring(1))-1 < 0){
			ret[0] = -1;
		} else {
			ret[0] = convertCharToIndex(coordonnees);
			ret[1] = Integer.valueOf(coordonnees.substring(1))-1;
		}
		return ret;
	}

	public void initialiserBateaux(){
		try {
			String filePath = "./bateaux.txt";
			Scanner scanner=new Scanner(new File(filePath));

			while (scanner.hasNextLine()) {
			    String line = scanner.nextLine();
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
						
						System.out.println("Veuillez saisir coordonnées du "+line.substring(2)+"(taille:"+line.substring(0,1)+") :");
						coordonee = Board.parseStringCoordonnee(sc.nextLine());
						if(coordonee[0] == -1){
							err += "Les coordonées du bateau ne sont pas valides";
						} else {
							sc = new Scanner(System.in);
							System.out.println("Veuillez saisir sens du "+line.substring(2)+" \n1-haut\n2-bas\n3-gauche\n4-droite");
							String value = sc.nextLine();
							if(isNumeric(value)){
								int choixSens = Integer.valueOf(value);
								if(choixSens >= 1 && choixSens <=4){
									if(choixSens == 1){
										coordonee[1] -= Integer.valueOf(line.substring(0,1))-1;
										choixSens = 2;
									}else if(choixSens ==3){
										coordonee[0] -= Integer.valueOf(line.substring(0,1))-1;
										choixSens = 4;
									}
									sens = (choixSens == 2)?SENS_VERTICAL:SENS_HORIZONTAL;
									isPLacementValide = true;
								} else {
									err += "La saisie du sens doit etre un chiffre entre 1 et 4\n";
								}
							} else {
								err += "La saisie du sens n'est pas valide\n";
							}
						}
						System.out.println(err);
					}while(!isPLacementValide);

					int placementErr = this.placerBateau(new Bateau(Integer.valueOf(line.substring(0,1)), line.substring(2), coordonee[0], coordonee[1], sens));
					if(placementErr == -1){
						System.out.println("Case déjà occupée par un autre bateau");
					} else if(placementErr == -2){
						System.out.println("Le bateau dépasse du plateau");
					} else {
						isBateauPlace = true;
					}
				}while(!isBateauPlace);
				
				System.out.println(this.afficheAllie());
			}
			scanner.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static int convertCharToIndex(String coordonnées) {
		return (int)coordonnées.toUpperCase().charAt(0)-65;
	}

	public static boolean isNumeric(String str) {  
		try {  
			int i = Integer.parseInt(str);  
		}
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}

	public boolean isBoardGameOver(){
		boolean ret = true;
		for(Bateau bateau : listBateaux){
			if(!bateau.estCoule()){
				ret = false;
			}
		}
		return ret;
	}

	public int tirIA(){
		String coordonnees = getCoordonneesTirIA();
		int resultatTir= tir(coordonnees);
		if(resultatTir==CASE_TOUCHE){
			listCaseToucheIA.add(coordonnees);
		} else if(resultatTir==BATEAU_COULE && listCaseToucheIA.size()>0){
			listCaseToucheIA.remove(listCaseToucheIA.size()-1);
		}
		if(resultatTir==-1)
		{
			return this.tirIA();
		}
		return resultatTir;
	}
	
	private String getCoordonneesTirIA(){
		String coordonnees="";
		if(listCaseToucheIA.isEmpty()){
			coordonnees=generateRandomCordonees();
		}else{
			String coordonneesDejaTouche = listCaseToucheIA.get(listCaseToucheIA.size()-1);
			coordonnees=tirAutourTouche(coordonneesDejaTouche);
			if(coordonnees.equals("")){
				listCaseToucheIA.remove(coordonneesDejaTouche);
				coordonnees = getCoordonneesTirIA();
			}
		}
		return coordonnees;
	}

	protected String generateRandomCordonees(){
		int lower = 0;
		int higher = 10;

		int random1 = (int)(Math.random() * (higher-lower)) + lower;
		int random2 = (int)(Math.random() * (higher-lower)) + lower;
		String coordonnees ="";
		coordonnees += getCharForNumber(random1);
		coordonnees += String.valueOf(random2+1);
		return coordonnees;
	}

	public String tirAutourTouche(String c){
		int[] coordonneeTouche = parseStringCoordonnee(c);
		ArrayList<Integer[]> coordonneePossible = new ArrayList<Integer[]>();
		coordonneePossible.add(new Integer[]{coordonneeTouche[0]-1, coordonneeTouche[1]});
		coordonneePossible.add(new Integer[]{coordonneeTouche[0]+1, coordonneeTouche[1]});
		coordonneePossible.add(new Integer[]{coordonneeTouche[0], coordonneeTouche[1]+1});
		coordonneePossible.add(new Integer[]{coordonneeTouche[0], coordonneeTouche[1]-1});
		ArrayList<Integer[]> choixRestant = new ArrayList<Integer[]>();
		for(Integer[] i : coordonneePossible){
			if(i[0]>=0 && i[0]< DIMENSION && i[1]>=0 && i[1]< DIMENSION && !(plateau[i[0]][i[1]]==CASE_DANS_EAU) && !(plateau[i[0]][i[1]]==CASE_TOUCHE)){
				choixRestant.add(i);
			} 
		}

		String coordonnees ="";

		if(!choixRestant.isEmpty()){
			int lower = 0;
			int higher = choixRestant.size();

			Integer[] i = choixRestant.get((int)(Math.random() * (higher-lower)) + lower);
			coordonnees += getCharForNumber(i[0]);
			coordonnees += String.valueOf(i[1]+1);
		}
		return coordonnees;

	}
	private String getCharForNumber(int i) {
		i++;
	    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
	}
}
