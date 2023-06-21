package me.pranavverma.advancedtech;
import java.util.logging.Level;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import static io.github.thebusybiscuit.slimefun4.core.debug.Debug.log;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.HazmatArmorPiece;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;

// Import All Custom Items
import me.pranavverma.advancedtech.items.firecake.FireCake;
import me.pranavverma.advancedtech.items.diggers.handheld_digger_1;
import me.pranavverma.advancedtech.items.diggers.handheld_digger_2;
import me.pranavverma.advancedtech.items.diggers.handheld_digger_3;
import me.pranavverma.advancedtech.items.resources.framed_uranium.framed_uranium;
import me.pranavverma.advancedtech.items.resources.boosted_carbonado.boosted_carbonado;
import me.pranavverma.advancedtech.items.resources.command_hub.command_hub;
import me.pranavverma.advancedtech.items.resources.command_engine.command_engine;

public class AdvancedTech extends JavaPlugin implements SlimefunAddon {

    private boolean enable_plugin = true;
    private boolean version_control = true;
    private boolean enable_diggers = true;
    private boolean enable_firecake = true;


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

        if (config_plugin.getBoolean("plugin.enable-plugin")) {
            enable_plugin = true;
        } else {
            enable_plugin = false;
        }


        //Make a Category
        ItemStack advanced_tech_define = new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I4NTg5ZTY3YTNhM2QxMmJmYzljOTgyMTBiMTAyYTM3MWQwNTIwNzk4YWU3MDBiMzYzMzVlOTlmNjkzMzc4ZCJ9fX0=")), "&4Advanced Tech", "The Most Advanced Tech in all of SF.", "&a> Click to open");
        NamespacedKey advanced_tech_id = new NamespacedKey(this, "advanced_tech");
        ItemGroup advanced_tech_category = new ItemGroup(advanced_tech_id, advanced_tech_define);




        //Resources

