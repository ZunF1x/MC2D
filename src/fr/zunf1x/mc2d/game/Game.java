package fr.zunf1x.mc2d.game;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.level.BlockPlacer;
import fr.zunf1x.mc2d.game.level.blocks.*;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.game.level.world.WorldOverworld;
import fr.zunf1x.mc2d.game.level.world.WorldProvider;
import fr.zunf1x.mc2d.math.Mathf;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;
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

    int activeBlock;

    public void update() {
        activeBlock = (int) Mathf.clamp(activeBlock + Mouse.getDWheel() / 120F, 0, Blocks.blocks.size() - 1);

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
                    int blockX = getMouseX(true) / 64;
                    int blockY = getMouseY(true) / 64;

                    boolean flag = this.world.getBlock(blockX, blockY) != null && this.world.getBlock(blockX, blockY - 1) != null && this.world.getBlock(blockX, blockY).getBlock() instanceof BlockDoor && this.world.getBlock(blockX, blockY - 1).getBlock() instanceof BlockNull;
                    boolean flag1 = this.world.getBlock(blockX, blockY) != null && this.world.getBlock(blockX, blockY + 1) != null  && this.world.getBlock(blockX, blockY).getBlock() instanceof BlockNull && this.world.getBlock(blockX, blockY + 1).getBlock() instanceof BlockDoor;

                    if (flag) {
                        this.world.removeBlock(blockX, blockY - 1);
                        this.world.removeBlock(blockX, blockY);
                    } else if (flag1) {
                        this.world.removeBlock(blockX, blockY + 1);
                        this.world.removeBlock(blockX, blockY);
                    } else {
                        this.world.removeBlock(getMouseX(true) / 64, getMouseY(true) / 64);
                    }
                }

                if (btn == 1) {
                    int blockX = getMouseX(true) / 64;
                    float mX = getMouseX(true) / 64F;
                    float mY = getMouseY(true) / 64F;
                    System.out.println(mX + " " + mY);

                    this.world.addBlock(getMouseX(true) / 64, getMouseY(true) / 64, Blocks.getBlock(activeBlock), mX > blockX + 0.50F);
                }
            }
        }

        while (Mouse.next()) {
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 1) {
                    BlockPlacer b = this.world.getBlock(getMouseX(true) / 64, getMouseY(true) / 64);

                    if (b != null) {
                        b.getBlock().onBlockInteract(this.world, getMouseX(true) / 64, getMouseY(true) / 64);
                    }
                }
            }
        }

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_F3) this.debug = !this.debug;
            }
        }
    }

    public void drawSelectedBlock(Block b) {
        float x = 48, y = 48;
        float w = 16, h = 16;
        float v = y + h * 4 - h / 2 - h;

        int xo = b.getTexture() % 16;
        int yo = b.getTexture() / 16;

        if (b instanceof ISpecialRender) {
            int tex = ((ISpecialRender) b).specialRender();

            xo = tex % 16;
            yo = tex / 16;

            x -= 32;
            y -= 32;

            Texture.ITEMS.bind();
            glBegin(GL_QUADS);
            glColor3f(1, 1, 1);
            glTexCoord2f(xo / 16F, yo / 16F);
            glVertex2f(x, y);
            glTexCoord2f((xo + 1) / 16F, yo / 16F);
            glVertex2f(x + 64, y);
            glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F);
            glVertex2f(x + 64, y + 64);
            glTexCoord2f(xo / 16F, (yo + 1) / 16F);
            glVertex2f(x, y + 64);
            glEnd();
            Texture.ITEMS.unbind();
        } else {
            Texture.BLOCKS.bind();
            glBegin(GL_QUADS);
            glColor3f(1, 1, 1);
            glTexCoord2f(xo / 16F, yo / 16F);
            glVertex2f(x + w * 2, y - h);
            glTexCoord2f(xo / 16F, (yo + 1) / 16F);
            glVertex2f(x, y - h * 2);
            glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F);
            glVertex2f(x - w * 2, y - h);
            glTexCoord2f((xo + 1) / 16F, yo / 16F);
            glVertex2f(x, y);

            glColor3f(0.69F, 0.69F, 0.69F);
            glTexCoord2f((xo + 1) / 16F, yo / 16F);
            glVertex2f(x, y);
            glTexCoord2f(xo / 16F, yo / 16F);
            glVertex2f(x - w * 2, y - h);
            glTexCoord2f(xo / 16F, (yo + 1) / 16F);
            glVertex2f(x - w * 2, y + h * 2 - h / 2);
            glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F);
            glVertex2f(x, v);

            glColor3f(0.43F, 0.43F, 0.43F);
            glTexCoord2f(xo / 16F, yo / 16F);
            glVertex2f(x, y);
            glTexCoord2f((xo + 1) / 16F, yo / 16F);
            glVertex2f(x + w * 2, y - h);
            glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F);
            glVertex2f(x + w * 2, y + h * 2 - h / 2);
            glTexCoord2f(xo / 16F, (yo + 1) / 16F);
            glVertex2f(x, v);
            glEnd();
            Texture.BLOCKS.unbind();
        }

        glEnd();
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

        this.entityManager.render();

        world.render(isDebug());

        if (flagX && flagY) {
            this.drawSelection(new Vector2d(getMouseX(true) / 64D, getMouseY(true) / 64D));
        }

        viewGuiAndOverlay();

        this.drawSelectedBlock(Blocks.getBlock(activeBlock));
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
