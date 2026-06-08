package zxc.fxreason.umbrellaProject.items.list.food;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import zxc.fxreason.umbrellaProject.UmbrellaProject;
import zxc.fxreason.umbrellaProject.items.Category;
import zxc.fxreason.umbrellaProject.items.Item;

public class Rot extends Item {

    private UmbrellaProject plugin;

    public Rot(UmbrellaProject plugin) {
        super("§fКусок гнили", "1002", Material.BREAD, Category.FOOD);
        this.plugin = plugin;
    }

    public void onUse(Player player) {
        if (player == null) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 10, 2));
    }

}
