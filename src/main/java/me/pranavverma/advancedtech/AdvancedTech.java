package me.pranavverma.advancedtech;


import java.util.logging.Level;

import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

// Import All Custom Items
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.pranavverma.advancedtech.items.firecake.FireCake;
import me.pranavverma.advancedtech.items.diggers.handheld_digger_1;
import me.pranavverma.advancedtech.items.diggers.handheld_digger_2;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


import org.bukkit.Bukkit;


import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import static io.github.thebusybiscuit.slimefun4.core.debug.Debug.log;

public class AdvancedTech extends JavaPlugin implements SlimefunAddon {

    private boolean version_control = true;

    public static boolean TestingMode() {
        return true;
    }

    public static class items {

    }

    @Override
    public void onEnable() {

        boolean shouldDisable = false;

        if (version_control) {
            if (!PaperLib.isPaper()) {
                log(String.valueOf(Level.SEVERE), "Advanced Tech only supports Paper and its forks (i.e. Airplane and Purpur)");
                log(String.valueOf(Level.SEVERE), "Please use Paper or a fork of Paper");
                shouldDisable = true;
            }
            if (Slimefun.getMinecraftVersion().isBefore(MinecraftVersion.MINECRAFT_1_19)) {
                log(String.valueOf(Level.SEVERE), "Advanced Tech is only supported on Minecraft 1.19 and above");
                log(String.valueOf(Level.SEVERE), "There will be no support for older versions of Minecraft");
                shouldDisable = false;
            }

            if (shouldDisable) {
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
        }

        // Read something from your config.yml
        Config cfg = new Config(this);


        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }

        //Make a Category
        ItemStack advanced_tech_define = new CustomItemStack(Material.EMERALD_BLOCK, "&4Advanced Tech", "The Most Advanced Tech in all of SF.", "&a> Click to open");
            // The Category is identified by this key
            NamespacedKey advanced_tech_id = new NamespacedKey(this, "advanced_tech");
            //Defines the Category
            ItemGroup advanced_tech_category = new ItemGroup(advanced_tech_id, advanced_tech_define);

        //Defining the Items
        SlimefunItemStack handheld_digger_1_ = new SlimefunItemStack("HANDHELD_DIGGER_1", Material.NETHERITE_PICKAXE, "&4Basic Handheld Power Digger", "&cAllows you to mine a 4x4 Area.");
        SlimefunItemStack handheld_digger_2_ = new SlimefunItemStack("HANDHELD_DIGGER_2", Material.NETHERITE_PICKAXE, "&eAdvanced Handheld Power Digger", "&cAllows you to mine a 5x5 Area.");
        SlimefunItemStack fire_cake = new SlimefunItemStack("FIRE_CAKE", Material.CAKE, "&aFire Cake", "Be Careful...", LoreBuilder.radioactive(Radioactivity.LOW));

        //Recipes
        ItemStack[] handheld_digger_1_recipe = { SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.POWER_CRYSTAL, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY };
        ItemStack[] fire_cake_recipe = { null, SlimefunItems.TINY_URANIUM, null, null, new ItemStack(Material.CAKE), null, null, new ItemStack(Material.FLINT_AND_STEEL), null };

        //Registering the Items

        // 1. Basic Handheld Power Digger (HANDHELD_DIGGER_1)
        handheld_digger_1 basic_handheld_power_digger = new handheld_digger_1(advanced_tech_category, handheld_digger_1_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_1_recipe);
        basic_handheld_power_digger.register(this);

        // 2. Basic Handheld Power Digger (HANDHELD_DIGGER_1)
        FireCake fire__cake = new FireCake(advanced_tech_category, fire_cake, RecipeType.MAGIC_WORKBENCH, fire_cake_recipe);
        fire__cake.register(this);



        ItemStack[] handheld_digger_2_recipe = { SlimefunItems.BATTERY, SlimefunItems.URANIUM, SlimefunItems.BATTERY, basic_handheld_power_digger.getItem(), SlimefunItems.POWER_CRYSTAL, basic_handheld_power_digger.getItem(), SlimefunItems.BATTERY, SlimefunItems.URANIUM, SlimefunItems.BATTERY };

        handheld_digger_2 advanced_handheld_power_digger = new handheld_digger_2(advanced_tech_category, handheld_digger_2_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_2_recipe);
        advanced_handheld_power_digger.register(this);













    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/PranavVerma-droid/AdvancedTech/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

}