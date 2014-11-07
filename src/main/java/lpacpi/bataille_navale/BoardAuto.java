package lpacpi.bataille_navale;

import java.util.ArrayList;

public class BoardAuto extends Board {
	public BoardAuto(){
		for(int i=0; i<DIMENSION;i++){
			for(int j=0; j<DIMENSION;j++){
				plateau[i][j] = CASE_EAU;
			}
		}
		listBateaux = new ArrayList<Bateau>();
		initialiserBateaux();
	}

	public void initialiserBateaux(){
		try {
			placerBateau(new Bateau(2, "catamaran", 0, 0, SENS_VERTICAL));
			placerBateau(new Bateau(5, "pedalo", 1, 0, SENS_VERTICAL));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
