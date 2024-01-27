package org.rd.parser;

public class QcmChoice {
    private final String name;
    private final boolean value;

    public QcmChoice(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public boolean getValue() {
        return value;
    }
}
