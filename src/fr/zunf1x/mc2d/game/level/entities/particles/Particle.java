package fr.zunf1x.mc2d.game.level.entities.particles;

import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

import java.awt.*;
import java.util.Random;

public class Particle {

    public double x, y;
    public double rx, ry;

    public boolean removed = false;

    public Random rand;

    public Particle() {

    }

    public Particle(Particle p, World world, double x, double y) {
        this.x = x / 64f;
        this.y = y / 64f;
        this.rand = world.getWorldProvider().getWorldSeededRandom();
        this.rx = this.rand.nextGaussian() / 64f;
        this.ry = this.rand.nextGaussian() / 64F;
    }

    public Particle(Color4f color, int size, float speed, int lifeTime, int[] randomness) {}

    public Particle(Texture texture, int size, float speed, int lifeTime, int[] randomness) {}

    public void update() {
        this.y += 0.55F;

        if (y >= 255) {
            removed = true;
        }
    }

    public void render() {
        Renderer.directCube(this.x, this.y, 2 / 64F, 5 / 64F, new Color4f(50 / 255F, 70 / 255F, 255 / 255F));
    }
}
