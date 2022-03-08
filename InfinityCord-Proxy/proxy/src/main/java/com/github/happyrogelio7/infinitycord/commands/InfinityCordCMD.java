package com.github.happyrogelio7.infinitycord.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class InfinityCordCMD extends Command {


    public InfinityCordCMD() {
        super( "infinitycord");
    }

    private static String prefix = "&8[&aInfinityCord&8]&r";

    @Override
    public void execute(CommandSender sender, String[] args) {

        sender.sendMessage( ChatColor.translateAlternateColorCodes('&', InfinityCordCMD.prefix+"&c Command available in a future update.") );

    }
}
