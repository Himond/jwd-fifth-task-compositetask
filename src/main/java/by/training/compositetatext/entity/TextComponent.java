package by.training.compositetatext.entity;

public interface TextComponent {

    boolean add(TextComponent component);
    boolean remove(TextComponent component);
    int count();
    String toString();

}
