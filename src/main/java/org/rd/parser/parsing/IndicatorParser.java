package org.rd.parser.parsing;

class IndicatorParser implements Parser {
    public static final QcmIndicatorChain PARSING_CHAIN = new QcmIndicatorChain(new QcuIndicatorChain());

    private final Context context;

    IndicatorParser(Context context) {
        this.context = context;
    }

    @Override
    public void parseRow(Context context, Row row) {
        PARSING_CHAIN.applyNext(this.context, row);
    }
}
