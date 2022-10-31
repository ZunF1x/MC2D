package fr.zunf1x.mc2d.game.level.inventory.inventories.crafting;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.game.level.inventory.*;

public class InventoryCraftingTableContainer extends Container {

    public InventoryCraftingTableContainer(Inventory inventory) {
        super(inventory);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void init() {
        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(Start.getInstance().getGame().player.inv, i, 8 + i * 18, 142));
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 9; i++) {
                this.addSlotToContainer(new Slot(Start.getInstance().getGame().player.inv, i + j * 9 + 9, 8 + i * 18, 84 + j * 18));
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                this.addSlotToContainer(new Slot(this.inventory, i + j * 3, 30 + i * 18, 17 + j * 18));
            }
        }

        this.addSlotToContainer(new Slot(this.inventory, 9, 124, 35) {
            @Override
            public boolean canTakeStack(EntityPlayer playerIn) {
                return false;
            }

            @Override
            protected boolean deleteStack() {
                return false;
            }

            @Override
            public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
                for (int i = 0; i < 9; i++) {
                    if (inventory.getStackInSlot(i).getItem() != null) {
                        if (currentStack.getCount() + stack.getCount() <= 64) {
                            if (inventory.getStackInSlot(i).getCount() > 1) {
                                inventory.getStackInSlot(i).shrink(1);
                            } else {
                                inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                            }
                        }
                    }
                }

                return super.onTake(thePlayer, stack);
            }
        });
    }
}
