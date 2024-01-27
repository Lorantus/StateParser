package org.rd.parser.parsing;

class QcmIndicatorChain extends Chain {

    QcmIndicatorChain(Chain next) {
        super(next);
    }

    @Override
    public void applyNext(Context context, Row row) {
        if (row.isQcmRow()) {
            parseQcmIndicator(context, row);
        } else {
            super.applyNext(context, row);
        }
    }

    private void parseQcmIndicator(Context context, Row row) {
        row.toQcmIndicator(context)
            .ifPresent(indicator -> {
                context.add(indicator);
                context.moveTo(new QcmChoiceParser(indicator));
            });
    }
}
