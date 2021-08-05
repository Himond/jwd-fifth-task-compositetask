package by.training.compositetask.parser;

import by.training.compositetask.entity.TextComponentType;
import by.training.compositetask.entity.TextComposite;
import by.training.compositetask.exception.TextComponentException;


public class ParagraphParser extends AbstractParser{

    private static final String PARAGRAPH_SPLIT_REGEX = "[\\t\\n]+";

    public ParagraphParser() {
        super(new SentenceParser());
    }

    @Override
    public void parse(TextComposite composite, String text) throws TextComponentException {
        String[] paragraphs = text.split(PARAGRAPH_SPLIT_REGEX);

        for (String paragraph : paragraphs) {
            var paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            composite.add(paragraphComponent);
            getHandler().parse(paragraphComponent, paragraph);
        }
    }
}
