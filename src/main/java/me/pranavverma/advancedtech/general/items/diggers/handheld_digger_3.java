package me.pranavverma.advancedtech.general.items.diggers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.pranavverma.advancedtech.general.items.diggers.lib.carbonado.ExplosiveTool7x7;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class handheld_digger_3 extends ExplosiveTool7x7 {

    @ParametersAreNonnullByDefault
    public handheld_digger_3(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }



}
