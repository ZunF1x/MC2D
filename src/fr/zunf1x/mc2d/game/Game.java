package fr.zunf1x.mc2d.game;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.math.vectors.Vector2f;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class Game {

    private int width, height;

    private World world;

    public EntityPlayer player;

    private double xScroll, yScroll;

    private double test = 32768 * 2;

    public Game() {
        this.world = new World(16);

        player = new EntityPlayer(new Vector2d(0, 150));
    }

    public void init() {
        this.player.init(this);
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

    public void translateView(double xa, double ya) {
        float xScrollFactor = -this.world.getSize() * 16 + this.width / 64F;
        float yScrollFactor = -256 + this.height / 64F;

        xScroll = xa;
        yScroll = ya;

        if (xScroll > 0) xScroll = 0;
        if (yScroll > 0) yScroll = 0;
        if (xScroll < xScrollFactor) xScroll = xScrollFactor;
        if (yScroll < yScrollFactor) yScroll = yScrollFactor;
    }

    public void update() {
        this.width = Start.getInstance().getWidth();
        this.height = Start.getInstance().getHeight();

        world.update();

        player.update();

        double xa = -player.getLocation().getX() + this.width / 128F - 0.5F;
        double ya = -player.getLocation().getY() + this.height / 128F - 1;
        translateView(xa, ya);

        boolean flagX = getMouseX(true) / 64F < this.player.getLocation().getX() + 4 && getMouseX(true) / 64F > this.player.getLocation().getX() - 3;
        boolean flagY = getMouseY(true) / 64F < this.player.getLocation().getY() + 5 && getMouseY(true) / 64F > this.player.getLocation().getY() - 3;

        if (flagX && flagY) {
            if (Mouse.isButtonDown(0)) {
                this.world.removeBlock(getMouseX(true) / 64, getMouseY(true) / 64);
            }

            if (Mouse.isButtonDown(1)) {
                this.world.addBlock(getMouseX(true) / 64, getMouseY(true) / 64, Blocks.GRASS);
            }
        }

        System.out.println(this.getMouseX(true) / 64F + " " + this.player.getLocation().getX());
    }

    public int getMouseX(boolean scrollingDepend) {
        int mouseX = Mouse.getX() / 2;

        if (scrollingDepend) return (int) (mouseX - xScroll * 64);
        else return mouseX;
    }

    public int getMouseY(boolean scrollingDepend) {
        int mouseY = (Display.getHeight() - Mouse.getY()) / 2;

        if (scrollingDepend) return (int) (mouseY - yScroll * 64);
        else return mouseY;
    }

    public void render() {
        viewGame();

        glScalef(64, 64, 0);
        glTranslated(xScroll, yScroll, 0);

        world.render();

        player.render();

        viewGuiAndOverlay();
    }

    public void viewGame() {
        glEnable(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, this.width, this.height, 0);
        glEnable(GL_MODELVIEW);

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void viewGuiAndOverlay() {
        glEnable(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, this.width, this.height, 0);
        glEnable(GL_MODELVIEW);

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public double getXScroll() {
        return xScroll;
    }

    public double getYScroll() {
        return yScroll;
    }
}
