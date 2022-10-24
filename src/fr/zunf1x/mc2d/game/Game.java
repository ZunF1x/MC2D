package fr.zunf1x.mc2d.game;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.math.vectors.Vector2f;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class Game {

    private int width, height;

    private World world;

    public EntityPlayer player;

    private float xScroll, yScroll;

    public Game() {
        this.world = new World(100000);

        player = new EntityPlayer(new Vector2f(64 * 16, 192 * 64));
    }

    public void init() {
        
    }

    public World getWorld() {
        return world;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void translateView(float xa, float ya) {
        xScroll = xa;
        yScroll = ya;

        if (xScroll > 0) xScroll = 0;
        if (yScroll > 0) yScroll = 0;
        if (yScroll < -64 * 256 + this.height) yScroll = -64 * 256 + this.height;
    }

    public void update() {
        this.width = Start.getInstance().getWidth();
        this.height = Start.getInstance().getHeight();

        world.update();

        player.update();

        float xa = -player.getLocation().getX() + (float) this.width / 2 - 32;
        float ya = -player.getLocation().getY() + (float) this.height / 2 - 64;
        translateView(xa, ya);
    }

    public void render() {
        viewGame();

        glTranslatef(xScroll, yScroll, 0);

        world.render();

        player.render();

        viewGuiAndOverlay();
    }

    public void viewGame() {
        glEnable(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, this.width, this.height, 0);
        glEnable(GL_MODELVIEW);
    }

    public void viewGuiAndOverlay() {
        glEnable(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, this.width, this.height, 0);
        glEnable(GL_MODELVIEW);
    }

    public float getXScroll() {
        return xScroll;
    }

    public float getYScroll() {
        return yScroll;
    }
}
