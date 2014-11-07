package lpacpi.bataille_navale;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class IA extends Board{

	public IA(){
		plateau = new int[DIMENSION][DIMENSION];
		for(int i=0; i<DIMENSION;i++){
			for(int j=0; j<DIMENSION;j++){
				plateau[i][j] = CASE_EAU;
			}
		}
		listBateaux = new ArrayList<Bateau>();
		this.initialiserBateaux();
	}


	public IA(int size) {
		DIMENSION = size;
		plateau = new int[DIMENSION][DIMENSION];
		for(int i=0; i<DIMENSION;i++){
			for(int j=0; j<DIMENSION;j++){
				plateau[i][j] = CASE_EAU;
			}
		}
		listBateaux = new ArrayList<Bateau>();
		this.initialiserBateaux();
	}


	public void initialiserBateaux(){
		placementAutoBateaux();
	}
	



}
