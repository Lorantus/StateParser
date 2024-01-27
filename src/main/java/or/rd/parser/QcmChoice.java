package or.rd.parser;

public class QcmChoice {
    private final String nom;
    private final boolean value;

    public QcmChoice(String nom, boolean value) {
        this.nom = nom;
        this.value = value;
    }

    public String getNom() {
        return nom;
    }

    public boolean getValue() {
        return value;
    }
}
