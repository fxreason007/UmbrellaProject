package zxc.fxreason.umbrellaProject.items.list.medicine;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.scheduler.BukkitRunnable;
import zxc.fxreason.umbrellaProject.UmbrellaProject;
import zxc.fxreason.umbrellaProject.items.Category;
import zxc.fxreason.umbrellaProject.items.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Bandage extends Item {

    public static final Map<UUID, BukkitRunnable> activeUses = new HashMap<>();
    private UmbrellaProject plugin;

    public Bandage(UmbrellaProject plugin) {
        super("§fБинт", "1001", Material.PAPER, Category.MEDICINE);
        this.plugin = plugin;
    }

    public void onUse(Player player) {
        UUID playerID = player.getUniqueId();

        if (activeUses.containsKey(playerID)) {
            player.sendMessage("Вы уже используете бинт!");
            return;
        }

        player.sendMessage("Перевязка началась! Не двигайтесь в течение 5 секунд");

        BukkitRunnable task = new BukkitRunnable() {
            int tick = 0;

            @Override
            public void run() {
                if (!player.isOnline() || player.isDead() || !isHoldingBandage(player)) {
                    cancelHealing(player);
                    return;
                }

                tick++;

                if (tick % 20 == 0) {
                    player.sendActionBar("Перевязка... " + (5 - tick / 20) + " сек. осталось!");
                }

                if (tick >= 100) {
                    completeHeal(player);
                }
            }
        };

        task.runTaskTimer(plugin, 0, 1L);
        activeUses.put(playerID, task);
    }

    private boolean isHoldingBandage(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != Material.PAPER) return false;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        CustomModelDataComponent cmd = meta.getCustomModelDataComponent();
        if (cmd == null) return false;

        List<String> strings = cmd.getStrings();
        return strings != null && strings.contains("1001");
    }

    public static void cancelHealing(Player player) {
        UUID playerID = player.getUniqueId();
        BukkitRunnable task = activeUses.remove(playerID);

        if (task != null && !task.isCancelled()) {
            task.cancel();
            if (player.isOnline()) {
                player.sendMessage("§cЛечение было прервано, вы убрали из руки бинт!");
            }
        }
    }

    private void completeHeal(Player player) {
        UUID playerID = player.getUniqueId();
        BukkitRunnable task = activeUses.remove(playerID);

        if (task != null && !task.isCancelled()) {
            task.cancel();

            if (player.isOnline() && !player.isDead()) {
                if (isHoldingBandage(player)) {
                    player.heal(2);
                    ItemStack item = player.getInventory().getItemInMainHand();
                    item.setAmount(item.getAmount() - 1);
                    player.sendMessage("§aВы успешно восстановили 2 хп.");
                } else {
                    player.sendMessage("§cЛечение не завершено, вы должны удерживать бинт в руке до конца!");
                }
            }
        }
    }
}