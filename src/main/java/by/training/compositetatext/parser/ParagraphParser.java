package by.training.compositetatext.parser;

import by.training.compositetatext.entity.TextComponentIndent;
import by.training.compositetatext.entity.TextComposite;
import by.training.compositetatext.exception.TextComponentException;

public class ParagraphParser extends AbstractParser{

    private static final String PARAGRAPH_SPLIT_REGEX = "[\\t\\n]+";

    public ParagraphParser() {
        super(new SentenceParser());
    }

    @Override
    public void parse(TextComposite composite, String text) throws TextComponentException {
        String[] paragraphs = text.split(PARAGRAPH_SPLIT_REGEX);
        for (String paragraph : paragraphs) {
            var paragraphComponent = new TextComposite(TextComponentIndent.PARAGRAPH);
            composite.add(paragraphComponent);
            getHandler().parse(paragraphComponent, paragraph);
        }
    }
}
