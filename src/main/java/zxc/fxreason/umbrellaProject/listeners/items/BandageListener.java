package zxc.fxreason.umbrellaProject.listeners.items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import zxc.fxreason.umbrellaProject.UmbrellaProject;
import zxc.fxreason.umbrellaProject.items.list.medicine.Bandage;

public class BandageListener implements Listener {

    private UmbrellaProject plugin;

    public BandageListener(UmbrellaProject plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent e) {
        if (e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().equals("§fБинт")) {
            plugin.getItemManager().getBandage().onUse(e.getPlayer());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerSwapItemsInHand(PlayerSwapHandItemsEvent e) {
        if (Bandage.activeUses.containsKey(e.getPlayer().getUniqueId())) {
            e.getPlayer().sendMessage("Смена рук прервала лечение!");
            Bandage.cancelHealing(e.getPlayer());
        }
    }

    

}
