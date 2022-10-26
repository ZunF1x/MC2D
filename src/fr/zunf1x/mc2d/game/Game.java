package fr.zunf1x.mc2d.game;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.game.level.world.WorldOverworld;
import fr.zunf1x.mc2d.game.level.world.WorldProvider;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class Game {

    private int width, height, scale = 2;

    private final World world;

    public EntityPlayer player;

    private double xScroll, yScroll;

    public EntityManager entityManager;

    private boolean debug = false;

    public Game() {
        this.world = new WorldOverworld(32768, this, new WorldProvider(new Random().nextLong(), 20, 10));

        this.entityManager = new EntityManager(this);

        player = new EntityPlayer(new Vector2d(0, 150));
        this.entityManager.addEntity(player);
    }

    public void init() {

    }

    public boolean isDebug() {
        return debug;
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

    public int getScale() {
        return scale;
    }

    public float xScrollFactor;
    float yScrollFactor;

    public void translateView(double xa, double ya) {
        xScrollFactor = -this.world.getSize() * 16 + this.width / 64F;
        yScrollFactor = -256 + this.height / 64F;

        xScroll = xa;
        yScroll = ya;

        if (xScroll > 0) xScroll = 0;
        if (yScroll > 0) yScroll = 0;
        if (xScroll < xScrollFactor) xScroll = xScrollFactor;
        if (yScroll < yScrollFactor) yScroll = yScrollFactor;
    }

    int btn = -1;
    int timer = 0;

    boolean flagX;
    boolean flagY;

    public void update() {
        this.width = Start.getInstance().getWidth();
        this.height = Start.getInstance().getHeight();
        this.scale = Start.getInstance().getScale();

        world.update();

        this.entityManager.update();

        double xa = -player.getLocation().getX() + this.width / 128F - 0.5F;
        double ya = -player.getLocation().getY() + this.height / 128F - 1;
        translateView(xa, ya);

        flagX = getMouseX(true) / 64F < this.player.getLocation().getX() + 4 && getMouseX(true) / 64F > this.player.getLocation().getX() - 3;
        flagY = getMouseY(true) / 64F < this.player.getLocation().getY() + 5 && getMouseY(true) / 64F > this.player.getLocation().getY() - 3;

        if (Mouse.isButtonDown(0)) {
            btn = 0;
        } else if (Mouse.isButtonDown(1)) {
            btn = 1;
        } else {
            btn = -1;
        }

        if (btn != -1) {
            timer++;
        } else {
            timer = 0;
        }

        if (timer % 15 == 0 || timer == 5) {
            if (flagX && flagY) {
                if (btn == 0) {
                    this.world.removeBlock(getMouseX(true) / 64, getMouseY(true) / 64);
                }

                if (btn == 1) {
                    this.world.addBlock(getMouseX(true) / 64, getMouseY(true) / 64, Blocks.STONE);
                }
            }
        }

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_F3) this.debug = !this.debug;
            }
        }
    }

    public void drawSelection(Vector2d loc) {
        int x = (int) loc.getX();
        int y = (int) loc.getY();

        float s = 1;

        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glLineWidth(2);

        glBegin(GL_QUADS);
        glColor3f(0, 0, 0);
        glVertex2f(x, y);
        glVertex2f(x + s, y);
        glVertex2f(x + s, y + s);
        glVertex2f(x, y + s);
        glEnd();

        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    }

    public int getMouseX(boolean scrollingDepend) {
        int mouseX = Mouse.getX() / this.scale;

        if (scrollingDepend) return (int) (mouseX - xScroll * 64);
        else return mouseX;
    }

    public int getMouseY(boolean scrollingDepend) {
        int mouseY = (Display.getHeight() - Mouse.getY()) / this.scale;

        if (scrollingDepend) return (int) (mouseY - yScroll * 64);
        else return mouseY;
    }

    public void render() {
        viewGame();

        glScalef(64, 64, 0);
        glTranslated(xScroll, yScroll, 0);

        world.render(isDebug());

        this.entityManager.render();

        if (flagX && flagY) {
            this.drawSelection(new Vector2d(getMouseX(true) / 64D, getMouseY(true) / 64D));
        }

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
