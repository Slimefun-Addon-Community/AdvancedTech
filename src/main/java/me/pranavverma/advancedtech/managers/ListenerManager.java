package me.pranavverma.advancedtech.managers;
import org.bukkit.event.Listener;

import me.pranavverma.advancedtech.AdvancedTech;

public class ListenerManager {
    private void addListener(Listener listener) {
        AdvancedTech.getPluginManager().registerEvents(listener, AdvancedTech.getInstance());
    }
}
