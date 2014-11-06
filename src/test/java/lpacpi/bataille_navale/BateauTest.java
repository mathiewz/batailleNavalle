package lpacpi.bataille_navale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BateauTest {


	@Test
	public void testTaille() {
		Bateau bateauTestPetit = new Bateau(2, "barque", 0, 0, Board.SENS_HORIZONTAL);
		Bateau bateauTestGrand = new Bateau(5, "paquebot", 3, 3, Board.SENS_VERTICAL);
		assertEquals(2, bateauTestPetit.GetTaille());
		assertEquals(5, bateauTestGrand.GetTaille());
	}
	@Test
	public void testNom() {
		Bateau bateauTestPetit = new Bateau(2, "barque", 0, 0, Board.SENS_HORIZONTAL);
		Bateau bateauTestGrand = new Bateau(5, "paquebot", 3, 3, Board.SENS_VERTICAL);
		assertEquals("barque", bateauTestPetit.GetNom());
		assertEquals("paquebot", bateauTestGrand.GetNom());
	}
	@Test
	public void testGetX() {
		Bateau bateauTestPetit = new Bateau(2, "barque", 0, 0, Board.SENS_HORIZONTAL);
		Bateau bateauTestGrand = new Bateau(5, "paquebot", 3, 3, Board.SENS_VERTICAL);
		assertEquals(0, bateauTestPetit.getX());
		assertEquals(3, bateauTestGrand.getX());
	}
	@Test
	public void testGetY() {
		Bateau bateauTestPetit = new Bateau(2, "barque", 0, 0, Board.SENS_HORIZONTAL);
		Bateau bateauTestGrand = new Bateau(5, "paquebot", 3, 3, Board.SENS_VERTICAL);
		assertEquals(0, bateauTestPetit.getY());
		assertEquals(3, bateauTestGrand.getY());
	}
	@Test
	public void testSens() {
		Bateau bateauTestPetit = new Bateau(2, "barque", 0, 0, Board.SENS_HORIZONTAL);
		Bateau bateauTestGrand = new Bateau(5, "paquebot", 3, 3, Board.SENS_VERTICAL);
		assertEquals(Board.SENS_HORIZONTAL, bateauTestPetit.getSens());
		assertEquals(Board.SENS_VERTICAL, bateauTestGrand.getSens());
	}
	@Test
	public void testBateauACetEndroit() {
		Bateau bateauTestPetit = new Bateau(2, "barque", 0, 0, Board.SENS_HORIZONTAL);
		Bateau bateauTestGrand = new Bateau(5, "paquebot", 3, 3, Board.SENS_VERTICAL);
		assertTrue(bateauTestPetit.isThisBateauAtThisPlace("a1"));
		assertFalse(bateauTestPetit.isThisBateauAtThisPlace("d2"));
		assertTrue(bateauTestGrand.isThisBateauAtThisPlace("d4"));
		assertFalse(bateauTestGrand.isThisBateauAtThisPlace("a1"));
	}
	@Test
	public void testVie() {
		Bateau bateauTestPetit = new Bateau(2, "barque", 0, 0, Board.SENS_HORIZONTAL);
		Bateau bateauTestGrand = new Bateau(5, "paquebot", 3, 3, Board.SENS_VERTICAL);
		assertEquals(1, bateauTestPetit.retirerVie());
		assertEquals(0, bateauTestPetit.retirerVie());
		assertEquals(4, bateauTestGrand.retirerVie());
	}
	@Test
	public void testCoule() {
		Bateau bateauTestPetit = new Bateau(2, "barque", 0, 0, Board.SENS_HORIZONTAL);
		Bateau bateauTestGrand = new Bateau(5, "paquebot", 3, 3, Board.SENS_VERTICAL);
		assertFalse(bateauTestPetit.estCoule());
		bateauTestPetit.retirerVie();
		bateauTestPetit.retirerVie();
		assertTrue(bateauTestPetit.estCoule());
		
		assertFalse(bateauTestGrand.estCoule());
		bateauTestPetit.retirerVie();
		assertFalse(bateauTestGrand.estCoule());
		bateauTestPetit.retirerVie();
		assertFalse(bateauTestGrand.estCoule());
		bateauTestPetit.retirerVie();
		assertFalse(bateauTestGrand.estCoule());
	}

}
