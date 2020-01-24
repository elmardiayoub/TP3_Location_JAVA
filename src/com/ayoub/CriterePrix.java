package com.ayoub;

public class CriterePrix implements Critere {
	private int prix;
	
	public CriterePrix(int prix) {
		this.prix = prix;
	}
	public boolean estSatisfaitPar(Voiture v)
	{
		if(v.getPrix() <= prix ) return true;
		return false;	
	}
}
