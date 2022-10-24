package fr.zunf1x.mc2d.game.level.items.items;

import fr.zunf1x.mc2d.game.level.items.properties.ItemProperties;

public class ItemShovel extends ItemTool {

    public ItemShovel(String name, ItemProperties itemProperties, int textureIndex, ToolMaterial mat, int damage) {
        super(name, itemProperties, textureIndex, mat, "shovel", damage);
    }
}
