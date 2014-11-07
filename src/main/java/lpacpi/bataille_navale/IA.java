package lpacpi.bataille_navale;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


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
			String filePath = "./bateaux.txt";
			Scanner scanner=new Scanner(new File(filePath));

			while (scanner.hasNextLine()) {
			    String line = scanner.nextLine();
				boolean isBateauPlace = false;
				do{
					int[] coordonee;
					int sens = -1;
					String err = "";
					coordonee = Board.parseStringCoordonnee(generateRandomCordonees());
					if(coordonee[0] != -1){
						int lower = 1;
						int higher = 3;
						sens = (int)(Math.random() * (higher-lower)) + lower;
					}
					int placementErr = this.placerBateau(new Bateau(Integer.valueOf(line.substring(0,1)), line.substring(2), coordonee[0], coordonee[1], sens));
					if(placementErr == -1){
					} else if(placementErr == -2){
					} else {
						isBateauPlace = true;
					}
				}while(!isBateauPlace);
			}
			scanner.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	



}
