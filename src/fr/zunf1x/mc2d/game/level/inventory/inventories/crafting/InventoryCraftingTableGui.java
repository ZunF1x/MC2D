package fr.zunf1x.mc2d.game.level.inventory.inventories.crafting;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.level.inventory.GuiContainer;
import fr.zunf1x.mc2d.game.level.inventory.Inventory;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

public class InventoryCraftingTableGui extends GuiContainer {

    public static final Texture INVENTORY = Texture.loadTexture("crafting_table");

    public InventoryCraftingTableGui(Inventory inv) {
        super(new InventoryCraftingTableContainer(inv));
    }

    @Override
    public void render() {
        INVENTORY.bind();

        Renderer.drawGuiInventory(this.container.guiLeft, this.container.guiTop, 0, 0, this.container.xSize * 2, this.container.ySize * 2, this.container.xSize, this.container.ySize);

        INVENTORY.unbind();

        Start.getInstance().getFont().drawStringWithShadow("Crafting", this.container.guiLeft + 56, this.container.guiTop + 12, 2, 1, 4210752);
        Start.getInstance().getFont().drawStringWithShadow("Inventory", this.container.guiLeft + 16, this.container.guiTop + this.container.ySize - 188, 2, 1, 4210752);

        super.render();
    }
}
