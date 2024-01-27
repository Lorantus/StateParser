package or.rd.parser;

public class QcuIndicator implements Indicator {
    private final String nom;
    private final int period;

    public QcuIndicator(String nom, int period) {
        this.nom = nom;
        this.period = period;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int period() {
        return period;
    }
}
