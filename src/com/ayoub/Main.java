package com.ayoub;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;



public class Main {

	@SuppressWarnings("null")
	public static void main(String[] args) {	
	
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		ArrayList<Voiture> arr = new ArrayList<>();
		Agence agence = new Agence();
		Critere c1 = null;
		int prix,annee;
		InterCritere inter = null;
		String marque;
		int index = -1;
		String cin = ""; 
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Agence.txt"));
			try {
				agence = (Agence)ois.readObject();
				agence.afficherVoitureAgence();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		do {
		System.out.println("1-Ajouter une voiture à l'agence");
		System.out.println("2-Afficher les voitures de l'agence");
		System.out.println("3-Les voitures avec un critere de prix");
		System.out.println("4-Les voitures avec 3 criteres (marque,annee,prix)");
		System.out.println("5-Louer une voiture");
		System.out.println("6-Vérifier si un client loue une voiture");
		System.out.println("7-Vérifier une voiture s'elle est louée");
		System.out.println("8-Rendre une voiture");
		System.out.println("9-Les voitures louées");
		System.out.println("10-Afficher les locations par TreeMap");
		System.out.println("11-Sérialiser");
		System.out.println("12-Quitter");
		
		try {
			choix = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Saisie invalide !!!\n");
		}
		sc.nextLine();
		switch (choix) {
		case 1:
			Voiture v = saisirVoiture(sc);
			agence.addVoiture(v);
			break;
		case 2 :
			agence.afficherVoitureAgence();
				break;
		case 3 :
			System.out.println("Donner critere de prix :");
			try {
				prix = sc.nextInt();
				c1 = new CriterePrix(prix);
			} catch (InputMismatchException e) {
				System.out.println("Marque invalide \n");
			}
			agence.afficherSelection(c1);
				break;
		case 4 :
			inter = saisirCriteres(inter,sc);
			agence.afficherSelection((Critere)inter);
			break;
		case 5 :
				System.out.println("Veuillez tout d'abord creer un profil");
				Voiture v1 = null;
				Client c = creerClient(sc);
				System.out.println("Choisiser une voiture de notre agence : \n");
				agence.afficherVoitureAgence();
				try {
					index = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("numero invalide \n");
				}
				if(index != -1)
				v1= agence.getVoitures(index);
				else 
					System.out.println("Erreur d'indice");
				if(v1!=null)
					agence.loueVoiture(c, v1);
				else 
					System.out.println("Aucune voiture correspond à l'indice");
				
				break;
		case 6 :
			
				System.out.println("Veuillez saisir son CIN :");
				try {
						cin = sc.nextLine();
				} catch (InputMismatchException e) {
					System.out.println("cin invalide \n");
				}
				if(agence.estLoueur(cin))
					System.out.println("Le client déja loue une voiture");
				else
					System.out.println("Le client ne loue aucune voiture");
			break;
		case 7 :
			
				System.out.println("Choisiser une voiture de notre agence : \n");
				agence.afficherVoitureAgence();
				try {
					index = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("numero invalide \n");
				}
				
				Voiture vtr = agence.getVoitures(index);
				if(vtr != null)
				{
					if(agence.estLoue(vtr))
					System.out.println("Voiture déja louée");
					else
						System.out.println("Voiture n'est jamais louée");
				}
				else
					System.out.println("Aucune voiture correspond à l'indice");
							
			break;
		case 8 :

			System.out.println("Veuillez saisir son CIN :");
			try {
					cin = sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("cin invalide \n");
			}
			agence.rendVoiture(cin);
			break;
		case 9:
			Iterator itr = agence.lesVoitureLouees();
			while(itr.hasNext())
			{
				System.out.println((Voiture)itr.next());
			}
			break;
		case 10:
			System.out.println(agence);
			break;
	
		case 11:
			Serialisation(agence);
			break;
		
		}
	}while(choix != 12);
		
	
}

	private static Client creerClient( Scanner sc) {
		String nom = null;
		String prenom = null;
		String cin = null;
		String civ = null;
		
		System.out.println("Nom :");
		try {
			nom = sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("nom invalide \n");
		}
		System.out.println("Prenom :");
		try {
			prenom = sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("prenom invalide \n");
		}
		System.out.println("CIN : ");
		try {
			cin = sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("CIN invalide \n");
		}
		System.out.println("Civilite :");
		try {
			civ = sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("CIVILITE invalide \n");
		}

		return new Client(nom, prenom, cin, civ);
	}

	private static InterCritere saisirCriteres(InterCritere inter, Scanner sc) {
		int prix,annee;
		String marque;
		Critere c1 = null;
		inter = new InterCritere();

		System.out.println("Donner critere de marque :");
		try {
			marque = sc.nextLine();
			c1 = new CritereMarque(marque);
		} catch (InputMismatchException e) {
			System.out.println("annee invalide \n");
		}
		inter.addCritere(c1);
		
		System.out.println("Donner critere de prix :");
		try {
			prix = sc.nextInt();
			c1 = new CriterePrix(prix);
		} catch (InputMismatchException e) {
			System.out.println("Marque invalide \n");
		}
		inter.addCritere(c1);
		System.out.println("Donner la critere de l'annee :");
		try {
			annee = sc.nextInt();
			c1 = new CritereAnnee(annee);
		} catch (InputMismatchException e) {
			System.out.println("annee invalide \n");
		}
		inter.addCritere(c1);
		return inter;
		
	}

	private static Voiture saisirVoiture(Scanner sc) {
		
		String marque = null;
		String modele = null;
		int annee = 0;
		int  prix = 0;
		
		System.out.println("Marque :");
		try {
			marque = sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Marque invalide \n");
		}
		
		System.out.println("Modele :");
		try {
			modele = sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Modele invalide \n");
		}
		
		System.out.println("Date de production :");
		try {
			annee = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Date invalide \n");
		}
		
		System.out.println("Prix :");
		try {
			prix = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Prix invalide !!!\n");
		}
		
		Voiture v = new Voiture(marque, modele, annee, prix);
		return v;
	}
	
private static void Serialisation(Agence agence) 
{
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("Agence.txt"));
			
			 try {
					oos.writeObject(agence);
					System.out.println("Enregistrement réussi");
					oos.flush();
				} finally {
					oos.close();
				}
		} catch (FileNotFoundException e) {
			System.out.println("Enregistrement échoué : FILE NOT FOUND");
			// TODO Auto-generated catch block
		} catch (IOException e) {
			System.out.println("Enregistrement échoué ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 		
		
	}

}
