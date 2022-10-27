package fr.zunf1x.mc2d.game.level.inventory.inventories;

import fr.zunf1x.mc2d.game.level.inventory.IInventory;
import fr.zunf1x.mc2d.game.level.inventory.ItemStack;
import fr.zunf1x.mc2d.game.level.inventory.ItemStackHelper;
import fr.zunf1x.mc2d.game.level.inventory.items.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPlayer implements IInventory {

    private List<ItemStack> stacks;

    public InventoryPlayer() {
        this.stacks = new ArrayList<>();

        for (int i = 0; i < 45; i++) {
            this.stacks.add(new ItemStack(null, 0));
        }

        setInventorySlotContents(0, new ItemStack(new Item(0) {

        }));
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
    public ItemStack incrStackSize(int index, int count) {
        ItemStack s = getStackInSlot(index);
        s.setCount(s.getCount() - count);

        return s;
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
}
