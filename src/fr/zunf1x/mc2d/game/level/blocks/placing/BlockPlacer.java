package fr.zunf1x.mc2d.game.level.blocks.placing;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.gui.inventory.InventoryPlayer;
import fr.zunf1x.mc2d.game.level.blocks.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.blocks.IShearable;
import fr.zunf1x.mc2d.game.level.items.items.ItemShears;
import fr.zunf1x.mc2d.game.level.items.items.ItemTool;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;
import fr.zunf1x.mc2d.graphics.Renderer;
import fr.zunf1x.mc2d.graphics.Texture;

public class BlockPlacer {

    public int x, y;

    public int size = 64;

    public Block block;

    public float damage = 0, maxDamage = 50;

    boolean damaging;

    public BlockPlacer(int x, int y, Block block) {
        this.x = x;
        this.y = y;
        this.block = block;

        maxDamage *= block.getBlockProperties().getHardness();
    }

    int giveIndex = 0;

    public void update() {
        block.getBlockProperties().update();

        InventoryPlayer inventory = MC2D.instance.game.player.inventory;
        int index = MC2D.instance.game.i;

        if (damage >= maxDamage) {
            Block b = this.block;
            ItemStack stack;

            if (!(b instanceof IShearable)) {
                if (!b.canBeDestroyByHand()) {
                    if (inventory.getStacks().get(index).getItem() instanceof ItemTool) {
                        ItemTool i = (ItemTool) inventory.getStacks().get(index).getItem();
                        if (i.getHarvestTool().equals(b.getDestroyer())) {
                            if (i.getHarvestLevel() >= b.getHarvestLevel()) {
                                stack = new ItemStack(b.getItemDroped());
                            } else {
                                stack = ItemStack.EMPTY;
                            }
                        } else {
                            stack = ItemStack.EMPTY;
                        }
                    } else {
                        stack = ItemStack.EMPTY;
                    }
                } else {
                    stack = new ItemStack(b.getItemDroped());
                }
            } else {
                if (inventory.getStacks().get(index).getItem() instanceof ItemShears) {
                    stack = new ItemStack(((IShearable) b).getItemDropedByShears());
                } else {
                    stack = new ItemStack(b.getItemDroped());
                }
            }

            while (inventory.getStacks().get(giveIndex).getItem() != stack.getItem() && inventory.getStacks().get(giveIndex) != ItemStack.EMPTY || inventory.getStacks().get(giveIndex).getStackSize() + stack.getStackSize() > 64) {
                giveIndex++;
            }

            if (giveIndex <= 36) {
                if (inventory.getStacks().get(giveIndex) != ItemStack.EMPTY) {
                    inventory.getStacks().get(giveIndex).incrStackSize(1);
                } else {
                    inventory.getStacks().set(giveIndex, stack);
                }
            }

            giveIndex = 0;

            MC2D.instance.game.level.world.removeBlock(this.x, this.y);
        }

        if (!damaging) {
            damage = 0;
        }
    }

    public void updateBreaking(boolean damaging) {
        this.damaging = damaging;
    }

    public void damage(int damage) {
        this.damage += damage;
    }

    public void render() {
        float x0 = x + MC2D.instance.game.xScroll / size;
        float y0 = y + MC2D.instance.game.yScroll / size;

        float x1 = x + size + MC2D.instance.game.xScroll / size;
        float y1 = y + size + MC2D.instance.game.yScroll / size;

        if (x1 < 0 || y1 < 0 || x0 > (float) MC2D.instance.width / size || y0 > (float) MC2D.instance.height / size) return;
        Texture.blocksTextures.bind();
        Renderer.drawBlockOrItem(x * size, y * size, size, block.getTextureIndex());
        Texture.blocksTextures.unbind();

        float i = 16 + ((damage / maxDamage) * 9);

        if (damage > 0) {
            Texture.blocksTextures.bind();
            Renderer.drawBlockOrItem(x * size, y * size, size, (int) i);
            Texture.blocksTextures.unbind();
        }
    }
}
