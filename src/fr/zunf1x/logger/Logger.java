package fr.zunf1x.logger;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.console.Console;

import java.awt.*;
import java.util.Date;

public class Logger {

    private final String prefix;
    private final Date date;

    public Logger(String name) {
        this.prefix = "[" + name + "]: ";
        this.date = new Date();
    }

    public void log(String... messages) {
        for (String message : messages) {
            MC2D.instance.console.print("[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "] " + "[" + Thread.currentThread().getName() + "/" + "INFO] " + this.prefix + message, false, Color.WHITE);
        }
    }

    public void warn(String... messages) {
        for (String message : messages) {
            MC2D.instance.console.print("[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "] " + "[" + Thread.currentThread().getName() + "/" + "WARNING] " + this.prefix + message, false, Color.YELLOW);
        }
    }

    public void err(String... messages) {
        for (String message : messages) {
            MC2D.instance.console.print("[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "] " + "[" + Thread.currentThread().getName() + "/" + "ERROR] " + this.prefix + message, false, Color.RED);
        }
    }
}
