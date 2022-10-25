package fr.zunf1x.mc2d.rendering;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void directCube(float x, float y, Color4f color) {
        float width = 64;
        float height = 64;

        glBegin(GL_QUADS);
        glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        glVertex2f(x * 64, y * 64);
        glVertex2f(x * 64 + width, y * 64);
        glVertex2f(x * 64 + width, y * 64 + height);
        glVertex2f(x * 64, y * 64 + height);
        glColor4f(1, 1, 1, 1);
        glEnd();
    }

    public static void directTexturedCube(float x, float y, Color4f color, int texture) {
        float width = 64;
        float height = 64;

        int xo = texture % 16;
        int yo = texture / 16;

        glBegin(GL_QUADS);
        glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        glTexCoord2f(xo / 16F, yo / 16F); glVertex2f(x * 64, y * 64);
        glTexCoord2f((xo + 1) / 16F, yo / 16F); glVertex2f(x * 64 + width, y * 64);
        glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F); glVertex2f(x * 64 + width, y * 64 + height);
        glTexCoord2f(xo / 16F, (yo + 1) / 16f); glVertex2f(x * 64, y * 64 + height);
        glColor4f(1, 1, 1, 1);
        glEnd();
    }
}
