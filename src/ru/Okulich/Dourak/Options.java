package ru.Okulich.Dourak;


import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Options {

	static int nbrAdversaires = 1;
	static boolean turnOver = false;
	static SelectOptions so;
	static JOptionPane regles;

	public static void launchSelectOptions() {
		so = new SelectOptions();
		so.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		so.setVisible(true);
	}

	@SuppressWarnings("static-access")
	public static void launchRegles(){
		
		/* A mettre en ordre...*/
		/*connerie de truc ne se size pas... et il n'ya pas de scrollBar qd le text depasse la Hight de l'ecran*/
		/*en + obligé de mettre les \n à la fin, pas de saut à la ligne auto...*/
		regles = new JOptionPane();
		regles.setBackground(new Color(0, 139, 139));
		//un example de commentaire pour tester le truc...
		regles.showMessageDialog(null,"Donne : " +
				"On distribue 6 cartes par joueurs, la dernière carte du paquet restant est retournée \n" +
				"sur le tas donnant la couleur forte, celle qui vaut plus que toutes les autres. Celui qui\n" +
				" commence est l’attaquant. Il y a deux phases de jeux, l’attaque et la défense.\n" +
				"Premier tour : Celui qui attaque doit poser une ou plusieurs cartes (de même hauteur, ex :\n" +
				" deux valets) et le défenseur doit contrer ces cartes avec des cartes plus fortes Si le\n" +
				" défenseur ne peut pas (ou ne veut pas, par stratégie) contrer les cartes, il les ramasse,\n" +
				" et les mets dans son jeu. Deuxième tour :" + "Au deuxième tour, l’attaquant doit jouer la\n" +
				" valeur d’une carte qui a déjà été posée (celles qui sont sur la table ; ex : si\n" +
				" l’attaquant pose un 9 et le défenseur une reine, l’attaquant doit poser soit une\n " +
				"reine soit un 9) S’il ne peut pas jouer ou si le défenseur contre chacune des six\n" +
				" attaques il devient attaquant et l’attaquant devient défenseur. À chaque fin de\n " +
				"tour (de chaque pli) les joueurs complètent leurs jeux pour avoir 6 cartes\n" +
				" (l’attaquant se sert en premier)\n" + 
				"Any player may deal the first hand. Subsequently each hand is dealt by the loser\n" +
				" of the previous hand. The dealer shuffles and deals out the cards face down to the\n" +
				" players one at a time, clockwise, until everyone has a hand of six cards. The next\n" +
				" card is placed face up in the centre of the table; its suit determines trumps. The\n" +
				" remaining undealt cards are placed in a stack face down on top of the trump card,\n" +
				" but crosswise so that the rank and value of the trump remain visible. These central\n" +
				" cards are called the prikup (talon). Note that dealing is traditionally regarded\n" +
				" as menial work, undertaken as a punishment by the loser of the previous hand. Only\n" +
				" the dealer handles the cards - they are not usually cut, as in other card games. If\n" +
				" any other player touches the cards they become the fool and take over the job of\n" +
				" dealing. Sometimes the dealer may offer the cards to be cut after shuffling; if \n" +
				"the player to whom they are offered falls into the trap of cutting the cards, that\n" +
				" player becomes the dealer and takes over the role of the fool. Hence the expression:\n" +
				" \"Shapku s duraka ne snimayut\" (\"One should not take the hat away from a fool\").\n" +
				"With six players and 36 cards there will be no talon. All the cards are dealt to the\n" +
				" players and the dealer's last card is turned face up to determine the trump suit. This\n" +
				" trump is part of the dealer's hand and is picked up along with the dealer's other five\n" +
				" cards when everyone has had a chance to look at it.\n" +
				"The players pick up their cards and look at them. In the first hand of a session,\n" +
				" the holder of the lowest trump plays first. If anyone has the trump 6 they show\n" +
				" it to prove they are entitled to begin. If no one has the trump 6, then the holder\n" +
				" of the trump 7 will start; if no one has that, the trump 8 and so on. The first play\n" +
				" does not have to include the lowest trump - the holder of the lowest trump can begin\n" +
				" with any card. In the second and subsequent hands of a session, the player to the left\n" +
				" of the dealer begins the play.\n");
	}
}
