package fr.zunf1x.mc2d.game.level.entities;

import fr.zunf1x.mc2d.math.vectors.Vector2f;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.*;

public class EntityPlayer extends Entity {

    public EntityPlayer(Vector2f loc) {
        super(loc);
    }

    public float speed = 25F;

    @Override
    public void update() {
        if (Keyboard.isKeyDown(Keyboard.KEY_Z)) getLocation().subY(25);
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) getLocation().addY(25);
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) getLocation().subX(speed);
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) getLocation().addX(speed);

        if (Keyboard.isKeyDown(Keyboard.KEY_F)) speed = 25F;
        if (Keyboard.isKeyDown(Keyboard.KEY_G)) speed = 1000000F;
    }

    @Override
    public void render() {
        glBegin(GL_QUADS);
        glColor3f(0, 1, 0);
        glVertex2f(getLocation().getX(), getLocation().getY());
        glVertex2f(getLocation().getX() + 64, getLocation().getY());
        glVertex2f(getLocation().getX() + 64, getLocation().getY() + 128);
        glVertex2f(getLocation().getX(), getLocation().getY() + 128);
        glEnd();
    }
}
