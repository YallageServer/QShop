package yallage.qshop.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import yallage.qshop.QShop;

import java.util.List;

public class ManagementInterface implements InventoryHolder {

    private Inventory inventory;
    private QShop plugin;

    public ItemStack createShopItem;
    public ItemStack aboutPluginItem;

    public ItemStack shopListItem;

    public ManagementInterface(QShop plugin) {
        this.plugin = plugin;
        init();
    }

    public void init() {
        this.inventory = Bukkit.createInventory(this, 9, plugin.languageConfig.getString("ADMIN_TITLE"));
        this.createShopItem = createItem(
                this.plugin.languageConfig.getString("ADMIN_CREATE_SHOP_ITEM_NAME"),
                1,
                Material.CHEST,
                this.plugin.languageConfig.getStringList("ADMIN_CREATE_SHOP_ITEM_LORE")
        );
        this.inventory.setItem(0, this.createShopItem);

        this.shopListItem = createItem(
                this.plugin.languageConfig.getString("ADMIN_SHOP_LIST_ITEM_NAME"),
                1,
                Material.CHEST,
                this.plugin.languageConfig.getStringList("ADMIN_SHOP_LIST_ITEM_LORE")
        );
        this.inventory.setItem(1, this.shopListItem);

        List<String> ADMIN_ABOUT_PLUGIN_ITEM_LORE = this.plugin.languageConfig.getStringList("ADMIN_ABOUT_PLUGIN_ITEM_LORE");
        ADMIN_ABOUT_PLUGIN_ITEM_LORE.set(0, ADMIN_ABOUT_PLUGIN_ITEM_LORE.get(0).replace("%author%", "Qianyiovo"));
        ADMIN_ABOUT_PLUGIN_ITEM_LORE.set(1, ADMIN_ABOUT_PLUGIN_ITEM_LORE.get(1).replace("%version%", this.plugin.getDescription().getVersion()));
        this.aboutPluginItem = createItem(
                this.plugin.languageConfig.getString("ADMIN_ABOUT_PLUGIN_ITEM_NAME"),
                1,
                Material.BOOK,
                ADMIN_ABOUT_PLUGIN_ITEM_LORE
        );
        this.inventory.setItem(8, this.aboutPluginItem);
    }

    public ItemStack createItem(String name, int amount, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
