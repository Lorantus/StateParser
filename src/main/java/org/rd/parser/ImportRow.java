package org.rd.parser;

import org.rd.parser.parsing.Context;
import org.rd.parser.parsing.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImportRow implements Row {
    private final List<String> errors;
    private final String name;
    private final boolean qcmRow;
    private final int period;
    private boolean value;

    private ImportRow(String name, boolean qcmRow, int period) {
        this.errors = new ArrayList<>();
        this.name = name;
        this.qcmRow = qcmRow;
        this.period = period;
    }

    private ImportRow(String name, boolean value) {
        this(name, false, 0);
        this.value = value;
    }

    public static Row createQcuIndicatorRow(String name, int period) {
        return new ImportRow(name, false, period);
    }

    public static Row createQcmIndicatorRow(String name, int period) {
        return new ImportRow(name, true, period);
    }

    public static Row createQcmIndicatorChoiceRow(String name, boolean value) {
        return new ImportRow(name, value);
    }

    @Override
    public boolean isQcmRow() {
        return qcmRow;
    }

    @Override
    public boolean isQcmChoice() {
        return !(period > 0);
    }

    private boolean hasError() {
        return !errors.isEmpty();
    }

    @Override
    public Optional<QcmChoice> toQcmChoice() {
       validateChoice();
        if(!isQcmChoice()) {
            errors.add("La ligne doit être marquée comme étant un choix pour un QCM.");
        }
        if(hasError()) {
            return Optional.empty();
        } else {
            return Optional.of(new QcmChoice(name, value));
        }
    }

    private void validateChoice() {
        if (null == name || name.isBlank()) {
            errors.add("Un choix doit avoir un nom.");
        }
    }

    private void validateIndicator() {
        if (null == name || name.isBlank()) {
            errors.add("Un indicateur doit avoir un nom.");
        }
        if(isQcmChoice()) {
            errors.add("Un indicateur doit avoir une période.");
        }
    }

    @Override
    public Optional<QcmIndicator> toQcmIndicator(Context context) {
        validateIndicator();
        if(!isQcmRow()) {
            errors.add("La ligne doit être marquée comme étant pour un QCM.");
        }
        if(hasError()) {
            return Optional.empty();
        } else {
            return Optional.of(new QcmIndicator(name, period));
        }
    }

    @Override
    public Optional<QcuIndicator> toQcuIndicator(Context context) {
        validateIndicator();
        if(isQcmRow()) {
            errors.add("La ligne doit être marquée comme étant pour un indicateur autre que QCM.");
        }
        if(hasError()) {
            return Optional.empty();
        } else {
            return Optional.of(new QcuIndicator(name, period));
        }
    }
}
