package fr.zunf1x.mc2d.game.level.world.biomes;

import fr.zunf1x.mc2d.game.level.blocks.*;
import fr.zunf1x.mc2d.game.level.inventory.items.Item;
import fr.zunf1x.mc2d.game.level.inventory.items.ItemBlock;
import fr.zunf1x.mc2d.game.level.inventory.items.Items;

import java.util.HashMap;
import java.util.Map;

public class RegisteredBiomes {

    public static HashMap<Integer, Biome> biomes;

    public static Biome PLAIN;

    public static Biome SNOWY_PLAIN;

    public static Biome DESERT;

    static {
        biomes = new HashMap<>();

        PLAIN = registerBiome(0, new BiomePlain());
        SNOWY_PLAIN = registerBiome(1, new BiomeSnowyPlain());
        DESERT = registerBiome(2, new BiomeDesert());
    }

    private static Biome registerBiome(int id, Biome b) {
        biomes.put(id, b);

        return biomes.get(id);
    }

    public static Biome getBiome(int id) {
        return biomes.get(id);
    }
}
