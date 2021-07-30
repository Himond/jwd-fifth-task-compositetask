package by.training.compositetatext.parser;

import by.training.compositetatext.entity.impl.TextComposite;
import by.training.compositetatext.exception.TextComponentException;

public class SentenceParser extends AbstractParser{

    private static final String SENTENCE_SPLIT_REGEX = "(?<=\\.{3}|\\.|\\?|!)\\s";


    public SentenceParser() {
        super(new LexemeParser());
    }

    @Override
    public void parse(TextComposite composite, String part) throws TextComponentException {
        String[] sentences = part.split(SENTENCE_SPLIT_REGEX);
        for (String sentence : sentences) {
            TextComposite sentenceComponent = new TextComposite();
            composite.add(sentenceComponent);
            getHandler().parse(sentenceComponent, sentence);

        }

    }
}
