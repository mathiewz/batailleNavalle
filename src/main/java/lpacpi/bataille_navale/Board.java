package lpacpi.bataille_navale;

public class Board {
	public static int DIMENSION=10;
	private int[][] plateau = new int[DIMENSION][DIMENSION];
	public static int SENS_HORIZONTAL=1;
	public static int SENS_VERTICAL=-1;
	public static int CASE_EAU=1;
	public static int CASE_BATEAU=2;
	public static int CASE_DANS_EAU=3;
	public static int CASE_TOUCHE=4;

	public Board()	{
		for(int i=0; i<DIMENSION;i++){
			for(int j=0; j<DIMENSION;j++){
				plateau[i][j] = CASE_EAU;
			}
		}
	}
	public void placerBateau(int coordonneeX,int coordonneeY,Bateau bateau,int sens) throws Exception
	{
		if(sens==SENS_HORIZONTAL){
			if(coordonneeX+bateau.GetTaille()<DIMENSION) {
				boolean onPeutPlacer = true;
				for(int n = 0; n < bateau.GetTaille(); n++){
					if(plateau[coordonneeX+n][coordonneeY]==CASE_BATEAU){
						onPeutPlacer = false;
					}
				}
				if(onPeutPlacer){
					for(int n = 0; n < bateau.GetTaille(); n++){
						plateau[coordonneeX+n][coordonneeY]=CASE_BATEAU;
					}
				} else {
					throw new Exception("Erreur : Case déja occupée");
				}
			} else {
				throw new Exception ("Erreur : Le bateau dépasse du plateau !");
			}

		}

		if(sens==SENS_VERTICAL){
			if(coordonneeY+bateau.GetTaille()<DIMENSION) {
				boolean onPeutPlacer = true;
				for(int n = 0; n < bateau.GetTaille(); n++){
					if(plateau[coordonneeX][coordonneeY+n]==CASE_BATEAU){
						onPeutPlacer = false;
					}
				}
				if(onPeutPlacer){
					for(int n = 0; n < bateau.GetTaille(); n++){
						plateau[coordonneeX][coordonneeY+n]=CASE_BATEAU;
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
		for(int i=0; i<DIMENSION;i++){
			ret += " ------------------- ";
			for(int j=0; j<DIMENSION;j++){
				ret+= plateau[i][j]+"|";
			}
			ret += "\n";
		}
		ret += " ------------------- ";
		return ret;
	}
	public int tir(String coordonnées){
		int[]coordonnee=parseStringCoordonnee(coordonnées);
	
		
				switch(plateau[coordonnee[0]][coordonnee[1]]){
		    	case 1:
		    		plateau[coordonnee[0]][coordonnee[1]]=CASE_DANS_EAU;
		    		return plateau[coordonnee[0]][coordonnee[1]];
		    	
		    	case 2:
		    		plateau[coordonnee[0]][coordonnee[1]]=CASE_TOUCHE;
		    		return plateau[coordonnee[0]][coordonnee[1]];
		    	case 3:
		    		System.out.println("case déjà visée");
		    		return -1;
		    		
		    	
		    	case 4:
		    		System.out.println("case déjà visée");
		    		return -1;
		    	
		    	default:
		            System.out.println("ERREUR");
		            return -1;
		       
			
	    
				}
	}
	private int[] parseStringCoordonnee(String coordonnées) {
		
		return null;
	}
}
