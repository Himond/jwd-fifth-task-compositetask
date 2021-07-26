package by.training.compositetatext.parser;

import by.training.compositetatext.entity.TextComponent;
import by.training.compositetatext.entity.impl.Letter;
import by.training.compositetatext.entity.impl.Punctuation;
import by.training.compositetatext.entity.impl.TextComposite;

import java.util.ArrayList;
import java.util.List;

public class SymbolParser extends AbstractParser{

    private List<TextComponent> dataSymbols = new ArrayList<>();

    @Override
    public void parse(TextComposite composite, String part) {
        char[] charArray = part.toCharArray();
        for (char symbolValue : charArray) {
            composite.add(getSymbolFromData(symbolValue));
        }
    }

    private boolean checkLetter(char ch){
        return (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122);
    }

    private TextComponent getSymbolFromData(char ch){
        TextComponent component = null;
        for (TextComponent value: dataSymbols){
            if(value.toString().equals(String.valueOf(ch))){
                component = value;
            }
        }
        if (component == null) {
            if (checkLetter(ch)) {
                component = new Letter(ch);
            } else {
                component = new Punctuation(ch);
            }
            dataSymbols.add(component);
        }
        return component;
    }

}
