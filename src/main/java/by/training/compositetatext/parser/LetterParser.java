package by.training.compositetatext.parser;

import by.training.compositetatext.entity.TextComponent;
import by.training.compositetatext.entity.impl.Letter;
import by.training.compositetatext.entity.impl.TextComposite;

import java.util.ArrayList;
import java.util.List;

public class LetterParser extends AbstractParser{

    private List<Letter> dataLetters = new ArrayList<>();

    @Override
    public void parse(TextComposite composite, String part) {
        char[] charArray = part.toCharArray();
        for (char symbolValue : charArray) {
            composite.add(getSymbolFromData(symbolValue));
        }
    }

    private TextComponent getSymbolFromData(char ch){
        Letter letter = null;
        for (Letter value: dataLetters){
            if(value.toString().equals(String.valueOf(ch))){
                letter = value;
            }
        }
        if (letter == null) {
            letter = new Letter(ch);
            dataLetters.add(letter);
        }
        return letter;
    }

}
