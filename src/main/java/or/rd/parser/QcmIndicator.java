package or.rd.parser;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class QcmIndicator implements Indicator {
    private final String nom;
    private final int period;
    private final List<QcmChoice> choices = new ArrayList<>();

    public QcmIndicator(String nom, int period) {
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

    public void add(QcmChoice choice) {
        choices.add(choice);
    }

    public List<QcmChoice> getChoices() {
        return unmodifiableList(choices);
    }
}
