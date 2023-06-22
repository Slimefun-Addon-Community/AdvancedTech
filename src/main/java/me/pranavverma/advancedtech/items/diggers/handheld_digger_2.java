package me.pranavverma.advancedtech.items.diggers;


import javax.annotation.ParametersAreNonnullByDefault;

import me.pranavverma.advancedtech.items.diggers.lib.advanced.ExplosiveTool5x5;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;


public class handheld_digger_2 extends ExplosiveTool5x5{

    @ParametersAreNonnullByDefault
    public handheld_digger_2(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

}
