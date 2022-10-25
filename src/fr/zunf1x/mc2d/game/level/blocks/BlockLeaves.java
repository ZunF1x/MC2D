package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.math.vectors.Vector2f;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

public class BlockLeaves extends Block {

    public BlockLeaves() {

    }

    @Override
    public void render(Vector2f loc, Color4f grassColor) {
        Texture.BLOCKS.bind();
        Renderer.directTexturedCube(loc.getX(), loc.getY(), grassColor, 14);
        Texture.BLOCKS.unbind();
    }
}
