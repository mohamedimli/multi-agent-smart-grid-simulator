package interfaceGraphique;

import interfaceGraphique.agents.AgentInterfaceGraphique;
import jade.gui.GuiEvent;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import utilitaires.Preferences;

/**
 * Classe : Principale
 * Description : Fenetre principale de l'application
 * @author Mohamed IMLI - Sihame AARAB
 * @version 1.0
 * 
 */

public class Principale extends JFrame {

	private static final long serialVersionUID = -8485784007815369135L;
	private JPanel contentPane;
	private JPanel graphePane;
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenuItem mntmQuitter;
	private JMenu mnAide;
	private JMenuItem mntmAPropos;
	private JPanel panelGauche;
	private JLabel lblNombreDeDistributeurs;
	private JSlider slider;
	private JLabel lblNombreDeTransformateurs;
	private JSlider slider_1;
	private JLabel lblNombreDeFoyers;
	private JSlider slider_2;
	private JButton btnLancerSimulation;
	private JPanel panelCentre;
	private Component verticalStrut;
	private JLabel lblNewLabel;
	private JSlider slider_3;
	private JPanel panelHeader;
	private JLabel lblNewLabel_1;
	
	private JLabel lblParamtresDeLa;
	
	//
	private AgentInterfaceGraphique monAgent;



	/**
	 * Create the frame.
	 */
	public Principale(AgentInterfaceGraphique agentInterfaceGraphique) {
		//
		this.monAgent=agentInterfaceGraphique;
		//
		setTitle("Simulateur Multi-Agents pour Smart Grids");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mntmQuitter = new JMenuItem("Quitter");
		mnFichier.add(mntmQuitter);
		
		mnAide = new JMenu("aide");
		menuBar.add(mnAide);
		
		mntmAPropos = new JMenuItem("A propos");
		mntmAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Apropos apropos = new Apropos(); //
				apropos.setVisible(true); //
				
			}
		});
		mnAide.add(mntmAPropos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelGauche = new JPanel();
		panelGauche.setMinimumSize(new Dimension(5, 10));
		contentPane.add(panelGauche, BorderLayout.WEST);
		panelGauche.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblParamtresDeLa = new JLabel("Param\u00E8tres de la simulation :");
		panelGauche.add(lblParamtresDeLa);
		
		lblNewLabel = new JLabel("Nombre de messages/s :");
		panelGauche.add(lblNewLabel);
		
		slider_3 = new JSlider();
		slider_3.setValueIsAdjusting(true);
		slider_3.setSnapToTicks(true);
		slider_3.setPaintLabels(true);
		slider_3.setPaintTicks(true);
		slider_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Preferences.setNOMBRE_MESSAGES_SECONDE(slider_3.getValue()); // ajout
				System.out.println(slider_3.getValue());
			}
		});
		slider_3.setValue(1);
		slider_3.setMaximum(10);
		slider_3.setMinimum(1);
		panelGauche.add(slider_3);
		
		lblNombreDeDistributeurs = new JLabel("Nombre de Distributeurs");
		panelGauche.add(lblNombreDeDistributeurs);
		
		slider = new JSlider();
		slider.setMaximum(10);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Preferences.setNOMBRE_DISTRIBUTEURS_CONTROLEUR(slider.getValue());
			}
		});
		slider.setMinimum(1);
		slider.setValue(5);
		panelGauche.add(slider);
		
		lblNombreDeTransformateurs = new JLabel("Nombre de Transformateurs par Distributeur");
		panelGauche.add(lblNombreDeTransformateurs);
		
		slider_1 = new JSlider();
		slider_1.setMaximum(10);
		slider_1.setPaintLabels(true);
		slider_1.setPaintTicks(true);
		slider_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Preferences.setNOMBRE_TRANSFORMATEURS_DISTRIBUTEUR(slider_1.getValue());
			}
		});
		slider_1.setValue(5);
		slider_1.setMinimum(1);
		panelGauche.add(slider_1);
		
		lblNombreDeFoyers = new JLabel("Nombre de Foyers par Transformteur :");
		panelGauche.add(lblNombreDeFoyers);
		
		slider_2 = new JSlider();
		slider_2.setMaximum(10);
		slider_2.setPaintLabels(true);
		slider_2.setPaintTicks(true);
		slider_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			Preferences.setNOMBRE_FOYERS_TRANSFORMATEUR(slider_2.getValue());
			}
		});
		slider_2.setValue(5);
		slider_2.setMinimum(1);
		panelGauche.add(slider_2);
		
		btnLancerSimulation = new JButton("Lancer Simulation");
		btnLancerSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// monAgent.creerAgentControleur(); // créer agent controleur pour démarrer la simulation
				GuiEvent lancementSimulationEvenement = new GuiEvent(null,Preferences.LANCER_SIMULATION_EVENEMENT);
				monAgent.postGuiEvent(lancementSimulationEvenement);
				// cacher le panel :
				
				panelGauche.hide();
				repaint();
				pack();
			}
		});
		btnLancerSimulation.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			}
		});
		panelGauche.add(btnLancerSimulation);
		
		verticalStrut = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut, BorderLayout.NORTH);
		
		panelCentre = new JPanel();
		contentPane.add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new GridLayout(0, 1, 0, 0));
		
		graphePane = new JPanel();
		panelCentre.add(graphePane);
		graphePane.setLayout(new GridLayout(0, 2, 0, 0));
		
		panelHeader = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelHeader.getLayout();
		contentPane.add(panelHeader, BorderLayout.NORTH);
		
		lblNewLabel_1 = new JLabel("Simulateur Multi-Agents pour Smart Grids");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelHeader.add(lblNewLabel_1);
	}

	
	public JPanel getConsolePane() {
		return getConsolePane();
	}
	public JPanel getGraphePane() {
		return graphePane;
	}
}
