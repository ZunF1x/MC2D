package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;

import java.awt.*;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public abstract class Block {

    private int texture;

    public Block() {
        this.texture = 404;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public int getTexture() {
        return texture;
    }

    public void render(Vector2d loc, Color4f grassColor) {
        Color4f color = Color4f.WHITE;

        if (this instanceof IColorized) {
            color = ((IColorized) this).getColor();
        }

        Renderer.directCube(loc.getX(), loc.getY(), color);
    }
}
