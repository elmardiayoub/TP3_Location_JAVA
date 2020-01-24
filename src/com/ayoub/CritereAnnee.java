package com.ayoub;

public class CritereAnnee implements Critere {
	private int Annee;
	
	public CritereAnnee(int Annee) {
		this.Annee = Annee;
	}
	public boolean estSatisfaitPar(Voiture v)
	{
		if(v.getAnnee() == Annee ) return true;
		return false;	
	}
}
