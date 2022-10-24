package fr.zunf1x.mc2d.game.level.blocks.blocks;

import fr.zunf1x.mc2d.game.level.blocks.properties.BlockProperties;
import fr.zunf1x.mc2d.game.level.items.Items;
import fr.zunf1x.mc2d.game.level.items.items.Item;

public class BlockLeaves extends Block implements IShearable {

    public BlockLeaves() {
        super("Oak Leaves", 13, false, false, 0.2F);
    }

    @Override
    public Item getItemDroped() {
        return null;
    }

    @Override
    public Item getItemDropedByShears() {
        return Items.getItemFromBlock(this);
    }
}
