package lpacpi.bataille_navale;

import java.util.ArrayList;

public class Board {
	public static int DIMENSION=10;
	private int[][] plateau = new int[DIMENSION][DIMENSION];
	public static int SENS_HORIZONTAL=1;
	public static int SENS_VERTICAL=-1;
	public static int CASE_EAU=1;
	public static int CASE_BATEAU=2;
	public static int CASE_DANS_EAU=3;
	public static int CASE_TOUCHE=4;

	public static int BATEAU_COULE = 0;

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
				} else {
					throw new Exception("Erreur : Case déja occupée");
				}
			} else {
				throw new Exception ("Erreur : Le bateau dépasse du plateau !");
			}
		}
	}

	public String toString(){
		String ret = new String();
		ret += "Etat du board\n";
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

	public int tir(String coordonnées){
		int[]coordonnee=parseStringCoordonnee(coordonnées);
		int ret = 0;
		if(coordonnee[0] == -1 || coordonnee[0]>=DIMENSION || coordonnee[1] >= DIMENSION){
			ret = -1;
		}
		switch(plateau[coordonnee[0]][coordonnee[1]]){
		case 1:
			plateau[coordonnee[0]][coordonnee[1]]=CASE_DANS_EAU;
			ret = plateau[coordonnee[0]][coordonnee[1]];
			break;
		case 2:
			plateau[coordonnee[0]][coordonnee[1]]=CASE_TOUCHE;
			for(Bateau bateau : listBateaux){
				if(bateau.isThisBateauAtThisPlace(coordonnées)){
					System.out.println(bateau.retirerVie());
					if(!bateau.estCoulé()){
						ret = plateau[coordonnee[0]][coordonnee[1]];
					} else {
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
		return ret;
	}
	public static int[] parseStringCoordonnee(String coordonnées){
		int[] ret = new int[2];
		if(convertCharToIndex(coordonnées) < 0 || convertCharToIndex(coordonnées) > 9 || convertCharToIndex(coordonnées)< 0|| convertCharToIndex(coordonnées) > 9){
			ret[0] = -1;
		} else {
			ret[0] = convertCharToIndex(coordonnées);
			ret[1] = Integer.valueOf(coordonnées.substring(1))-1;
		}
		return ret;
	}
	
	private static int convertCharToIndex(String coordonnées) {
		return (int)coordonnées.toUpperCase().charAt(0)-65;
	}
}
