package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.game.level.inventory.items.Item;
import fr.zunf1x.mc2d.game.level.inventory.items.ItemBlock;
import fr.zunf1x.mc2d.game.level.inventory.items.Items;

import java.util.HashMap;
import java.util.Map;

public class Blocks {

    public static Block GRASS;
    public static Block LEAVES;
    public static Block STONE;
    public static Block SAND;
    public static Block GRAVEL;
    public static Block LOG;
    public static Block PLANKS;
    public static Block DOOR;
    public static Block STAIRS;
    public static Block ORE_COAL;
    public static Block ORE_IRON;
    public static Block ORE_GOLD;
    public static Block ORE_DIAMOND;
    public static Block ORE_REDSTONE;
    public static Block ORE_LAPIS;
    public static Block CRAFTING_TABLE;

    public static HashMap<Integer, Block> blocks;

    static {
        blocks = new HashMap<>();

        GRASS = registerBlock(0, new BlockGrass());
        LEAVES = registerBlock(1, new BlockLeaves());
        STONE = registerBlock(2, new BlockStone());
        SAND = registerBlock(3, new BlockSand());
        GRAVEL = registerBlock(4, new BlockGravel());
        LOG = registerBlock(5, new BlockLog());
        PLANKS = registerBlock(6, new BlockPlanks());
        DOOR = registerBlock(7, new BlockDoor());
        STAIRS = registerBlock(8, new BlockStairs());
        ORE_COAL = registerBlock(9, new BlockOreCoal());
        ORE_IRON = registerBlock(10, new BlockOreIron());
        ORE_GOLD = registerBlock(11, new BlockOreGold());
        ORE_DIAMOND = registerBlock(12, new BlockOreDiamond());
        ORE_REDSTONE = registerBlock(13, new BlockOreRedstone());
        ORE_LAPIS = registerBlock(14, new BlockOreLapis());
        CRAFTING_TABLE = registerBlock(15, new BlockCraftingTable());
    }

    private static Block registerBlock(int id, Block b) {
        blocks.put(id, b);
        Items.items.put(256 + id, new ItemBlock(b));

        return blocks.get(id);
    }

    public static Block getBlock(int id) {
        return blocks.get(id);
    }

    public static Item getItemBlock(int id) {
        return Items.items.get(256 + id);
    }

    public static Item getItemBlock(Block b) {
        int id = 0;

        for (Map.Entry<Integer, Block> entry : blocks.entrySet()) {
            if (entry.getValue() == b) {
                if (getBlock(entry.getKey()) != null) {
                    id = entry.getKey();
                }
            }
        }

        return getItemBlock(id);
    }
}
