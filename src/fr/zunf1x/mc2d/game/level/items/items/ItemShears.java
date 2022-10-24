package fr.zunf1x.mc2d.game.level.items.items;

import fr.zunf1x.mc2d.game.level.items.properties.ItemProperties;

public class ItemShears extends Item {

    public ItemShears() {
        super("Shears", new ShearsProperties(), 4);
    }

    public static class ShearsProperties extends ItemProperties {

    }
}
