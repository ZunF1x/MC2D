package fr.zunf1x.mc2d.game;

import fr.zunf1x.mc2d.game.entities.Entity;
import fr.zunf1x.mc2d.game.entities.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    public List<Entity> entities;

    public EntityManager() {
        entities = new ArrayList<>();
    }

    public void addEntity(Entity e) {
        this.entities.add(e);
    }

    public void removeEntity(Entity e) {
        this.entities.remove(e);
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (e.isRemoved()) removeEntity(e);
            e.update();
        }
    }

    public void render() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.render();
        }
    }
}
