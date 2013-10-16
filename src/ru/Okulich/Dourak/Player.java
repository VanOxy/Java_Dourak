package ru.Okulich.Dourak;

import java.util.ArrayList;

public class Player {
	
	public ArrayList<Carte> laMain = new ArrayList<Carte>();
	private boolean tour;
	//private boolean attaquant; 
	
	public Carte giveCards (Carte carte){
		// selecton de la carte que le joueur va jouer
		return carte;
	}
	
	public void range(){
		// ranger les cartes en fct de grandeur des cartes et l'atout
			Carte min, max;
			for(int i = 0; i< laMain.size(); i++){
				for(int k = 0; k< laMain.size() - 1; k++){
					if(laMain.get(k).getValeur() > laMain.get(k + 1).getValeur()){
						max = laMain.get(k);
						min = laMain.get(k + 1);
						laMain.remove(min);
						laMain.remove(max);
						laMain.add(k, min);
						laMain.add(k + 1, max);
					}
				}
			}
		}
	
	public int getLeastAtout(String couleur){
		//choisit la premiere carte atout qu'il rencontre dans le stack
		//et comme le stack est rangé la première qu'il rencontrera sera la + petite
		int min = 0; 
		for (Carte carte : laMain) {
			if((carte.getCouleur() == couleur) && (min == 0)){
				min = carte.getValeur();
			}
		}
		
		if (min == 0){
			min = 20;
		}
		return min;
	}
	
	public boolean isHisTurn(){
		return tour;
	}
	
	public void setHisTurn(boolean tour){
		this.tour = tour;
	}
}