package by.training.compositetatext.interpreter.expression.impl;

import by.training.compositetatext.interpreter.expression.AbstractExpression;

import java.util.function.BiFunction;

public class OperationExpression implements AbstractExpression {
    private final BiFunction<AbstractExpression, AbstractExpression, Integer> operation;
    private AbstractExpression firstOperand;
    private AbstractExpression secondOperand;

    public OperationExpression(BiFunction<AbstractExpression, AbstractExpression, Integer> operation) {
        this.operation = operation;
    }

    public void initializeOperands(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public int interpret() {
        // if operands are not set, result of interpreting is 0 (BiFunction's apply() method does not throw exceptions)
        return (firstOperand != null && secondOperand != null)
                ? operation.apply(firstOperand, secondOperand)
                : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OperationExpression{");
        sb.append("operation=").append(operation);
        sb.append(", firstOperand=").append(firstOperand);
        sb.append(", secondOperand=").append(secondOperand);
        sb.append('}');
        return sb.toString();
    }
}
