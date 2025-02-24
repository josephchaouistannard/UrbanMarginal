package modele;

import java.util.Collection;

import javax.swing.JLabel;

/**
 * Informations communes à tous les objets (joueurs, murs, boules)
 * permet de mémoriser la position de l'objet et de gérer les  collisions
 *
 */
public abstract class Objet {

	/**
	 * position X de l'objet
	 */
	protected Integer posX ;
	/**
	 * position Y de l'objet
	 */
	protected Integer posY ;
	/**
	 * label contenant l'objet graphique (personnage, mur, boule)
	 */
	protected JLabel jLabel;
	
	/**
	 * Getter pour le jlabel d'un objet de type Objet
	 * @return the jLabel
	 */
	public JLabel getjLabel() {
		return jLabel;
	}

	/**
	 * contrôle si l'objet actuel touche l'objet passé en paramètre
	 * @param objet contient l'objet à contrôler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet (Objet objet) {
		if (objet.jLabel==null || objet.jLabel==null) {
			return false ;
		}else{
			return(this.posX+this.jLabel.getWidth()>objet.posX &&
				this.posX<objet.posX+objet.jLabel.getWidth() && 
				this.posY+this.jLabel.getHeight()>objet.posY &&
				this.posY<objet.posY+objet.jLabel.getHeight()) ;
		}
	}
	
	/**
	 * Contrôle si le joueur touche un des objets dans une collection
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @return true si l'objet touche une autre
	 */
	public Boolean toucheCollectionObjets(Collection<Objet> lesObjets) {
		for(Objet objet : lesObjets) {
			if(!this.equals(objet)) {
				if(this.toucheObjet(objet)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Contrôle si le joueur touche un des objets dans une collection
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @return true si l'objet touche une autre
	 */
	public Objet getObjetTouche(Collection<Objet> lesObjets) {
		for(Objet objet : lesObjets) {
			if(!this.equals(objet)) {
				if(this.toucheObjet(objet)) {
					return objet;
				}
			}
		}
		return null;
	}
}
