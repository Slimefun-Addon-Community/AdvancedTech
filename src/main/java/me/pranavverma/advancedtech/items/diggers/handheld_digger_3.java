package me.pranavverma.advancedtech.items.diggers;


import javax.annotation.ParametersAreNonnullByDefault;

import me.pranavverma.advancedtech.items.diggers.lib.carbonado.ExplosiveTool7x7;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;


public class handheld_digger_3 extends ExplosiveTool7x7 {

    @ParametersAreNonnullByDefault
    public handheld_digger_3(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

}
