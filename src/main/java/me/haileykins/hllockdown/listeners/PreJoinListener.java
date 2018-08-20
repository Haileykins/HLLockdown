package me.haileykins.hllockdown.listeners;

import me.haileykins.hllockdown.utils.ConfigUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PreJoinListener implements Listener {

    private ConfigUtils cfgUtils;

    public PreJoinListener(ConfigUtils configUtils) {
        cfgUtils = configUtils;
    }

    @EventHandler
    private void onPreJoin(AsyncPlayerPreLoginEvent event) {
        if (cfgUtils.lockdown) {
            if (cfgUtils.customLockdown) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, cfgUtils.customLDMsg);
                return;
            }

            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, cfgUtils.lockdownMsg);
        }
    }
}
