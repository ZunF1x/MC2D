package fr.zunf1x.mc2d.game.entities;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.gui.GuiContainer;
import fr.zunf1x.mc2d.game.gui.inventory.InventoryPlayer;
import fr.zunf1x.mc2d.game.level.Level;
import fr.zunf1x.mc2d.game.level.blocks.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.blocks.BlockContainer;
import fr.zunf1x.mc2d.game.level.blocks.blocks.IShearable;
import fr.zunf1x.mc2d.game.level.blocks.placing.BlockPlacer;
import fr.zunf1x.mc2d.game.level.items.items.ItemShears;
import fr.zunf1x.mc2d.game.level.items.items.ItemBlock;
import fr.zunf1x.mc2d.game.level.items.items.ItemTool;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;
import fr.zunf1x.mc2d.graphics.Renderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class EntityPlayer extends Entity {

    public InventoryPlayer inventory;

    public boolean guiOpen = false;

    public GuiContainer currentGui;

    public EntityPlayer(Level level, float x, float y) {
        super(level, x, y);

        this.inventory = new InventoryPlayer();

        mass = 0.48F;
        drag = 0.95F;
    }

    public void openInventory(GuiContainer gui) {
        this.currentGui = gui;
        this.guiOpen = true;
    }

    public void closeInventory() {
        this.guiOpen = false;
    }

    float xa, ya;

    float speed = 0.162F;

    int giveIndex = 0;

    int g = 0;
    int dam = 10;

    public void updateMovements(int index) {
        g++;

        ya += level.gravity * mass;

        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            xa -= speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            xa += speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            if (isOnGround()) {
                ya -= 18F;
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            if (speed == 0.162F) {
                speed *= 2;
            }
        } else {
            speed = 0.162F;
        }

        if (Mouse.isButtonDown(0)) {
            if (MC2D.instance.game.level.world.getSolidBlock(MC2D.instance.game.getMouseX(true) / 64, MC2D.instance.game.getMouseY(true) / 64) != null && !MC2D.instance.game.level.world.getSolidBlock(MC2D.instance.game.getMouseX(true) / 64, MC2D.instance.game.getMouseY(true) / 64).block.getBlockProperties().unbreakable) {
                BlockPlacer bl = level.world.getSolidBlock(MC2D.instance.game.getMouseX(true) / 64, MC2D.instance.game.getMouseY(true) / 64);

                if (inventory.getStacks().get(index).getItem() instanceof ItemTool) {
                    ItemTool i = (ItemTool) inventory.getStacks().get(index).getItem();
                    if (i.getHarvestTool().equals(bl.block.getDestroyer())) {
                        if (i.getHarvestLevel() >= bl.block.getHarvestLevel()) {
                            dam = 2;
                        } else {
                            dam = 10;
                        }
                    } else {
                        dam = 10;
                    }
                } else {
                    dam = 10;
                }

                if (g % dam == 0) {
                    bl.damage(2);
                }
            }
        }

        if (Mouse.isButtonDown(1)) {
            if (inventory.getStacks().get(index).getItem() instanceof ItemBlock) {
                ItemBlock ib = (ItemBlock) inventory.getStacks().get(index).getItem();

                if (level.world.getSolidBlock(MC2D.instance.game.getMouseX(true) / 64, MC2D.instance.game.getMouseY(true) / 64) == null) {
                    level.world.addBlock(MC2D.instance.game.getMouseX(true) / 64, MC2D.instance.game.getMouseY(true) / 64, new BlockPlacer(MC2D.instance.game.getMouseX(true) / 64, MC2D.instance.game.getMouseY(true) / 64, ib.getBlock()));
                    inventory.getStacks().get(index).decrStackSize(1);
                }
            }

            if (level.world.getSolidBlock(MC2D.instance.game.getMouseX(true) / 64, MC2D.instance.game.getMouseY(true) / 64) != null) {
                if (level.world.getSolidBlock(MC2D.instance.game.getMouseX(true) / 64, MC2D.instance.game.getMouseY(true) / 64).block instanceof BlockContainer) {
                    BlockContainer b = (BlockContainer) level.world.getSolidBlock(MC2D.instance.game.getMouseX(true) / 64, MC2D.instance.game.getMouseY(true) / 64).block;

                    b.interact();
                }
            }
        }

        for (int i = 0; i < inventory.getStacks().size(); i++) {
            if (inventory.getStacks().get(i).getStackSize() <= 0 || inventory.getStacks().get(i).getItem() == null) {
                inventory.getStacks().set(i, ItemStack.EMPTY);
            }
        }

        int xStep = (int) Math.abs(xa * 1000);
        for (int i = 0; i < xStep; i++) {
            if (!isSolidTile(xa / xStep, 0)) {
                x += xa / xStep;
            } else {
                xa = 0;
            }
        }

        int yStep = (int) Math.abs(ya * 1000);
        for (int i = 0; i < yStep; i++) {
            if (!isSolidTile(0, ya / yStep)) {
                y += ya / yStep;
            } else {
                ya = 0;
            }
        }

        if (isEntityItem((int) xa, (int) ya)) {
            level.world.removeEntityItem((int) xa, (int) ya);
        }

        xa *= drag;
        ya *= drag;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        texture.bind();
        Renderer.drawEntity(getX(), getY(), 64, 128, 0, true);
        texture.unbind();
    }
}
