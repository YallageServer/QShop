package yallage.qshop.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import yallage.qshop.QShop;
import yallage.qshop.shops.Shop;
import yallage.qshop.shops.ShopManager;

import java.util.List;

public class ShopListInterface implements InventoryHolder {

    private Inventory inventory;
    private QShop plugin;

    public ShopListInterface(QShop plugin) {
        this.plugin = plugin;
        init();
    }

    public void init() {
        this.inventory = Bukkit.createInventory(this, 9, plugin.languageConfig.getString("SHOP_LIST_TITLE"));
        ShopManager shopManager = new ShopManager(this.plugin);
        List<Shop> shops = shopManager.getAllShops();
        for (Shop shop : shops) {
            ItemStack item = createItem(
                    shop.getShopName(),
                    1,
                    Material.CHEST,
                    this.plugin.languageConfig.getStringList("SHOP_LIST_ITEM_LORE")
            );
            this.inventory.addItem(item);
        }
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