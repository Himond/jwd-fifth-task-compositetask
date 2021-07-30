package by.training.compositetatext.parser;

import by.training.compositetatext.entity.impl.Punctuation;
import by.training.compositetatext.entity.impl.TextComposite;
import by.training.compositetatext.exception.TextComponentException;
import by.training.compositetatext.interpreter.TextInterpreterContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser{

    private static final String LEXEME_SPLIT_REGEX = "\\s";
    private static final String WORD_REGEX = "[a-zA-Z|а-яА-Я]+";
    private static final String EXPRESSION_REGEX = "(\\S+\\d+\\S+(?<=[\\)\\d]))";
    private static final String WORD_EXPRESSION_AND_PUNCTUATION_REGEX = "([a-zA-Z|а-яА-Я]+)|(\\S+\\d+\\S+(?<=[\\)\\d]))|([^a-zA-Z|а-яА-Я|\\s])";

    public LexemeParser( ) {
        super(new LetterParser());
    }

    @Override
    public void parse(TextComposite composite, String part) throws TextComponentException {

        String[] lexemes = part.split(LEXEME_SPLIT_REGEX);
        Pattern wordAndPunctuationPattern = Pattern.compile(WORD_EXPRESSION_AND_PUNCTUATION_REGEX);
        TextInterpreterContext interpreterContext = new TextInterpreterContext();

        for (String lexeme : lexemes) {

            Matcher wordAndPunctuationMatcher = wordAndPunctuationPattern.matcher(lexeme);

            while (wordAndPunctuationMatcher.find()){
                String line = wordAndPunctuationMatcher.group();
                if(line.matches(WORD_REGEX)){
                    TextComposite wordComponent = new TextComposite();
                    composite.add(wordComponent);
                    getHandler().parse(wordComponent, line);
                }else if(line.matches(EXPRESSION_REGEX)){
                    int calculatedExpression = interpreterContext.evaluate(line);
                    TextComposite expressionComponent = new TextComposite();
                    composite.add(expressionComponent);
                    getHandler().parse(expressionComponent, String.valueOf(calculatedExpression));
                }else {
                    Punctuation punctuation = new Punctuation(line.charAt(0));
                    composite.add(punctuation);
                }
            }
        }

    }
}
