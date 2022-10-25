package fr.zunf1x.mc2d.game.level.entities;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.math.vectors.Vector2f;

public abstract class Entity {

    private Vector2f location;
    protected Game game;
    protected World world;

    public Entity(Vector2f location) {
        this.location = location;
    }

    public void init(Game game) {
        this.game = game;
        this.world = this.game.getWorld();
    }

    public boolean isOnGround() {
        for (int i = 3; i <= 61; i++) {
            if (this.world.getBlock((int) (this.getLocation().getX() + i) / 64, (int) (this.getLocation().getY() + 125.1F) / 64) != null) return true;
        }

        return false;
    }

    public boolean collide(float xa, float ya) {
        float x = getLocation().getX();
        float y = getLocation().getY();

        int x0 = (int) (x + xa + 3) / 64;
        int x1 = (int) (x + xa + 61) / 64;
        int y0 = (int) (y + ya + 3) / 64;
        int y1 = (int) (y + ya + 61) / 64;

        int x2 = (int) (x + xa + 3) / 64;
        int x3 = (int) (x + xa + 61) / 64;
        int y2 = (int) (y + ya + 64) / 64;
        int y3 = (int) (y + ya + 125) / 64;

        if (world.getBlock(x0, y0) != null) return true;
        if (world.getBlock(x1, y0) != null) return true;
        if (world.getBlock(x1, y1) != null) return true;
        if (world.getBlock(x0, y1) != null) return true;

        if (world.getBlock(x2, y2) != null) return true;
        if (world.getBlock(x3, y2) != null) return true;
        if (world.getBlock(x3, y3) != null) return true;

        return world.getBlock(x2, y3) != null;
    }

    public abstract void update();

    public abstract void render();

    public Vector2f getLocation() {
        return location;
    }
}
