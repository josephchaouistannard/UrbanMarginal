package vue;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
	private Arene frmArene;
	
	private void btnFlecheGauche_Click() {
		System.out.println("back");
	}
	private void btnFlecheDroite_Click() {
		System.out.println("forward");
	}
	private void btnGo_Click() {
		this.frmArene = new Arene();
		this.frmArene.setVisible(true);
		this.dispose();
	}

	/**
	 * Create the frame.
	 */
	public ChoixJoueur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		this.setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFlecheGauche = new JLabel("");
		lblFlecheGauche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnFlecheGauche_Click();
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
		});
		btnGo.setBounds(311, 200, 62, 65);
		contentPane.add(btnGo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setIcon(new ImageIcon(ChoixJoueur.class.getResource("/fonds/fondchoix.jpg")));
		lblNewLabel.setBounds(0, 0, 400, 275);
		contentPane.add(lblNewLabel);
	}

}
