package by.training.compositetatext.parser;

import by.training.compositetatext.entity.impl.Punctuation;
import by.training.compositetatext.entity.impl.TextComposite;
import by.training.compositetatext.exception.TextComponentException;
import by.training.compositetatext.interpreter.TextInterpreterContext;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser{

    private static final String LEXEME_SPLIT_REGEX = "\\s";
    private static final String WORD_REGEX = "[a-zA-Z|а-яА-Я]+";
    private static final String EXPRESSION_REGEX = "(\\S+\\d+\\S+(?<=[\\)\\d]))";
    private static final String WORD_EXPRESSION_AND_PUNCTUATION_REGEX = "([a-zA-Z|а-яА-Я]+)|(\\S+\\d+\\S+(?<=[\\)\\d]))|([^a-zA-Z|а-яА-Я|\\s])";
    private static final String EXPRESSION_DELIMITER_REGEX =
            "(?<=\\d)(?=[&|^><])|(?<=[&|^><])(?=\\d)|(?<=\\()|(?<=\\))|(?=\\))|(?=\\()|(?<=\\~)";


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
                    List<String> array = new ArrayList(Arrays.asList(line.split(EXPRESSION_DELIMITER_REGEX)));
                    Deque<Integer> openParenthesis = new ArrayDeque<>();
                    Deque<Integer> closeParenthesis = new ArrayDeque<>();

                    for(int i = 0; i < array.size(); i++){
                        if (array.get(i).equals("(")){
                            openParenthesis.push(i);
                        }
                    }

                    while (!openParenthesis.isEmpty()){
                        StringBuilder sb = new StringBuilder();
                        int start = openParenthesis.pop();

                        for (int j = start + 1; j < array.size(); j++){
                            if (!array.get(j).equals(")")){
                                sb.append(array.get(j));
                            }else {
                                closeParenthesis.offer(j);
                                break;
                            }
                        }
                        if (closeParenthesis.peek() >= start) {
                            array.subList(start, closeParenthesis.poll() + 1).clear();
                        }
                        int number = interpreterContext.evaluate(sb.toString());
                        array.add(start, Integer.toString(number));



                    }

                    StringBuilder str = new StringBuilder();
                    for (String st: array){
                        str.append(st);
                    }

                    int calculatedExpression = interpreterContext.evaluate(str.toString());

                    TextComposite expressionComponent = new TextComposite();
                    composite.add(expressionComponent);
                    getHandler().parse(expressionComponent, String.valueOf(calculatedExpression));


                    // FIXME: 27.07.2021 
                }else {
                    Punctuation punctuation = new Punctuation(line.charAt(0));
                    composite.add(punctuation);
                }
            }
        }

    }
}
