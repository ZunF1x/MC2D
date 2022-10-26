package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

public class BlockLeaves extends Block {

    public BlockLeaves() {
        setTexture(18);
    }

    @Override
    public void render(Vector2d loc, World world, Color4f grassColor, boolean half) {
        Texture.BLOCKS.bind();
        Renderer.directTexturedCube(loc.getX(), loc.getY(), grassColor, 14);
        Texture.BLOCKS.unbind();
    }
}
