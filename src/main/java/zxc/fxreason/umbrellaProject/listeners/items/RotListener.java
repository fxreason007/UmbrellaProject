package zxc.fxreason.umbrellaProject.listeners.items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import zxc.fxreason.umbrellaProject.UmbrellaProject;

public class RotListener implements Listener {

    private UmbrellaProject plugin;

    public RotListener(UmbrellaProject plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemUse(PlayerItemConsumeEvent e) {
        if (e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().equals("§fКусок гнили")) {
            plugin.getItemManager().getRot().onUse(e.getPlayer());
        }
    }

}
