package or.rd.parser.parsing;

class IndicatorParser implements Parser {
    public static final QcmChain PARSING_CHAIN = new QcmChain(new QcuChain());

    private final Context context;

    IndicatorParser(Context context) {
        this.context = context;
    }

    @Override
    public void parseRow(Row row) {
        PARSING_CHAIN.doNext(context, row);
    }

    abstract static class Chain {

        private final Chain next;

        protected Chain(Chain next) {
            this.next = next;
        }

        void doNext(Context context, Row row) {
            next.doNext(context, row);
        }
    }

    static class QcmChain extends Chain {
        public QcmChain(Chain next) {
            super(next);
        }

        @Override
        public void doNext(Context context, Row row) {
            if (row.isQCM()) {
                parseQcmIndicator(context, row);
            } else {
                super.doNext(context, row);
            }
        }

        private static void parseQcmIndicator(Context context, Row row) {
            row.toQcmIndicator(context)
                .ifPresent(indicator -> {
                    context.add(indicator);
                    context.moveTo(new QcmChoiceParser(context, indicator));
                });
        }
    }

    static class QcuChain extends Chain {
        public QcuChain() {
            super(null);
        }

        @Override
        public void doNext(Context context, Row row) {
            row.toQcuIndicator(context)
                .ifPresent(context::add);
        }
    }
}
