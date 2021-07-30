package by.training.compositetatext.entity;


import java.util.ArrayList;
import java.util.List;

public class TextComposite extends TextComponent {

    private List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentIndent indent) {
        super(indent);
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    @Override
    public int count() {
        int counter = 0;
        for (TextComponent component: components){
            counter += component.count();
        }
        return counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TextComposite composite = (TextComposite) o;

        return components != null ? components.equals(composite.components) : composite.components == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (components != null ? components.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        components.forEach(component -> {
            TextComponentIndent type = component.getComponentType();
            String prefix = type.getPrefix();
            String suffix = type.getSuffix();
            stringBuilder.append(prefix).append(component).append(suffix);
        });

        return (indent != TextComponentIndent.TEXT)
                ? stringBuilder.toString().strip()
                : stringBuilder.toString().stripTrailing();
    }


}
