package ru.Okulich.Dourak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Game extends JFrame{

	/*
	 * La classe qui va etre responsable du bon fonctionnement du jeu, qui va
	 * implementer un "checker"(Advisor) qui impose que les regles soient bien suivies.
	 * Ainsi que va gerer le mapping de la zone du jeu et va envoyer les signals 
	 * pour "redraw" à la classe.
	 */
	
	//-------------------------   VISUAL   ---------------------------------------
	
	/*Panel principale du jeu, sur lequel vont etre sutués tt les autres
	 * element de l'interface graphique. Ex: panels de l'adv qui contiendera les
	 * cartes, panel du bas qui contiendera les cartes de joueur, le panel du
	 * millieu qui contiendera 2 panels pour superposer les cartes, etc...
	 */	
	
	// ---------------PANELS PRINCIPALES----------------------------
	private JPanel mainPanel;
	private JPanel adversary1;
	//private JPanel adversary2;
	private JPanel playerPanel;
	private JPanel middle;
	private JPanel middleLeft;
	private JPanel middleRight;
	// -----------------PANELS DE MILLIEU---------------------------
	private JPanel midTopPanel;
	private JPanel midBotPanel;
	//------------------LES LABELS NBR CARTES-----------------------
	//private JLabel nbrCardEnemy = new JLabel();
	private JLabel nbrCardStill = new JLabel();
	//--------------------------Layouts & Colors--------------------
	private final Color BACKGROUND = new Color(0, 139, 139);
	private BorderLayout bordLayout;
	private FlowLayout flowLeftAllign;
	
	//------------------------   /VISUAL   --------------------------------------
	
	//------------------------   SYSTEM   ---------------------------------------
	private Advisor advisor;
	private SizeKit kit;
	//------------------------   /SYSTEM   --------------------------------------
	
	//------------------------   GAME    ----------------------------------------
	private Player player;
	private IA ia;
	private Paquet paq;
	private Carte atout;
	private ArrayList<Carte> midTopArray = new ArrayList<Carte>();
	private ArrayList<Carte> midBotArray = new ArrayList<Carte>();
	private JButton button = new JButton("Prendre");
	//------------------------   /GAME   ----------------------------------------
	
	
	public void init() {
		//constructor
		new JFrame();
		kit = new SizeKit();
		initTable(/*Options.nbrAdversaires*/);
		initFrame();
		
		initGame();
	}
	
	private void initFrame(){
		//va mettre en place la fenetre en taille de tout l'ecran
		//et ajoute le panel principal du jeu sur la frame
		setTitle("Dourak");
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 
				Toolkit.getDefaultToolkit().getScreenSize().height - 30);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mainPanel);
		setVisible(true);
	}
	
	private void initTable(/*int adversaires*/){
		//va faire le mapping de la zone du jeu en fct de nbr de joueurs
		mainPanel = new JPanel();
		bordLayout = new BorderLayout();
		flowLeftAllign = new FlowLayout();
		playerPanel = new JPanel();
		middle = new JPanel();
		middleLeft = new JPanel();
		middleRight = new JPanel();
		midTopPanel = new JPanel();
		midBotPanel = new JPanel();
		adversary1 = new JPanel(new FlowLayout());
		/*
		 * if (adversaires == 2)
			adversary2 = new JPanel(new FlowLayout());
		*/
		
		// config du panel principal MyPanel (la classe)
		mainPanel.setBackground(BACKGROUND);
		mainPanel.setLayout(bordLayout);
		
		//config du FlowLayout
		flowLeftAllign.setAlignment(FlowLayout.LEFT);
		flowLeftAllign.setHgap(25);

		// config du panel Adversaire
		adversary1.setPreferredSize(kit.getTopPanelDimension());
		adversary1.setBackground(BACKGROUND);

		// config du panel myCards
		playerPanel.setPreferredSize(kit.getBottomPanelDimension());
		playerPanel.setBackground(BACKGROUND);

		// config du panel middleLeft
		middleLeft.setPreferredSize(kit.getLeftPanelDimension());
		middleLeft.setLayout(flowLeftAllign);
		middleLeft.setBackground(BACKGROUND);

		// config du panel middleRight
		middleRight.setPreferredSize(kit.getRightPanelDimension());
		middleRight.setBackground(BACKGROUND);
		middleRight.add(button);

		// ---------------------------------------------------
		// config du panel middle
		middle.setPreferredSize(kit.getCenterPanelDimension());
		middle.setBackground(BACKGROUND);
		middle.setLayout(null);

		/* reglages de panel mid1 --> qui s'affiche devant je ss pas pq???
		 * donc on va l'utiliser comme MID2 en le descendant en bas	
		 * QUI VA ETRE AFFICE COMME LA CARTE QUI COUVRE --> AU DESUS*/
		midTopPanel.setBounds((int) (kit.getCenterPanelDimension().width * 0.1) +15, 
						(int) (kit.getCenterPanelDimension().height * 0.325),
						kit.getCenterPanelDimension().width, 
						kit.getMiddleCardHeight());
		midTopPanel.setLayout(flowLeftAllign);
		midTopPanel.setOpaque(false);
		
		// reglages de panel mid2 --> qui s'affiche derriere 
		// DONC VA ETRE AFFICHE EN DESOUS
		midBotPanel.setBounds((int) (kit.getCenterPanelDimension().width * 0.1), 
					(int) (kit.getCenterPanelDimension().height * 0.225),
					kit.getCenterPanelDimension().width, 
					kit.getMiddleCardHeight());
		midBotPanel.setLayout(flowLeftAllign);
		midBotPanel.setOpaque(false);

		// ajout de panels sur le pane du mid
		middle.add(midTopPanel);
		middle.add(midBotPanel);
		// -----------------------------------------------------------

		// ajout des panels à la frame
		mainPanel.add(adversary1, BorderLayout.NORTH);
		mainPanel.add(playerPanel, BorderLayout.SOUTH);
		mainPanel.add(middleLeft, BorderLayout.WEST);
		mainPanel.add(middleRight, BorderLayout.EAST);
		mainPanel.add(middle, BorderLayout.CENTER);
		
		mainPanel.updateUI();
	}
	
	private void initGame(){
		//init les elements essentiels
		//distribue les cartes de debut du jeu
		//et place les les cartes de gauche
		paq = new Paquet(this);
		advisor = new Advisor();
		advisor.selectAtout(paq, this);
		player = new Player();
		ia = new IA(this);
		
		advisor.distributeNewGame(player, ia, paq, this);
		showPlayerCartes();
		showIaCards();
		showAtout_nbrCardsStill();
		
		advisor.play(this);
	}
	
	private void showPlayerCartes(){
		//aficher les cartes de joueur
		player.range();
		for (Carte carte : player.laMain) {
			playerPanel.add(carte);
		}
		update();
	}
	
	private void showIaCards(){
		//afficher les cartes IA (dos) en fct de leur nbr
		for (@SuppressWarnings("unused") Carte carte : ia.laMain) {
			try {
				Carte clone = paq.dosCarte.clone();
				adversary1.add(clone);
				clone.checkImage(clone.img, this);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		update();
	}
	
	private void showAtout_nbrCardsStill(){
		middleLeft.removeAll();
		nbrCardStill.setText(Integer.toString(paq.size()+1));
		middleLeft.add(atout);
		middleLeft.add(paq.dosCarte);
		middleLeft.add(nbrCardStill);
		update();
	}
	
	public void update(){
		//renouvelle l'image après qu'on a fait une acton
		mainPanel.updateUI();
	}
	
	@SuppressWarnings("unused")
	private void backToMenu(){
		//qd on va fermer le jeu
		dispose();
	}
	
	//------------------------------Getter's & Setter's----------------------------------

	public Carte getAtout() {
		return atout;
	}

	public void setAtout(Carte atout) {
		this.atout = atout;
	}
	
	public JPanel getPlayerPanel(){
		return this.playerPanel;
	}
	
	public JPanel getMidTopPanel(){
		return this.midTopPanel;
	}
	
	public JPanel getMidBotPanel(){
		return this.midBotPanel;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public IA getIA(){
		return this.ia;
	}
	
	public ArrayList<Carte> getMidTopArray() {
		//recupere les cartes du joueur qui def
		return midTopArray;
	}
	
	public ArrayList<Carte> getMidBotArray() {
		//recupere les cartes du joueur qui attaque
		return midBotArray;
	}
	
	public Advisor getAdvisor(){
		return this.advisor;
	}
	
	public JPanel getAdversairePanel(){
		return this.adversary1;
	}
	
	///-------------------------------------Show IA Cards-------------------------------------
	//truc à supprimer à la fin, car il sert pour suivre le bon fct du jeu
	public void showIACards(){
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		for(Carte carte : ia.laMain){
			panel.add(carte);
		}
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
}
