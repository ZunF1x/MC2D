package fr.zunf1x.mc2d.game;

import fr.zunf1x.mc2d.Start;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class Game {

    private int width, height;

    public Game() {

    }

    public void init() {

    }

    public void update() {
        this.width = Start.getInstance().getWidth();
        this.height = Start.getInstance().getHeight();
    }

    public void render() {
        viewGame();

        glBegin(GL_QUADS);
            glVertex2f(0, 0);
            glVertex2f(32, 0);
            glVertex2f(32, 32);
            glVertex2f(0, 32);
        glEnd();

        viewGuiAndOverlay();
    }

    public void viewGame() {
        glEnable(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, this.width, this.height, 0);
        glEnable(GL_MODELVIEW);
    }

    public void viewGuiAndOverlay() {
        glEnable(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, this.width, this.height, 0);
        glEnable(GL_MODELVIEW);
    }
}
