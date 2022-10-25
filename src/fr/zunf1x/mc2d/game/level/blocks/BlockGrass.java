package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.math.vectors.Vector2f;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

import java.util.Random;

public class BlockGrass extends Block {

    public BlockGrass() {

    }

    @Override
    public void render(Vector2d loc, Color4f grassColor) {
        Texture.BLOCKS.bind();
        Renderer.directTexturedCube(loc.getX(), loc.getY(), new Color4f(1, 1, 1), 2);
        Renderer.directTexturedCube(loc.getX(), loc.getY(), grassColor, 3);
        Texture.BLOCKS.unbind();
    }
}
