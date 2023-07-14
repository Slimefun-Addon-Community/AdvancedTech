package me.pranavverma.advancedtech.general.items.firecake;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.pranavverma.advancedtech.AdvancedTech;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import org.bukkit.inventory.ItemStack;

public class FireCake extends SlimefunItem implements Radioactive {

    public FireCake(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        BlockUseHandler blockUseHandler = this::onBlockRightClick;
        addItemHandler(blockUseHandler);
    }

    private void onBlockRightClick(PlayerRightClickEvent event) {
        event.cancel();
        event.getPlayer().sendMessage("Uh Oh! You Right Clicked the FIRE CAKE!");
        event.getPlayer().setFireTicks(5 * 20);
        
    }

    @Override
    public Radioactivity getRadioactivity() {
        return Radioactivity.HIGH;
    }
}