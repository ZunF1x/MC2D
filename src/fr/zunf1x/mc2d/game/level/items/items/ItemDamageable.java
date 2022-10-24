package fr.zunf1x.mc2d.game.level.items.items;

import fr.zunf1x.mc2d.game.level.items.properties.ItemProperties;

public abstract class ItemDamageable extends Item {

    public int damage;

    public ItemDamageable(String name, ItemProperties itemProperties, int textureIndex, int damage) {
        super(name, itemProperties, textureIndex);

        this.damage = damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}