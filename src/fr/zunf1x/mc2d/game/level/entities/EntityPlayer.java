package fr.zunf1x.mc2d.game.level.entities;

import fr.zunf1x.mc2d.math.vectors.Vector2f;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.*;

public class EntityPlayer extends Entity {

    public EntityPlayer(Vector2f loc) {
        super(loc);
    }

    public float speed = 0.162F;
    public float xa, ya;

    @Override
    public void update() {
        ya += 1.8F * 0.62;

        if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
            ya -= speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            ya += speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            xa -= speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            xa += speed;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            if (isOnGround()) {
                ya -= 20F;
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            if (speed == 0.162F) {
                speed *= 2;
            }
        } else {
            speed = 0.162F;
        }

        int xStep = (int) Math.abs(xa * 1000);
        for (int i = 0; i < xStep; i++) {
            if (collide(xa / xStep, 0)) {
                xa = 0;
            } else {
                getLocation().addX(xa / xStep);
            }
        }

        int yStep = (int) Math.abs(ya * 1000);
        for (int i = 0; i < yStep; i++) {
            if (collide(0, ya / yStep)) {
                ya = 0;
            } else {
                getLocation().addY(ya / yStep);
            }
        }

        xa *= 0.95F;
        ya *= 0.95F;
    }

    @Override
    public void render() {
        this.drawPlayer(this.getLocation().getX(), this.getLocation().getY());
    }

    private void drawPlayer(float x, float y) {
        glBegin(GL_QUADS);
        glColor3f(1, 0, 0);
        glVertex2f(x, y);
        glVertex2f(x + 64, y);
        glVertex2f(x + 64, y + 128);
        glVertex2f(x, y + 128);
        glColor3f(1, 1, 1);
        glEnd();
    }
}
