package histoire;

import villagegaulois.Etal;

public class ScenarioCasDegrade {

    public static void main(String[] args) {
        Etal etal = new Etal(); // jamais occupé
        try {
            etal.libererEtal(); // doit lancer une exception
        } catch (Exception e) {
            System.out.println("Exception capturée : " + e.getMessage());
        }
        System.out.println("Fin du test");
    }
}