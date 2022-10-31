package fr.zunf1x.mc2d.game.level.inventory;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;

public abstract class Inventory implements IInventory {

    public Game game;
    public EntityPlayer player;

    public Inventory(Game game) {
        this.game = game;
        this.player = game.player;
    }
}
