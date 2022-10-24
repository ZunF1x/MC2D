package fr.zunf1x.mc2d.game.level;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.math.vectors.Vector2f;
import fr.zunf1x.mc2d.rendering.Color4f;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class BlockPlacer {

    private final Vector2f loc;
    private final Block block;

    Color4f color;

    public BlockPlacer(Vector2f loc, Block block) {
        this.loc = loc;
        this.block = block;
    }

    public void init(Game game) {
        Random rdm = game.getWorld().getWorldProvider().getWorldSeededRandom();

        color = new Color4f(rdm.nextFloat() * 4, rdm.nextFloat() * 2, rdm.nextFloat() * 2);
    }

    public void render() {
        float x = this.getLocation().getX();
        float y = this.getLocation().getY();

        Game game = Start.getInstance().getGame();

        float x0 = x + game.getXScroll() / 64;
        float y0 = y + game.getYScroll() / 64;

        float x1 = x + 64 + game.getXScroll() / 64;
        float y1 = y + 64 + game.getYScroll() / 64;

        if (x1 < 0 || y1 < 0 || x0 > game.getWidth() / 64f || y0 > game.getHeight() / 64f) return;

        renderCube();
    }

    public void renderCube() {
        glBegin(GL_QUADS);
        glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        glVertex2f(this.loc.getX() * 64, this.loc.getY() * 64);
        glVertex2f(this.loc.getX() * 64 + 64, this.loc.getY() * 64);
        glVertex2f(this.loc.getX() * 64 + 64, this.loc.getY() * 64 + 64);
        glVertex2f(this.loc.getX() * 64, this.loc.getY() * 64 + 64);
        glEnd();
    }

    public Vector2f getLocation() {
        return loc;
    }

    public Block getBlock() {
        return block;
    }
}
