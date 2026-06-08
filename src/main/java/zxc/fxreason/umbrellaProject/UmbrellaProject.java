package zxc.fxreason.umbrellaProject;

import org.bukkit.plugin.java.JavaPlugin;
import zxc.fxreason.umbrellaProject.commands.GiveItemsCMD;
import zxc.fxreason.umbrellaProject.items.ItemManager;
import zxc.fxreason.umbrellaProject.listeners.items.BandageListener;

public final class UmbrellaProject extends JavaPlugin {

    private ItemManager itemManager = new ItemManager(this);

    @Override
    public void onEnable() {
        itemManager.initItems();

        getServer().getPluginManager().registerEvents(new BandageListener(this), this);

        this.getCommand("giveitem").setExecutor(new GiveItemsCMD(this));
        this.getCommand("giveitem").setTabCompleter(new GiveItemsCMD(this));
    }

    @Override
    public void onDisable() {
        System.out.println("Bye, world!");
    }

    public ItemManager getItemManager() {
        return itemManager;
    }
}
