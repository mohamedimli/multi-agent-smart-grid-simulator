package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Classe : Apropos
 * Description : Fenetre A propos de l'application
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */
public class Apropos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4682498810305565961L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apropos frame = new Apropos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Apropos() {
		setTitle("SMA Smart Girds : A propos ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Simulateur Multi-Agents pour smart Grids");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		JLabel lblNewLabel_1 = new JLabel("Ce logiciel est con\u00E7u par :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);
		
		JLabel lblMohamedImli = new JLabel("Mohamed IMLI");
		lblMohamedImli.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblMohamedImli.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMohamedImli);
		
		JLabel lblNewLabel_2 = new JLabel("Sihame AARAB");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Encadr\u00E9 par Mr :");
		panel.add(lblNewLabel_3);
		
		JLabel lblNacimBelkhir = new JLabel("Nacim Belkhir");
		lblNacimBelkhir.setHorizontalAlignment(SwingConstants.CENTER);
		lblNacimBelkhir.setFont(new Font("Tahoma", Font.ITALIC, 14));
		panel.add(lblNacimBelkhir);
		
		JLabel lblUniversitParisDescartes = new JLabel("Universit\u00E9 Paris Descartes");
		lblUniversitParisDescartes.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblUniversitParisDescartes.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblUniversitParisDescartes);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		contentPane.add(btnOk, BorderLayout.SOUTH);
	}

}
