package or.rd.parser.parsing;

import or.rd.parser.QcmChoice;
import or.rd.parser.QcmIndicator;

class QcmChoiceParser implements Parser {
    private final Context context;
    private final QcmIndicator indicator;

    QcmChoiceParser(Context context, QcmIndicator indicator) {
        this.context = context;
        this.indicator = indicator;
    }

    @Override
    public void parseRow(Row row) {
        if(row.isWithPeriod()) {
            context.moveTo(new IndicatorParser(context));
            context.parseRow(row);
        } else {
            row.toQcmChoice()
                .ifPresent(indicator::add);
        }
    }
}
