package zxc.fxreason.umbrellaProject;

import org.bukkit.plugin.java.JavaPlugin;
import zxc.fxreason.umbrellaProject.commands.GiveItemsCMD;
import zxc.fxreason.umbrellaProject.items.list.Bandage;
import zxc.fxreason.umbrellaProject.items.list.Rot;
import zxc.fxreason.umbrellaProject.listeners.items.BandageListener;

public final class UmbrellaProject extends JavaPlugin {

    private Bandage bandage;
    private Rot rot;

    @Override
    public void onEnable() {
        bandage = new Bandage(this);
        rot = new Rot();
        getServer().getPluginManager().registerEvents(new BandageListener(this), this);

        this.getCommand("giveitem").setExecutor(new GiveItemsCMD(this));
        this.getCommand("giveitem").setTabCompleter(new GiveItemsCMD(this));
    }

    @Override
    public void onDisable() {
        System.out.println("Bye, world!");
    }

    public Bandage getBandage() {
        return bandage;
    }
}
