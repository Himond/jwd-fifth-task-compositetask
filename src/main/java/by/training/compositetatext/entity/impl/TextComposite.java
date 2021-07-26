package by.training.compositetatext.entity.impl;

import by.training.compositetatext.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private List<TextComponent> chars = new ArrayList<>();

    public TextComposite(List<TextComponent> chars) {
        this.chars = chars;
    }

    public TextComposite() {
    }

    @Override
    public boolean add(TextComponent component) {
        return chars.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return chars.remove(component);
    }

    @Override
    public int count() {
        int counter = 0;
        for (TextComponent component: chars){
            counter += component.count();
        }
        return counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite that = (TextComposite) o;

        return chars != null ? chars.equals(that.chars) : that.chars == null;
    }

    @Override
    public int hashCode() {
        return chars != null ? chars.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(" ");
        for (TextComponent component: chars){
            res.append(component.toString());
        }
        return res.toString();
    }

}
