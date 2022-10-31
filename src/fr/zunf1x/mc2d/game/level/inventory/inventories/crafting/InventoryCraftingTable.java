package fr.zunf1x.mc2d.game.level.inventory.inventories.crafting;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.inventory.ITickable;
import fr.zunf1x.mc2d.game.level.inventory.Inventory;
import fr.zunf1x.mc2d.game.level.inventory.ItemStack;
import fr.zunf1x.mc2d.game.level.inventory.ItemStackHelper;
import fr.zunf1x.mc2d.game.level.inventory.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InventoryCraftingTable extends Inventory implements ITickable {

    private final List<ItemStack> stacks;

    private final CraftMatrix craftMatrix;

    public InventoryCraftingTable(Game game) {
        super(game);

        this.craftMatrix = new CraftMatrix();

        this.stacks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            this.stacks.add(ItemStack.EMPTY);
        }
    }

    @Override
    public int getSizeInventory() {
        return this.stacks.size();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.stacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.stacks, index, count);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.stacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.stacks) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        Collections.fill(this.stacks, ItemStack.EMPTY);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.stacks, index);
    }

    @Override
    public void markDirty() {

    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void update() {
        Item[] ingredients = new Item[9];
        for (int i = 0; i < 9; i++) {
            ingredients[i] = this.getStackInSlot(i).getItem();
        }

        this.craftMatrix.updateIngredients(ingredients);

        for (IRecipe recipe : this.game.recipeRegistry.recipes) {
            if (recipe.matches(this.craftMatrix)) {
                this.setInventorySlotContents(9, recipe.getResult());
                break;
            } else {
                this.setInventorySlotContents(9, ItemStack.EMPTY);
            }
        }
    }
}
