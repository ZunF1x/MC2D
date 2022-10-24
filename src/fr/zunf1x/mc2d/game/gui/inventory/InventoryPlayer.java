package fr.zunf1x.mc2d.game.gui.inventory;

import fr.zunf1x.mc2d.game.crafting.Recipes;
import fr.zunf1x.mc2d.game.level.items.items.ItemArmor;
import fr.zunf1x.mc2d.game.level.items.utils.CraftingResultSlot;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;
import fr.zunf1x.mc2d.game.level.items.utils.Slot;

public class InventoryPlayer extends Inventory {

    public InventoryPlayer() {
        super(45);
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

        for (int i = 0; i < 4; i++) {
            this.addSlotInInventory(new Slot(36 + i, guiX + 8, guiY + 8 + (i * 18)) {
                @Override
                public boolean isItemValidForSlot(ItemStack stack) {
                    return stack.getItem() instanceof ItemArmor;
                }
            });
        }

        this.addSlotInInventory(new Slot(40, guiX + 98, guiY + 18));
        this.addSlotInInventory(new Slot(41, guiX + 116, guiY + 18));
        this.addSlotInInventory(new Slot(42, guiX + 98, guiY + 36));
        this.addSlotInInventory(new Slot(43, guiX + 116, guiY + 36));
        this.addSlotInInventory(new CraftingResultSlot(44, guiX + 154, guiY + 28));
    }

    @Override
    public void update() {
        super.update();

        ItemStack result = Recipes.getRecipeResult(new ItemStack[]{
                getStacks().get(40), getStacks().get(41), ItemStack.EMPTY,
                getStacks().get(42), getStacks().get(43), ItemStack.EMPTY,
                ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
        if (result != null) {
            getStacks().set(44, result);
        } else {
            getStacks().set(44, ItemStack.EMPTY);
        }
    }
}
