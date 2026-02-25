package villagegaulois;

import personnages.Gaulois;

//classe interne Marhcé
public class Marche {
	private Etal[] etals;

	public Marche(int nbEtals) {
		etals = new Etal[nbEtals];
		for (int i = 0; i < nbEtals; i++) {
			etals[i] = new Etal();
		}
	}

	public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
		if (indiceEtal >= 0 && indiceEtal < etals.length) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

	}

	public int trouverEtalLibre() {
		for (int i = 0; i < etals.length; i++) {
			if (!etals[i].isEtalOccupe())
				return i;
		}
		return -1;
	}

	public afficherMarche() {
		int nbEtalVide=0;
		String etalOccupe="";
		for (int i =0;i<etals.length;i++) {
			if (etals[i].isEtalOccupe()) {
				etalOccupe+= etals[i].afficherEtal();
			}
			nbEtalVide++;
		}
		if (nbEtalVide>0){
			etalOccupe+= "Il reste " +
					nbEtalVide + " étals non utilisés dans le marché.\n";
		}
		return etalOccupe;
	}
}
