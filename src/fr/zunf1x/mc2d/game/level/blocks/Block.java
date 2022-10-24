package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.rendering.Color4f;

public abstract class Block {

    private Color4f color;
    private int texture;

    public Block() {
        this.color = new Color4f(1, 1, 1);
        this.texture = 404;
    }

    public void setColor(Color4f color) {
        this.color = color;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public Color4f getColor() {
        return color;
    }

    public int getTexture() {
        return texture;
    }
}
