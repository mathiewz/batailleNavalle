package lpacpi.bataille_navale;

public class Bateau {
		
	private int taille;
	private String nom;
	
	public Bateau(int uneTaille, String unNom)
	{
		taille = uneTaille;
		nom = unNom;
		
	}
	 public int GetTaille(){
		 return taille;
	 }
	 
	 public String GetNom(){
		 return nom;
	 }
	 
	 public void SetTaille(int uneTaille)
	  {
		 taille = uneTaille;
	  }
	 
	 public void SetNom(String unNom)
	  {
		 nom = unNom;

	  }

	 public String toString(String nom, int taille)
	 {
		 String toString = "nom du bateau :"+ nom +"taille du bateau"+taille;	
		 return toString;	
	 }
	 

	
	
} 