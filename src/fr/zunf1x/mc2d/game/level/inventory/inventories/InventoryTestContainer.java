package fr.zunf1x.mc2d.game.level.inventory.inventories;

import fr.zunf1x.mc2d.game.level.inventory.Container;
import fr.zunf1x.mc2d.game.level.inventory.IInventory;
import fr.zunf1x.mc2d.game.level.inventory.Slot;

public class InventoryTestContainer extends Container {
    
    public InventoryTestContainer(IInventory inventory) {
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
    }
}
