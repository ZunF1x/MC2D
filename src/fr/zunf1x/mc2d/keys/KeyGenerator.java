package fr.zunf1x.mc2d.keys;

import java.util.Random;
import java.util.UUID;

public class KeyGenerator {

    public static void main(String[] args) {
        Random rand = new Random();

        UUID key = new UUID(rand.nextLong(), rand.nextLong());
        System.out.println(key);
    }
}
