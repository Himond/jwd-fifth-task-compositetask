package by.training.compositetatext.interpreter;

import by.training.compositetatext.exception.TextComponentException;
import by.training.compositetatext.interpreter.expression.AbstractExpression;
import by.training.compositetatext.interpreter.expression.impl.NumberExpression;
import by.training.compositetatext.interpreter.expression.impl.OperationExpression;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class TextInterpreterContext {
    private static final String EXPRESSION_DELIMITER_REGEX =
            "(?<=\\d)(?=[&|^><])|(?<=[&|^><])(?=\\d)|(?<=\\()|(?<=\\))|(?=\\))|(?=\\()|(?<=\\~)";


    public int evaluate(String stringExpression) throws TextComponentException {
        String[] tokens = stringExpression.split(EXPRESSION_DELIMITER_REGEX);
        Deque<AbstractExpression> polishNotationDeque = toReversePolishNotation(tokens);
        AbstractExpression syntaxTree = buildExpressionTree(polishNotationDeque);
        return syntaxTree.interpret();



    }





    private AbstractExpression buildExpressionTree(Deque<AbstractExpression> expressions) {
        Deque<AbstractExpression> bufferDeque = new ArrayDeque<>();
        for (AbstractExpression expression : expressions) {
            if (expression.getClass() == OperationExpression.class) {
                AbstractExpression secondOperand = bufferDeque.removeLast();
                AbstractExpression firstOperand;
                try {
                    firstOperand = bufferDeque.removeLast();
                }catch (NoSuchElementException e){
                    firstOperand = new NumberExpression(0);
                }

                OperationExpression operationExpression = (OperationExpression) expression;
                operationExpression.initializeOperands(firstOperand, secondOperand);
                bufferDeque.add(operationExpression);
            } else {
                bufferDeque.add(expression);
            }
        }
        return bufferDeque.removeFirst();
    }

    /**
     * @return Iterable of expressions adjusted according to reverse polish notation
     */
    private Deque<AbstractExpression> toReversePolishNotation(String[] tokens) throws TextComponentException {
        Deque<AbstractExpression> result = new ArrayDeque<>();
        Deque<OperationType> operationBuffer = new ArrayDeque<>();

        for (String token : tokens) {
            if (OperationType.contains(token)) {
                OperationType operationType = OperationType.findByValue(token);
                int operationPriority = operationType.getPriority();

                while (!operationBuffer.isEmpty() && operationBuffer.peekLast().getPriority() >= operationPriority) {
                    OperationType poppedOperation = operationBuffer.removeLast();
                    AbstractExpression operationExpression = new OperationExpression(poppedOperation);
                    result.add(operationExpression);
                }
                operationBuffer.add(operationType);

            } else {
                try {
                    int value = Integer.parseInt(token);
                    AbstractExpression operandExpression = new NumberExpression(value);
                    result.add(operandExpression);
                } catch (NumberFormatException e) {
                    throw new TextComponentException("Invalid token: " + token, e);
                }
            }
        }

        while (!operationBuffer.isEmpty()) {
            OperationType operationType = operationBuffer.removeLast();
            AbstractExpression operationExpression = new OperationExpression(operationType);
            result.add(operationExpression);
        }

        return result;
    }


}
