package me.haileykins.hllockdown.utils;

import me.haileykins.hllockdown.HLLockdown;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {

    private HLLockdown plugin;

    public ConfigUtils(HLLockdown pl) {
        plugin = pl;
    }

    public String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public boolean lockdown = false;
    public boolean customLockdown = false;
    public String customLDMsg;
    public String prefix;
    private String reloadMsg;
    public String unknownCommand;
    public String noPerms;
    public String lockdownMsg;
    private String lockdownConfirmMsg;
    private int lockdownNotifyType;
    private String lockdownNotifyMsg;

    public void loadConfig() {
        plugin.saveDefaultConfig();

        FileConfiguration config = plugin.getConfig();
        ConfigurationSection messages = config.getConfigurationSection("Messages");

        prefix = config.getString("Prefix");
        lockdownNotifyType = config.getInt("Lockdown-Notify-Type");

        reloadMsg = messages.getString("Reloaded-Message");
        unknownCommand = messages.getString("Unknown-Command");
        noPerms = messages.getString("No-Permission");
        lockdownMsg = messages.getString("Lockdown-Message");
        lockdownConfirmMsg = messages.getString("Lockdown-Confirm-Message");
        lockdownNotifyMsg = messages.getString("Lockdown-Notify-Message");
    }

    public void reloadConfig(CommandSender sender) {
        plugin.reloadConfig();
        plugin.getConfig();
        plugin.saveConfig();
        sender.sendMessage(colorize(prefix + " " + reloadMsg));
    }

    public void toggleLockdown(CommandSender sender, String message) {
        lockdown = !lockdown;

        customLockdown = message != null;

        customLDMsg = message;
        String status;

        if (!lockdown) {
            status = "Unlocked";
            sender.sendMessage(colorize(prefix + " " + lockdownConfirmMsg.replace("{status}", status)));
        } else {
            status = "Locked";
            sender.sendMessage(colorize(prefix + " " + lockdownConfirmMsg.replace("{status}", status)));
        }

        switch (lockdownNotifyType) {
            case 0:
                plugin.getServer().broadcastMessage(colorize(lockdownNotifyMsg
                        .replace("{sender}", sender.getName())
                        .replace("{status}", status)));
                break;
            case 1:
                plugin.getServer().broadcast(lockdownNotifyMsg
                        .replace("{sender}", sender.getName()
                                .replace("{status}", status)), "hllockdown.notify");
                break;
            default:
                break;
        }
    }
}
