package fr.zunf1x.mc2d;

import java.io.File;

public class Start {

    private static MC2D instance;

    public static MC2D getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File("libs/natives").getAbsolutePath());

        instance = new MC2D();
        instance.start();
    }
}
