package me.pranavverma.advancedtech.items.resources.command_hub;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.pranavverma.advancedtech.AdvancedTech;
import me.pranavverma.advancedtech.BaseItems;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import sun.tools.jconsole.JConsole;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;

import static io.github.thebusybiscuit.slimefun4.core.debug.Debug.log;

import me.pranavverma.advancedtech.items.resources.command_engine.command_engine;


public class command_hub extends SlimefunItem implements HologramOwner {




    public command_hub(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(onBreak());
        addItemHandler(onPlace());


        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return false;
            }

            @Override
            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                if (scanForCommandEngine(block)) {
                    updateHologram(block, "&aReady to Use");
                } else {
                    updateHologram(block, "&cEngine NOT Found");
                }
            }
        });
    }



    @Nonnull
    private BlockBreakHandler onBreak() {
        return new SimpleBlockBreakHandler() {
            @Override
            public void onBlockBreak(@Nonnull Block b) {
                removeHologram(b);
            }
        };
    }


    private boolean scanForCommandEngine(@Nonnull Block block) {
        BlockFace[] faces = {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.DOWN, BlockFace.UP };

        for (BlockFace face : faces) {
            Block relativeBlock = block.getRelative(face);


            if (BlockStorage.check(relativeBlock, BaseItems.command_engine_.getItemId())) {
                return true;
            }
        }
        return false;

    }



    @Nonnull
    private BlockPlaceHandler onPlace() {

        return new BlockPlaceHandler(true) {
            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                if (scanForCommandEngine(e.getBlock())) {
                    updateHologram(e.getBlock(), "&aReady to Use");
                } else {
                    updateHologram(e.getBlock(), "&cEngine NOT Found");
                }
            }
        };
    }

}
