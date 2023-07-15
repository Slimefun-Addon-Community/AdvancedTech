/* mvn clean package */
package me.pranavverma.advancedtech;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.pranavverma.advancedtech.general.BaseItems;
import me.pranavverma.advancedtech.general.items.commanders.command_engine.command_engine;
import me.pranavverma.advancedtech.general.items.commanders.command_hub.command_hub;
import me.pranavverma.advancedtech.general.items.diggers.handheld_digger_1;
import me.pranavverma.advancedtech.general.items.diggers.handheld_digger_2;
import me.pranavverma.advancedtech.general.items.diggers.handheld_digger_3;
import me.pranavverma.advancedtech.general.items.firecake.FireCake;
import me.pranavverma.advancedtech.general.items.solargen.AdvancedSolarGen.AdvancedSolarGen;
import me.pranavverma.advancedtech.general.resources.boosted_carbonado.boosted_carbonado;
import me.pranavverma.advancedtech.general.resources.framed_uranium.framed_uranium;
import me.pranavverma.advancedtech.managers.ListenerManager;
// import me.pranavverma.advancedtech.managers.SupportedPluginManager;

import javax.annotation.Nonnull;

import java.util.logging.Level;

import static io.github.thebusybiscuit.slimefun4.core.debug.Debug.log;



public class AdvancedTech extends JavaPlugin implements SlimefunAddon {

    private static AdvancedTech instance;
    private ListenerManager listenerManager;

    private boolean version_control = true;


