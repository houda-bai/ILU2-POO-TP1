package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			String nomGauloisTableau = gaulois.getNom();
			if (nomGauloisTableau != null && nomGauloisTableau.equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		if (chef == null) {
	        throw new VillageSansChefException();
	    }

		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef ");
			chaine.append(chef.getNom());
			chaine.append(".\n");
		} else {
			chaine.append("Au village du chef ");
			chaine.append(chef.getNom());
			chaine.append(" vivent les légendaires gaulois :\n");
			
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- ");
				chaine.append(villageois[i].getNom());
				chaine.append("\n");
			}
		}
		return chaine.toString();
	}

	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom());
		chaine.append(" cherche un endroit pour vendre ");
		chaine.append(nbProduit);
		chaine.append(" ");
		chaine.append(produit);
		chaine.append(".\n");
		
		int indiceEtalLibre= marche.trouverEtalLibre();
		if (indiceEtalLibre!=-1) {
			marche.utiliserEtal(indiceEtalLibre, vendeur, produit, nbProduit);
			chaine.append("Le vendeur ");
			chaine.append(vendeur.getNom());
			chaine.append(" vend des ");
			chaine.append(produit);
			chaine.append(" à l'étal n°");
			chaine.append((indiceEtalLibre+1));
			chaine.append(".\n");
		}else {
			chaine.append("Il n'y a plus d'étal disponible.\n");		}
		return chaine.toString();}

	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etals = marche.trouverEtals(produit);
		if (etals.length == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des ");
			chaine.append(produit);
			chaine.append(" au marché.\n");
		}else if((etals.length == 1)){
			chaine.append("Seul le vendeur ");
			chaine.append(etals[0].getVendeur().getNom());
			chaine.append(" propose des ");
			chaine.append(produit);
			chaine.append(" au marché.\n");
		}else {
			chaine.append("Les vendeurs qui proposent des ");
			chaine.append(produit);
			chaine.append(" sont : \n");
			for (Etal etal :etals) {
				chaine.append("- ");
				chaine.append(etal.getVendeur().getNom());
				chaine.append("\n");
			}
			
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
		
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();

	    Etal etal = rechercherEtal(vendeur);

	    if (etal != null) {
	        chaine.append(etal.libererEtal());
	    }

	    return chaine.toString();
	}
	
	public String afficherMarche() {
	    return marche.afficherMarche();
	}
	
	
	private static class Marche {
		private Etal[] etals;

		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int taille = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					taille++;
				}
			}

			Etal[] etalsProduit = new Etal[taille];

			for (int i = 0, j = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[j] = etals[i];
					j++;
				}
			}

			return etalsProduit;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (gaulois != null && gaulois.equals(etals[i].getVendeur())) {
					return etals[i];
				}
			}
			return null;
		}

		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalsOccupes = 0;

			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
					nbEtalsOccupes++;
				}
			}
			int nbEtalsVides = etals.length - nbEtalsOccupes;
			if (nbEtalsVides > 0) {
				chaine.append("Il reste ");
				chaine.append(nbEtalsVides);
				chaine.append(" étals non utilisés dans le marché.\n");
			}
			return chaine.toString();
		}
	}
}