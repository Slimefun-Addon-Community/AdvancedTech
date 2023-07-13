package me.pranavverma.advancedtech.managers;
import com.google.common.base.Preconditions;
import me.pranavverma.advancedtech.AdvancedTech;
import org.bukkit.Bukkit;

public class SupportedPluginManager {

    private static SupportedPluginManager instance;


    public SupportedPluginManager() {
        Preconditions.checkArgument(instance == null, "Cannot instantiate class");
        instance = this;
        AdvancedTech.getInstance()
            .getServer()
            .getScheduler();
    }



    public static SupportedPluginManager getInstance() {
        return instance;
    }
}
