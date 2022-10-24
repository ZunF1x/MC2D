package fr.zunf1x.mc2d.console.inputs;

public class Input {

    private static InputHandler inputHandler;

    static {
        inputHandler = new InputHandler();
    }

    public static boolean getKey(int keyCode) {
        return inputHandler.keys[keyCode];
    }

    public static InputHandler getInputHandler() {
        return inputHandler;
    }
}
