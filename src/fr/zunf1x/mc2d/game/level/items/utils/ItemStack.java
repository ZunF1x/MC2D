package fr.zunf1x.mc2d.game.level.items.utils;

import fr.zunf1x.mc2d.game.level.items.items.Item;

public class ItemStack {

    public static final ItemStack EMPTY = new ItemStack(null, 0);

    private Item item;
    private int stackSize;

    public ItemStack(Item item) {
        this(item, 1);
    }

    public ItemStack(Item item, int stackSize) {
        this.item = item;
        this.stackSize = stackSize;
    }

    private Item setItem(Item item) {
        this.item = item;

        return this.item;
    }

    private int setStackSize(int stackSize) {
        this.stackSize = stackSize;

        return this.stackSize;
    }

    public int incrStackSize(int v) {
        this.stackSize += v;

        return this.stackSize;
    }

    public int decrStackSize(int v) {
        this.stackSize -= v;

        return this.stackSize;
    }

    public Item getItem() {
        return item;
    }

    public boolean isEmpty() {
        return this == ItemStack.EMPTY;
    }

    public int getStackSize() {
        return stackSize;
    }

    public ItemStack copy() {
        return new ItemStack(item, stackSize);
    }
}
