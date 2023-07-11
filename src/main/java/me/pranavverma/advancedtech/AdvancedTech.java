/* mvn clean package */
package me.pranavverma.advancedtech;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.pranavverma.advancedtech.items.BaseItems;
import me.pranavverma.advancedtech.items.items.diggers.handheld_digger_1;
import me.pranavverma.advancedtech.items.items.diggers.handheld_digger_2;
import me.pranavverma.advancedtech.items.items.diggers.handheld_digger_3;
import me.pranavverma.advancedtech.items.items.firecake.FireCake;
import me.pranavverma.advancedtech.items.resources.boosted_carbonado.boosted_carbonado;
import me.pranavverma.advancedtech.items.resources.commanders.command_engine.command_engine;
import me.pranavverma.advancedtech.items.resources.commanders.command_hub.command_hub;
import me.pranavverma.advancedtech.items.resources.framed_uranium.framed_uranium;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.security.CodeSource;

import org.bukkit.plugin.PluginDescriptionFile;

import java.util.logging.Level;

import static io.github.thebusybiscuit.slimefun4.core.debug.Debug.log;

public class AdvancedTech extends JavaPlugin implements SlimefunAddon {

    public boolean enable_plugin = true;
    private boolean version_control = true;
    private boolean enable_diggers = true;
    private boolean enable_firecake = true;

    public static boolean TestingMode() {
        return false;

    }



    private static final String MASTER_URL = "https://api.github.com/repos/PranavVerma-droid/AdvancedTech/releases";
    private String currentVersion;
    private PluginUpdater pluginUpdater;

    @Override
    public void onLoad() {
        // Retrieve the version from the plugin description
        PluginDescriptionFile pluginDescription = getDescription();
        currentVersion = pluginDescription.getVersion();
    }


    public class PluginUpdater {

    private final JavaPlugin plugin;
    private final String repositoryUrl;
    private final String currentVersion;

    public PluginUpdater(JavaPlugin plugin, String repositoryUrl, String currentVersion) {
        this.plugin = plugin;
        this.repositoryUrl = repositoryUrl;
        this.currentVersion = currentVersion;
    }

    public void checkForUpdates() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL(repositoryUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String response = reader.readLine();
                    reader.close();

                    JSONObject jsonObject = new JSONObject(response);
                    String latestVersion = jsonObject.getString("version");

                    if (!currentVersion.equals(latestVersion)) {
                        Bukkit.getLogger().info("A new version (" + latestVersion + ") of the plugin is available!");
                        Bukkit.getLogger().info("Please update it from: " + jsonObject.getString("download_url"));
                    } else {
                        Bukkit.getLogger().info("The plugin is up to date.");
                    }
                } else {
                    Bukkit.getLogger().warning("Failed to check for plugin updates. Response Code: " + responseCode);
                }

