package fr.zunf1x.mc2d.game;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.entities.EntityPlayer;
import fr.zunf1x.mc2d.game.gui.GuiPlayerInventory;
import fr.zunf1x.mc2d.game.level.Level;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.blocks.placing.BlockPlacer;
import fr.zunf1x.mc2d.game.level.items.Items;
import fr.zunf1x.mc2d.game.level.items.items.ItemDamageable;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;
import fr.zunf1x.mc2d.graphics.ItemRendering;
import fr.zunf1x.mc2d.graphics.Renderer;
import fr.zunf1x.mc2d.graphics.Texture;
import fr.zunf1x.mc2d.utils.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import javax.swing.*;
import java.io.File;

import static org.lwjgl.opengl.GL11.*;

public class Game {

    public Level level;

    public float xScroll, yScroll;
    int width, height, scale;

    public final EntityManager entityManager;

    public int i = 0;

    public EntityPlayer player;

    public Game(int width, int height, int scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;

        Blocks.init();
        Items.init();

        level = new Level(scale, 1024, 256);
        xScroll = level.getBound(0);
        yScroll = level.getBound(1);

        this.entityManager = new EntityManager();

        File playerData = new File("world/player_data.json");

        if (!playerData.exists()) {
            player = new EntityPlayer(level, 0, 170);
        } else {
            JSONArray jsonFile = JSONUtils.readFromJSON("world/player_data.json");
            JSONArray jsonArray = (JSONArray) jsonFile.get(0);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            float x = ((Double) jsonObject.get("x")).floatValue();
            float y = ((Double) jsonObject.get("y")).floatValue();

            player = new EntityPlayer(level, x, y);
        }

        if (playerData.exists()) {
            JSONArray jsonArray = JSONUtils.readFromJSON("world/player_data.json");
            JSONArray inv = (JSONArray) jsonArray.get(1);

            for (int i = 0; i < inv.size(); i++) {
                JSONObject jsonObject = (JSONObject) inv.get(i);
                int itemId = ((Long) jsonObject.get("item")).intValue();
                int stackSize = ((Long) jsonObject.get("stackSize")).intValue();

                if (itemId == 4096) {
                    player.inventory.getStacks().set(i, ItemStack.EMPTY);
                } else {
                    player.inventory.getStacks().set(i, new ItemStack(Items.getItem(itemId), stackSize));
                }
            }
        }

        entityManager.addEntity(player);
    }

    public void translateView(float xa, float ya) {
        xScroll = xa;
        yScroll = ya;

        if (xScroll > level.getBound(0)) xScroll = level.getBound(0);
        if (xScroll < level.getBound(2)) xScroll = level.getBound(2);
        if (yScroll > level.getBound(1)) yScroll = level.getBound(1);
        if (yScroll < level.getBound(3)) yScroll = level.getBound(3);
    }

