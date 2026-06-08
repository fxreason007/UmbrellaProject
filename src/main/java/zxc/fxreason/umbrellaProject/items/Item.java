package zxc.fxreason.umbrellaProject.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;

import java.util.List;

public class Item {

    private String name, id;
    private Material material;
    private Category category;

    public Item(String name, String id, Material material, Category category) {
        this.name = name;
        this.id = id;
        this.material = material;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        CustomModelDataComponent customModelData = meta.getCustomModelDataComponent();
        customModelData.setStrings(List.of(id));
        meta.setCustomModelDataComponent(customModelData);
        item.setItemMeta(meta);
        return item;
    }
}
