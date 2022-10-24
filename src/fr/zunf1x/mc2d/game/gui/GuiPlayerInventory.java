package fr.zunf1x.mc2d.game.gui;

import fr.zunf1x.mc2d.game.gui.inventory.Inventory;
import fr.zunf1x.mc2d.graphics.Renderer;
import fr.zunf1x.mc2d.graphics.Texture;

public class GuiPlayerInventory extends GuiContainer {

    public GuiPlayerInventory(Inventory inventory) {
        super(inventory, Texture.loadTexture("/inventory.png"));
    }

    @Override
    public void render() {
        super.render();

        Texture player = Texture.loadTexture("/entities.png");

        player.bind();
        Renderer.drawEntity(guiX + 34, guiY + 11, 32, 64, 0, true);
        player.unbind();
    }
}
