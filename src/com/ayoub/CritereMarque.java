package com.ayoub;

public class CritereMarque implements Critere {
	private String marque;
	
	public CritereMarque(String marque) {
		this.marque = marque;
	}
	
	public boolean estSatisfaitPar(Voiture v)
	{
		if(marque.equals(v.getMarque())) return true;
		return false;
	}

}
