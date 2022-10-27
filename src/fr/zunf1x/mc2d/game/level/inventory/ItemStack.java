package fr.zunf1x.mc2d.game.level.inventory;

import fr.zunf1x.mc2d.game.level.inventory.items.Item;
import fr.zunf1x.mc2d.math.Mathf;

public class ItemStack {

    public static final ItemStack EMPTY = new ItemStack(null);

    private Item item;
    private int stackCount;

    public ItemStack(Item item) {
        this(item, 1);
    }

    public ItemStack(Item item, int stackCount) {
        this.item = item;
        this.stackCount = (int) Mathf.clamp(stackCount, 1, 64);
    }

    public ItemStack setCount(int value) {
        this.stackCount = value;

        return this;
    }

    public ItemStack copy() {
        return new ItemStack(this.item, this.stackCount);
    }

    public void grow(int quantity)
    {
        this.setCount(this.stackCount + quantity);
    }

    public void shrink(int quantity)
    {
        this.grow(-quantity);
    }

    public ItemStack splitStack(int amount)
    {
        int i = Math.min(amount, this.stackCount);
        ItemStack itemstack = this.copy();
        itemstack.setCount(i);
        this.shrink(i);
        return itemstack;
    }

    public boolean isEmpty() {
        return this.stackCount <= 0 || this.item == null;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return stackCount;
    }
}
