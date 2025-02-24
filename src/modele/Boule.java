package modele;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Global;

/**
 * Gestion de la boule
 *
 */
public class Boule extends Objet implements Global, Runnable {

	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur ;
	
	/**
	 * collection des murs
	 */
	private Collection<Mur> lesMurs;
	
	/**
	 * L'attaquant qui tire la boule
	 */
	private Joueur attaquant;
	
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur server) {
		this.jeuServeur = server;
		super.jLabel = new JLabel();
		super.jLabel.setVisible(false);
		URL resource = getClass().getClassLoader().getResource(BOULE);
		super.jLabel.setIcon(new ImageIcon(resource));
		super.jLabel.setBounds(0, 0, LARGEURBOULE, HAUTEURBOULE);
		
	}
	
	/**
	 * Tire d'une boule
	 * @param lesMurs 
	 * @param joueur 
	 */
	public void tireBoule(Joueur joueur, ArrayList<Mur> lesMurs) {
		this.attaquant = joueur;
		this.lesMurs = lesMurs;
		this.posY = Math.round(this.attaquant.posY + ((HAUTEURPERSO - HAUTEURBOULE) / 2));
		if (this.attaquant.getOrientation() == GAUCHE) {
			this.posX = attaquant.posX - LARGEURBOULE - 1;
		} else {
			this.posX = attaquant.posX + LARGEURPERSO + LARGEURBOULE + 1;
		}
		new Thread(this).start();
		
	}
	
	/**
	 * Thread independent une fois une boule lancé
	 */
	public void run() {
		this.attaquant.setEtape(1);
		super.jLabel.setVisible(true);
		Joueur cible = null;
		int lePas;
		if (this.attaquant.getOrientation() == GAUCHE) {
			lePas = -PAS;
		} else {
			lePas = PAS;
		}
		do {
			this.posX += lePas;
			super.jLabel.setBounds(this.posX, this.posY, LARGEURBOULE, HAUTEURBOULE);
			this.jeuServeur.envoiJeuATous();
			ArrayList<Objet> combinedCollection = this.jeuServeur.getMursEtJoueurs();
			if (this.toucheCollectionObjets(combinedCollection) && this.getObjetTouche(combinedCollection) instanceof Joueur) {
				cible = (Joueur)this.getObjetTouche(combinedCollection);
				break;
			}
			pause(10, 0);
		} while (this.posX > 0 && this.posX < LARGEURARENE && !this.toucheCollectionObjets(this.jeuServeur.getMursEtJoueurs()));
		if (cible != null && !cible.estMort()) {
			cible.perteVie();
			attaquant.gainVie();
			for (int i = 1; i < 3; i++) {
				cible.affiche(TOUCHE, i);
				pause(80, 0);
			}
			if (cible.estMort()) {
				for (int i = 1; i < 3; i++) {
					cible.affiche(MORT, i);
					pause(80, 0);
				}
			} else {
				cible.affiche(MARCHE, 1);
			}
		}
		super.jLabel.setVisible(false);
		this.jeuServeur.envoiJeuATous();
	}
	
	public void pause(long milli, int nano) {
		try {
			Thread.sleep(milli, nano);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
