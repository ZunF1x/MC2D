package fr.zunf1x.mc2d.game.level.inventory.inventories.player;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.inventory.*;
import fr.zunf1x.mc2d.game.level.inventory.inventories.crafting.CraftMatrix;
import fr.zunf1x.mc2d.game.level.inventory.inventories.crafting.IRecipe;
import fr.zunf1x.mc2d.game.level.inventory.items.Item;
import fr.zunf1x.mc2d.game.level.inventory.items.ItemBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPlayer extends Inventory implements ITickable {

    private final List<ItemStack> stacks;

    private CraftMatrix craftMatrix;

    public InventoryPlayer(Game game) {
        super(game);

        this.craftMatrix = new CraftMatrix();

        this.stacks = new ArrayList<>();

        for (int i = 0; i < 45; i++) {
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
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void update() {
        Item[] ingredients = new Item[9];
        ingredients[0] = this.getStackInSlot(40).getItem();
        ingredients[1] = this.getStackInSlot(42).getItem();
        ingredients[3] = this.getStackInSlot(41).getItem();
        ingredients[4] = this.getStackInSlot(43).getItem();

        this.craftMatrix.updateIngredients(ingredients);

        for (IRecipe recipe : this.game.recipeRegistry.recipes) {
            if (recipe.matches(this.craftMatrix)) {
                this.setInventorySlotContents(44, recipe.getResult());
                break;
            } else {
                this.setInventorySlotContents(44, ItemStack.EMPTY);
            }
        }
    }
}
