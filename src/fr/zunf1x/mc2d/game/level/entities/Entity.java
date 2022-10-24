package fr.zunf1x.mc2d.game.level.entities;

import fr.zunf1x.mc2d.math.vectors.Vector2f;

public abstract class Entity {

    private Vector2f location;

    public Entity(Vector2f location) {
        this.location = location;
    }

    public abstract void update();

    public abstract void render();

    public Vector2f getLocation() {
        return location;
    }
}
