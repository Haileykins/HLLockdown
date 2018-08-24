package me.haileykins.hllockdown;

import me.haileykins.hllockdown.commands.LockdownCommand;
import me.haileykins.hllockdown.listeners.PreJoinListener;
import me.haileykins.hllockdown.utils.ConfigUtils;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class HLLockdown extends JavaPlugin {

    @Override
    @SuppressWarnings("unused")
    public void onEnable() {
        // Start BStats
        Metrics metrics = new Metrics(this);

        // Create Instances
        ConfigUtils cfgUtils = new ConfigUtils(this);

        // Load Config
        cfgUtils.loadConfig();

        // Register Listeners
        getServer().getPluginManager().registerEvents(new PreJoinListener(cfgUtils), this);

        // Register Commands
        getCommand("lockdown").setExecutor(new LockdownCommand(cfgUtils));

    }
}
