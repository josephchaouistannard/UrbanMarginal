package modele;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {

	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n° correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * message qui s'affiche sous le personnage (contenant pseudo et vie)
	 */
	private JLabel message;
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * numéro d'étape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * vie restante du joueur
	 */
	private int vie ; 
	/**
	 * tourné vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation ;
	
	/**
	 * Constructeur : récupératon de jeuServeur et initialisaton de certaines propriétés
	 * @param jeuServeur instance de JeuServeur pour lui envoyer des informations
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.setEtape(1);
		this.orientation = DROITE;
	}

	/**
	 * Get pseudo d'une instance de Joueur
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position, affichage, création de la boule)
	 * @param pseudo pseudo du joueur
	 * @param numPerso numéro du personnage
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @param lesMurs collection contenant les murs
	 */
	public void initPerso(String pseudo, int numPerso, Collection<Joueur>lesJoueurs, ArrayList<Mur> lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		this.boule = new Boule(jeuServeur);
		System.out.println("joueur "+pseudo+" - num perso "+numPerso+" créé");
		// création du label du personnage
		super.jLabel = new JLabel();
		// création du label du message sous le personnage
		this.message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		// calcul de la première position du personnage
		this.premierePosition(lesJoueurs, lesMurs);
		// demande d'ajout du label du personnage et du message dans l'arène du serveur
		this.jeuServeur.ajoutJLabelJeuArene(jLabel);
		this.jeuServeur.ajoutJLabelJeuArene(message);
		this.jeuServeur.ajoutJLabelJeuArene(boule.getjLabel());
		// demande d'affichage du personnage
		this.affiche(MARCHE, this.getEtape());
		
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @param lesMurs collection contenant les murs
	 */
	private void premierePosition(Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		ArrayList<Objet> combinedCollection = new ArrayList<Objet>();
		combinedCollection.addAll(lesJoueurs);
		combinedCollection.addAll(lesMurs);
		jLabel.setBounds(0, 0, LARGEURPERSO, HAUTEURPERSO);
		do {
			posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURPERSO)) ;
			posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE)) ;
		}while(this.toucheCollectionObjets(combinedCollection));
	}
	
	/**
	 * Affiche le personnage et son message
	 * @param etape Etape dans le mouvement du personnage
	 * @param etat etat du personnage : "marche", "touche", "mort"
	 */
	public void affiche(String etat, int etape) {
		// positionnement du personnage et affectation de la bonne image
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = CHEMINPERSONNAGES+PERSO+this.numPerso+etat+etape+"d"+this.getOrientation()+EXTFICHIERPERSO;
		URL resource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(resource));
		// positionnement et remplissage du message sous le perosnnage
		this.message.setBounds(posX-10, posY+HAUTEURPERSO, LARGEURPERSO+10, HAUTEURMESSAGE);
		this.message.setText(pseudo+" : "+vie);
		// demande d'envoi à tous des modifications d'affichage
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tire de boule...)
	 * @param lesMurs 
	 * @param collection 
	 * @param i 
	 */
	public void action(int i, Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		if (!this.estMort()) {
			ArrayList<Objet> combinedCollection = new ArrayList<Objet>();
			combinedCollection.addAll(lesJoueurs);
			combinedCollection.addAll(lesMurs);
			switch (i) {
			case KeyEvent.VK_LEFT:
				if (this.posX - PAS > 0) {
					this.orientation = GAUCHE;
					this.deplace(this.posX - PAS, this.posY, combinedCollection);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (this.posX + PAS < 800 - LARGEURPERSO) {
					this.orientation = DROITE;
					this.deplace(this.posX + PAS, this.posY, combinedCollection);
				}
				break;
			case KeyEvent.VK_UP:
				if (this.posY - PAS > 0) {
					this.deplace(this.posX, this.posY - PAS, combinedCollection);
				}
				break;
			case KeyEvent.VK_DOWN:
				if (this.posY + PAS < 600 - HAUTEURPERSO) {
					this.deplace(this.posX, this.posY + PAS, combinedCollection);
				}
				break;
			case KeyEvent.VK_SPACE:
				if (!this.boule.getjLabel().isVisible()) {
					this.boule.tireBoule(this, lesMurs);
				}
				break;
			}
			this.affiche(MARCHE, this.getEtape());
			this.setEtape(this.getEtape() + 1);
			if (this.getEtape() > 4) {
				this.setEtape(1);
			} 
		}
	}

	/**
	 * Gère le déplacement du personnage
	 */
	private void deplace(int newPosX, int newPosY, Collection<Objet> lesObjets) { 
		int oldPosX = this.posX;
		int oldPosY = this.posY;
		this.posX = newPosX;
		this.posY = newPosY;
		if (this.toucheCollectionObjets(lesObjets))
		{
			this.posX = oldPosX;
			this.posY = oldPosY;
		}
	}
	
	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
		this.vie += GAIN;
		if (this.vie > MAXVIE) {
			this.vie = MAXVIE;
		}
	}
	
	/**
	 * Perte de points de vie après avoir été touché 
	 */
	public void perteVie() {
		this.vie -= PERTE;
		if (this.vie < 0) {
			this.vie = 0;
		}
	}
	
	/**
	 * vrai si la vie est à 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return (this.vie == 0);
	}
	
	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
	}

	public int getOrientation() {
		return orientation;
	}

	public int getEtape() {
		return etape;
	}

	public void setEtape(int etape) {
		this.etape = etape;
	}
	
}
