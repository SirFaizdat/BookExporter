package xyz.thegamecube.bookexporter;

import org.bukkit.plugin.java.JavaPlugin;

public final class BookExporter extends JavaPlugin {

    @Override
    public void onEnable() {
        if(!getDataFolder().exists()) getDataFolder().mkdir();

        getCommand("exportbook").setExecutor(new ExportCommand(this));
        getCommand("bookexporter").setExecutor(new VersionCommand(this));
        getLogger().info("Enabled " + getDescription().getFullName() + ". Crafted with <3 by SirFaizdat.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled " + getDescription().getFullName() + ". I'm sure you'll miss me!");
    }

}
