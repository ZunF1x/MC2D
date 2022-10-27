package fr.zunf1x.mc2d.game.level.blocks;

import java.util.HashMap;

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
    }

    private static Block registerBlock(int id, Block b) {
        blocks.put(id, b);

        return blocks.get(id);
    }

    public static Block getBlock(int id) {
        return blocks.get(id);
    }
}
