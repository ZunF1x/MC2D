package test;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class Test {

    public static final Test INSTANCE = new Test();

    private boolean running;

    private int scale;
    private int width, height;

    public Test() {
        scale = 2;
        width = 1280 / scale;
        height = 720 / scale;

        try {
            Display.setTitle("Jeu");
            Display.setDisplayMode(new DisplayMode(width * scale, height * scale));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        running = true;
        loop();
    }

    public void stop() {
        running = false;
    }

    public void exit() {
        Display.destroy();
        System.exit(0);
    }

    public void loop() {
        long before = System.nanoTime();
        long now;
        double elapsed;
        double tickTime = 1000000000.0 / 60.0;

        int frames = 0;
        int ticks = 0;
        int tickTimer = 0;

        while (running) {
            if (Display.isCloseRequested()) this.stop();

            Display.update();

            now = System.nanoTime();
            elapsed = now - before;

            boolean tick = false;
            if (elapsed >= tickTime) {
                this.update();
                ticks++;
                tickTimer++;

                if (tickTimer % 60 == 0) {
                    tick = true;

                    tickTimer = 0;
                }

                before += tickTime;
            } else {
                this.render();
                frames++;
            }

            if (tick) {
                System.out.println(ticks + " tps, " + frames + " fps");
                ticks = 0;
                frames = 0;
            }
        }

        this.exit();
    }

    public void update() {

    }

    float x = 0;

    public void render() {
        viewGame();

        glScalef(64, 64, 0);

        glTranslatef(-0.5F, 0, 0);

        glBegin(GL_QUADS);
        glVertex2f(x, 0);
        glVertex2f(x + 1, 0);
        glVertex2f(x + 1, 1);
        glVertex2f(x, 1);
        glEnd();
    }

    public void viewGame() {
        glEnable(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, width, height, 0);
        glEnable(GL_MODELVIEW);
    }

    public static void main(String[] args) {
        INSTANCE.start();
    }
}
