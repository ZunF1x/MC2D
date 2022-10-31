package fr.zunf1x.mc2d.game.level.inventory.inventories.player;

import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.game.level.inventory.*;

public class InventoryPlayerContainer extends Container {
    
    public InventoryPlayerContainer(Inventory inventory) {
        super(inventory);
    }

    @Override
    public void init() {
        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 9; i++) {
                this.addSlotToContainer(new Slot(inventory, i + j * 9 + 9, 8 + i * 18, 84 + j * 18));
            }
        }

        for (int i = 0; i < 4; i++) {
            this.addSlotToContainer(new Slot(inventory, 36 + i, 8, 8 + i * 18));
        }

        this.addSlotToContainer(new Slot(inventory, 40, 98, 18));
        this.addSlotToContainer(new Slot(inventory, 41, 98, 36));
        this.addSlotToContainer(new Slot(inventory, 42, 116, 18));
        this.addSlotToContainer(new Slot(inventory, 43, 116, 36));

        this.addSlotToContainer(new Slot(inventory, 44, 154, 28) {
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
                for (int i = 0; i < 4; i++) {
                    if (inventory.getStackInSlot(40 + i).getItem() != null) {
                        if (currentStack.getCount() + stack.getCount() <= 64) {
                            if (inventory.getStackInSlot(40 + i).getCount() > 1) {
                                inventory.getStackInSlot(40 + i).shrink(1);
                            } else {
                                inventory.setInventorySlotContents(40 + i, ItemStack.EMPTY);
                            }
                        }
                    }
                }

                return super.onTake(thePlayer, stack);
            }
        });
    }
}
