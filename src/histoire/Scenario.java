package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class Scenario {

    public static void main(String[] args) {
        // Création du village
        Village village = new Village("le village des irréductibles", 10, 5);

        // Création du chef
        Chef abraracourcix = new Chef("Abraracourcix", 10, village);
        village.setChef(abraracourcix);

        // Création des habitants
        Druide druide = new Druide("Panoramix", 2, 5, 10);
        Gaulois obelix = new Gaulois("Obélix", 25);
        Gaulois asterix = new Gaulois("Astérix", 8);
        Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
        Gaulois bonemine = new Gaulois("Bonemine", 7);

        // on ajoute les habitants au village
        village.ajouterHabitant(bonemine);
        village.ajouterHabitant(assurancetourix);
        village.ajouterHabitant(asterix);
        village.ajouterHabitant(obelix);
        village.ajouterHabitant(druide);
        village.ajouterHabitant(abraracourcix);

        // affichge les villageois
        try {
            System.out.println(village.afficherVillageois());
        } catch (VillageSansChefException e) {
            System.out.println(e.getMessage());
        }

        // Installation des vendeurs
        System.out.println(village.installerVendeur(bonemine, "fleurs", 20));
        System.out.println(village.installerVendeur(assurancetourix, "lyres", 5));
        System.out.println(village.installerVendeur(obelix, "menhirs", 2));
        System.out.println(village.installerVendeur(druide, "fleurs", 10));

        // Acheter des produits
        Etal etalFleur = village.rechercherEtal(bonemine);
        if (etalFleur != null) {
            System.out.println(etalFleur.acheterProduit(10, abraracourcix));
            System.out.println(etalFleur.acheterProduit(15, obelix));
            System.out.println(etalFleur.acheterProduit(15, assurancetourix));
        }

        // Libérer les étals
        System.out.println(village.partirVendeur(bonemine));

        // Afficher le marché après libération
        System.out.println(village.afficherMarche());
    }
}