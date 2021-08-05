package by.training.compositetask.parser;

import by.training.compositetask.entity.Punctuation;
import by.training.compositetask.entity.TextComponentType;
import by.training.compositetask.entity.TextComposite;
import by.training.compositetask.exception.TextComponentException;
import by.training.compositetask.interpreter.InterpreterContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends AbstractParser{

    private static final String WORD_REGEX = "[a-zA-Z|а-яА-Я]+";
    private static final String EXPRESSION_REGEX = "(\\S+\\d+\\S+(?<=[\\)\\d]))";
    private static final String WORD_EXPRESSION_AND_PUNCTUATION_REGEX =
            "([a-zA-Z|а-яА-Я]+)|(\\S+\\d+\\S+(?<=[\\)\\d]))|([^a-zA-Z|а-яА-Я|\\s])";

    public WordParser( ) {
        super(new LetterParser());
    }

    @Override
    public void parse(TextComposite composite, String lexeme) throws TextComponentException {
        Pattern wordAndPunctuationPattern = Pattern.compile(WORD_EXPRESSION_AND_PUNCTUATION_REGEX);
        InterpreterContext interpreterContext = new InterpreterContext();

        Matcher wordAndPunctuationMatcher = wordAndPunctuationPattern.matcher(lexeme);

        while (wordAndPunctuationMatcher.find()){
            String word = wordAndPunctuationMatcher.group();
            if(word.matches(WORD_REGEX)){
                TextComposite wordComponent = new TextComposite(TextComponentType.WORD);
                composite.add(wordComponent);
                getHandler().parse(wordComponent, word);
            }else if(word.matches(EXPRESSION_REGEX)){
                int calculatedExpression = interpreterContext.evaluate(word);
                TextComposite expressionComponent = new TextComposite(TextComponentType.EXPRESSION);
                composite.add(expressionComponent);
                getHandler().parse(expressionComponent, String.valueOf(calculatedExpression));
            }else {
                Punctuation punctuation = new Punctuation(word.charAt(0));
                composite.add(punctuation);
            }
        }
    }
}
