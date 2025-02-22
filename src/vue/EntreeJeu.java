package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class EntreeJeu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIP;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	
	/*
	 * Ouvrir nouvelle arene
	 */
	private void btnStart_Click() {
		this.frmArene = new Arene();
		this.frmArene.setVisible(true);
		this.dispose();
	}
	
	/*
	 * Ouvrir choix joueur
	 */
	private void btnConnect_Click() {
		this.frmChoixJoueur = new ChoixJoueur();
		this.frmChoixJoueur.setVisible(true);
		this.dispose();
	}
	


	/**
	 * Create the frame.
	 */
	public EntreeJeu() {
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 213);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart_Click();
			}
		});
		btnStart.setBounds(220, 9, 110, 35);
		contentPane.add(btnStart);
		
		JLabel lblNewLabel = new JLabel("Start a server :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 14, 200, 24);
		contentPane.add(lblNewLabel);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnect_Click();
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConnect.setBounds(220, 83, 110, 35);
		contentPane.add(btnConnect);
		
		JLabel lblConnectToAn = new JLabel("Connect to an existing server :");
		lblConnectToAn.setHorizontalAlignment(SwingConstants.LEFT);
		lblConnectToAn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConnectToAn.setBounds(10, 53, 225, 24);
		contentPane.add(lblConnectToAn);
		
		JLabel lblIpServer = new JLabel("IP Server :");
		lblIpServer.setHorizontalAlignment(SwingConstants.LEFT);
		lblIpServer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIpServer.setBounds(10, 88, 79, 24);
		contentPane.add(lblIpServer);
		
		txtIP = new JTextField();
		txtIP.setText("127.0.0.1");
		txtIP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIP.setBounds(98, 88, 110, 24);
		contentPane.add(txtIP);
		txtIP.setColumns(10);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(220, 129, 110, 35);
		contentPane.add(btnExit);
	}
}
