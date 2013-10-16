package ru.Okulich.Dourak;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class Carte extends JPanel implements MouseListener, Cloneable{
	//represente obj carte - contient sa valeur, couleur, proprietes tels que 
	//"isCovered" pour la meilleure gestion du jeu, etc
	private int valeur;
	private String couleur;
	public Image img;
	private boolean covered = false;
	private boolean played = false;
	private SizeKit kit = new SizeKit();
	private Game game;
	public String name = "boom";
	private Advisor advisor = new Advisor(); 
	
	// COULEURS
	@SuppressWarnings("unused")
	private final String CARREAU = "car";
	@SuppressWarnings("unused")
	private final String COEUR = "coe";
	@SuppressWarnings("unused")
	private final String PIQUE = "piq";
	@SuppressWarnings("unused")
	private final String TREFFLE = "tref";
	
	public Carte(int i, String couleur, Image img, Game game){
		this.valeur = i;
		this.couleur = couleur;
		this.img = img;
		this.game = game;
		setPreferredSize(kit.getTopCardDimension());
		addMouseListener(this);
	}
	
	// VALEUR 
	public int getValeur(){
		return this.valeur;
	}
	
	// COULEUR
	public String getCouleur(){
		return this.couleur;
	}
	
	// STATE
	public boolean isCovered() {
		return covered;
	}
	public void setCovered() {
		this.covered = true;
	}
	
	public boolean isPlayed(){
		return played;
	}
	
	public void setPlayed(){
		this.played = true;
	}
	
	public void setNotPlayed(){
		this.played = false;
	}
	
	public Carte clone() throws CloneNotSupportedException {
		return (Carte) super.clone();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, kit.getTopCardDimension().width,
				kit.getTopCardDimension().height, null);
	}
	
	public void mouseClicked(MouseEvent e) {
		if ((!this.img.equals(Toolkit.getDefaultToolkit().getImage("res/dos/blue.png"))) 
			&& (isPlayed() == false)){
			
			
			if((game.getPlayer().isHisTurn()) && (advisor.checkPlayedCard(this))){
			//donc si c'est le joueur qui attaque --> on s'en fout quels cartes poser
				
				//aspect visuel
				game.getPlayerPanel().remove(this);
				//game.getMidBotArray().add(this);
				//game.getMidBotPanel().add(this);
				advisor.addCarteMidBot(this, game);
				game.update();
				this.setPlayed();
				
				//l'ia qui joue et tout le reste autour qui s'adapte
				if (advisor.isIaAdmittedToPlay()){
					//une fois que l'ia va renvoyer null - càd une fois décidé de prendre
					//elle ne jouera +
					game.getIA();
					advisor.iaPlay();
				}
				
			}else if(!game.getPlayer().isHisTurn()){
			//et ici on se fait attaquer donc fo reg qu'est ce que le joueurs joue et 
			//exclure les mauvaises demarche --> la triche
			
				//on parcoure la tables avc les cartes à couvrir, vue qu'il ne peut avoir qu'une
				//seule à la fois qui aura le status "notCovered" 
				//et alors on reg par rapport à celle là
				Carte tableC = null;
				for (Carte carte  : game.getMidBotArray()) {
					if(!carte.isCovered())
						tableC = carte;
				}
				
				if(advisor.movementAccepted(game.getAtout().getCouleur(),this, tableC)){
					//game.getPlayerPanel().remove(this);
					advisor.addCarteMidTop(this, game);
					game.update();
					this.setPlayed();
				}else{
					System.out.println("You're doing it wrong...!!!!");
					//Boîte de dialogue qui va afficher le message d'erreur
					 JOptionPane warningDia = new JOptionPane();
					 warningDia.showMessageDialog(null, "Mauvais mouvement!", "You're doing it wrong!!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	// DO NOTHING
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	/*static public void Message (Component parent,String titre,String mes){

			Component frame = parent;
			while ( frame != null && !(frame instanceof Frame))
			frame = frame.getParent ();

			MessageBox box = new MessageBox ((Frame)frame, titre);
			box.add("");
			boite.setResizable (false);
			box.pack ();
			box.show ();
			}*/
}
