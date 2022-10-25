package fr.zunf1x.mc2d.rendering;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void directCube(double x, double y, Color4f color) {
        float width = 1;
        float height = 1;

        glBegin(GL_QUADS);
        glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        glVertex2d(x, y);
        glVertex2d(x + width, y);
        glVertex2d(x + width, y + height);
        glVertex2d(x, y + height);
        glColor4f(1, 1, 1, 1);
        glEnd();
    }

    public static void directTexturedCube(double x, double y, Color4f color, int texture) {
        float width = 1;
        float height = 1;

        int xo = texture % 16;
        int yo = texture / 16;

        glBegin(GL_QUADS);
        glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        glTexCoord2f(xo / 16F, yo / 16F); glVertex2d(x, y);
        glTexCoord2f((xo + 1) / 16F, yo / 16F); glVertex2d(x + width, y);
        glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F); glVertex2d(x + width, y + height);
        glTexCoord2f(xo / 16F, (yo + 1) / 16f); glVertex2d(x, y + height);
        glColor4f(1, 1, 1, 1);
        glEnd();
    }
}
