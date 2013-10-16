package ru.Okulich.Dourak;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;


@SuppressWarnings("serial")
public class Paquet extends ArrayList<Carte>{
	
	public Carte dosCarte;
/*
 * Classe qui represente le paquet de carte, qui est en fait un arrayList d'objets Carte
 */
	
	public Paquet(Game game) {
		//constructeur du paquet qui le remplit des cartes, tt en les creant en mm temps
		// et en leur affectant leur valeurs et l'image pour la GUI
		//crée la carte "dosDeCarte" que l'on va clonner apres pour afficher tjs la meme
		
		String[] couleurs = new String[] { "car", "coe", "piq", "tref" };
		
		for (String couleur : couleurs) {
			for (int i = 6; i <= 14; i++) {
				Carte carte  = new Carte(i, couleur, Toolkit.getDefaultToolkit()
						.getImage("res/" + couleur + "/" + i + ".png"), game);
				add(carte);
				carte.checkImage(carte.img, carte);
			}
		}
		for(int i = 0; i < 5; i++){
			mix();
		}
		dosCarte = new Carte(0, null, Toolkit.getDefaultToolkit().getImage("res/dos/blue.png"), game);
	}

	public void mix(ArrayList<Carte> paq) {
		// methode qui va mellanger l'ArrayList de la classe Paquet
		Collections.shuffle(paq);
	}
	
	public void mix() {
		// methode qui va mellanger l'ArrayList de la classe Paquet
		Collections.shuffle(this);
	}

	public ArrayList<Carte> distribute(int n) {
		// la methode qui va retourner un ArrayList de Cartes
		// le nbr de Cartes ds L'Array --> nbr passe en parametre
		int index;
		Carte carte;
		ArrayList<Carte> list = new ArrayList<Carte>();

		for (int i = 0; i < n; i++) {
			index = this.size() - 1;
			carte = this.get(index);
			this.remove(index);
			list.add(carte);
		}
		// System.out.println("---Cartes distribues-----"); // test
		// truc(list); // test
		return list;
	}
}
