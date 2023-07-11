package me.pranavverma.advancedtech.items.resources.commanders.command_hub;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.pranavverma.advancedtech.items.BaseItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nonnull;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;


public class command_hub extends SlimefunItem implements HologramOwner {

    public command_hub(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(onBreak());
        addItemHandler(onPlace());
        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
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

    @Override
    public void preRegister() {
        BlockUseHandler blockUseHandler = this::onBlockRightClick;
        addItemHandler(blockUseHandler);
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

    private void onBlockRightClick(PlayerRightClickEvent e) {
        Block block2 = e.getClickedBlock().get();
        if (block2 != null && BlockStorage.check(block2, BaseItems.command_hub_.getItemId())) {
            if (scanForCommandEngine(block2)) {
                openGUI(e.getPlayer());
            }
        }
    }


        private boolean scanForCommandEngine (@Nonnull Block block){
            BlockFace[] faces = {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.DOWN, BlockFace.UP};

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

    private void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Command Hub GUI");

        // Create a start item with the name "start" and make it unmovable
        ItemStack startItem = new ItemStack(Material.OAK_SAPLING);
        ItemMeta startMeta = startItem.getItemMeta();
        startMeta.setDisplayName(ChatColor.GREEN + "Start");
        startMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        startItem.setItemMeta(startMeta);

        // Create a stop item with the name "stop" and make it unmovable
        ItemStack stopItem = new ItemStack(Material.OAK_SAPLING);
        ItemMeta stopMeta = stopItem.getItemMeta();
        stopMeta.setDisplayName(ChatColor.RED + "Stop");
        stopMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        stopItem.setItemMeta(stopMeta);

        // Set unmovable flag for both items
        startItem = setUnmovable(startItem);
        stopItem = setUnmovable(stopItem);

        // gui.setItem(3, startItem);
        // gui.setItem(5, stopItem);

        player.openInventory(gui);
    }

    private ItemStack setUnmovable(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);  // Set a unique custom model data
        item.setItemMeta(meta);
        return item;
    }


}
