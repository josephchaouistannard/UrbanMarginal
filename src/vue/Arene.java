package vue;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Arene extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtChat;


	/**
	 * Create the frame.
	 */
	public Arene() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 804);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().setPreferredSize(new Dimension(800, 1000));
		this.pack();
		this.setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 800, 600);
		lblNewLabel.setIcon(new ImageIcon(Arene.class.getResource("/fonds/fondarene.jpg")));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPaneChat = new JScrollPane();
		scrollPaneChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneChat.setBounds(0, 600, 800, 400);
		contentPane.add(scrollPaneChat);
		
		JTextArea txtAreaChat = new JTextArea();
		txtAreaChat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneChat.setViewportView(txtAreaChat);
		
		txtChat = new JTextField();
		txtChat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneChat.setColumnHeaderView(txtChat);
		txtChat.setColumns(10);
	}
}
