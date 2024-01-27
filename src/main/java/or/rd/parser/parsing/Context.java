package or.rd.parser.parsing;

import or.rd.parser.Indicator;
import or.rd.parser.QcmIndicator;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Context {
    private Parser parser;
    private final List<Indicator> indicators = new ArrayList<>();
    private final List<String> errors = new ArrayList<>();

    public Context() {
        this.parser = new IndicatorParser(this);
    }

    void moveTo(Parser parser) {
        this.parser = parser;
    }

    public void parseRow(Row row) {
        parser.parseRow(row);
    }

    void add(Indicator indicator) {
        indicators.add(indicator);
    }

    public List<Indicator> getIndicators() {
        return unmodifiableList(indicators);
    }

    public List<String> getErrors() {
        return unmodifiableList(errors);
    }

    public void parseRows(List<Row> rows) {
        rows.forEach(this::parseRow);
    }

    public void addError(String message) {
        errors.add(message);
    }
}
