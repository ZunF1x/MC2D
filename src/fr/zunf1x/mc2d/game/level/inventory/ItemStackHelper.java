package fr.zunf1x.mc2d.game.level.inventory;

import java.util.List;

public class ItemStackHelper {

    public static ItemStack getAndSplit(List<ItemStack> stacks, int index, int amount) {
        return index >= 0 && index < stacks.size() && !stacks.get(index).isEmpty() && amount > 0 ? stacks.get(index).splitStack(amount) : ItemStack.EMPTY;
    }

    public static ItemStack getAndRemove(List<ItemStack> stacks, int index) {
        return index >= 0 && index < stacks.size() ? stacks.set(index, ItemStack.EMPTY) : ItemStack.EMPTY;
    }
}
