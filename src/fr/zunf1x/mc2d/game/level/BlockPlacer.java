package fr.zunf1x.mc2d.game.level;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.world.WorldGenerator;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;

import java.awt.*;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class BlockPlacer {

    private final Vector2d loc;
    private final Block block;

    private Color4f color;

    private WorldGenerator world;

    public BlockPlacer(Vector2d loc, Block block, WorldGenerator world) {
        this.loc = loc;
        this.block = block;

        this.world = world;

        Random rdm = world.getWorldProvider().getWorldSeededRandom();

        this.color = new Color4f(rdm.nextFloat() * 4, rdm.nextFloat() * 2, rdm.nextFloat() * 2);
    }

    public void render(Color4f grassColor) {
        double x = this.getLocation().getX();
        double y = this.getLocation().getY();

        Game game = Start.getInstance().getGame();

        double x0 = x + game.getXScroll();
        double y0 = y + game.getYScroll();

        double x1 = x + 1 + game.getXScroll();
        double y1 = y + 1 + game.getYScroll();

        if (x1 < 0 || y1 < 0 || x0 > game.getWidth() / 64F || y0 > game.getHeight() / 64F) return;

        this.block.render(this.loc, grassColor);
    }

    public Vector2d getLocation() {
        return loc;
    }

    public Block getBlock() {
        return block;
    }
}
