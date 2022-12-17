package fr.zunf1x.mc2d.game;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.level.BlockPlacer;
import fr.zunf1x.mc2d.game.level.blocks.*;
import fr.zunf1x.mc2d.game.level.entities.particles.Particle;
import fr.zunf1x.mc2d.game.level.inventory.*;
import fr.zunf1x.mc2d.game.level.inventory.inventories.crafting.RecipeRegistry;
import fr.zunf1x.mc2d.game.level.inventory.items.ItemBlock;
import fr.zunf1x.mc2d.game.level.world.Chunk;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.game.level.world.WorldOverworld;
import fr.zunf1x.mc2d.game.level.world.WorldProvider;
import fr.zunf1x.mc2d.math.Mathf;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
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

    private Gui currentScreen;

    public RecipeRegistry recipeRegistry;

    public Game() {
        this.world = new WorldOverworld(32768, this, new WorldProvider(new Random().nextLong(), 20, 10));

        this.entityManager = new EntityManager(this);
    }

    public void init() {
        player = new EntityPlayer(new Vector2d(8138, 192));
        this.entityManager.addEntity(player);

        this.recipeRegistry = new RecipeRegistry();
        this.recipeRegistry.addRecipes();
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

    boolean add = false;

    public void update() {
        activeBlock = (int) Mathf.clamp(activeBlock - Mouse.getDWheel() / 120F, 0, 8);

        this.width = Start.getInstance().getWidth();
        this.height = Start.getInstance().getHeight();
        this.scale = Start.getInstance().getScale();

        world.update();

        if (this.currentScreen != null) {
            this.currentScreen.update();
        }
        if (this.currentScreen == null) this.entityManager.update();

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
            if (flagX && flagY && this.currentScreen == null) {
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
                    int blockY = getMouseY(true) / 64;
                    float mX = getMouseX(true) / 64F;
                    float mY = getMouseY(true) / 64F;

                    if (this.player.inv.getStackInSlot(this.activeBlock).getItem() instanceof ItemBlock) {
                        if (this.world.getBlock(getMouseX(true) / 64, getMouseY(true) / 64) == null) {
                            ItemBlock ib = ((ItemBlock) this.player.inv.getStackInSlot(this.activeBlock).getItem());
                            this.world.addBlock(getMouseX(true) / 64, getMouseY(true) / 64, ib.block, mX > blockX + 0.50F, mY <= blockY + 0.50F);
                            if (this.player.inv.getStackInSlot(this.activeBlock).getCount() > 1) {
                                this.player.inv.getStackInSlot(this.activeBlock).shrink(1);
                            } else {
                                this.player.inv.setInventorySlotContents(this.activeBlock, ItemStack.EMPTY);
                            }
                        }
                    }
                }
            }
        }

        while (Mouse.next()) {
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 1) {
                    BlockPlacer b = this.world.getBlock(getMouseX(true) / 64, getMouseY(true) / 64);

                    if (b != null) {
                        b.getBlock().onBlockInteract(this, getMouseX(true) / 64, getMouseY(true) / 64);
                    }
                }

                if (currentScreen != null && currentScreen instanceof GuiContainer) ((GuiContainer) this.currentScreen).updateSlots();
            }
        }

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_R) this.add = !this.add;
                if (Keyboard.getEventKey() == Keyboard.KEY_F3) this.debug = !this.debug;
                if (Keyboard.getEventKey() == Keyboard.KEY_E){
                    if (isInventoryOpened()) {
                        this.closeInventory();
                        return;
                    }

                    if (!isInventoryOpened()) {
                        this.openInventory(0);
                    }
                }
            }
        }
    }

    public void drawSelectedBlock(Block b, float x, float y) {
        float w = 3.5F, h = 3.5F;
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

        if (currentScreen != null) this.currentScreen.render();

        if (isDebug()) {
            Start.getInstance().getFont().drawStringWithShadow("FPS : " + Start.getInstance().getFps(), 4, 4, 2, 1);

            int chunkX = (int) ((this.player.getLocation().getX() + 0.5F) / 16f);
            Chunk c = this.world.getChunk(chunkX);

            Start.getInstance().getFont().drawStringWithShadow("Biome : " + c.chunkBiome.getBiomeName(), 4, 24, 2, 1);
            Start.getInstance().getFont().drawStringWithShadow("Biome Length : " + c.biomeLength, 4, 44, 2, 1);

            int rainStageSecond = this.world.rainTimer / 60;

            Start.getInstance().getFont().drawStringWithShadow("Weather Stage : " + rainStageSecond + "seconds", 4, 84, 2, 1);
        }

        Texture.WIDGETS.bind();

        Renderer.drawGuiInventory(this.width / 2f - 182, this.height - 50, 0, 0, 364, 44, 182, 22);
        Renderer.drawGuiInventory(this.width / 2f - 184 + (this.activeBlock * 40), this.height - 52, 0, 22, 48, 48, 24, 24);

        Texture.WIDGETS.unbind();

        for (int i = 0; i < 9; i++) {
            if (this.player.inv.getStackInSlot(i).getItem() != null) {
                this.player.inv.getStackInSlot(i).getItem().render(new Vector2d(this.width / 2f - 182 + 6 + i * 40, this.height - 50 + 6));
                if (this.player.inv.getStackInSlot(i).getCount() > 1) Start.getInstance().getFont().drawCenteredStringWithShadow("" + this.player.inv.getStackInSlot(i).getCount(), this.width / 2f - 180 + 26 + i * 40, this.height - 50 + 30, 2, 1);
            }
        }
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

    public void openInventory(int id) {
        Gui g = RegisteredInventories.gui(id, this, this.player);

        if (g instanceof GuiContainer) ((GuiContainer) g).container.inventory.openInventory();
        this.currentScreen = g;
    }

    public void closeInventory(int id) {
        if (this.currentScreen == RegisteredInventories.gui(id, this, this.player)) {
            this.closeInventory();
        }
    }

    public boolean isInventoryOpened() {
        return this.currentScreen != null;
    }

    public void closeInventory() {
        if (this.currentScreen instanceof GuiContainer) ((GuiContainer) this.currentScreen).container.inventory.closeInventory();
        this.currentScreen = null;
    }

    public double getXScroll() {
        return xScroll;
    }

    public double getYScroll() {
        return yScroll;
    }
}
