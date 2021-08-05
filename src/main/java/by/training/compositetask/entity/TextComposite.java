package by.training.compositetask.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite extends TextComponent {

    private List<TextComponent> components = new ArrayList<>();


    public TextComposite(TextComponentType componentType) {
        super(componentType);
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean addAll(List<TextComponent> components) {
        return this.components.addAll(components);
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
    public List<TextComponent> getComponents() {
        return components;
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
        var stringBuilder = new StringBuilder();
        components.forEach(component -> {
            TextComponentType type = component.getComponentType();
            String prefix = type.getPrefix();
            String suffix = type.getSuffix();
            stringBuilder.append(prefix).append(component).append(suffix);
        });

        return (componentType != TextComponentType.TEXT)
                ? stringBuilder.toString().strip()
                : stringBuilder.toString().stripTrailing();
    }


}
