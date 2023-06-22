package me.pranavverma.advancedtech.items.resources.command_engine;


import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.pranavverma.advancedtech.AdvancedTech;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class command_engine extends SlimefunItem implements Radioactive {

    public command_engine(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }



    @Override
    public Radioactivity getRadioactivity() {
        return Radioactivity.MODERATE;
    }


}
