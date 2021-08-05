package by.training.compositetask.parser;

import by.training.compositetask.entity.TextComponent;
import by.training.compositetask.entity.Letter;
import by.training.compositetask.entity.TextComposite;

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

    private TextComponent getSymbolFromData(char symbol){
        Letter letter = null;
        for (Letter value: dataLetters){
            if(value.toString().equals(String.valueOf(symbol))){
                letter = value;
            }
        }
        if (letter == null) {
            letter = new Letter(symbol);
            dataLetters.add(letter);
        }
        return letter;
    }

}
