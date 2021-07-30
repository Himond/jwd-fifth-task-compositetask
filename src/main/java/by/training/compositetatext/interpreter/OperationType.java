package by.training.compositetatext.interpreter;

import by.training.compositetatext.interpreter.expression.AbstractExpression;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum OperationType implements BiFunction<AbstractExpression, AbstractExpression, Integer> {

    AND("&", 3) {
        @Override
        public Integer apply(AbstractExpression firstOperand, AbstractExpression secondOperand) {
            return firstOperand.interpret() & secondOperand.interpret();
        }
    },
    UNARY_NAG("~", 5) {
        @Override
        public Integer apply(AbstractExpression firstOperand, AbstractExpression secondOperand) {
            return ~secondOperand.interpret();
        }
    },
    OR("|", 1) {
        @Override
        public Integer apply(AbstractExpression firstOperand, AbstractExpression secondOperand) {
            return firstOperand.interpret() | secondOperand.interpret();
        }
    },
    XOR("^", 2) {
        @Override
        public Integer apply(AbstractExpression firstOperand, AbstractExpression secondOperand) {
            return firstOperand.interpret() ^ secondOperand.interpret();
        }
    },
    RIGHT_SHIFT(">>", 4) {
        @Override
        public Integer apply(AbstractExpression firstOperand, AbstractExpression secondOperand) {
            return firstOperand.interpret() >> secondOperand.interpret();
        }
    },
    UNSIGNED_RIGHT_SHIFT(">>>", 4) {
        @Override
        public Integer apply(AbstractExpression firstOperand, AbstractExpression secondOperand) {
            return firstOperand.interpret() >>> secondOperand.interpret();
        }
    },
    LEFT_SHIFT("<<", 4) {
        @Override
        public Integer apply(AbstractExpression firstOperand, AbstractExpression secondOperand) {
            return firstOperand.interpret() << secondOperand.interpret();
        }
    };

    private static final Map<String, OperationType> nameToEnumMap;

    private final String value;
    private final int priority;

    static {
        nameToEnumMap = new HashMap<>();

        for (OperationType item : values()) {
            nameToEnumMap.put(item.value, item);
        }
    }

    OperationType(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public String getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }

    public static OperationType findByValue(String value) {
        return nameToEnumMap.get(value);
    }

    public static boolean contains(String testString) {
        return Arrays.stream(values())
                .map(OperationType::getValue)
                .anyMatch(value -> value.equals(testString));
    }
}
