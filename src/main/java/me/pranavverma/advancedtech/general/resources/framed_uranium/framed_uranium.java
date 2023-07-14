package me.pranavverma.advancedtech.general.resources.framed_uranium;


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


public class framed_uranium extends SlimefunItem implements Radioactive {

    public framed_uranium(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public Radioactivity getRadioactivity() {
        return Radioactivity.VERY_DEADLY;
    }
}
