package fr.zunf1x.mc2d.game.level.items;

import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.blocks.blocks.*;
import fr.zunf1x.mc2d.game.level.items.items.*;
import fr.zunf1x.mc2d.game.level.items.properties.ItemProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Items {

    public static Map<Integer, Item> items = new HashMap<>();
    public static Map<Block, Item> itemBlocks = new HashMap<>();

    public static Item iron_ingot, gold_ingot, diamond, stick;

    public static Item stone, dirt, grass, bedrock, log, planks, crafting_table, coal_ore, iron_ore, gold_ore, diamond_ore, redstone_ore, lapis_ore, leaves, shears, cobblestone;
    public static Item wooden_sword, wooden_pickaxe, wooden_axe, wooden_shovel, wooden_hoe;

    public static void init() {
        iron_ingot = registerItem(0, new Item("Iron Ingot", ItemProperties.DEFAULT_PROPERTIES, 0));
        gold_ingot = registerItem(1, new Item("Gold Ingot", ItemProperties.DEFAULT_PROPERTIES, 1));
        diamond = registerItem(2, new Item("Diamond", ItemProperties.DEFAULT_PROPERTIES, 2));

        stone = registerItemBlocks(3, Blocks.stone);
        dirt = registerItemBlocks(4, Blocks.dirt);
        grass = registerItemBlocks(5, Blocks.grass);
        bedrock = registerItemBlocks(6, Blocks.bedrock);

        stick = registerItem(7, new Item("Stick", ItemProperties.DEFAULT_PROPERTIES, 3));

        log = registerItemBlocks(8, Blocks.log);
        planks = registerItemBlocks(9, Blocks.planks);

        crafting_table = registerItemBlocks(10, Blocks.crafting_table);
        coal_ore = registerItemBlocks(11, Blocks.coal_ore);
        iron_ore = registerItemBlocks(12, Blocks.iron_ore);
        gold_ore = registerItemBlocks(13, Blocks.gold_ore);
        diamond_ore = registerItemBlocks(14, Blocks.diamond_ore);
        redstone_ore = registerItemBlocks(15, Blocks.redstone_ore);
        lapis_ore = registerItemBlocks(16, Blocks.lapis_ore);
        leaves = registerItemBlocks(17, Blocks.leaves);

        shears = registerItem(18, new ItemShears());

        cobblestone = registerItemBlocks(19, Blocks.cobblestone);

        wooden_sword = registerItem(20, new Item("Wooden Sword", ItemProperties.DEFAULT_PROPERTIES, 5));
        wooden_pickaxe = registerItem(21, new ItemPickaxe("Wooden Pickaxe", ItemProperties.DEFAULT_PROPERTIES, 6, ToolMaterial.WOOD, 5));
        wooden_axe = registerItem(22, new ItemAxe("Wooden Axe", ItemProperties.DEFAULT_PROPERTIES, 7, ToolMaterial.WOOD, 6));
        wooden_shovel = registerItem(23, new ItemShovel("Wooden Shovel", ItemProperties.DEFAULT_PROPERTIES, 8, ToolMaterial.WOOD, 7));
        wooden_hoe = registerItem(24, new Item("Wooden Hoe", ItemProperties.DEFAULT_PROPERTIES, 9));
    }

    private static Item registerItem(int id, Item item) {
        items.put(id, item);

        return items.get(id);
    }

    private static Item registerItemBlocks(int id, Block block) {
        ItemBlock ib = new ItemBlock(block);

        items.put(id, ib);

        itemBlocks.put(block, ib);

        return items.get(id);
    }

    public static Item getItem(int id) {
        return items.get(id);
    }

    public static int getKeyByValue(Item value) {
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }

        return 4096;
    }

    public static Item getItemFromBlock(Block block) {
        return itemBlocks.get(block);
    }
}
