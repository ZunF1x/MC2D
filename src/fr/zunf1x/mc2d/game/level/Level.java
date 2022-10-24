package fr.zunf1x.mc2d.game.level;

import fr.zunf1x.mc2d.game.EntityManager;
import fr.zunf1x.mc2d.game.level.world.World;
import org.lwjgl.opengl.Display;

public class Level {

    public World world;

    public float gravity = 1.8F;

    private final int[] bounds = new int[4];

    public int width, height;

    public int scale;

    public Level(int scale, int width, int height) {
        world = new World(width, height);

        this.width = width;
        this.height = height;

        this.scale = scale;

        bounds[0] = 0;
        bounds[1] = 0;
        bounds[2] = -width * 64 + Display.getWidth() / scale;
        bounds[3] = -height * 64 + Display.getHeight() / scale;
    }

    public void update(int scale, EntityManager entityManager) {
        this.scale = scale;

        bounds[0] = 0;
        bounds[1] = 0;
        bounds[2] = -width * 64 + Display.getWidth() / scale;
        bounds[3] = -height * 64 + Display.getHeight() / scale;

        world.update(entityManager);
    }

    public void render(EntityManager entityManager) {
        world.render(entityManager);
    }

    public int getBound(int index) {
        return bounds[index];
    }
}
