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
    }

    private static Block registerBlock(int id, Block b) {
        blocks.put(id, b);

        return blocks.get(id);
    }

    public static Block getBlock(int id) {
        return blocks.get(id);
    }
}
