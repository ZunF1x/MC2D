package fr.zunf1x.mc2d;

import fr.zunf1x.logger.Logger;
import fr.zunf1x.mc2d.console.Console;
import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.graphics.Font;
import fr.zunf1x.mc2d.gui.GuiLoadingScreen;
import fr.zunf1x.mc2d.gui.GuiMainMenu;
import fr.zunf1x.mc2d.gui.GuiManager;
import fr.zunf1x.mc2d.utils.GameState;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import javax.swing.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import static org.lwjgl.opengl.GL11.*;

public class MC2D {

    private boolean running = false;

    public final int SCALE = 2;

    public int width = 854 / SCALE;
    public int height = 480 / SCALE;

    public final Logger logger;

    public int fps;

    public static MC2D instance;

    public final Game game;
    public final GuiManager guiManager;

    private GameState gameState = GameState.INMENU;

    public Font font;

    public Console console;

    public MC2D() {
        console = new Console();

        this.logger = new Logger("MC2D");

        createDisplay();
        view2D(width, height);

        this.game = new Game(width, height, SCALE);
        this.guiManager = new GuiManager();

        this.font = new Font();
    }

    public void createDisplay() {
        try {
            Display.setTitle("MC2D");
            Display.setDisplayMode(new DisplayMode(width * SCALE, height * SCALE));
            Display.setResizable(false);
            Display.create();

            Keyboard.create();
            Mouse.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public void view2D(int width, int height) {
        glViewport(0, 0, width * SCALE, height * SCALE);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, width, height, 0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glEnable(GL_TEXTURE_2D);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void start() {
        this.logger.log("Starting MC2D...");

        this.guiManager.displayGuiScreen(new GuiMainMenu());
        this.guiManager.displayGuiScreen(new GuiLoadingScreen());

        running = true;
        loop();
    }

    public void stop() {
        this.logger.log("Stopping MC2D...");

        MC2D.instance.logger.log("Saving World...");
        MC2D.instance.game.saveWorld();

        running = false;
    }

    public void exit() {
        this.logger.log("Exiting MC2D...");

        Display.destroy();
        System.exit(0);
    }

    public void loop() {
        long before = System.nanoTime();
        long now;
        double elapsed;
        double tickTime = 1000000000.0 / 60.0;

        int frames = 0;
        int tickTimer = 0;

        while (running) {
            if (Display.isCloseRequested()) stop();

            Display.update();

            width = Display.getWidth() / SCALE;
            height = Display.getHeight() / SCALE;

            now = System.nanoTime();
            elapsed = now - before;

            if (elapsed >= tickTime) {
                update();
                tickTimer++;

                if (tickTimer % 20 == 0) {
                    fps = frames;
                    frames = 0;

                    tickTimer = 0;
                }

                before += tickTime;
            } else {
                render();
                frames++;
            }
        }

        exit();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void update() {
        console.update();

        if (getGameState() == GameState.INGAME) {
            this.game.update(width, height, SCALE);
        } else if (getGameState() == GameState.INMENU) {
            this.guiManager.update();
        }
    }

    public void render() {
        view2D(width, height);

        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(0.3941176470588235F, 0.6529411764705882F, 1F, 1F);

        if (getGameState() == GameState.INGAME)
            this.game.render();
        else if (getGameState() == GameState.INMENU)
            this.guiManager.render();
    }

    public static void main(String[] args) {
        instance = new MC2D();
        instance.start();
    }
}
