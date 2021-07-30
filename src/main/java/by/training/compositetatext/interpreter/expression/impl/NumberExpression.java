package by.training.compositetatext.interpreter.expression.impl;

import by.training.compositetatext.interpreter.expression.AbstractExpression;

public class NumberExpression implements AbstractExpression {
    private final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpret() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(value);
        return sb.toString();
    }
}
