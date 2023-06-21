package me.pranavverma.advancedtech.items.diggers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.pranavverma.advancedtech.items.diggers.lib.carbonado.ExplosiveTool7x7;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class handheld_digger_3 extends ExplosiveTool7x7 implements Listener {

    @ParametersAreNonnullByDefault
    public handheld_digger_3(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public int getMaxDurability() {
        return 5 * Material.NETHERITE_PICKAXE.getMaxDurability();
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {


            int currentDurability = event.getItem().getDurability();
            int maxDurability = Material.NETHERITE_PICKAXE.getMaxDurability();

            // Calculate the adjusted durability (5 times the normal durability)
            int adjustedDurability = (int) (currentDurability / 5.0 * maxDurability);

            // Set the adjusted durability
            event.getItem().setDurability((short) adjustedDurability);

    }

}
