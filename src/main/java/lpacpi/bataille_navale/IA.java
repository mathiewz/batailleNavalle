package lpacpi.bataille_navale;

import java.util.ArrayList;


public class IA extends Board{

	public IA(){
		for(int i=0; i<DIMENSION;i++){
			for(int j=0; j<DIMENSION;j++){
				plateau[i][j] = CASE_EAU;
			}
		}
		listBateaux = new ArrayList<Bateau>();
		this.initialiserBateaux();
	}


	public void initialiserBateaux(){
		try {
			String[] nomBateau=new String[]{"torpilleur","sous-marin","contre-torpilleur","croiseur","porte-avions"};
			int[] dimBateau=new int[]{2,3,3,4,5};
			for(int i=0;i<5;i++){
				boolean isBateauPlace = false;
				do{
					int[] coordonee;
					int sens = -1;
					String err;
					err = "";
					coordonee = Board.parseStringCoordonnee(generateRandomCordonees());
					if(coordonee[0] != -1){
						int lower = 1;
						int higher = 3;
						sens = (int)(Math.random() * (higher-lower)) + lower;
					}
					int placementErr = this.placerBateau(new Bateau(dimBateau[i], nomBateau[i], coordonee[0], coordonee[1], sens));
					if(placementErr == -1){
					} else if(placementErr == -2){
					} else {
						isBateauPlace = true;
					}
				}while(!isBateauPlace);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	



}
