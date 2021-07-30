package by.training.compositetatext.parser;

import by.training.compositetatext.entity.impl.TextComposite;
import by.training.compositetatext.exception.TextComponentException;

public class ParagraphParser extends AbstractParser{

    private static final String PARAGRAPH_SPLIT_REGEX = "(?m)(?=^\\s{4})";

    public ParagraphParser() {
        super(new SentenceParser());
    }

    @Override
    public void parse(TextComposite composite, String part) throws TextComponentException {
        String[] paragraphs = part.split(PARAGRAPH_SPLIT_REGEX);

        for (String paragraph : paragraphs) {
            TextComposite paragraphComponent = new TextComposite();
            composite.add(paragraphComponent);
            getHandler().parse(paragraphComponent, paragraph.trim());
        }

    }
}