    @Override
    public void onEnable() {
        instance = this;

        boolean shouldDisable = false;
        if (version_control) {
            if (!PaperLib.isPaper()) {
                log(String.valueOf(Level.SEVERE), "Advanced Tech only supports Paper and its forks (i.e. Airplane and Purpur)");
                log(String.valueOf(Level.SEVERE), "These Versions are NOT Supported!");
                shouldDisable = false;
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


        
        Config config_plugin = new Config(this);


        if (config_plugin.getBoolean("updating.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "PranavVerma-droid/AdvancedTech/dev").start();
        }



        ItemStack advanced_tech_define = new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I4NTg5ZTY3YTNhM2QxMmJmYzljOTgyMTBiMTAyYTM3MWQwNTIwNzk4YWU3MDBiMzYzMzVlOTlmNjkzMzc4ZCJ9fX0=")), "&4Advanced Tech", "The Most Advanced Tech in all of SF.", "&a> Click to open");
        NamespacedKey advanced_tech_id = new NamespacedKey(this, "advanced_tech");
        ItemGroup advanced_tech_category = new ItemGroup(advanced_tech_id, advanced_tech_define);


        ItemStack[] boosted_carbonado_recipe = { SlimefunItems.CARBONADO, SlimefunItems.CARBONADO, null, SlimefunItems.CARBONADO, SlimefunItems.CARBONADO, null, null, null, null };
        boosted_carbonado boosted_carbonado = new boosted_carbonado(advanced_tech_category, BaseItems.boosted_carbonado_, RecipeType.ENHANCED_CRAFTING_TABLE, boosted_carbonado_recipe);

        ItemStack[] framed_uranium_recipe = { SlimefunItems.CARBONADO, SlimefunItems.URANIUM, SlimefunItems.CARBONADO, SlimefunItems.URANIUM, SlimefunItems.BOOSTED_URANIUM, SlimefunItems.URANIUM, SlimefunItems.CARBONADO, SlimefunItems.URANIUM, SlimefunItems.CARBONADO };
        framed_uranium framed_uranium = new framed_uranium(advanced_tech_category, BaseItems.framed_uranium_, RecipeType.ENHANCED_CRAFTING_TABLE, framed_uranium_recipe);

        ItemStack[] fire_cake_recipe = { null, SlimefunItems.TINY_URANIUM, null, null, new ItemStack(Material.CAKE), null, null, new ItemStack(Material.FLINT_AND_STEEL), null };
        FireCake fire__cake = new FireCake(advanced_tech_category, BaseItems.fire_cake, RecipeType.MAGIC_WORKBENCH, fire_cake_recipe);

        boosted_carbonado.register(this);
        framed_uranium.register(this);
        fire__cake.register(this); 
        

        
        ItemStack[] command_hub_recipe = { framed_uranium.getItem(), SlimefunItems.ANDROID_MEMORY_CORE, boosted_carbonado.getItem(), SlimefunItems.LARGE_CAPACITOR, SlimefunItems.POWER_CRYSTAL, SlimefunItems.NUCLEAR_REACTOR, boosted_carbonado.getItem(), SlimefunItems.ENERGY_CONNECTOR, framed_uranium.getItem() };

        command_hub command_hub = new command_hub(advanced_tech_category, BaseItems.command_hub_, RecipeType.ENHANCED_CRAFTING_TABLE, command_hub_recipe);

        ItemStack[] command_engine_recipe = { framed_uranium.getItem(), SlimefunItems.BATTERY, framed_uranium.getItem(), SlimefunItems.ENERGY_CONNECTOR, SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRIC_MOTOR, framed_uranium.getItem(), boosted_carbonado.getItem(), framed_uranium.getItem() };
        command_engine command_engine = new command_engine(advanced_tech_category, BaseItems.command_engine_, RecipeType.ENHANCED_CRAFTING_TABLE, command_engine_recipe);


        command_engine.register(this);
        command_hub.register(this);

        


        ItemStack[] handheld_digger_1_recipe = { SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.POWER_CRYSTAL, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY };
        handheld_digger_1 basic_handheld_power_digger = new handheld_digger_1(advanced_tech_category, BaseItems.handheld_digger_1_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_1_recipe);

        ItemStack[] handheld_digger_2_recipe = { SlimefunItems.BATTERY, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BATTERY, basic_handheld_power_digger.getItem(), SlimefunItems.POWER_CRYSTAL, basic_handheld_power_digger.getItem(), SlimefunItems.BATTERY, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BATTERY };
        handheld_digger_2 advanced_handheld_power_digger = new handheld_digger_2(advanced_tech_category, BaseItems.handheld_digger_2_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_2_recipe);

        ItemStack[] handheld_digger_3_recipe = { boosted_carbonado.getItem(), framed_uranium.getItem(), boosted_carbonado.getItem(), advanced_handheld_power_digger.getItem(), SlimefunItems.POWER_CRYSTAL, advanced_handheld_power_digger.getItem(), boosted_carbonado.getItem(), framed_uranium.getItem(), boosted_carbonado.getItem() };
        handheld_digger_3 carbonado_handheld_power_digger = new handheld_digger_3(advanced_tech_category, BaseItems.handheld_digger_3_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_3_recipe);




        
        basic_handheld_power_digger.register(this);
        advanced_handheld_power_digger.register(this);
        carbonado_handheld_power_digger.register(this); 




        ItemStack[] advanced_solar_gen_recipe = {SlimefunItems.SOLAR_GENERATOR_2, SlimefunItems.SOLAR_GENERATOR_2, SlimefunItems.SOLAR_GENERATOR_2, null, boosted_carbonado.getItem(), null, null, SlimefunItems.POWER_CRYSTAL, null};
        AdvancedSolarGen advanced_solar_gen = new AdvancedSolarGen(advanced_tech_category, BaseItems.advanced_solar_gen, RecipeType.ENHANCED_CRAFTING_TABLE, advanced_solar_gen_recipe, BaseItems.advanced_solar_gen_power_day, BaseItems.advanced_solar_gen_power_night);

        advanced_solar_gen.register(this);

        
        

        NamespacedKey diggerKey = new NamespacedKey(this, "advanced_tech_diggers");
        Research diggerResearch = new Research(diggerKey, 12839710, "Advanced Tech", 65);
        diggerResearch.addItems(basic_handheld_power_digger, advanced_handheld_power_digger, carbonado_handheld_power_digger);
        diggerResearch.register();

        NamespacedKey resourcesKey = new NamespacedKey(this, "advanced_tech_resources");
        Research resourcesResearch = new Research(resourcesKey, 12839711, "Advanced Tech", 36);
        resourcesResearch.addItems(boosted_carbonado, framed_uranium);
        resourcesResearch.register();

        
        NamespacedKey genKey = new NamespacedKey(this, "advanced_tech_gens");
        Research genResearch = new Research(genKey, 12839712, "Advanced Tech", 105);
        genResearch.addItems(advanced_solar_gen);
        genResearch.register();

    }

    




    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/PranavVerma-droid/AdvancedTech/issues";
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nonnull
    public static PluginManager getPluginManager() {
        return AdvancedTech.getInstance().getServer().getPluginManager();
    }

    public static AdvancedTech getInstance() {
        return AdvancedTech.instance;
    }


    public static ListenerManager getListenerManager() {
        return AdvancedTech.getInstance().listenerManager;
    }
    


}



