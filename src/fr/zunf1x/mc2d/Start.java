package fr.zunf1x.mc2d;

public class Start {

    private static MC2D instance;

    public static MC2D getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        instance = new MC2D();
        instance.start();
    }
}
