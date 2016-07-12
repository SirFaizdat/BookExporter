package xyz.thegamecube.bookexporter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author SirFaizdat
 */
final class ExportCommand implements CommandExecutor {

    private BookExporter plugin;

    ExportCommand(BookExporter plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You have to be a player to use this command.");
            return true;
        }
        Player player = (Player) sender;

        ItemStack inHand = player.getItemInHand();
        if (inHand.getType() != Material.WRITTEN_BOOK) {
            sender.sendMessage(ChatColor.RED + "You must be holding a written book to export one!");
            return true;
        }

        BookMeta meta = (BookMeta) inHand.getItemMeta();
        File file = new File(plugin.getDataFolder(), meta.getTitle() + ".book.txt");
        try {
            // We're overwriting it regardless
            if (file.exists()) file.delete();
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String page : meta.getPages()) {
                writer.write(page);
                writer.newLine();
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            sender.sendMessage(ChatColor.RED + "An internal error occurred while writing the book. Check the console for more details.");
            plugin.getLogger().log(Level.SEVERE, "An error occurred while writing the book " + meta.getTitle() + ".", e);
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "Successfully exported the book to file /plugins/BookExporter/" + file.getName());
        return true;
    }

}
