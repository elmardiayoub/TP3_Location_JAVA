package com.ayoub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InterCritere implements Critere {

	List lesCriteres;
	public InterCritere() {
		lesCriteres = new ArrayList<Critere>();
	}
	void addCritere(Critere c)
	{
		lesCriteres.add(c);
	}
	@Override
	public boolean estSatisfaitPar(Voiture v) {
		Iterator itr = lesCriteres.iterator();
		while(itr.hasNext())
		{
			Critere c = (Critere) itr.next();
			if(!c.estSatisfaitPar(v))
				return false;	
		}
		return true;
	}

}
