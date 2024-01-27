package org.rd.parser.parsing;

class QcuIndicatorChain extends Chain {

    QcuIndicatorChain() {
        super(EMPTY);
    }

    @Override
    public void applyNext(Context context, Row row) {
        row.toQcuIndicator(context)
            .ifPresent(context::add);
    }
}
