package fr.zunf1x.mc2d;

import fr.zunf1x.mc2d.game.Game;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class MC2D {

    // ---- Variables and Constants declaration ----

    private boolean running;
    private final int scale;
    private int width;
    private int height;
    private final Game game;

    // ---- Variables and Constants affectation ----

    public MC2D() {
        this.running = false;
        this.scale = 2;
        this.width = 840 / this.scale;
        this.height = 480 / this.scale;

        this.game = new Game();

        this.createDisplay();

        this.init();
    }

    public void start() {
        this.running = true;
        this.loop();
    }

    public void stop() {
        this.running = false;
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

        while (this.isRunning()) {
            if (Display.isCloseRequested()) this.stop();

            Display.update();

            width = Display.getWidth() / scale;
            height = Display.getHeight() / scale;

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

    public void init() {
        this.game.init();
    }

    public void update() {
        this.game.update();
    }

    public void render() {
        glViewport(0, 0, this.width * scale, this.height * scale);

        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
        glClearColor(0.3941176470588235F, 0.6529411764705882F, 1F, 1F);

        this.game.render();
    }

    public void createDisplay() {
        try {
            Display.setTitle("MC2D");
            Display.setDisplayMode(new DisplayMode(this.width * this.scale, this.height * this.scale));
            Display.setFullscreen(false);
            Display.setResizable(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    // ---- Getters And Setters ----


    public boolean isRunning() {
        return running;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Game getGame() {
        return game;
    }
}
