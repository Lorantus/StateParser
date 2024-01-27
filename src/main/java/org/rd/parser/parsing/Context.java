package org.rd.parser.parsing;

import org.rd.parser.Indicator;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Context {
    private Parser parser;
    private final List<Indicator> indicators = new ArrayList<>();

    public Context() {
        moveTo(new IndicatorParser(this));
    }

    final void moveTo(Parser parser) {
        this.parser = parser;
    }

    public void parseRow(Row row) {
        parser.parseRow(this, row);
    }

    void add(Indicator indicator) {
        indicators.add(indicator);
    }

    public List<Indicator> getIndicators() {
        return unmodifiableList(indicators);
    }

    public void parseRows(List<Row> rows) {
        rows.forEach(this::parseRow);
    }
}
