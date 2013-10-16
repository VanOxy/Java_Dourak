package ru.Okulich.Dourak;

import javax.swing.JPanel;


public class Advisor {
	/*
	 * Classe qui va surveiller le bon fonctionnement du jeu, reg les cartes des
	 * autres et determine si le mouvement est approprié. Ainsi que de gerer les
	 * les actions de fin de tour, faire tourner le jeton, prendre, etc...
	 */
	
	private IA ia;
	private Player player;
	@SuppressWarnings("unused")
	private JPanel playerPanel;
	private String atout;
	private Game game;
	private boolean gameOver = false;
	private boolean drop;
	private boolean iaAdmittedToPlay = true;
	private boolean isBotArrayEmpty = true;

	public void distributeNewGame(Player player, IA ia, Paquet paq, Game game) {

		// le joueur humain
		while (player.laMain.size() < 6) {
			player.laMain.add(paq.get(0));
			paq.remove(0);
		}

		// l'IA
		while (ia.laMain.size() < 6) {
			ia.laMain.add(paq.get(0));
			paq.remove(0);
		}
	}
	
	public void selectAtout(Paquet paq, Game game) {
		// déterminer l'atout
		int i = 0;
		boolean atoutDone = false;
		for (Carte carte : paq) {
			if (atoutDone) {
				// do nothing
			} else {
				if (carte.getValeur() != 14) {
					try {
						game.setAtout(carte.clone());
						game.getAtout().checkImage(game.getAtout().img, game);
						game.getAtout().removeMouseListener(carte);
						atoutDone = true;
						i = paq.indexOf(carte);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		paq.remove(i);
	}

	public void play(Game game) {
		// recup pour manipuler les obj + facilement
		this.game = game;
		ia = game.getIA();
		player = game.getPlayer();
		playerPanel = game.getPlayerPanel();
		
		// on commence le truc
		whoPlayFirst();
		
		if(ia.isHisTurn()){
			Carte carte = ia.play();
			if(checkPlayedCard(carte)){
				addCarteMidBot(carte, game);
			}
		}
	/*	
		//*********************************EXEMPLE***************************************
		Carte testCarte = new Carte(10, "car", 
				Toolkit.getDefaultToolkit().getImage("res/dos/blue.png"), game);
		player.setAttaquant(false);
		game.getMidBotPanel().add(testCarte);
		game.getMidBotArray().add(testCarte);
		//********************************************************************************
	 */
		/*
		int i =0;
		while(!isGameOver()){
			game.update();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (player.isHisTurn()) {
				System.out.println("Hello i'm player");
				if(i==5)
					break;
			}else if(ia.isHisTurn()){
				System.out.println("Hello i'm ia");
				if(i==5)
					break;
			}
			i++;
		}
		*/
	}

	public boolean movementAccepted(String atout, Carte playerC, Carte tableC) {
		//le cas de defense
		boolean choix = false;
		// d'abord checker si les deux carte sont du meme code couleur
		if (playerC.getCouleur() == tableC.getCouleur()) {
			if (playerC.getValeur() > tableC.getValeur()) {
				choix = true;
			} else if (playerC.getValeur() < tableC.getValeur()) {
				choix = false;
			}
		}else if ((playerC.getCouleur() == atout) && 
				  (tableC.getCouleur() != atout)) {
						choix = true;
		}else if ((tableC.getCouleur() == atout) && 
				  (playerC.getCouleur() == atout)){
			if(tableC.getValeur() < playerC.getValeur()){
				choix = true;
			}
			else
				choix = false;
		}else
			choix = false;
		return choix;
	}
		
	private void whoPlayFirst() {
		// determinner l'autout le plus petit dans la mains de jouers
		ia.range();
		this.atout = game.getAtout().getCouleur();
		// ************************************************************************************************
		/************************************* le truc qui affiche les cartes de l'IA */
		game.showIACards();
		// *************************************************************************************************
		if (player.getLeastAtout(atout) < ia.getLeastAtout(atout)) {
			System.out.println("player & " + player.getLeastAtout(atout) + " "
					+ atout);
			playerBegins();
		} else if (player.getLeastAtout(atout) > ia.getLeastAtout(atout)) {
			System.out.println("ia & " + ia.getLeastAtout(atout) + " " + atout);
			iaBegins();
		} else {
			System.out.println("Nope... Player 1 begins");
			playerBegins();
		}
	}
	
	public boolean checkPlayedCard(Carte carteJoue){
		//le cas de l'attaque
		if(isBotArrayEmpty)
		{
			return true;
		}else{
			for (Carte carte : game.getMidBotArray()) {
				if(carte.getValeur() == carteJoue.getValeur()){
					isBotArrayEmpty = false;
					return true;
				}
			}
			for (Carte carte : game.getMidTopArray()) {
				if(carte.getValeur() == carteJoue.getValeur()){
					isBotArrayEmpty = false;
					return true;
				}
			}
			return false;
		}
	}
	
	public void iaPlay(){
		
		if(ia.play() == null){
			//essayer avc autre chose que "null"
			//ex moins de 6 ou + de 14
			setIaAdmittedToPlay(false);
		}
		else if((ia.play() == null) && (game.getMidBotArray().size() < 6)){
			player.setHisTurn(true);
			if ((player.isHisTurn() == true) && (drop == false)){
				//il continue de jouer, donc le mouseclik
			}else{
				drop = true;}
		}else if ((ia.play() != null)){
			game.getMidTopPanel().add(ia.play());
			try {
				game.getMidTopArray().add(ia.play().clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			//drop = true;
		}
	}
	
	public void takeThis ()
	{
		player.laMain.addAll(game.getMidBotArray());
		player.laMain.addAll(game.getMidTopArray());
		game.getMidBotPanel().removeAll();
		game.getMidTopPanel().removeAll();
	}
	
	public void addCarteMidBot(Carte carte, Game game){
		game.getMidBotArray().add(carte);
		game.getMidBotPanel().add(carte);
	}
	
	public void addCarteMidTop(Carte carte, Game game){
			game.getMidTopArray().add(carte);
			game.getMidTopPanel().add(carte);
	}
	
	public void playerBegins() {
		player.setHisTurn(true);
		ia.setHisTurn(false);
	}

	public void iaBegins() {
		player.setHisTurn(false);
		ia.setHisTurn(true);
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void gameOver() {
		gameOver = true;
	}
	
	public boolean isIaAdmittedToPlay(){
		return this.iaAdmittedToPlay;
	}
	
	public void setIaAdmittedToPlay(boolean truc){
		this.iaAdmittedToPlay = truc;
	}
	
	public IA getIA(){
		return this.ia;
	}
}
