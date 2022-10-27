package fr.zunf1x.mc2d.game.level.inventory.items;

public abstract class Item {

    private final int texture;

    public Item(int texture) {
        this.texture = texture;
    }

    public int getTexture() {
        return texture;
    }
}
