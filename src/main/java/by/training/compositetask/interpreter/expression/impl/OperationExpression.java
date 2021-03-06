package by.training.compositetask.interpreter.expression.impl;

import by.training.compositetask.interpreter.expression.AbstractExpression;

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
