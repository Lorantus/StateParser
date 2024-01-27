package or.rd.parser;

import or.rd.parser.parsing.Context;
import or.rd.parser.parsing.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImportRow implements Row {
    private final String nom;
    private final boolean qcm;
    private final int period;
    private boolean value;

    private ImportRow(String nom, boolean qcm, int period) {
        this.nom = nom;
        this.qcm = qcm;
        this.period = period;
    }

    private ImportRow(String nom, boolean value) {
        this(nom, false, 0);
        this.value = value;
    }

    public static Row createQcuIndicatorRow(String nom, int period) {
        return new ImportRow(nom, false, period);
    }

    public static Row createQcmIndicatorRow(String nom, int period) {
        return new ImportRow(nom, true, period);
    }

    public static Row createQcmIndicatorChoiceRow(String nom, boolean value) {
        return new ImportRow(nom, value);
    }

    @Override
    public boolean isQCM() {
        return qcm;
    }

    @Override
    public boolean isWithPeriod() {
        return period > 0;
    }

    @Override
    public Optional<QcmChoice> toQcmChoice() {
        List<String> errors = validateChoice();
        if(errors.isEmpty()) {
            return Optional.of(new QcmChoice(nom, value));
        } else {
            return Optional.empty();
        }
    }

    private List<String> validateChoice() {
        List<String> errors = new ArrayList<>();
        if (null == nom || nom.isBlank()) {
            errors.add("Un choix doit avoir un nom.");
        }
        return errors;
    }

    @Override
    public Optional<QcmIndicator> toQcmIndicator(Context context) {
        List<String> errors = validateIndicator();
        if(errors.isEmpty()) {
            return Optional.of(new QcmIndicator(nom, period));
        } else {
            errors.forEach(context::addError);
        }
        return Optional.empty();
    }

    @Override
    public Optional<QcuIndicator> toQcuIndicator(Context context) {
        List<String> errors = validateIndicator();
        if(errors.isEmpty()) {
            return Optional.of(new QcuIndicator(nom, period));
        } else {
            errors.forEach(context::addError);
        }
        return Optional.empty();
    }

    private List<String> validateIndicator() {
        List<String> errors = new ArrayList<>();
        if (null == nom || nom.isBlank()) {
            errors.add("Un indicateur doit avoir un nom.");
        }
        if(!isWithPeriod()) {
            errors.add("Un indicateur doit avoir une période.");
        }
        return errors;
    }
}
