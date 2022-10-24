package fr.zunf1x.mc2d.rendering;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void directCube(float x, float y, float width, float height) {
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x + width, y + height);
        glVertex2f(x, y + height);
        glEnd();
    }
}
