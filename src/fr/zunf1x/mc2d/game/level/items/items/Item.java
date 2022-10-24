package fr.zunf1x.mc2d.game.level.items.items;

import fr.zunf1x.mc2d.game.level.items.properties.ItemProperties;
import fr.zunf1x.mc2d.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private final ItemProperties itemProperties;
    private final int textureIndex;
    private final List<DescriptionElement> de;
    private final String name;

    public Item(String name, ItemProperties itemProperties, int textureIndex) {
        this.itemProperties = itemProperties;
        this.textureIndex = textureIndex;
        this.name = name;
        this.de = new ArrayList<>();
        de.add(new DescriptionElement(name, new Color(1, 1, 1, 1, false)));
        addDescriptionElements(de, new Color(85, 85, 85, 255, true));
    }

    public String getName() {
        return name;
    }

    public ItemProperties getItemProperties() {
        return this.itemProperties;
    }

    public List<DescriptionElement> getDescriptionElements() {
        return this.de;
    }

    public void addDescriptionElements(List<DescriptionElement> de, Color descriptionColor) {}

    public DescriptionElement setDescriptionElement(int index, DescriptionElement d) {
        return de.set(index, d);
    }

    public int getTextureIndex() {
        return this.textureIndex;
    }
}
