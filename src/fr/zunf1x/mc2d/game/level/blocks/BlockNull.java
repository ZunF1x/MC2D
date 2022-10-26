package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

public class BlockNull extends Block {

    public BlockNull() {
        setTexture(20);
    }

    public void render(Vector2d loc, World world, Color4f grassColor, boolean half) {
        boolean open = !world.getBlock((int) loc.getX(), (int) loc.getY()).isCollide();

        Texture.BLOCKS.bind();

        if (!open) {
            Renderer.directTexturedCube(loc.getX(), loc.getY(), new Color4f(1, 1, 1), 20, half);
        } else {
            Renderer.directTexturedCube(loc.getX(), loc.getY(), new Color4f(1, 1, 1), 22, half);
        }

        Texture.BLOCKS.unbind();
    }

    @Override
    public void onBlockInteract(World world, int x, int y) {
        world.getBlock(x, y).setCollide(!world.getBlock(x, y).isCollide());
        world.getBlock(x, y + 1).setCollide(world.getBlock(x, y).isCollide());
    }
}
