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
					if(plateau[coordonneeY][coordonneeX+n]==CASE_BATEAU){
						onPeutPlacer = false;
					}
				}
				if(onPeutPlacer){
					for(int n = 0; n < bateau.GetTaille(); n++){
						plateau[coordonneeY][coordonneeX+n]=CASE_BATEAU;
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
					if(plateau[coordonneeY+n][coordonneeX]==CASE_BATEAU){
						onPeutPlacer = false;
					}
				}
				if(onPeutPlacer){
					for(int n = 0; n < bateau.GetTaille(); n++){
						plateau[coordonneeY+n][coordonneeX]=CASE_BATEAU;
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
		ret += "  A B C D E F G H I J\n";
		for(int i=0; i<DIMENSION;i++){
			ret += "  ------------------- \n"+(i+1)+"|";
			for(int j=0; j<DIMENSION;j++){
				ret+= plateau[i][j]+"|";
			}
			ret += "\n";
		}
		ret += " ------------------- ";
		return ret;
	}
}
