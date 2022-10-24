package fr.zunf1x.mc2d.game.crafting;

import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.items.Items;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Recipes {

    private static HashMap<ItemStack[], ItemStack> recipes = new HashMap<>();

    static {
        addRecipe(new ItemStack[] {new ItemStack(Items.getItemFromBlock(Blocks.log)), ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY}, new ItemStack(Items.getItemFromBlock(Blocks.planks), 4), true);

        addRecipe(new ItemStack[] {
                new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY, ItemStack.EMPTY,
                new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY, ItemStack.EMPTY,
                ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY},
                new ItemStack(Items.stick, 4), false);

        addRecipe(new ItemStack[] {
                        ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY,
                        ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY,
                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY},
                new ItemStack(Items.stick, 4), false);

        addRecipe(new ItemStack[] {
                        ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)),
                        ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)),
                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY},
                new ItemStack(Items.stick, 4), false);

        addRecipe(new ItemStack[] {
                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
                        new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY, ItemStack.EMPTY,
                        new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY, ItemStack.EMPTY},
                new ItemStack(Items.stick, 4), false);

        addRecipe(new ItemStack[] {
                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
                        ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY,
                        ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY},
                new ItemStack(Items.stick, 4), false);

        addRecipe(new ItemStack[] {
                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
                        ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)),
                        ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks))},
                new ItemStack(Items.stick, 4), false);

        addRecipe(new ItemStack[] {
                        new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY,
                        new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY,
                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY},
                new ItemStack(Items.getItemFromBlock(Blocks.crafting_table)), false);

        addRecipe(new ItemStack[] {
                        ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks)),
                        ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks)),
                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY},
                new ItemStack(Items.getItemFromBlock(Blocks.crafting_table)), false);

        addRecipe(new ItemStack[] {
                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
                        new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY,
                        new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks)), ItemStack.EMPTY},
                new ItemStack(Items.getItemFromBlock(Blocks.crafting_table)), false);

        addRecipe(new ItemStack[] {
                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
                        ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks)),
                        ItemStack.EMPTY, new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks))},
                new ItemStack(Items.getItemFromBlock(Blocks.crafting_table)), false);

        addRecipe(new ItemStack[] {
                        new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks)), new ItemStack(Items.getItemFromBlock(Blocks.planks)),
                        ItemStack.EMPTY, new ItemStack(Items.stick), ItemStack.EMPTY,
                        ItemStack.EMPTY, new ItemStack(Items.stick), ItemStack.EMPTY},
                new ItemStack(Items.wooden_pickaxe), false);
    }

    private static void addRecipe(ItemStack[] inputs, ItemStack output, boolean shapeless) {
        if (shapeless) {
            ItemStack[] r1 = new ItemStack[] {inputs[8], inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5], inputs[6], inputs[7]};
            ItemStack[] r2 = new ItemStack[] {inputs[7], inputs[8], inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5], inputs[6]};
            ItemStack[] r3 = new ItemStack[] {inputs[6], inputs[7], inputs[8], inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]};
            ItemStack[] r4 = new ItemStack[] {inputs[5], inputs[6], inputs[7], inputs[8], inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]};
            ItemStack[] r5 = new ItemStack[] {inputs[4], inputs[5], inputs[6], inputs[7], inputs[8], inputs[0], inputs[1], inputs[2], inputs[3]};
            ItemStack[] r6 = new ItemStack[] {inputs[3], inputs[4], inputs[5], inputs[6], inputs[7], inputs[8], inputs[0], inputs[1], inputs[2]};
            ItemStack[] r7 = new ItemStack[] {inputs[2], inputs[3], inputs[4], inputs[5], inputs[6], inputs[7], inputs[8], inputs[0], inputs[1]};
            ItemStack[] r8 = new ItemStack[] {inputs[1], inputs[2], inputs[3], inputs[4], inputs[5], inputs[6], inputs[7], inputs[8], inputs[0]};
            ItemStack[] r9 = new ItemStack[] {inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5], inputs[6], inputs[7], inputs[8]};

            recipes.put(r1, output);
            recipes.put(r2, output);
            recipes.put(r3, output);
            recipes.put(r4, output);
            recipes.put(r5, output);
            recipes.put(r6, output);
            recipes.put(r7, output);
            recipes.put(r8, output);
            recipes.put(r9, output);
        } else {
            recipes.put(inputs, output);
        }
    }

    private static boolean areKeysEqual(ItemStack[] key1, ItemStack[] key2) {
        if(key1.length != key2.length) return false;

        for(int i = 0; i < key1.length; i++) {
            ItemStack s1 = key1[i];
            ItemStack s2 = key2[i];
            if(s1.isEmpty() && !s2.isEmpty()) return false;
            if(!s1.isEmpty() && s2.isEmpty()) return false;
            if(s1.getItem() != s2.getItem()) return false;
        }
        return true;
    }

    public static ItemStack getRecipeResult(ItemStack[] ingredients) {
        for (Map.Entry<ItemStack[], ItemStack> entry : recipes.entrySet()) {
            if (areKeysEqual(entry.getKey(), ingredients)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
