package vue;

import java.awt.Cursor;
import java.awt.Dimension;
import controleur.Controle;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class ChoixJoueur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPseudo;
	private JLabel lblPersonnage;
	private Arene frmArene;
	private Controle controle;
	private int num_perso;
	private static int nbPersonnages = 3;
	
	private void btnFlecheGauche_Click() {
		num_perso--;
		if (num_perso < 1) {
			num_perso = nbPersonnages;
		}
		affichePerso();
	}
	private void btnFlecheDroite_Click() {
		num_perso++;
		if (num_perso > nbPersonnages) {
			num_perso = 1;
		}
		affichePerso();
		
	}
	private void btnGo_Click() {
		if (txtPseudo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
			txtPseudo.requestFocusInWindow();
		}
		else {
			this.controle.evenementChoixJoueur(txtPseudo.getText(), num_perso);
		}
	}
	private void affichePerso() {
		lblPersonnage.setIcon(new ImageIcon(ChoixJoueur.class.getResource("/personnages/perso"+Integer.toString(num_perso)+"marche1d1.gif")));
	}
	private void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	private void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		this.setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.lblPersonnage = new JLabel("");
		this.lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblPersonnage.setBounds(144, 114, 117, 123);
		contentPane.add(this.lblPersonnage);
		
		JLabel lblFlecheGauche = new JLabel("");
		lblFlecheGauche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnFlecheGauche_Click();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		lblFlecheGauche.setBounds(59, 143, 46, 46);
		contentPane.add(lblFlecheGauche);
		
		JLabel lblFlecheDroite = new JLabel("");
		lblFlecheDroite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnFlecheDroite_Click();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		lblFlecheDroite.setBounds(288, 143, 46, 46);
		contentPane.add(lblFlecheDroite);
		
		txtPseudo = new JTextField();
		txtPseudo.setHorizontalAlignment(SwingConstants.CENTER);
		txtPseudo.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPseudo.setBounds(144, 245, 117, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		txtPseudo.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel btnGo = new JLabel("");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnGo_Click();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		btnGo.setBounds(311, 200, 62, 65);
		contentPane.add(btnGo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setIcon(new ImageIcon(ChoixJoueur.class.getResource("/fonds/fondchoix.jpg")));
		lblNewLabel.setBounds(0, 0, 400, 275);
		contentPane.add(lblNewLabel);
		
		this.controle = controle;
		this.num_perso = 1;
		affichePerso();
	}

}
