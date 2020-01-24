package com.ayoub;

import java.io.Serializable;

public class Client implements Comparable,Serializable {
	
	private String nom;
	private String prenom;
	private String cin;
	private String civ;
	
	public Client(String nom, String prenom, String cin, String civ) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.civ = civ;
	}

	public String getCin() {
		return cin;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", CIN=" + cin + ", civ=" + civ + "]";
	}

	@Override
	public int compareTo(Object o) {
		if(this.nom.compareTo(((Client)o).nom) < 0 )
			return -1;
		else if(this.nom.compareTo(((Client)o).nom) > 0 )
			return 1;
		return 0;
	}
	
	

}
