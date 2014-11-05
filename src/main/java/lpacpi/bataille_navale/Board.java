package lpacpi.bataille_navale;

import java.util.ArrayList;

public class Board {
	public static int DIMENSION=10;
	private int[][] plateau = new int[DIMENSION][DIMENSION];
	public static int SENS_HORIZONTAL=1;
	public static int SENS_VERTICAL=2;
	public static int CASE_EAU=1;
	public static int CASE_BATEAU=2;
	public static int CASE_DANS_EAU=3;
	public static int CASE_TOUCHE=4;
	

	public static int BATEAU_COULE = 9;

	private ArrayList<Bateau> listBateaux;

	public Board()	{
		for(int i=0; i<DIMENSION;i++){
			for(int j=0; j<DIMENSION;j++){
				plateau[i][j] = CASE_EAU;
			}
		}
		listBateaux = new ArrayList<Bateau>();
	}
	public void placerBateau(Bateau bateau) throws Exception
	{
		if(bateau.getSens()==SENS_HORIZONTAL){
			if(bateau.getX()+bateau.GetTaille()<DIMENSION) {
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
					throw new Exception("Erreur : Case déja occupée");
				}
			} else {
				throw new Exception ("Erreur : Le bateau dépasse du plateau !");
			}

		}

		if(bateau.getSens()==SENS_VERTICAL){
			if(bateau.getX()+bateau.GetTaille()<DIMENSION) {
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
					throw new Exception("Erreur : Case déja occupée");
				}
			} else {
				throw new Exception ("Erreur : Le bateau dépasse du plateau !");
			}
		}
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
					ret += "X";
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
				}
				ret+= "|";
			}
			ret += "\n";
		}
		ret += "   ------------------- ";
		return ret;
	}

	public int tir(String coordonnees){
		int[]coordonnee=parseStringCoordonnee(coordonnees);
		int ret = 0;
		if(coordonnee[0] == -1 || coordonnee[0]>=DIMENSION || coordonnee[1] >= DIMENSION){
			ret = -1;
		} else {
			switch(plateau[coordonnee[0]][coordonnee[1]]){
			case 1:
				System.out.println("dans l'eau");
				plateau[coordonnee[0]][coordonnee[1]]=CASE_DANS_EAU;
				ret = CASE_DANS_EAU;
				break;
			case 2:
				plateau[coordonnee[0]][coordonnee[1]]=CASE_TOUCHE;
				for(Bateau bateau : listBateaux){
					if(bateau.isThisBateauAtThisPlace(coordonnees)){
						bateau.retirerVie();
						if(!bateau.estCoulé()){
							System.out.println("Touché !");
							ret = CASE_TOUCHE;
						} else {
							System.out.println(bateau.GetNom()+" coulé !");
							ret = BATEAU_COULE;
							
							
						}
					}
				}
				break;
			case 3:
				System.out.println("case déjà visée");
				ret = -1;
				break;

			case 4:
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
		if(convertCharToIndex(coordonnees) < 0 || convertCharToIndex(coordonnees) > 9 || !isNumeric(coordonnees.substring(1))){
			ret[0] = -1;
		}else if(Integer.valueOf(coordonnees.substring(1))-1 > 9 || Integer.valueOf(coordonnees.substring(1))-1 < 0){
			ret[0] = -1;
		} else {
			ret[0] = convertCharToIndex(coordonnees);
			ret[1] = Integer.valueOf(coordonnees.substring(1))-1;
		}
		return ret;
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
}
