package by.training.compositetask.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Letter extends TextComponent {

    private static Logger logger = LogManager.getLogger();
    private char symbol;

    public Letter(char symbol){
        super(TextComponentType.LETTER);
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean add(TextComponent component) {
        logger.info("prohibited operation to add a component to letter");
        throw new UnsupportedOperationException("add impossible");
    }

    @Override
    public boolean addAll(List<TextComponent> components) {
        logger.info("prohibited operation to add a component to letter");
        throw new UnsupportedOperationException("add impossible");
    }

    @Override
    public boolean remove(TextComponent component) {
        logger.info("prohibited operation to remove a component to letter");
        throw new UnsupportedOperationException("remove impossible");
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public List<TextComponent> getComponents() {
        logger.error("An attempt to get children of symbol");
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter = (Letter) o;

        return symbol == letter.symbol;
    }

    @Override
    public int hashCode() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

}
