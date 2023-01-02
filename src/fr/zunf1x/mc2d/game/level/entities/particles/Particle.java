package fr.zunf1x.mc2d.game.level.entities.particles;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

import java.util.Random;

public class Particle {

    public boolean removed = false;

    public double x;
    public double y;
    private double rx, ry;
    private Random rand;

    private Color4f color;
    private float speed;
    private int lifeTime;

    private Vector2d direction;

    private int texture;

    private boolean fall;

    public Particle(Color4f color, int texture, Vector2d direction, float speed, int lifeTime, boolean fall) {
        this.color = color;
        this.texture = texture;
        this.direction = direction;
        this.speed = speed;
        this.lifeTime = lifeTime;
        this.fall = fall;
    }

    public Particle(Particle p, double x, double y) {
        this.x = x / 64F;
        this.y = y / 64F;

        this.color = p.color;
        this.texture = p.texture;
        this.direction = p.direction;
        this.speed = p.speed;
        this.lifeTime = p.lifeTime;
        this.fall = p.fall;
    }

    public Particle(Color4f color, float speed, int lifeTime, int[] randomness) {}

    public Particle(Texture texture, float speed, int lifeTime, int[] randomness) {}

    public void init(Game game) {
        this.rand = game.getWorld().getWorldProvider().getWorldSeededRandom();
        this.rx = this.rand.nextGaussian();
        this.ry = this.rand.nextGaussian();
    }

    private int time = 0;

    public void update() {
        time++;

        if (!fall) x += (rx / 64F + direction.getX() / 64F) * speed;
        y += ((ry / 64F) + (direction.getY() / 64F)) * speed;

        if (lifeTime != 0) {
            if (time >= lifeTime) this.removed = true;
        }
        else if (y >= 255) this.removed = true;
    }

    public void render() {
        Renderer.directParticle(x, y, 16 / 64F, 16 / 64F, this.color, this.texture);
    }
}
