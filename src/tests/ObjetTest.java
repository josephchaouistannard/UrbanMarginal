package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controleur.Global;
import modele.Mur;
import modele.Objet;

class ObjetTest implements Global {
	private Mur objet1;
	private Mur objet2;

	@Test
	void testToucheObjetGauche() {
		objet1 = new Mur();
		objet1.setPosX(100);
		objet1.setPosY(100);
		objet2 = new Mur();
		objet2.setPosX(80);
		objet2.setPosY(100);
		assertEquals(true, objet1.toucheObjet(objet2));
	}
	
	@Test
	void testToucheObjetDroite() {
		objet1 = new Mur();
		objet1.setPosX(100);
		objet1.setPosY(100);
		objet2 = new Mur();
		objet2.setPosX(120);
		objet2.setPosY(100);
		assertEquals(true, objet1.toucheObjet(objet2));
	}
	
	@Test
	void testToucheObjetHaut() {
		objet1 = new Mur();
		objet1.setPosX(100);
		objet1.setPosY(100);
		objet2 = new Mur();
		objet2.setPosX(100);
		objet2.setPosY(80);
		assertEquals(true, objet1.toucheObjet(objet2));
	}

	@Test
	void testToucheObjetBas() {
		objet1 = new Mur();
		objet1.setPosX(100);
		objet1.setPosY(100);
		objet2 = new Mur();
		objet2.setPosX(100);
		objet2.setPosY(120);
		assertEquals(true, objet1.toucheObjet(objet2));
	}
	
	@Test
	void testToucheObjetGauchePAS() {
		objet1 = new Mur();
		objet1.setPosX(100);
		objet1.setPosY(100);
		objet2 = new Mur();
		objet2.setPosX(60);
		objet2.setPosY(100);
		assertEquals(false, objet1.toucheObjet(objet2));
	}
	
	@Test
	void testToucheObjetDroitePAS() {
		objet1 = new Mur();
		objet1.setPosX(100);
		objet1.setPosY(100);
		objet2 = new Mur();
		objet2.setPosX(140);
		objet2.setPosY(100);
		assertEquals(false, objet1.toucheObjet(objet2));
	}
	
	@Test
	void testToucheObjetHautPAS() {
		objet1 = new Mur();
		objet1.setPosX(100);
		objet1.setPosY(100);
		objet2 = new Mur();
		objet2.setPosX(100);
		objet2.setPosY(60);
		assertEquals(false, objet1.toucheObjet(objet2));
	}

	@Test
	void testToucheObjetBasPAS() {
		objet1 = new Mur();
		objet1.setPosX(100);
		objet1.setPosY(100);
		objet2 = new Mur();
		objet2.setPosX(100);
		objet2.setPosY(140);
		assertEquals(false, objet1.toucheObjet(objet2));
	}

}
