package by.training.compositetask;

import by.training.compositetask.exception.TextComponentException;
import by.training.compositetask.interpreter.InterpreterContext;
import org.junit.Assert;
import org.junit.Test;

public class CompositeInterpreterTest {

    private InterpreterContext interpreterContext = new InterpreterContext();

    @Test
    public void testExpressionCalculateFirst() throws TextComponentException {
        String expression = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
        int actual = interpreterContext.evaluate(expression);
        int expected = (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78;
        Assert.assertEquals(actual, expected);;
    }

    @Test
    public void testExpressionCalculateSecond() throws TextComponentException {
        String expression = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
        int actual = interpreterContext.evaluate(expression);
        int expected = 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1);
        Assert.assertEquals(actual, expected);;
    }

    @Test
    public void testExpressionCalculateThird() throws TextComponentException {
        String expression = "13<<2";
        int actual = interpreterContext.evaluate(expression);
        int expected = 13<<2;
        Assert.assertEquals(actual, expected);;
    }

    @Test
    public void testExpressionCalculateFourth() throws TextComponentException {
        String expression = "(7^5|1&2<<(2|5>>2&71))|1200";
        int actual = interpreterContext.evaluate(expression);
        int expected = (7^5|1&2<<(2|5>>2&71))|1200;
        Assert.assertEquals(actual, expected);;
    }

}
