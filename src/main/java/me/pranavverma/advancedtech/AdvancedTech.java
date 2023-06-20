package me.pranavverma.advancedtech;


import java.util.logging.Level;

import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

// Import All Custom Items
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.pranavverma.advancedtech.items.firecake.FireCake;
import me.pranavverma.advancedtech.items.diggers.handheld_digger_1;
import me.pranavverma.advancedtech.items.diggers.handheld_digger_2;
import me.pranavverma.advancedtech.items.diggers.handheld_digger_3;

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
import io.github.bakedlibs.dough.*;

import me.mrCookieSlime.Slimefun.*;

public class AdvancedTech extends JavaPlugin implements SlimefunAddon {

    private boolean version_control = true;

    public static boolean TestingMode() {
        return false;
    }

    public static class items {

    }

    @Override
    public void onEnable() {
        log(String.valueOf(Level.INFO), "Booting Advanced Tech (Addon to Slimefun) v1.0.0");

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

        log(String.valueOf(Level.INFO), "Loading Items...");

        //Make a Category
        ItemStack advanced_tech_define = new CustomItemStack(SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFiZGU4NzQyMWEwMWE4N2FhZDc5YmU0MDFlZmIxNTFkZDc4YjNjZTg0NDA2MDhhYmIyMmE3NTQ1ZWY5ZTRlOCJ9fX0="), "&4Advanced Tech", "The Most Advanced Tech in all of SF.", "&a> Click to open");
            // The Category is identified by this key
            NamespacedKey advanced_tech_id = new NamespacedKey(this, "advanced_tech");
            //Defines the Category
            ItemGroup advanced_tech_category = new ItemGroup(advanced_tech_id, advanced_tech_define);

        //Defining the Items
        SlimefunItemStack handheld_digger_1_ = new SlimefunItemStack("HANDHELD_DIGGER_1", Material.NETHERITE_PICKAXE, "&4Basic Handheld Power Digger", "&cAllows you to mine a 4 x 4 Area.");
        SlimefunItemStack handheld_digger_2_ = new SlimefunItemStack("HANDHELD_DIGGER_2", Material.NETHERITE_PICKAXE, "&eAdvanced Handheld Power Digger", "&cAllows you to mine a 5 x 5 Area.", LoreBuilder.radioactive(Radioactivity.LOW));
        SlimefunItemStack handheld_digger_3_ = new SlimefunItemStack("HANDHELD_DIGGER_3", Material.NETHERITE_PICKAXE, "&dCarbonado Handheld Power Digger", "&cAllows you to mine a 7 x 7 Area.", LoreBuilder.radioactive(Radioactivity.LOW));
        SlimefunItemStack fire_cake = new SlimefunItemStack("FIRE_CAKE", Material.CAKE, "&aFire Cake", "Be Careful...", LoreBuilder.radioactive(Radioactivity.HIGH));

        log(String.valueOf(Level.INFO), "Loading Recipes...");
        ItemStack[] handheld_digger_1_recipe = { SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.POWER_CRYSTAL, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY };



        ItemStack[] fire_cake_recipe = { null, SlimefunItems.TINY_URANIUM, null, null, new ItemStack(Material.CAKE), null, null, new ItemStack(Material.FLINT_AND_STEEL), null };

        FireCake fire__cake = new FireCake(advanced_tech_category, fire_cake, RecipeType.MAGIC_WORKBENCH, fire_cake_recipe);
        fire__cake.register(this);

        handheld_digger_1 basic_handheld_power_digger = new handheld_digger_1(advanced_tech_category, handheld_digger_1_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_1_recipe);
        basic_handheld_power_digger.register(this);



        ItemStack[] handheld_digger_2_recipe = { SlimefunItems.BATTERY, SlimefunItems.URANIUM, SlimefunItems.BATTERY, basic_handheld_power_digger.getItem(), SlimefunItems.POWER_CRYSTAL, basic_handheld_power_digger.getItem(), SlimefunItems.BATTERY, SlimefunItems.URANIUM, SlimefunItems.BATTERY };

        handheld_digger_2 advanced_handheld_power_digger = new handheld_digger_2(advanced_tech_category, handheld_digger_2_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_2_recipe);
        advanced_handheld_power_digger.register(this);

        ItemStack[] handheld_digger_3_recipe = { SlimefunItems.CARBONADO, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.CARBONADO, advanced_handheld_power_digger.getItem(), SlimefunItems.POWER_CRYSTAL, advanced_handheld_power_digger.getItem(), SlimefunItems.CARBONADO, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.CARBONADO };

        handheld_digger_3 carbonado_handheld_power_digger = new handheld_digger_3(advanced_tech_category, handheld_digger_3_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_3_recipe);
        carbonado_handheld_power_digger.register(this);

        log(String.valueOf(Level.INFO), "Defining Researches....");
        NamespacedKey diggerKey = new NamespacedKey(this, "advanced_tech_digger");
        Research diggerResearch = new Research(diggerKey, 12839710, "Welp - Unlock it to find out xD", 65);
        diggerResearch.addItems(basic_handheld_power_digger, advanced_handheld_power_digger, carbonado_handheld_power_digger);
        diggerResearch.register();

        log(String.valueOf(Level.INFO), "Boot Finished. Advanced Tech is now ready to use!");














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