package me.pranavverma.advancedtech.interfaces;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface BlockRightClickHandler {
    void onRightClick(PlayerInteractEvent event, ItemStack item);
}
