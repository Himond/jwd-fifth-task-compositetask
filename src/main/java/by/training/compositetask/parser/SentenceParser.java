package by.training.compositetask.parser;

import by.training.compositetask.entity.TextComponentType;
import by.training.compositetask.entity.TextComposite;
import by.training.compositetask.exception.TextComponentException;

public class SentenceParser extends AbstractParser{

    private static final String SENTENCE_SPLIT_REGEX = "(?<=\\.{3}|\\.|\\?|!)\\s";

    public SentenceParser() {
        super(new LexemeParser());
    }

    @Override
    public void parse(TextComposite composite, String paragraph) throws TextComponentException {

        String[] sentences = paragraph.split(SENTENCE_SPLIT_REGEX);
        for (String sentence : sentences) {
            var sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            composite.add(sentenceComponent);
            getHandler().parse(sentenceComponent, sentence);
        }

    }
}
