package by.training.compositetatext.entity;

public abstract class TextComponent {
    protected final TextComponentIndent indent;

    public TextComponent(TextComponentIndent indent) {
        this.indent = indent;
    }

    public TextComponentIndent getComponentType() {
        return indent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComponent component = (TextComponent) o;

        return indent == component.indent;
    }

    @Override
    public int hashCode() {
        return indent != null ? indent.hashCode() : 0;
    }

    public abstract boolean add(TextComponent component);
    public abstract boolean remove(TextComponent component);
    public abstract int count();



}
