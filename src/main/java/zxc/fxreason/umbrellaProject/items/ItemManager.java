package zxc.fxreason.umbrellaProject.items;

import zxc.fxreason.umbrellaProject.UmbrellaProject;
import zxc.fxreason.umbrellaProject.items.list.food.Rot;
import zxc.fxreason.umbrellaProject.items.list.medicine.Bandage;

public class ItemManager {

    private UmbrellaProject plugin;
    private Bandage bandage;
    private Rot rot;

    public ItemManager(UmbrellaProject plugin) {
        this.plugin = plugin;
    }

    public void initItems() {
        bandage = new Bandage(plugin);
        rot = new Rot(plugin);
    }

    public Bandage getBandage() {
        return bandage;
    }

    public Rot getRot() {
        return rot;
    }
}
