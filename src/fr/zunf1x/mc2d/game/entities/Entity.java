package fr.zunf1x.mc2d.game.entities;

import fr.zunf1x.mc2d.game.level.Level;
import fr.zunf1x.mc2d.graphics.Texture;

public abstract class Entity {

    public float x, y;
    public boolean removed = false;
    public float mass;
    public float drag;

    Texture texture;

    public Level level;

    public Entity(Level level, float x, float y) {
        this.level = level;
        this.x = x * 64;
        this.y = y * 64;
        this.texture = Texture.loadTexture("/entities.png");
    }

    public boolean isSolidTile(float xa, float ya) {
        int x0 = (int) (x + xa + 3) / 64;
        int x1 = (int) (x + xa + 61) / 64;
        int y0 = (int) (y + ya + 3) / 64;
        int y1 = (int) (y + ya + 61) / 64;

        int x2 = (int) (x + xa + 3) / 64;
        int x3 = (int) (x + xa + 61) / 64;
        int y2 = (int) (y + ya + 64) / 64;
        int y3 = (int) (y + ya + 125) / 64;

        if (level.world.getSolidBlock(x0, y0) != null) return true;
        if (level.world.getSolidBlock(x1, y0) != null) return true;
        if (level.world.getSolidBlock(x1, y1) != null) return true;
        if (level.world.getSolidBlock(x0, y1) != null) return true;

        if (level.world.getSolidBlock(x2, y2) != null) return true;
        if (level.world.getSolidBlock(x3, y2) != null) return true;
        if (level.world.getSolidBlock(x3, y3) != null) return true;
        return level.world.getSolidBlock(x2, y3) != null;
    }

    public boolean isEntityItem(int xa, int ya) {
        int x0 = (int) (x + xa) / 64;
        int x1 = (int) (x + xa + 6) / 64;
        int y0 = (int) (y + ya) / 64;
        int y1 = (int) (y + ya + 61) / 64;

        int x2 = (int) (x + xa) / 64;
        int x3 = (int) (x + xa + 6) / 64;
        int y2 = (int) (y + ya + 64) / 64;
        int y3 = (int) (y + ya + 125) / 64;

        if (level.world.getEntityItem(x0, y0) != null) return true;
        if (level.world.getEntityItem(x1, y0) != null) return true;
        if (level.world.getEntityItem(x1, y1) != null) return true;
        if (level.world.getEntityItem(x0, y1) != null) return true;

        if (level.world.getEntityItem(x2, y2) != null) return true;
        if (level.world.getEntityItem(x3, y2) != null) return true;
        if (level.world.getEntityItem(x3, y3) != null) return true;
        return level.world.getEntityItem(x2, y3) != null;
    }

    public boolean isOnGround() {
        return level.world.getSolidBlock((int) (x + 29) / 64, (int) (y + 125.1) / 64) != null;
    }

    public abstract void update();

    public abstract void render();

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isRemoved() {
        return removed;
    }
}
