package by.training.compositetatext.parser;

import by.training.compositetatext.entity.TextComponentIndent;
import by.training.compositetatext.entity.TextComposite;
import by.training.compositetatext.exception.TextComponentException;

public class SentenceParser extends AbstractParser{

    private static final String SENTENCE_SPLIT_REGEX = "(?<=\\.{3}|\\.|\\?|!)\\s";

    public SentenceParser() {
        super(new LexemeParser());
    }

    @Override
    public void parse(TextComposite composite, String paragraph) throws TextComponentException {

        String[] sentences = paragraph.split(SENTENCE_SPLIT_REGEX);
        for (String sentence : sentences) {
            var sentenceComponent = new TextComposite(TextComponentIndent.SENTENCE);
            composite.add(sentenceComponent);
            getHandler().parse(sentenceComponent, sentence);
        }

    }
}