    float xa = 0, ya = 0;
    public void update(int width, int height, int scale) {
        level.update(scale, entityManager);

        this.width = width;
        this.height = height;
        this.scale = scale;

        i -= Mouse.getDWheel() / 120;

        if (i < 0) {
            i = 8;
        } else if (i > 8) {
            i = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
            i = 0;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
            i = 1;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
            i = 2;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
            i = 3;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_5)) {
            i = 4;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_6)) {
            i = 5;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_7)) {
            i = 6;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_8)) {
            i = 7;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_9)) {
            i = 8;
        }

        /*player.inventory.update((int) -xScroll, (int) -yScroll);

        if (inventoryOpened) {
            inv.update((int) -xScroll, (int) -yScroll);
        }*/

        if (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_E && Keyboard.getEventKeyState()) {
                if (!player.guiOpen) {
                    player.openInventory(new GuiPlayerInventory(player.inventory));
                } else {
                    /*if (player.currentGui.inventory.currentStack != ItemStack.EMPTY) {
                        level.world.addEntityItem((int) (player.x + 96) / 64, (int) player.y / 64, new EntityItem(level, (int) (player.x + 96) / 64, (int) player.y / 64, player.currentGui.inventory.currentStack));
                        player.currentGui.inventory.currentStack = ItemStack.EMPTY;
                    }*/

                    if (player.currentGui.inventory.currentStack == ItemStack.EMPTY) {
                        player.closeInventory();
                    }
                }
            } else if (Keyboard.getEventKey() == Keyboard.KEY_G && Keyboard.getEventKeyState()) {
                giveItem();
            } /*else if (Keyboard.getEventKey() == Keyboard.KEY_A && Keyboard.getEventKeyState()) {
                if (player.inventory.stacks.get(i) != ItemStack.EMPTY) {
                    player.inventory.stacks.get(i).decrStackSize(1);
                }
            }*/
        }

        /*if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
            if (player.inventory.stacks.get(i) != ItemStack.EMPTY) {
                player.inventory.stacks.set(i, ItemStack.EMPTY);
            }
        }*/

        if (!player.guiOpen) {
            player.updateMovements(i);
        }

        if (player.currentGui != null) {
            player.currentGui.update();
        }

        xa = -player.getX() + (float) MC2D.instance.width / 2 - 32;
        ya = -player.getY() + (float) MC2D.instance.height / 2 - 64;
        translateView(xa, ya);
    }

    public void saveWorld() {
        File worldFolder = new File("world");

        if (!worldFolder.exists()) {
            worldFolder.mkdir();
        }

        world();
        playerData();
    }

    private void world() {
        JSONArray jsonSolidArray = new JSONArray();
        JSONArray jsonBgArray = new JSONArray();
        JSONArray jsonArray = new JSONArray();

        for (BlockPlacer b : level.world.getBlocks()) {
            int index = b.block.getTextureIndex();
            float x = b.x;
            float y = b.y;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("index", index);
            jsonObject.put("x", x);
            jsonObject.put("y", y);
            jsonSolidArray.add(jsonObject);
        }

        for (BlockPlacer b : level.world.getBgBlocks()) {
            int index = b.block.getTextureIndex();
            float x = b.x;
            float y = b.y;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("index", index);
            jsonObject.put("x", x);
            jsonObject.put("y", y);
            jsonBgArray.add(jsonObject);
        }

        jsonArray.add(jsonSolidArray);
        jsonArray.add(jsonBgArray);
        JSONUtils.writeToJSON(jsonArray, "world/world.json");
    }

    private void playerData() {
        JSONArray playerData = new JSONArray();
        JSONArray inventory = new JSONArray();
        JSONArray cpData = new JSONArray();

        JSONObject location = new JSONObject();
        location.put("x", player.x / 64);
        location.put("y", player.y / 64);
        playerData.add(location);

        for (ItemStack stack : player.inventory.getStacks()) {
            int itemId = Items.getKeyByValue(stack.getItem());
            int stackSize = stack.getStackSize();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("item", itemId);
            jsonObject.put("stackSize", stackSize);
            inventory.add(jsonObject);
        }

        cpData.add(playerData);
        cpData.add(inventory);
        JSONUtils.writeToJSON(cpData, "world/player_data.json");
    }

    int giveIndex = 0;

    public void giveItem() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter a block or item id"));
        int number = Integer.parseInt(JOptionPane.showInputDialog("Enter a number"));

        ItemStack stack = ItemStack.EMPTY;

        if (player.inventory.getStacks().get(giveIndex).getStackSize() + stack.getStackSize() > 64) {
            giveIndex++;
        }

        if (player.inventory.getStacks().get(giveIndex) == ItemStack.EMPTY) {
            player.inventory.getStacks().set(giveIndex, stack);
        } else {
            player.inventory.getStacks().get(giveIndex).incrStackSize(stack.getStackSize());
        }

        giveIndex = 0;
    }

    public void render() {
        glTranslatef(xScroll, yScroll, 0);

        level.render(entityManager);

        if (player.guiOpen) {
            player.currentGui.render();
        }

        drawOverlay(-xScroll, -yScroll);
    }

    public void drawOverlay(float x, float y) {
        MC2D.instance.font.drawString("fps: " + MC2D.instance.fps, x + 2, y + 2, 8, 255);

        MC2D.instance.font.drawString("X / Y: " + (player.getX() / 64) + " / " + (256 - (player.getY() / 64)), x + 2,  y + 12, 8, 255);

        Texture.widgetsTextures.bind();
        Renderer.drawTexturedModalRect(x + ((float) (width / 2) - (float) (182 / 2)), y + (height - 27), 0, 0, 182, 22);
        drawStackHoverer(x, y, i);
        Texture.widgetsTextures.unbind();

        float ix = x + ((float) (width / 2) - (float) (182 / 2) + 3);
        float iy = y + (height - 27) + 3;

        for (int i = 0; i < 9; i++) {
            ItemRendering item = new ItemRendering(player.inventory.getStacks().get(i));
            item.render(ix + (i * 20), iy);
        }

        /*if (inventoryOpened) {
            inv.render();
        }*/
    }

    public void drawStackHoverer(float x, float y, int index) {
        Renderer.drawTexturedModalRect(x + ((((float) (width / 2) - (float) (182 / 2)) - 1) + index * 20), y + (height - 28), 0, 22, 24, 24);
    }

    public int getMouseX(boolean scrollingDepend) {
        int mouseX = Mouse.getX() / MC2D.instance.SCALE;

        if (scrollingDepend) {
            return (int) (mouseX - xScroll);
        } else {
            return mouseX;
        }
    }

    public int getMouseY(boolean scrollingDepend) {
        int mouseY = (Display.getHeight() - Mouse.getY()) / MC2D.instance.SCALE;

        if (scrollingDepend) {
            return (int) (mouseY - yScroll);
        } else {
            return mouseY;
        }
    }
}
