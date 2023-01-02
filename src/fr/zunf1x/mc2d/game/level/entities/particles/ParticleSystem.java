package fr.zunf1x.mc2d.game.level.entities.particles;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.rendering.Texture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSystem {

    public List<Particle> particles = new ArrayList<>();

    private double x, y;
    private int num;
    private Particle p;

    private int rand;

    public ParticleSystem(double x, double y, int num, Particle p, int rand) {
        this.x = x;
        this.y = y;
        this.num = num;
        this.p = p;

        this.rand = rand;
    }

    public void init(Game game) {
        Random rand = game.getWorld().getWorldProvider().getWorldSeededRandom();

        for (int i = 0; i < num; i++) {
            particles.add(new Particle(p, x + rand.nextInt(this.rand), y + rand.nextInt(this.rand)));
        }

        for (int i = 0; i < this.particles.size(); i++) {
            Particle p = particles.get(i);
            p.init(game);
        }
    }

    public void update() {
        for (int i = 0; i < this.particles.size(); i++) {
            Particle p = particles.get(i);

            if (p.removed) this.particles.remove(p);

            p.update();
        }
    }

    public void render() {
        Texture.PARTICLES.bind();
        for (int i = 0; i < this.particles.size(); i++) {
            Particle p = particles.get(i);
            p.render();
        }
        Texture.PARTICLES.unbind();
    }
}
