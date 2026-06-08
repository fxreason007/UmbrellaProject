package zxc.fxreason.umbrellaProject.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zxc.fxreason.umbrellaProject.UmbrellaProject;
import zxc.fxreason.umbrellaProject.items.list.medicine.Bandage;
import zxc.fxreason.umbrellaProject.items.list.food.Rot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GiveItemsCMD implements CommandExecutor, TabCompleter {

    private UmbrellaProject plugin;
    private List<String> items = Arrays.asList("bandage", "rot");

    public GiveItemsCMD(UmbrellaProject plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Эта комманда доступна только игрокам!");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("Использование: /giveitem <предмет> [количество]");
            sender.sendMessage("Допуступные предметы для выдачи: bandage");
            return true;
        }

        String itemName = args[0].toLowerCase();
        int amount = 1;

        if (args.length >= 2) {
            try {
                amount = Integer.parseInt(args[1]);
                if (amount < 1) amount = 1;
                if (amount > 64) amount = 64;
            } catch (NumberFormatException e) {
                sender.sendMessage("Количество должно быть числом");
            }
        }

        switch (itemName) {
            case "bandage":
                giveBandage(player, amount);
                player.sendMessage("Вы получили " + amount + " бинтов!");
                break;
            case "rot":
                giveRot(player, amount);
                player.sendMessage("Вы получили " + amount + " кусков гнили!");
                break;
            default:
                sender.sendMessage("Предмет не найден!");
                return true;
        }

        return true;
    }

    private void giveBandage(Player player, int amount) {
        Bandage bandage = plugin.getItemManager().getBandage();
        for (int i = 0; i < amount; i++) {
            player.getInventory().addItem(bandage.createItem());
        }
    }

    private void giveRot(Player player, int amount) {
        Rot rot = plugin.getItemManager().getRot();
        for (int i = 0; i < amount; i++) {
            player.getInventory().addItem(rot.createItem());
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return items.stream().filter(item -> item.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        } else if (args.length == 2) {
            return Arrays.asList("1", "5", "10", "16", "32", "64");
        }

        return new ArrayList<>();
    }
}
