package fr.zunf1x.mc2d.game.gui.inventory;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.crafting.Recipes;
import fr.zunf1x.mc2d.game.level.items.utils.CraftingResultSlot;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;
import fr.zunf1x.mc2d.game.level.items.utils.Slot;

public class InventoryCraftingTable extends Inventory {

    public InventoryCraftingTable() {
        super(46);

        for (int i = 0; i < 36; i++) {
            this.setStackInInventory(i, MC2D.instance.game.player.inventory.getStackInInventory(i));
        }
    }

    @Override
    public void init() {
        for (int i = 0; i < 9; i++) {
            this.addSlotInInventory(new Slot(i, guiX + 8 + i * 18, guiY + 142));
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 9; i++) {
                this.addSlotInInventory(new Slot(i + j * 9 + 9, guiX + (8 + i * 18), guiY + (84 + j * 18)));
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                this.addSlotInInventory(new Slot(i + j + 36, guiX + 30 + (i * 18), guiY + 17 + (j * 18)));
            }
        }

        this.addSlotInInventory(new CraftingResultSlot(45, guiX + 124, guiY + 35));
    }

    @Override
    public void update() {
        super.update();

        ItemStack result = Recipes.getRecipeResult(new ItemStack[]{
                getStacks().get(36), getStacks().get(37), getStacks().get(38),
                getStacks().get(39), getStacks().get(40), getStacks().get(41),
                getStacks().get(42), getStacks().get(43), getStacks().get(44)});
        if (result != null) {
            getStacks().set(45, result);
        } else {
            getStacks().set(45, ItemStack.EMPTY);
        }
    }
}
