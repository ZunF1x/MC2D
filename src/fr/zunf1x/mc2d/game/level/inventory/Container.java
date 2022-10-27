package fr.zunf1x.mc2d.game.level.inventory;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.level.inventory.items.Item;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Container {

    public List<Slot> containerSlots;
    public int guiTop;
    public int guiLeft;
    public int xSize;
    public int ySize;
    protected IInventory inventory;

    private ItemStack currentStack;

    public Container(IInventory inventory) {
        this.containerSlots = new ArrayList<>();
        this.inventory = inventory;

        this.xSize = 176;
        this.ySize = 166;
        this.guiLeft = (Display.getWidth() / 2 / 2) - (xSize / 2);
        this.guiTop = (Display.getHeight() / 2 / 2) - (ySize / 2);

        currentStack = ItemStack.EMPTY;

        this.init();
    }

    public void init() {}

    public void addSlotToContainer(Slot s) {
        s.setX(guiLeft + s.getX());
        s.setY(guiTop + s.getY());

        this.containerSlots.add(s);
    }

    public void update() {
        this.guiLeft = ((Display.getWidth() / Start.getInstance().getGame().getScale()) / 2) - (xSize / 2);
        this.guiTop = ((Display.getHeight() / Start.getInstance().getGame().getScale()) / 2) - (ySize / 2);

        this.containerSlots.clear();
        this.init();
    }

    public void updateSlots() {
        for (Slot s : containerSlots) {
            float x = s.getX();
            float y = s.getY();

            if (mouseHover(x, y)) {
                if (Mouse.getEventButton() == 0) {
                    if (currentStack.isEmpty()) {
                        this.currentStack = s.getStack().copy();
                        this.inventory.setInventorySlotContents(s.getIndex(), ItemStack.EMPTY);
                    } else {
                        this.inventory.setInventorySlotContents(s.getIndex(), this.currentStack.copy());
                        this.currentStack = ItemStack.EMPTY;
                    }
                }
            }
        }
    }

    public void render() {
        for (Slot s : containerSlots) {
            float x = s.getX();
            float y = s.getY();

            Texture.BLOCKS.bind();

            if (s.getHasStack()) Renderer.directTexturedCube(x, y, 16, 16, new Color4f(1, 1, 1), s.getStack().getItem().getTexture());

            Texture.BLOCKS.unbind();

            if (mouseHover(x, y)) {
                Renderer.directCube(x, y, 16, 16, new Color4f(1, 1, 1, 0.5F));
            }
        }

        float mouseX = Start.getInstance().getGame().getMouseX(false);
        float mouseY = Start.getInstance().getGame().getMouseY(false);

        Texture.BLOCKS.bind();

        if (!currentStack.isEmpty()) Renderer.directTexturedCube(mouseX - 8, mouseY - 8, 16, 16, new Color4f(1, 1, 1), this.currentStack.getItem().getTexture());

        Texture.BLOCKS.unbind();
    }

    public boolean mouseHover(float slotX, float slotY) {
        float mouseX = Start.getInstance().getGame().getMouseX(false);
        float mouseY = Start.getInstance().getGame().getMouseY(false);

        return mouseX >= slotX && mouseX <= slotX + 16 && mouseY >= slotY && mouseY <= slotY + 16;
    }
}
