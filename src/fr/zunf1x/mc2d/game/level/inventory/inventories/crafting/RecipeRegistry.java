package fr.zunf1x.mc2d.game.level.inventory.inventories.crafting;

import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.inventory.ItemStack;
import fr.zunf1x.mc2d.game.level.inventory.items.Item;
import fr.zunf1x.mc2d.game.level.inventory.items.Items;

import java.util.ArrayList;
import java.util.List;

public class RecipeRegistry {

    public List<IRecipe> recipes;

    public RecipeRegistry() {
        this.recipes = new ArrayList<>();
    }

    public void addRecipes() {
        // LOG -> PLANKS
        this.registerShapelessRecipe(new Item[] {Blocks.getItemBlock(Blocks.LOG)},
                new ItemStack(Blocks.getItemBlock(Blocks.PLANKS), 4), 0);
        // PLANKS -> STICKS
        this.registerShapedRecipe(new Item[] {Blocks.getItemBlock(Blocks.PLANKS), null, null, Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Items.STICK, 4), 0);
        this.registerShapedRecipe(new Item[] {null, Blocks.getItemBlock(Blocks.PLANKS), null, null, Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Items.STICK, 4), 0);
        this.registerShapedRecipe(new Item[] {null, null, Blocks.getItemBlock(Blocks.PLANKS), null, null, Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Items.STICK, 4), 0);
        this.registerShapedRecipe(new Item[] {null, null, null, Blocks.getItemBlock(Blocks.PLANKS), null, null, Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Items.STICK, 4), 0);
        this.registerShapedRecipe(new Item[] {null, null, null, null, Blocks.getItemBlock(Blocks.PLANKS), null, null, Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Items.STICK, 4), 0);
        this.registerShapedRecipe(new Item[] {null, null, null, null, null, Blocks.getItemBlock(Blocks.PLANKS), null, null, Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Items.STICK, 4), 0);
        // PLANKS -> CRAFTING TABLE
        this.registerShapedRecipe(new Item[] {Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS), null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Blocks.getItemBlock(Blocks.CRAFTING_TABLE), 1), 0);
        this.registerShapedRecipe(new Item[] {null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS), null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Blocks.getItemBlock(Blocks.CRAFTING_TABLE), 1), 0);
        this.registerShapedRecipe(new Item[] {null, null, null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS), null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Blocks.getItemBlock(Blocks.CRAFTING_TABLE), 1), 0);
        this.registerShapedRecipe(new Item[] {null, null, null, null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS), null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Blocks.getItemBlock(Blocks.CRAFTING_TABLE), 1), 0);
        // PLANKS -> DOORS
        this.registerShapedRecipe(new Item[] {Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS), null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS), null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS), null},
                new ItemStack(Blocks.getItemBlock(Blocks.DOOR), 3), 0);
        this.registerShapedRecipe(new Item[] {null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS), null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS), null, Blocks.getItemBlock(Blocks.PLANKS), Blocks.getItemBlock(Blocks.PLANKS)},
                new ItemStack(Blocks.getItemBlock(Blocks.DOOR), 3), 0);
    }

    public void registerShapedRecipe(Item[] ingredients, ItemStack output, int xpOut) {
        this.recipes.add(new ShapedRecipe(ingredients, output, xpOut));
    }

    public void registerShapelessRecipe(Item[] ingredients, ItemStack output, int xpOut) {
        this.recipes.add(new ShapelessRecipe(ingredients, output, xpOut));
    }
}
