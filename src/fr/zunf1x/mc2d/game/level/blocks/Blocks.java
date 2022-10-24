package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.level.blocks.blocks.*;
import fr.zunf1x.mc2d.game.level.blocks.properties.BlockProperties;
import fr.zunf1x.mc2d.game.level.items.Items;
import fr.zunf1x.mc2d.game.level.items.items.Item;
import fr.zunf1x.mc2d.game.level.items.items.ItemBlock;

import java.util.HashMap;
import java.util.Map;

public class Blocks {

    private static Map<Integer, Block> blocks;

    public static Block stone, dirt, grass, bedrock, log, planks, crafting_table, coal_ore, iron_ore, gold_ore, diamond_ore, redstone_ore, lapis_ore, leaves, cobblestone;

    public static void init() {
        blocks = new HashMap<>();

        stone = registerBlock(0, new Block("Stone", 0, false, false, 1.5F) {
            @Override
            public Item getItemDroped() {
                return Items.getItemFromBlock(Blocks.cobblestone);
            }
        }.setDestroyer("pickaxe"));
        dirt = registerBlock(1, new Block("Dirt", 1, false, false, 0.5F) {
            @Override
            public boolean canBeDestroyByHand() {
                return true;
            }
        }.setDestroyer("shovel").setHarvestLevel(0));
        grass = registerBlock(2, new Block("Grass", 2, false, false, 0.6F) {
            @Override
            public Item getItemDroped() {
                return Items.getItemFromBlock(Blocks.dirt);
            }

            @Override
            public boolean canBeDestroyByHand() {
                return true;
            }
        }.setDestroyer("shovel").setHarvestLevel(0));
        bedrock = registerBlock(3, new BlockBedrock());
        log = registerBlock(4, new Block("Oak Log", BlockProperties.DEFAULT_PROPERTIES, 4) {
            @Override
            public boolean canBeDestroyByHand() {
                return true;
            }
        }.setDestroyer("axe").setHarvestLevel(0));
        planks = registerBlock(5, new Block("Oak Planks", BlockProperties.DEFAULT_PROPERTIES, 5) {
            @Override
            public boolean canBeDestroyByHand() {
                return true;
            }
        }.setDestroyer("axe").setHarvestLevel(0));
        crafting_table = registerBlock(6, new BlockCraftingTable() {
            @Override
            public boolean canBeDestroyByHand() {
                return true;
            }
        }.setDestroyer("axe").setHarvestLevel(0));
        coal_ore = registerBlock(7, new Block("Coal Ore", BlockProperties.DEFAULT_PROPERTIES, 7).setDestroyer("pickaxe").setHarvestLevel(0));
        iron_ore = registerBlock(8, new Block("Iron Ore", BlockProperties.DEFAULT_PROPERTIES, 8).setDestroyer("pickaxe").setHarvestLevel(1));
        gold_ore = registerBlock(9, new Block("Gold Ore", BlockProperties.DEFAULT_PROPERTIES, 9).setDestroyer("pickaxe").setHarvestLevel(2));
        diamond_ore = registerBlock(10, new Block("Diamond Ore", BlockProperties.DEFAULT_PROPERTIES, 10).setDestroyer("pickaxe").setHarvestLevel(2));
        redstone_ore = registerBlock(11, new Block("Redstone Ore", BlockProperties.DEFAULT_PROPERTIES, 11).setDestroyer("pickaxe").setHarvestLevel(2));
        lapis_ore = registerBlock(12, new Block("Lapis Lazuli Ore", BlockProperties.DEFAULT_PROPERTIES, 12).setDestroyer("pickaxe").setHarvestLevel(2));
        leaves = registerBlock(13, new BlockLeaves());
        cobblestone = registerBlock(14, new Block("Cobblestone", 14, false, false, 2.0F).setDestroyer("pickaxe").setHarvestLevel(0));
    }

    private static Block registerBlock(int id, Block block) {
        blocks.put(id, block);

        return blocks.get(id);
    }

    public static Block getBlock(int id) {
        return blocks.get(id);
    }
}
