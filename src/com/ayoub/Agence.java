package com.ayoub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;


public class Agence implements Serializable{
	private List<Voiture> voitures;
	private Map<Client, Voiture> Locations;

	public Voiture getVoitures(int index) {
		return voitures.get(index-1);
	}
	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}
	public Agence()
	{
		voitures = new ArrayList<Voiture>();
		Locations = new TreeMap<Client,Voiture>();
	}
	public void addVoiture(Voiture v)
	{
		voitures.add(v);
		System.out.println("Voiture ajoutée avec succés");
	}
	public Iterator selectionne(Critere c)
	{
		try {
			ArrayList<Voiture> arraylist = new ArrayList<Voiture>();
			Iterator itr = voitures.iterator();
			
			while(itr.hasNext())
			{
				Voiture v = (Voiture)itr.next();
				if(c.estSatisfaitPar(v))
				{
					arraylist.add(v);
				}
			}
			Iterator iterator = arraylist.iterator();
			
			return iterator;
		  }catch (Exception NullPointerException) {
			System.out.println("Aucune voiture trouvée");
			return null;	
		}
		
	}
	public void afficherSelection(Critere c)
	{
		if(selectionne(c) != null)
		{
			Iterator itr = selectionne(c);
			while(itr.hasNext())
			{
				
				System.out.println((Voiture)itr.next());
			}
		}
	}
	
	public void loueVoiture(Client client,Voiture v)
	{
		if(!voitures.contains(v))
			System.out.println("Voiture n'existe pas en agence");
		else if(Locations.containsValue(v))
			System.out.println(v+" déja louée");
		else if(Locations.containsKey(client))
			System.out.println(client+" ne peut pas loué deux voitures à la fois");
		else
			Locations.put(client, v);
	}
	public boolean estLoueur(String cin)
	{ 
		Set<Client> cles = Locations.keySet();
		Iterator itr = cles.iterator();
		while(itr.hasNext())
		{
			if(((Client)itr.next()).getCin().equals(cin))
				return true;		
		}
		return false;
	}
	public boolean estLoue(Voiture v)
	{ 
		if(Locations.containsValue(v)) return true;
		return false;
	}
	public void rendVoiture(String cin)
	{
		Set<Client> cles = Locations.keySet();
		Iterator itr = cles.iterator();
		while(itr.hasNext())
		{
			Client c = (Client)itr.next();
			
			if((c).getCin().equals(cin))
			{
				Locations.remove(c);
				System.out.println("Voiture rettirée avec succés");
				break;
			}
				
		}
			
	}
	public Iterator lesVoitureLouees()
	{ 
		Iterator itr = voitures.iterator();
		ArrayList<Voiture> arr = new ArrayList<Voiture>();
		while(itr.hasNext())
		{
			Voiture v = (Voiture)itr.next();
			if(estLoue(v))
				arr.add(v);
		}
		Iterator iterator = arr.iterator();
		return iterator;
	}
	void afficherVoitureAgence()
	{
		int i=1;
		for (Voiture voiture : voitures) {
			System.out.println(i+" : "+voiture);
			i++;
		}
	}
	
	@Override
	public String toString() {
		String concat;
		Set<Client> cles = Locations.keySet();
		Iterator itr = cles.iterator();
		while(itr.hasNext())
		{
			Client c = (Client)itr.next();
			Voiture v = Locations.get(c);
			System.out.print(c+ " ---> ");
			System.out.print(v);	
		}
		return "";
	}
	
	
		
}
