package villagegaulois;

public class VillageSansChefException extends Exception {

    public VillageSansChefException() {
        super("Le village n'a pas de chef !");
    }
}
