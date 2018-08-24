package me.haileykins.hllockdown.commands;

import me.haileykins.hllockdown.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class LockdownCommand implements CommandExecutor {

    private ConfigUtils cfgUtils;

    public LockdownCommand(ConfigUtils configUtils) {
        cfgUtils = configUtils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("hllockdown.admin")) {
            sender.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.noPerms));
            return true;
        }

        if (args.length == 0) {
            cfgUtils.toggleLockdown(sender, null);
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(cfgUtils.colorize("&6/lockdown - &8Locks the server with default message"));
                sender.sendMessage(cfgUtils.colorize("&6/lockdown (message) - &8Locks the server with custom message"));
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                cfgUtils.reloadConfig(sender);
                return true;
            }

            sender.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.unknownCommand));
            return true;
        }

        List<String> incoming = Arrays.asList(args).subList(0, args.length);
        String outgoing = String.join(" ", incoming);

        cfgUtils.toggleLockdown(sender, outgoing);
        return true;
    }
}
