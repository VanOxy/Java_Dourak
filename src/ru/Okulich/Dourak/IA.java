package ru.Okulich.Dourak;

import java.util.ArrayList;
import java.util.Random;

public class IA extends Player {

	// private boolean etat; // va dйfinir l'йtat de l'ia, donc si elle prends
	// la table ou pas
	private Carte playinCard; // la carte que l'Ia va jouer, elle reprйsente un
								// int parmis sont paquet (ex: il joue la 5иme
								// carte de son paquet)
	private String atout;
	private Game game;
	private Carte carteT;
	// private boolean turnover;

	// ----------------------------------------------------------------------------------------------------
	// L'IA easy va toujour jouer la carte la plus haute dans chaque couleur
	// qu'il possиde sauf en atout ou la sa sera un random
	// l'Ia va a chaque dois rйagir de 2 maniиre: 1) en attaquй; 2) en attaquant
	// private boolean drop; // va dйfinir l'йtat de l'ia, donc si elle prends
	// la table ou pas
	// true = on prends // false = on prends pas
	private ArrayList<Carte> atoutArray = new ArrayList<Carte>();
	private ArrayList<Carte> autreArray = new ArrayList<Carte>();
	Random randy = new Random();

	public IA(Game game) {
		this.game = game;
		this.atout = game.getAtout().getCouleur();
	}

	// On sйpare le paquet en 2-atout-non atout-

	public void remplissageAtout(Carte carte) {
		atoutArray.add(carte);
	}

	public void remplissageAutre(Carte carte) {
		autreArray.add(carte);
	}

	public Carte play() {
		// on va d'abord sйparer les paquets, pour travailler soit sur de
		// l'atout, soit sur le reste
		range();
		for (Carte carte : laMain) {
			if (carte.getCouleur() == atout) {
				remplissageAtout(carte);
			} else {
				remplissageAutre(carte);
			}
		}

		// lors d'une defense
		if (!isHisTurn()) {
			for (Carte carte : autreArray) {

				if (chooseCarteToCover().getCouleur() == carte.getCouleur()) {
					if (carte.getValeur() > chooseCarteToCover().getValeur()) {
						game.getAdversairePanel().remove(0);
						// game.getMidTopPanel().add();

						// dans la carte ne pas faire advisor.iaPlay();
						// mais dirrectement ia.play(); qui ne va rien retourner
						// mais agir comme une procedure
						// cad travailler elle meme avc les objets
						// et en cas de l'exception gerer ce que se passe qd on
						// a l'exception

						// ajouter les conditions avec drop, si ya drop de la
						// part de joueur, faire une boucle pour
						// ajouter les cartes sur la table. Et seulement apres
						// advisor.faitQQch() pour ramasser
						// les cartes --> donner au joueur
						return carte;
					}
				}
				/*
				 * else if(carte.getValeur() ==
				 * chooseCarteToCover().getValeur()){ /*if(turnover== true){
				 * playinCard = carte; return playinCard; } }
				 */
			}
			for (Carte carte : atoutArray) {
				if (carte.getValeur() == chooseCarteToCover().getValeur()) {
					game.getAdversairePanel().remove(0);
					return carte;
				} else {
					// envoie le signal qu'elle n'a plus de cartes avc lesquels
					// se def
					game.getAdvisor().setIaAdmittedToPlay(false);
					return null;
				}
			}
		}

		// le cas lors d'une attaque
		else if (isHisTurn()) {
			for (Carte carte : autreArray) {
				if (game.getAdvisor().checkPlayedCard(carte))
					game.getAdversairePanel().remove(0);
				return carte;
			}
			if (autreArray.isEmpty()) {
				for (Carte carte : atoutArray) {
					game.getAdversairePanel().remove(0);
					return carte;
				}
			}
		}
		// un truc pour que le compil ne se plaigne pas
		return (autreArray.get(0));
	}

	private Carte chooseCarteToCover() {
		for (Carte carte : game.getMidBotArray()) {
			if (!carte.isCovered()) {
				playinCard = carte;
			}
		}
		return playinCard;
	}

	// le turnover
	/*
	 * public boolean turnOvMeth(boolean midBotArray){ if
	 * (game.getMidTopArray().isEmpty()){ turnover = true; }else{ turnover =
	 * false; }return turnover;
	 */
}
