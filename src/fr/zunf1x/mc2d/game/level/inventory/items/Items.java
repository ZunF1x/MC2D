package fr.zunf1x.mc2d.game.level.inventory.items;

import java.util.HashMap;
import java.util.Map;

public class Items {

    public static Map<Integer, Item> items;

    public static Item STICK;

    static {
        items = new HashMap<>();

        STICK = registerItem(0, new Item(3));
    }

    public static Item registerItem(int id, Item item) {
        items.put(id, item);

        return getItem(id);
    }

    public static Item getItem(int id) {
        return items.get(id);
    }
}
