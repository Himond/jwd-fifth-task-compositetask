package by.training.compositetatext.interpreter;

import by.training.compositetatext.exception.TextComponentException;
import by.training.compositetatext.interpreter.expression.AbstractExpression;
import by.training.compositetatext.interpreter.expression.impl.NumberExpression;
import by.training.compositetatext.interpreter.expression.impl.OperationExpression;

import java.util.*;

public class InterpreterContext {

    private static final String OPENING_PARENTHESIS = "(";
    private static final String CLOSING_PARENTHESIS = ")";
    private static final String EXPRESSION_DELIMITER_REGEX =
            "(?<=\\d)(?=[&|^><])|(?<=[&|^><])(?=\\d)|(?<=\\()|(?<=\\))|(?=\\))|(?=\\()|(?<=\\~)";


    public int evaluate(String stringExpression) throws TextComponentException {

        List<String> array = new ArrayList(Arrays.asList(stringExpression.split(EXPRESSION_DELIMITER_REGEX)));
        Deque<Integer> openParenthesis = new ArrayDeque<>();
        Deque<Integer> closeParenthesis = new ArrayDeque<>();

        for(int i = 0; i < array.size(); i++){
            if (array.get(i).equals(OPENING_PARENTHESIS)){
                openParenthesis.push(i);
            }
        }

        while (!openParenthesis.isEmpty()){
            List<String> partOfExpression;
            int start = openParenthesis.pop();
            for (int j = start + 1; j < array.size(); j++){
                if (array.get(j).equals(CLOSING_PARENTHESIS)){
                    closeParenthesis.offer(j);
                    break;
                }
            }
            partOfExpression = new ArrayList<>(array.subList(start + 1, closeParenthesis.peek()));
            if (closeParenthesis.peek() >= start) {
                array.subList(start, closeParenthesis.poll() + 1).clear();
            }
            int number = calculate(partOfExpression);
            array.add(start, Integer.toString(number));
        }
        return calculate(array);

    }

    private int calculate(List<String> expression) throws TextComponentException {
        Deque<AbstractExpression> polishNotationDeque = toReversePolishNotation(expression);
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

    private Deque<AbstractExpression> toReversePolishNotation(List<String> tokens) throws TextComponentException {
        Deque<AbstractExpression> result = new ArrayDeque<>();
        Deque<ExpressionType> operationBuffer = new ArrayDeque<>();

        for (String token : tokens) {
            if (ExpressionType.contains(token)) {
                ExpressionType operationType = ExpressionType.findByValue(token);
                int operationPriority = operationType.getPriority();

                while (!operationBuffer.isEmpty() && operationBuffer.peekLast().getPriority() >= operationPriority) {
                    ExpressionType poppedOperation = operationBuffer.removeLast();
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
            ExpressionType operationType = operationBuffer.removeLast();
            AbstractExpression operationExpression = new OperationExpression(operationType);
            result.add(operationExpression);
        }

        return result;
    }


}
