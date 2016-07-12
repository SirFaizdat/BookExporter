package xyz.thegamecube.bookexporter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author SirFaizdat
 */
final class VersionCommand implements CommandExecutor {

    private BookExporter bookExporter;

    VersionCommand(BookExporter bookExporter) {
        this.bookExporter = bookExporter;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.LIGHT_PURPLE + bookExporter.getDescription().getFullName());
        sender.sendMessage(ChatColor.DARK_PURPLE + "Crafted with <3 by SirFaizdat.");
        return true;
    }

}
