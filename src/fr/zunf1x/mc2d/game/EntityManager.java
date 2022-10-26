package fr.zunf1x.mc2d.game;

import fr.zunf1x.mc2d.game.level.entities.Entity;
import fr.zunf1x.mc2d.rendering.Color4f;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    List<Entity> entities;

    private Game game;

    public EntityManager(Game game) {
        this.entities = new ArrayList<>();

        this.game = game;
    }

    public void addEntity(Entity e) {
        this.entities.add(e);
        e.init(this.game);
    }

    public void removeEntity(Entity e) {
        this.entities.remove(e);
    }

    public void removeEntity(int index) {
        this.entities.remove(index);
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            entity.update();
        }
    }

    public void render() {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            entity.render();
        }
    }
}
