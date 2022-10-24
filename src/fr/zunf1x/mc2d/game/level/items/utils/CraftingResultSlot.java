package fr.zunf1x.mc2d.game.level.items.utils;

import java.nio.file.LinkPermission;

public class CraftingResultSlot extends Slot {

    public CraftingResultSlot(int id, float x, float y) {
        super(id, x, y);
    }

    @Override
    public boolean isItemValidForSlot(ItemStack stack) {
        return true;
    }
}
