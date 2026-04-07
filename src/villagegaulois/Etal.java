package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		etalOccupe = false;
		StringBuilder chaine = new StringBuilder();
		try {
			chaine.append("Le vendeur " + vendeur.getNom() + " quitte son étal, ");
			int produitVendu = quantiteDebutMarche - quantite;
			if (produitVendu > 0) {
				chaine.append("il a vendu ");
				chaine.append( produitVendu); 
				chaine.append(" parmi ");
				chaine.append( produit);
				chaine.append( ".\n");
			} else {
				chaine.append("il n'a malheureusement rien vendu.\n");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite + " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		if(!etalOccupe) {
			throw new IllegalStateException("L'etal n'est pas occupé");
		}
		if(quantiteAcheter < 1) {
			throw new IllegalArgumentException("La quantite ne peut pas être négative");
		}
		StringBuilder chaine = new StringBuilder();
		try {
			chaine.append(acheteur.getNom());
			chaine.append(" veut acheter ");
			chaine.append( " " );
			chaine.append( " à ") ;
			chaine.append( vendeur.getNom());
			if (quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " );
				chaine.append(quantite);
				chaine.append( ", ");
				chaine.append(acheteur.getNom());
				chaine.append(" vide l'étal de "); 
				chaine.append(vendeur.getNom());
				chaine.append(".\n");
				quantiteAcheter = quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(". " );
				chaine.append(acheteur.getNom());
				chaine.append(", est ravi de tout trouver sur l'étal de ");
				chaine.append(vendeur.getNom());
				chaine.append("\n");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return chaine.toString();
	}

	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}

}
