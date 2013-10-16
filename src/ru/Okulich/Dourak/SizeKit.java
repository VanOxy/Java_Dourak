package ru.Okulich.Dourak;
import java.awt.Dimension;
import java.awt.Toolkit;

public class SizeKit {
	
	/* Classe qui va dimentionner les panels de l'interface princiâle du jeu,
	 * les panels qui representent les cartes (qui contienent les images) recto et verso,
	 * ces derniers auront les tailles differentes en fonction de leur situation sur la table, etc.
	 * Les panels vont etre dimentionner en fonction de la resolution de l'ecran, de cette maniere
	 * on peut lancer le jeu sur n'importe quelle machine - les proportions seront gardées... 
	 *
	 *
	 * Classe qui va dimentionner les panel de l'interface princiâle du jeu,
	 * les panels qui representent les cartes (qui contienent les images) recto et verso,
	 * ces derniers auront les tailles differentes en fonction de leur situation sur la table, etc.
	 * Les panels vont etre dimentionner en fonction de la resolution de l'ecran, de cette maniere
	 * on peut lancer le jeu sur n'importe quelle machine - les proportions seront gardées... 
	 */
	
	/* TODO
	 * getMiddleCardDimension()
	 * getBottomCardDimension()
	 * getLeftCardsDimension()
	 */
	
	//size le l'ecran
	private int screenHeight;
	private int screenWidth;
	
	public SizeKit(){
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 30;
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		//serie de tests
		/*
		System.out.println(screenHeight + " " + screenWidth);
		System.out.println(getTopPanel());
		System.out.println(getBottomPanel());
		System.out.println(getLeftPanel());
		System.out.println(getCenterPanel());
		System.out.println(getRightPanel());
		*/
	}
	
	public Dimension getTopPanelDimension(){
		//definit et retourne la dim du panel de haut
		Dimension dim = new Dimension(screenWidth, 
					(int) Math.floor(screenHeight * 0.2)); 
		return dim;
	}
	
	public Dimension getBottomPanelDimension(){
		//definit et retourne la dim du panel de bas
		Dimension dim = new Dimension(screenWidth, 
					(int) Math.floor(screenHeight * 0.2)); 
		return dim;
	}
	
	public Dimension getLeftPanelDimension(){
		//definit et retourne la dim du panel de gauche
		Dimension dim = new Dimension((int) Math.floor(screenWidth * 0.15), 
					(int) Math.floor(screenHeight * 0.65)); 
		return dim;
	}

	public Dimension getRightPanelDimension(){
		//definit et retourne la dim du panel de bas
		Dimension dim = new Dimension((int) Math.floor(screenWidth * 0.1), 
					(int) Math.floor(screenHeight * 0.65)); 
		return dim;
	}
	
	public Dimension getCenterPanelDimension(){
		//definit et retourne la dim du panel de bas
		Dimension dim = new Dimension((int) Math.floor(screenWidth * 0.7), 
					(int) Math.floor(screenHeight * 0.65)); 
		return dim;
	}
	
	public Dimension getBtnDimension(){
		//la dimension que va prendre le btn pour "prendre" ou finir le tour
		Dimension dim = getRightPanelDimension();
		dim.width = (int) (dim.width - dim.width * 0.1);
		dim.height = (int) (dim.height * 0.1);
		return dim;
	}
	
	public int getTopCardHeight(){
		//retourne la valeur de la hauteur que va prendre le panel de la carte du adversaire
		Dimension dim = getTopPanelDimension();
		return (int) (dim.height - dim.height * 0.1);
	}
	
	public Dimension getTopCardDimension(){
		//retourne la dimention de panel pour la carte du panel de haut
		Dimension dim = new Dimension();
		dim.height = getTopCardHeight();
		dim.width = (int)(getTopCardHeight() - (getTopCardHeight() * 0.29));
		return dim;
	}
	
	public int getBottomCardHeight(){
		//retourne la valeur de la hauteur que va prendre le panel de la carte du joueur
		Dimension dim = getBottomPanelDimension();
		return (int) (dim.height - dim.height * 0.1);
	}
	
	public int getMiddleCardHeight(){
		//retourne la valeur de la hauteur que va prendre le panel de la carte au millieu
		Dimension dim = getCenterPanelDimension();
		return (int) (dim.height - dim.height * 0.5);
	}
}
