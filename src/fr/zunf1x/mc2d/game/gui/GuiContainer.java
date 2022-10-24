package fr.zunf1x.mc2d.game.gui;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.gui.inventory.Inventory;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;
import fr.zunf1x.mc2d.game.level.items.utils.Slot;
import fr.zunf1x.mc2d.graphics.ItemRendering;
import fr.zunf1x.mc2d.graphics.Renderer;
import fr.zunf1x.mc2d.graphics.Texture;

public abstract class GuiContainer {

    public float gx, gy;
    public float guiWidth, guiHeight;
    public float guiX, guiY;
    public float mouseX, mouseY;

    public Inventory inventory;

    public final Texture INVENTORY_TEXTURE;

    public GuiContainer(Inventory inventory, Texture invTexture) {
        this.inventory = inventory;

        this.INVENTORY_TEXTURE = invTexture;

        this.gx = inventory.gx;
        this.gy = inventory.gy;
        this.guiWidth = inventory.guiWidth;
        this.guiHeight = inventory.guiHeight;
        this.guiX = inventory.guiX;
        this.guiY = inventory.guiY;
    }

    public void update() {
        inventory.update();

        for (Slot slot : inventory.getSlots()) {
            slot.update();
        }

        this.gx = inventory.gx;
        this.gy = inventory.gy;
        this.guiWidth = inventory.guiWidth;
        this.guiHeight = inventory.guiHeight;
        this.guiX = inventory.guiX;
        this.guiY = inventory.guiY;
        this.mouseX = inventory.mouseX;
        this.mouseY = inventory.mouseY;
    }

    public void render() {
        INVENTORY_TEXTURE.bind();
        Renderer.drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
        INVENTORY_TEXTURE.unbind();

        for (Slot slot : inventory.getSlots()) {
            slot.render();
        }

        ItemRendering it = new ItemRendering(inventory.getCurrentStack());
        it.render(mouseX - 8, mouseY - 8);

        for (Slot slot : inventory.getSlots()) {
            if (slot.getStackInSlot() != ItemStack.EMPTY) {
                if (slot.mouseHover((int) mouseX, (int) mouseY)) {
                    Renderer.drawHoveringBoxText(mouseX, mouseY, slot.getStackInSlot().getItem().getDescriptionElements());
                }
            }
        }
    }
}
