package fr.zunf1x.mc2d.game.level.entities.particles;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.entities.Entity;
import fr.zunf1x.mc2d.math.vectors.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSystem extends Entity {

    public List<Particle> particles = new ArrayList<>();

    private int num;
    private Particle p;
    public ParticleSystem(double x, double y, int num, Particle p) {
        super(new Vector2d(x, y));
        this.num = num;
        this.p = p;
    }

    @Override
    public void init(Game game) {
        super.init(game);

        Random rand = game.getWorld().getWorldProvider().getWorldSeededRandom();

        for (int i = 0; i < num; i++) {
            this.particles.add(new Particle(this.p, game.getWorld(), this.getLocation().getX() + rand.nextInt(64), this.getLocation().getY() + rand.nextInt(64)));
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            p.update();

            if (p.removed) this.particles.remove(p);
        }
    }

    @Override
    public void render() {
        for (int i = 0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            p.render();
        }
    }
}
