package fr.zunf1x.mc2d.game.level.blocks.properties;

public abstract class BlockProperties {

    public static final BlockProperties DEFAULT_PROPERTIES = new BlockProperties(false, false, 1.0F) {
        @Override
        public void update() {}
    };

    public boolean unbreakable, gravity;
    public float hardness;

    public BlockProperties(boolean unbreakable, boolean gravity, float hardness) {
        this.unbreakable = unbreakable;
        this.gravity = gravity;
        this.hardness = hardness;
    }

    public float getHardness() {
        return hardness;
    }

    public abstract void update();
}
