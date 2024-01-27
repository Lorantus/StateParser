package org.rd.parser.parsing;

import org.rd.parser.QcmIndicator;

class QcmChoiceParser implements Parser {
    private final QcmIndicator indicator;

    QcmChoiceParser(QcmIndicator indicator) {
        this.indicator = indicator;
    }

    @Override
    public void parseRow(Context context, Row row) {
        if (!row.isQcmChoice()) {
            context.moveTo(new IndicatorParser(context));
            context.parseRow(row);
        } else {
            row.toQcmChoice()
                .ifPresent(indicator::add);
        }
    }
}
