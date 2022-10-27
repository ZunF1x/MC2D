package fr.zunf1x.mc2d.game.level.inventory;

import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.rendering.Renderer;

public class Slot {

    private IInventory inventory;
    private int slotIndex;
    private int x, y;

    public Slot(IInventory inventory, int slotIndex, int x, int y) {
        this.inventory = inventory;
        this.slotIndex = slotIndex;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_)
    {
        int i = p_75220_2_.getCount() - p_75220_1_.getCount();

        if (i > 0)
        {
            this.onCrafting(p_75220_2_, i);
        }
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack stack, int amount)
    {
    }

    protected void onSwapCraft(int p_190900_1_)
    {
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    protected void onCrafting(ItemStack stack)
    {
    }

    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        this.onSlotChanged();
        return stack;
    }

    public boolean isItemValid(ItemStack stack) {
        return true;
    }

    public ItemStack getStack() {
        return this.inventory.getStackInSlot(this.slotIndex);
    }

    public boolean getHasStack() {
        return !this.getStack().isEmpty();
    }

    public void putStack(ItemStack stack) {
        this.inventory.setInventorySlotContents(this.slotIndex, stack);
        this.onSlotChanged();
    }

    public void onSlotChanged() {
        this.inventory.markDirty();
    }

    public int getSlotStackLimit() {
        return this.inventory.getInventoryStackLimit();
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return this.getSlotStackLimit();
    }

    public ItemStack decrStackSize(int amount)
    {
        return this.inventory.decrStackSize(this.slotIndex, amount);
    }

    public ItemStack incrStackSize(int amount) {
        return this.inventory.incrStackSize(this.slotIndex, amount);
    }

    public boolean isHere(IInventory inv, int slotIn)
    {
        return inv == this.inventory && slotIn == this.slotIndex;
    }

    public boolean canTakeStack(EntityPlayer playerIn)
    {
        return true;
    }

    public int getIndex() {
        return slotIndex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
