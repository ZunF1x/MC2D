package fr.zunf1x.mc2d.game.level.items.items;

import fr.zunf1x.mc2d.game.level.items.properties.ItemProperties;

public class ItemTool extends ItemDamageable {

    private String harvestTool;

    public ToolMaterial material;

    public ItemTool(String name, ItemProperties itemProperties, int textureIndex, ToolMaterial mat, String harvestTool, int damage) {
        super(name, itemProperties, textureIndex, damage);

        this.material = mat;

        setHarvestTool(harvestTool);
    }

    public String getHarvestTool() {
        return harvestTool;
    }

    public void setHarvestTool(String harvestTool) {
        this.harvestTool = harvestTool;
    }

    public int getHarvestLevel() {
        return material.getHarvestLevel();
    }
}
