package org.rd.parser;

public class QcuIndicator implements Indicator {
    private final String name;
    private final int period;

    public QcuIndicator(String name, int period) {
        this.name = name;
        this.period = period;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int period() {
        return period;
    }
}
