package fr.zunf1x.mc2d.console.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    public boolean[] keys = new boolean[256];

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyChar()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyChar()] = false;
    }

    public void keyTyped(KeyEvent e) {}
}
