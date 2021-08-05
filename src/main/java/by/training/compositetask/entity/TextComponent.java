package by.training.compositetask.entity;

import java.util.List;

public abstract class TextComponent {
    protected final TextComponentType componentType;

    public TextComponent(TextComponentType componentType) {
        this.componentType = componentType;
    }

    public TextComponentType getComponentType() {
        return componentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComponent component = (TextComponent) o;

        return componentType == component.componentType;
    }

    @Override
    public int hashCode() {
        return componentType != null ? componentType.hashCode() : 0;
    }

    public abstract boolean add(TextComponent component);
    public abstract boolean addAll(List<TextComponent> components);
    public abstract boolean remove(TextComponent component);
    public abstract int count();
    public abstract List<TextComponent> getComponents();



}
