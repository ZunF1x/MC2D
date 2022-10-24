package fr.zunf1x.mc2d.game.level.items.items;

import fr.zunf1x.mc2d.game.level.items.properties.ItemProperties;

public class ItemSword extends Item {

    public ToolMaterial material;

    public ItemSword(String name, ToolMaterial material, ItemProperties properties, int textureIndex) {
        super(name, properties, textureIndex);

        this.material = material;
    }
}
