package fr.zunf1x.mc2d.game.level.items.utils;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.level.items.Items;
import fr.zunf1x.mc2d.game.level.items.items.ItemBlock;
import fr.zunf1x.mc2d.graphics.Color;
import fr.zunf1x.mc2d.graphics.Renderer;
import fr.zunf1x.mc2d.graphics.Texture;

public class Slot {

    public float id, x, y;
    private ItemStack stack;

    private final int SIZE = 16;

    public Slot(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.stack = ItemStack.EMPTY;
    }

    public void update() {
        if (stack.getStackSize() <= 0 || stack.getItem() == null) {
            this.stack = ItemStack.EMPTY;
        }
    }

    public void render() {
        if (stack.getItem() != null) {
            if (stack.getItem() instanceof ItemBlock) {
                Texture.blocksTextures.bind();
                Renderer.drawBlockOrItem(this.x, this.y, this.SIZE, this.stack.getItem().getTextureIndex());
                Texture.blocksTextures.unbind();
            } else {
                Texture.itemsTextures.bind();
                Renderer.drawBlockOrItem(this.x, this.y, this.SIZE, this.stack.getItem().getTextureIndex());
                Texture.itemsTextures.unbind();
            }

            if (stack.getStackSize() >= 2) {
                if (stack.getStackSize() > 9) {
                    MC2D.instance.font.drawString(Integer.toString(stack.getStackSize()), this.x + 5, this.y + 9, 8, 255);
                } else {
                    MC2D.instance.font.drawCenteredString(Integer.toString(stack.getStackSize()), this.x + 5, this.y + 13, 8, 255);
                }
            }
        }

        if (mouseHover(MC2D.instance.game.getMouseX(true), MC2D.instance.game.getMouseY(true))) {
            Renderer.drawQuad(this.x, this.y, this.SIZE, this.SIZE, new Color(1, 1, 1, 0.5F, false));
        }
    }

    public void setStackInSlot(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStackInSlot() {
        return this.stack;
    }

    public boolean mouseHover(float mouseX, float mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.SIZE && mouseY < this.y + this.SIZE;
    }

    public boolean isItemValidForSlot(ItemStack stack) {
        return true;
    }

    public boolean canTakeStack() {
        return true;
    }
}
