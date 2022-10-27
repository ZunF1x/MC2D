package fr.zunf1x.mc2d.game.level.inventory.inventories;

import fr.zunf1x.mc2d.game.level.inventory.GuiContainer;
import fr.zunf1x.mc2d.game.level.inventory.IInventory;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

public class InventoryTestGui extends GuiContainer {


    public static final Texture INVENTORY = Texture.loadTexture("inventory");

    public InventoryTestGui(IInventory inv) {
        super(new InventoryTestContainer(inv));
    }

    @Override
    public void render() {
        INVENTORY.bind();

        Renderer.drawGuiInventory(this.container.guiLeft, this.container.guiTop, 0, 0, this.container.xSize, this.container.ySize);

        INVENTORY.unbind();

        super.render();
    }
}
