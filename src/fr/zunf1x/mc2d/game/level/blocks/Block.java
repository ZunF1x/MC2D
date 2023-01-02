package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

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

    public void render(Vector2d loc, World world, Color4f grassColor, boolean halfSide, boolean halfTop) {
        Color4f color = Color4f.WHITE;

        if (this instanceof IColorized) {
            color = ((IColorized) this).getColor();
        }

        Texture.BLOCKS.bind();

        Renderer.directTexturedCube(loc.getX(), loc.getY(), color, this.texture);

        Texture.BLOCKS.unbind();
    }

    public void tick(Vector2d loc, World world, int chunkX, boolean halfSide, boolean halfTop) {

    }

    public void onBlockInteract(Game game, int x, int y) {}
}
