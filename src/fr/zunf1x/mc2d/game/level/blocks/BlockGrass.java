package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

public class BlockGrass extends Block {

    public BlockGrass() {
        setTexture(2);
    }

    @Override
    public void render(Vector2d loc, World world, Color4f grassColor, boolean half, boolean halfTop) {
        Texture.BLOCKS.bind();
        Renderer.directTexturedCube(loc.getX(), loc.getY(), new Color4f(1, 1, 1), 2);
        Renderer.directTexturedCube(loc.getX(), loc.getY(), grassColor, 3);
        Texture.BLOCKS.unbind();
    }
}
