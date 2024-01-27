package org.rd.parser.parsing;

class Chain {
    static final Chain EMPTY = new Chain(null);

    private final Chain next;

    protected Chain(Chain next) {
        this.next = next;
    }

    void applyNext(Context context, Row row) {
        next.applyNext(context, row);
    }
}
