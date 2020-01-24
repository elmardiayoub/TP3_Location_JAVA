package com.ayoub;

import java.io.Serializable;

public class Voiture implements Serializable {
	private String marque;
	private String modele;
	private int annee;
	private int prix;
	
	public Voiture(String marque, String modele, int annee, int prix) {
		this.marque = marque;
		this.modele = modele;
		this.annee = annee;
		this.prix = prix;
	}
	
	
	@Override
	public String toString() {
		return "Voiture [marque=" + marque + ", modele=" + modele + ", annee=" + annee + ", prix=" + prix + "]\n";
	}
	
	public boolean equals(Voiture voiture)
	{
		if(this.annee == voiture.annee &&
		   this.prix ==voiture.prix   && 
		   this.marque.equals(voiture.marque) &&
		   this.modele.equals(voiture.modele))
		return true;
		return false;
	}


	public String getMarque() {
		return marque;
	}


	public String getModele() {
		return modele;
	}


	public int getAnnee() {
		return annee;
	}


	public int getPrix() {
		return prix;
	}
	
}
