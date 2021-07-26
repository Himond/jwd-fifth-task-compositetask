package by.training.compositetatext.parser;

import by.training.compositetatext.entity.impl.TextComposite;

public class ParagraphParser extends AbstractParser{

    private static final String PARAGRAPH_SPLIT_REGEX = "    ";

    public ParagraphParser() {
        super(new WordParser());
    }

    @Override
    public void parse(TextComposite composite, String part) {
        String[] paragraphs = part.split(PARAGRAPH_SPLIT_REGEX);

        for (String paragraph : paragraphs) {
            TextComposite paragraphComponent = new TextComposite();
            composite.add(paragraphComponent);
            getHandler().parse(paragraphComponent, paragraph);
        }

    }
}
