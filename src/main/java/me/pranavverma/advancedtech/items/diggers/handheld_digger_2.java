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


public class handheld_digger_2 extends ExplosiveTool5x5 implements Listener {

    @ParametersAreNonnullByDefault
    public handheld_digger_2(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        if (event.getItem().getType() == Material.NETHERITE_PICKAXE) {
            int currentDurability = event.getItem().getDurability();
            int maxDurability = Material.NETHERITE_PICKAXE.getMaxDurability();
            int adjustedDurability = (int) (currentDurability / 2.0 * maxDurability);
            event.getItem().setDurability((short) adjustedDurability);
        }
    }
}
