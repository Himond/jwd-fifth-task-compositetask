package by.training.compositetatext.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Punctuation extends TextComponent {

    private static Logger logger = LogManager.getLogger();
    private char ch;

    public Punctuation(char ch) {
        super(TextComponentIndent.PUNCTUATION);
        this.ch = ch;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    @Override
    public boolean add(TextComponent component) {
        logger.info("prohibited operation to add a component to punctuation");
        throw new UnsupportedOperationException("add impossible");
    }

    @Override
    public boolean remove(TextComponent component) {
        logger.info("prohibited operation to remove a component to punctuation");
        throw new UnsupportedOperationException("remove impossible");
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Punctuation that = (Punctuation) o;

        return ch == that.ch;
    }

    @Override
    public int hashCode() {
        return ch;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }

}