        SlimefunItemStack command_hub_ = new SlimefunItemStack("COMMAND_HUB", PlayerHead.getItemStack(PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWM4ZjA4MzljMmRiNDlmZGI1ZmM0NTUxNmY3ZTBlMWY2MzE1MTY4OGEwMjY3ZDBkZmQxM2Y4MTYyMWU2ZDU0NCJ9fX0=")), "&eCommand Hub","" + "&r&l&cMain Central Command Hub", LoreBuilder.radioactive(Radioactivity.LOW));
        SlimefunItemStack boosted_carbonado_ = new SlimefunItemStack("BOOSTED_CARBONADO", PlayerHead.getItemStack(PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDU4ZjFhNzJlYjUyYzdkNjgxZjc5NTk5YzNiZjdlYTkxMmY4YzhhZmM4ZWU5NTRiZTMzZWFhNmJiNGNlNGZiIn19fQ==")), "&dBoosted Carbonado","" + "&r&lBlack Diamond V2.0");
        SlimefunItemStack framed_uranium_ = new SlimefunItemStack("FRAMED_URANIUM", PlayerHead.getItemStack(PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBjMGM5Nzg5ZGNmYTk5ZDZjMDRiOTE2MTc3MTEwYjNmOWEwNzdjOTY0ZTVjYTBhMGJmODZkNTdkYmU1OTNiYSJ9fX0=")), "&dFramed Uranium","" + "&r&lHypercharged Uranium", LoreBuilder.radioactive(Radioactivity.VERY_DEADLY), LoreBuilder.HAZMAT_SUIT_REQUIRED);
        SlimefunItemStack command_engine_ = new SlimefunItemStack("COMMAND_HUB_ENGINE", PlayerHead.getItemStack(PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NmMTZiYmZlYzZlMGU1YTU4YTk5YjEwYzUyYWNkMDgzZTJhYzg2ZTAwMTNlMDM3ZDZmOTA2MjQzYjYzYTI3OSJ9fX0=")), "&cCommand Hub Engine","" + "&r&lEngine For Command Hub", LoreBuilder.radioactive(Radioactivity.MODERATE), LoreBuilder.HAZMAT_SUIT_REQUIRED);

        ItemStack[] boosted_carbonado_recipe = { SlimefunItems.CARBONADO, SlimefunItems.CARBONADO, null, SlimefunItems.CARBONADO, SlimefunItems.CARBONADO, null, null, null, null };
        boosted_carbonado boosted_carbonado = new boosted_carbonado(advanced_tech_category, boosted_carbonado_, RecipeType.PRESSURE_CHAMBER, boosted_carbonado_recipe);

        ItemStack[] framed_uranium_recipe = { SlimefunItems.CARBONADO, SlimefunItems.URANIUM, SlimefunItems.CARBONADO, SlimefunItems.URANIUM, SlimefunItems.BOOSTED_URANIUM, SlimefunItems.URANIUM, SlimefunItems.CARBONADO, SlimefunItems.URANIUM, SlimefunItems.CARBONADO };
        framed_uranium framed_uranium = new framed_uranium(advanced_tech_category, framed_uranium_, RecipeType.ENHANCED_CRAFTING_TABLE, framed_uranium_recipe);

        if (enable_plugin) {
            boosted_carbonado.register(this);
            framed_uranium.register(this);
        }

        ItemStack[] command_hub_recipe = { framed_uranium.getItem(), SlimefunItems.ANDROID_MEMORY_CORE, boosted_carbonado.getItem(), SlimefunItems.LARGE_CAPACITOR, SlimefunItems.POWER_CRYSTAL, SlimefunItems.NUCLEAR_REACTOR, boosted_carbonado.getItem(), SlimefunItems.ENERGY_CONNECTOR, framed_uranium.getItem() };
        command_hub command_hub = new command_hub(advanced_tech_category, command_hub_, RecipeType.ENHANCED_CRAFTING_TABLE, command_hub_recipe);

        ItemStack[] command_engine_recipe = { framed_uranium.getItem(), SlimefunItems.BATTERY, framed_uranium.getItem(), SlimefunItems.ENERGY_CONNECTOR, SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRIC_MOTOR, framed_uranium.getItem(), boosted_carbonado.getItem(), framed_uranium.getItem() };
        command_engine command_engine = new command_engine(advanced_tech_category, command_engine_, RecipeType.ENHANCED_CRAFTING_TABLE, command_engine_recipe);

        if (enable_plugin) {
            command_hub.register(this);
            command_engine.register(this);
        }




        //Items
        SlimefunItemStack fire_cake = new SlimefunItemStack("FIRE_CAKE", Material.CAKE, "&aFire Cake", "Be Careful...", LoreBuilder.radioactive(Radioactivity.HIGH));
        SlimefunItemStack handheld_digger_1_ = new SlimefunItemStack("HANDHELD_DIGGER_1", Material.NETHERITE_PICKAXE, "&4Basic Handheld Power Digger", "&cAllows you to mine a 4 x 4 Area.");
        SlimefunItemStack handheld_digger_2_ = new SlimefunItemStack("HANDHELD_DIGGER_2", Material.NETHERITE_PICKAXE, "&eAdvanced Handheld Power Digger", "&cAllows you to mine a 5 x 5 Area.");
        SlimefunItemStack handheld_digger_3_ = new SlimefunItemStack("HANDHELD_DIGGER_3", Material.NETHERITE_PICKAXE, "&dCarbonado Handheld Power Digger", "&cAllows you to mine a 7 x 7 Area.");


        ItemStack[] fire_cake_recipe = { null, SlimefunItems.TINY_URANIUM, null, null, new ItemStack(Material.CAKE), null, null, new ItemStack(Material.FLINT_AND_STEEL), null };
        FireCake fire__cake = new FireCake(advanced_tech_category, fire_cake, RecipeType.MAGIC_WORKBENCH, fire_cake_recipe);

        ItemStack[] handheld_digger_1_recipe = { SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.POWER_CRYSTAL, SlimefunItems.EXPLOSIVE_PICKAXE, SlimefunItems.BATTERY, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.BATTERY };
        handheld_digger_1 basic_handheld_power_digger = new handheld_digger_1(advanced_tech_category, handheld_digger_1_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_1_recipe);

        ItemStack[] handheld_digger_2_recipe = { SlimefunItems.BATTERY, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BATTERY, basic_handheld_power_digger.getItem(), SlimefunItems.POWER_CRYSTAL, basic_handheld_power_digger.getItem(), SlimefunItems.BATTERY, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BATTERY };
        handheld_digger_2 advanced_handheld_power_digger = new handheld_digger_2(advanced_tech_category, handheld_digger_2_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_2_recipe);

        ItemStack[] handheld_digger_3_recipe = { boosted_carbonado.getItem(), framed_uranium.getItem(), boosted_carbonado.getItem(), advanced_handheld_power_digger.getItem(), SlimefunItems.POWER_CRYSTAL, advanced_handheld_power_digger.getItem(), boosted_carbonado.getItem(), framed_uranium.getItem(), boosted_carbonado.getItem() };
        handheld_digger_3 carbonado_handheld_power_digger = new handheld_digger_3(advanced_tech_category, handheld_digger_3_, RecipeType.ENHANCED_CRAFTING_TABLE, handheld_digger_3_recipe);

    if (enable_plugin) {

        if (enable_firecake) {
            fire__cake.register(this); }
        if (enable_diggers) {
            basic_handheld_power_digger.register(this);
            advanced_handheld_power_digger.register(this);
            carbonado_handheld_power_digger.register(this); }


    }



        NamespacedKey diggerKey = new NamespacedKey(this, "advanced_tech_digger");
        Research diggerResearch = new Research(diggerKey, 12839710, "Advanced Tech", 65);
        diggerResearch.addItems(basic_handheld_power_digger, advanced_handheld_power_digger, carbonado_handheld_power_digger);
        diggerResearch.register();

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