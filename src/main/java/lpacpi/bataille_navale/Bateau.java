package lpacpi.bataille_navale;

public class Bateau {
		
	private int taille;
	private String nom;
	private int posX;
	private int posY;
	private int sens;
	private static int nbCasesPasTouchees;
	
	public Bateau(int uneTaille, String unNom, int posX, int posY, int sens)
	{
		taille = uneTaille;
		nbCasesPasTouchees = uneTaille;
		nom = unNom;
		this.posX = posX;
		this.posY = posY;
		this.sens = sens;
	}
	 public int GetTaille(){
		 return taille;
	 }
	 
	 public String GetNom(){
		 return nom;
	 }
	 
	 public int getX(){
		 return this.posX;
	 }
	 
	 public int getY(){
		 return this.posY;
	 }
	 
	 public int getSens(){
		 return this.sens;
	 }
	 
	 public String toString(String nom, int taille)
	 {
		 String toString = "nom du bateau :"+ nom +"taille du bateau"+taille;	
		 return toString;	
	 }
	 
	 public boolean isThisBateauAtThisPlace(String coordoneesTxt){
		 boolean ret = false;
		 int[] coordonees = Board.parseStringCoordonnee(coordoneesTxt);
		 for(int i=0;i<taille;i++){
			 if(sens == Board.SENS_HORIZONTAL){
				 if(coordonees[0] == posX+i && coordonees[1] == posY){
					 ret = true;
				 }
			 } else if(sens == Board.SENS_VERTICAL) {
				 if(coordonees[0] == posX && coordonees[1] == posY+i){
					 ret = true;
				 }
			 }
		 }
		 return ret;
	 }
	 
	 public int retirerVie(){
		 nbCasesPasTouchees--;
		 return nbCasesPasTouchees;
	 }
	 
	 public static boolean estCoulé(){
		 return (nbCasesPasTouchees == 0);
	 }
	

	 
} 