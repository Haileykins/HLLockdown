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
            cfgUtils.toggleLockdown(sender);
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(cfgUtils.colorize("&aRun /lockdown without any arguments to lock the server"));
                sender.sendMessage(cfgUtils.colorize("&aand display the default message on a join attempt."));
                sender.sendMessage(cfgUtils.colorize("&aRun /lockdown (message) to display a custom message on a join"));
                sender.sendMessage(cfgUtils.colorize("&aattempt."));
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                cfgUtils.reloadConfig(sender);
            }
        }

        List<String> incoming = Arrays.asList(args).subList(0, args.length);
        String outgoing = String.join(" ", incoming);

        cfgUtils.toggleLockdown(sender, outgoing);
        return true;
    }
}
