package controleur;

import vue.*;
import outils.connexion.*;

public class Controle implements AsyncResponse {
	private EntreeJeu frmEntreeJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private ServeurSocket server;
	private ClientSocket client;
	private String typeJeu;
	private static int port = 6666;
    
    /**
     * Constructeur
     */
    private Controle() {
        this.frmEntreeJeu = new EntreeJeu(this);
        this.frmEntreeJeu.setVisible(true);
    }
    
    public void evenementEntreeJeu(String info) {
    	if (info.equals("serveur")) {
    		this.frmArene = new Arene();
    		this.frmArene.setVisible(true);
    		this.typeJeu = "serveur";
    		this.server = new ServeurSocket(this, port);
    	}
    	else {
    		this.typeJeu = "client";
    		this.client = new ClientSocket(this, info, port);
    	}
    	this.frmEntreeJeu.dispose();
    }

    /**
     * Méthode de démarrage
     * @param args non utilisé
     */
    public static void main(String[] args) {
        new Controle();  // Create a new Controle instance to start the application
    }

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
			case "connexion":
				if (this.typeJeu.equals("client")) {
		    		this.frmChoixJoueur = new ChoixJoueur();
		    		this.frmChoixJoueur.setVisible(true);
					this.frmArene = new Arene();
				}
				break;
			case "déconnexion":
				break;
			case "réception":
				break;
		}
		
	}
}