                connection.disconnect();
            } catch (IOException e) {
                Bukkit.getLogger().warning("An error occurred while checking for updates: " + e.getMessage());
            }
        });
    }
}




    @Override
    public void onEnable() {

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


        


        // Read something from your config.yml
        Config config_plugin = new Config(this);


        if (config_plugin.getBoolean("items.enable-diggers")) {
            enable_diggers = true;
        } else {
            enable_diggers = false;
        }

        if (config_plugin.getBoolean("items.enable-firecake")) {
            enable_firecake = true;
        } else {
            enable_firecake = false;
        }

        if (config_plugin.getBoolean("plugin.auto-update")) {
            pluginUpdater = new PluginUpdater(this, MASTER_URL, currentVersion);
            pluginUpdater.checkForUpdates();
        } else {
                
        }




        //Make a Category
        ItemStack advanced_tech_define = new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I4NTg5ZTY3YTNhM2QxMmJmYzljOTgyMTBiMTAyYTM3MWQwNTIwNzk4YWU3MDBiMzYzMzVlOTlmNjkzMzc4ZCJ9fX0=")), "&4Advanced Tech", "The Most Advanced Tech in all of SF.", "&a> Click to open");
        NamespacedKey advanced_tech_id = new NamespacedKey(this, "advanced_tech");
        ItemGroup advanced_tech_category = new ItemGroup(advanced_tech_id, advanced_tech_define);







        ItemStack[] boosted_carbonado_recipe = { SlimefunItems.CARBONADO, SlimefunItems.CARBONADO, null, SlimefunItems.CARBONADO, SlimefunItems.CARBONADO, null, null, null, null };
        boosted_carbonado boosted_carbonado = new boosted_carbonado(advanced_tech_category, BaseItems.boosted_carbonado_, RecipeType.PRESSURE_CHAMBER, boosted_carbonado_recipe);

        ItemStack[] framed_uranium_recipe = { SlimefunItems.CARBONADO, SlimefunItems.URANIUM, SlimefunItems.CARBONADO, SlimefunItems.URANIUM, SlimefunItems.BOOSTED_URANIUM, SlimefunItems.URANIUM, SlimefunItems.CARBONADO, SlimefunItems.URANIUM, SlimefunItems.CARBONADO };
        framed_uranium framed_uranium = new framed_uranium(advanced_tech_category, BaseItems.framed_uranium_, RecipeType.ENHANCED_CRAFTING_TABLE, framed_uranium_recipe);

        if (enable_plugin) {
            boosted_carbonado.register(this);
            framed_uranium.register(this);
        }

        

        ItemStack[] command_hub_recipe = { framed_uranium.getItem(), SlimefunItems.ANDROID_MEMORY_CORE, boosted_carbonado.getItem(), SlimefunItems.LARGE_CAPACITOR, SlimefunItems.POWER_CRYSTAL, SlimefunItems.NUCLEAR_REACTOR, boosted_carbonado.getItem(), SlimefunItems.ENERGY_CONNECTOR, framed_uranium.getItem() };

        command_hub command_hub = new command_hub(advanced_tech_category, BaseItems.command_hub_, RecipeType.ENHANCED_CRAFTING_TABLE, command_hub_recipe);

        ItemStack[] command_engine_recipe = { framed_uranium.getItem(), SlimefunItems.BATTERY, framed_uranium.getItem(), SlimefunItems.ENERGY_CONNECTOR, SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRIC_MOTOR, framed_uranium.getItem(), boosted_carbonado.getItem(), framed_uranium.getItem() };
        command_engine command_engine = new command_engine(advanced_tech_category, BaseItems.command_engine_, RecipeType.ENHANCED_CRAFTING_TABLE, command_engine_recipe);

        if (enable_plugin) {
            command_engine.register(this);
            command_hub.register(this);

        }



        ItemStack[] fire_cake_recipe = { null, SlimefunItems.TINY_URANIUM, null, null, new ItemStack(Material.CAKE), null, null, new ItemStack(Material.FLINT_AND_STEEL), null };
        FireCake fire__cake = new FireCake(advanced_tech_category, BaseItems.fire_cake, RecipeType.MAGIC_WORKBENCH, fire_cake_recipe);

        ItemStack[] handheld_digger_1_recipe = { SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.POWER_CRYSTAL, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY };
        handheld_digger_1 basic_handheld_power_digger = new handheld_digger_1(advanced_tech_category, BaseItems.handheld_digger_1_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_1_recipe);

        ItemStack[] handheld_digger_2_recipe = { SlimefunItems.BATTERY, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BATTERY, basic_handheld_power_digger.getItem(), SlimefunItems.POWER_CRYSTAL, basic_handheld_power_digger.getItem(), SlimefunItems.BATTERY, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BATTERY };
        handheld_digger_2 advanced_handheld_power_digger = new handheld_digger_2(advanced_tech_category, BaseItems.handheld_digger_2_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_2_recipe);

        ItemStack[] handheld_digger_3_recipe = { boosted_carbonado.getItem(), framed_uranium.getItem(), boosted_carbonado.getItem(), advanced_handheld_power_digger.getItem(), SlimefunItems.POWER_CRYSTAL, advanced_handheld_power_digger.getItem(), boosted_carbonado.getItem(), framed_uranium.getItem(), boosted_carbonado.getItem() };
        handheld_digger_3 carbonado_handheld_power_digger = new handheld_digger_3(advanced_tech_category, BaseItems.handheld_digger_3_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_3_recipe);

    if (enable_plugin) {

        if (enable_firecake) {
            fire__cake.register(this); }
        if (enable_diggers) {
            basic_handheld_power_digger.register(this);
            advanced_handheld_power_digger.register(this);
            carbonado_handheld_power_digger.register(this); }


    }


        NamespacedKey diggerKey = new NamespacedKey(this, "advanced_tech_diggers");
        Research diggerResearch = new Research(diggerKey, 12839710, "Advanced Tech", 65);
        diggerResearch.addItems(basic_handheld_power_digger, advanced_handheld_power_digger, carbonado_handheld_power_digger);
        diggerResearch.register();

        NamespacedKey resourcesKey = new NamespacedKey(this, "advanced_tech_resources");
        Research resourcesResearch = new Research(resourcesKey, 12839711, "Advanced Tech", 36);
        resourcesResearch.addItems(boosted_carbonado, framed_uranium);
        resourcesResearch.register();

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
        return this;
    }




}