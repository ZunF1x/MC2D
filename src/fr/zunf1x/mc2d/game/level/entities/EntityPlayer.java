package fr.zunf1x.mc2d.game.level.entities;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.inventory.ItemStack;
import fr.zunf1x.mc2d.game.level.inventory.inventories.player.InventoryPlayer;
import fr.zunf1x.mc2d.game.level.inventory.inventories.player.InventoryPlayerGui;
import fr.zunf1x.mc2d.game.level.inventory.items.Item;
import fr.zunf1x.mc2d.game.level.inventory.items.ItemBlock;
import fr.zunf1x.mc2d.game.level.inventory.items.Items;
import fr.zunf1x.mc2d.math.Mathf;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;
import org.lwjgl.input.Keyboard;

public class EntityPlayer extends Entity {

    public InventoryPlayer inv;
    public InventoryPlayerGui g;

    public EntityPlayer(Vector2d loc) {
        super(loc);
    }

    @Override
    public void init(Game game) {
        this.inv = new InventoryPlayer(game);
        this.g = new InventoryPlayerGui(this.inv);

        for (int i = 0; i < Blocks.blocks.size(); i++) {
            this.inv.setInventorySlotContents(i, new ItemStack(Blocks.getItemBlock(i), 64));
        }

        super.init(game);
    }

    public float speed = 0.162F / 64F;
    public float xa, ya;

    @Override
    public void update() {
        ya += 1.8F * 0.62F / 64F;

        if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
            ya -= speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            ya += speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            xa -= speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            xa += speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            if (isOnGround()) {
                ya = -20F / 64F;
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            if (speed == 0.162F / 64F) {
                speed *= 2;
            }
        } else {
            speed = 0.162F / 64F;
        }

        int xStep = (int) Math.abs(xa * 1000);
        for (int i = 0; i < xStep; i++) {
            if (collide(xa / xStep, 0)) {
                xa = 0;
            } else {
                getLocation().setX(Mathf.clamp(getLocation().getX() + xa / xStep, 0.0001D, game.getWorld().getSize() * 16F - 1, e -> xa = 0));
            }
        }

        int yStep = (int) Math.abs(ya * 1000);
        for (int i = 0; i < yStep; i++) {
            if (collide(0, ya / yStep)) {
                ya = 0;
            } else {
                getLocation().addY(ya / yStep);
            }
        }

        xa *= 0.95F;
        ya *= 0.95F;
    }

    @Override
    public void render() {
        this.drawPlayer(this.getLocation().getX(), this.getLocation().getY());
    }

    private void drawPlayer(double x, double y) {
        Texture.ENTITIES.bind();
        Renderer.drawEntity(x, y, 0);
        Texture.ENTITIES.unbind();
    }

    public void updateSlots() {
        this.g.updateSlots();
    }

    public void renderGui() {
        g.render();
    }
}
