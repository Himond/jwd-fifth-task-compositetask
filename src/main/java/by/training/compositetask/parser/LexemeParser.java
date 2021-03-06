package by.training.compositetask.parser;

import by.training.compositetask.entity.TextComponentType;
import by.training.compositetask.entity.TextComposite;
import by.training.compositetask.exception.TextComponentException;

public class LexemeParser extends AbstractParser{

    private static final String LEXEME_SPLIT_REGEX = "\\s";

    public LexemeParser() {
        super(new WordParser());
    }

    @Override
    public void parse(TextComposite composite, String sentence) throws TextComponentException {
        String[] sentences = sentence.split(LEXEME_SPLIT_REGEX);
        for (String lexeme : sentences) {
            var lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            composite.add(lexemeComponent);
            getHandler().parse(lexemeComponent, lexeme);
        }
    }

}
