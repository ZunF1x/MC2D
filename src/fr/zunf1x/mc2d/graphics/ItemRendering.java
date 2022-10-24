package fr.zunf1x.mc2d.graphics;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.level.items.items.ItemBlock;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;

public class ItemRendering {

    public float x, y;
    public ItemStack stack;

    public ItemRendering(ItemStack stack) {
        this.stack = stack;
    }

    public void render(float x, float y) {
        this.x = x;
        this.y = y;

        if (stack.getItem() != null) {
            if (stack.getItem() instanceof ItemBlock) {
                Texture.blocksTextures.bind();
                Renderer.drawBlockOrItem(this.x, this.y, 16, this.stack.getItem().getTextureIndex());
                Texture.blocksTextures.unbind();
            } else {
                Texture.itemsTextures.bind();
                Renderer.drawBlockOrItem(this.x, this.y, 16, this.stack.getItem().getTextureIndex());
                Texture.itemsTextures.unbind();
            }
        }

        if (stack.getStackSize() >= 2) {
            if (stack.getStackSize() > 9) {
                MC2D.instance.font.drawString(Integer.toString(stack.getStackSize()), x + 5, y + 9, 8, 255);
            } else {
                MC2D.instance.font.drawCenteredString(Integer.toString(stack.getStackSize()), x + 5, y + 13, 8, 255);
            }
        }
    }
}
