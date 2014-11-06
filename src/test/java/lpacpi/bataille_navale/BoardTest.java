package lpacpi.bataille_navale;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testPlacerBateau() {
		BoardAuto board = new BoardAuto();
		try{
			assertEquals(-1, board.placerBateau(new Bateau(1, "Ikea", 0, 0, Board.SENS_HORIZONTAL)));
			assertEquals(-2, board.placerBateau(new Bateau(6, "FarFarAway", 9, 0, Board.SENS_HORIZONTAL)));
			assertEquals(-2, board.placerBateau(new Bateau(5, "ForForLointain", 0, 9, Board.SENS_VERTICAL)));
			assertEquals(0, board.placerBateau(new Bateau(1, "crash test", 4, 5, Board.SENS_HORIZONTAL)));
			assertEquals(0, board.placerBateau(new Bateau(1, "Yelosubmarinn", 5, 4, Board.SENS_VERTICAL)));
			assertEquals(-1, board.placerBateau(new Bateau(1, "Snorlax", 0, 0, Board.SENS_VERTICAL)));
		} catch(Exception e){
			e.printStackTrace();
		}

	}
	@Test
	public void testTir() {
		BoardAuto board = new BoardAuto();
		assertEquals(-1, board.tir("K1"));
		assertEquals(-1, board.tir("A11"));
		assertEquals(Board.CASE_DANS_EAU, board.tir("A10"));
		assertEquals(Board.CASE_TOUCHE, board.tir("A1"));
		assertEquals(Board.BATEAU_COULE, board.tir("A2"));
		assertEquals(-1, board.tir("A10"));
		assertEquals(-1, board.tir("A1"));
	}
	@Test
	public void testParseStringCoordonnees() {
		assertEquals(-1,Board.parseStringCoordonnee("")[0]);
		assertEquals(-1,Board.parseStringCoordonnee("K1")[0]);
		assertEquals(-1,Board.parseStringCoordonnee("1A")[0]);
		assertEquals(-1,Board.parseStringCoordonnee("A11")[0]);
		assertEquals(-1,Board.parseStringCoordonnee("A0")[0]);
		assertEquals(0,Board.parseStringCoordonnee("A1")[0]);
		assertEquals(0,Board.parseStringCoordonnee("A1")[1]);
		assertEquals(9,Board.parseStringCoordonnee("J10")[0]);
		assertEquals(9,Board.parseStringCoordonnee("J10")[1]);
	}

	@Test
	public void testIsNumeric() {
		String[] StringsDontEntiers = new String[]{"A","B","C18","Coucou les gens","Z","1","2","-314","4","9000"};
		for (int i=0;i<5;i++){
			assertFalse(Board.isNumeric(StringsDontEntiers[i]));
		}
		for (int i=5;i<10;i++){
			assertTrue(Board.isNumeric(StringsDontEntiers[i]));
		}
	}
	@Test
	public void testIsBoardGameOver() {
		BoardAuto board = new BoardAuto();
		assertFalse(board.isBoardGameOver());
		for(int cible=1 ; cible<3 ; cible++){
			board.tir("a"+cible);
		}
		for(int cible=1 ; cible<6 ; cible++){
			board.tir("b"+cible);
		}
		assertTrue(board.isBoardGameOver());
	}

	@Test
	public void testTirIA() {
		BoardAuto board = new BoardAuto();
		for(int i= 0; i<100;i++){
			int resultatTir=(board.tirIA());
			assertTrue(resultatTir==-1||resultatTir==Board.CASE_DANS_EAU||resultatTir==Board.CASE_TOUCHE||resultatTir==Board.BATEAU_COULE);
		}
	}

	@Test
	public void testGenerateRandomCoordonnees() {
		BoardAuto board = new BoardAuto();
		for(int i= 0; i<100;i++){
			String str= board.generateRandomCordonees();
			String[] tabStringString = new String[]{"A","B","C","D","E","F","G","H","I","J"};
			assertTrue(Arrays.binarySearch(tabStringString,str.substring(0, 1))>=0);
			String[] tabStringInt = new String[]{"1","2","3","4","5","6","7","8","9","10"};
			assertTrue(Arrays.binarySearch(tabStringInt,str.substring(1))>=0);
		}
	}
}
