package fr.zunf1x.mc2d.game.level.entities;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.BlockPlacer;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.math.vectors.Vector2d;

public abstract class Entity {

    private Vector2d location;
    protected Game game;
    protected World world;

    public Entity(Vector2d location) {
        this.location = location;
    }

    public void init(Game game) {
        this.game = game;
        this.world = this.game.getWorld();
    }

    public boolean isOnGround() {
        for (float i = 0.046875F; i < 0.953125F; i += 0.0625F) {
            BlockPlacer b = this.world.getBlock((int) (this.getLocation().getX() + i), (int) (this.getLocation().getY() + 1.9546875F));

            if (b != null && b.isCollide()) return true;
        }

        return false;
    }

    public boolean blockOnGround() {
        for (float i = 0.046875F; i < 0.953125F; i += 0.0625F) {
            BlockPlacer b = this.world.getBlock((int) (this.getLocation().getX() + i), (int) (this.getLocation().getY() + 0.9546875F));

            if (b != null && b.isCollide()) return true;
        }

        return false;
    }

    public boolean collide(float xa, float ya) {
        double x = getLocation().getX();
        double y = getLocation().getY();

        int x0 = (int) (x + xa + 0.046875F);
        int x1 = (int) (x + xa + 0.953125F);
        int y0 = (int) (y + ya + 0.046875F);
        int y1 = (int) (y + ya + 0.953125F);

        int x2 = (int) (x + xa + 0.046875F);
        int x3 = (int) (x + xa + 0.953125F);
        int y2 = (int) (y + ya + 1);
        int y3 = (int) (y + ya + 1.953125F);

        if (world.getBlock(x0, y0) != null && world.getBlock(x0, y0).isCollide()) return true;
        if (world.getBlock(x1, y0) != null && world.getBlock(x1, y0).isCollide()) return true;
        if (world.getBlock(x1, y1) != null && world.getBlock(x1, y1).isCollide()) return true;
        if (world.getBlock(x0, y1) != null && world.getBlock(x0, y1).isCollide()) return true;

        if (world.getBlock(x2, y2) != null && world.getBlock(x2, y2).isCollide()) return true;
        if (world.getBlock(x3, y2) != null && world.getBlock(x3, y2).isCollide()) return true;
        if (world.getBlock(x3, y3) != null && world.getBlock(x3, y3).isCollide()) return true;

        return world.getBlock(x2, y3) != null && world.getBlock(x2, y3).isCollide();
    }

    public boolean collideBlock(float xa, float ya) {
        double x = getLocation().getX();
        double y = getLocation().getY();

        int x0 = (int) (x + xa + 0.046875F);
        int x1 = (int) (x + xa + 0.953125F);
        int y0 = (int) (y + ya + 0.046875F);
        int y1 = (int) (y + ya + 0.953125F);

        if (world.getBlock(x0, y0) != null) return true;
        if (world.getBlock(x1, y0) != null) return true;
        if (world.getBlock(x1, y1) != null) return true;

        return world.getBlock(x0, y1) != null;
    }

    public abstract void update();

    public abstract void render();

    public Vector2d getLocation() {
        return location;
    }
}
