package fr.zunf1x.mc2d.game.level.entities.particles;

import fr.zunf1x.mc2d.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSystem {

    public List<Particle> particles = new ArrayList<>();

    private double x, y;
    private int num;
    private Particle p;

    public ParticleSystem(double x, double y, int num, Particle p) {
        this.x = x;
        this.y = y;
        this.num = num;
        this.p = p;
    }

    public void init(Game game) {
        for (int i = 0; i < this.particles.size(); i++) {
            Particle p = particles.get(i);
            p.init(game);
        }

        Random rand = game.getWorld().getWorldProvider().getWorldSeededRandom();

        for (int i = 0; i < num; i++) {
            particles.add(new Particle(p, x + rand.nextInt(56), y + rand.nextInt(56)));
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
        for (int i = 0; i < this.particles.size(); i++) {
            Particle p = particles.get(i);
            p.render();
        }
    }
}
