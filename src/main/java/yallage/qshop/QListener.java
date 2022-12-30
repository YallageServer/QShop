package yallage.qshop;

import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import yallage.qshop.prompts.ShopNamePrompt;

public class QListener implements Listener {

    public QShop plugin;

    public QListener(QShop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryItemClick(InventoryClickEvent e) {
        if (e.getClick().isLeftClick()) {
            // 判断是否点击的是空的格子（防止报错
            if (e.getCurrentItem() == null) {
                return;
            }
            // 判断是否是ManagementInterface的创建商店按钮
            if (e.getCurrentItem().equals(this.plugin.managementInterface.createShopItem)) {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                Conversation conversation = new ConversationFactory(this.plugin)
                        .withFirstPrompt(new ShopNamePrompt(this.plugin))
                        .withLocalEcho(false)
                        .buildConversation((Player) e.getWhoClicked());
                conversation.begin();
            }
            // 判断是否是ManagementInterface的商店列表按钮
            if (e.getCurrentItem().equals(this.plugin.managementInterface.shopListItem)) {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                // 刷新商店列表
                this.plugin.shopListInterface.init();
                e.getWhoClicked().openInventory(this.plugin.shopListInterface.getInventory());
            }
            // 判断是否是ManagementInterface的关于插件按钮
            if (e.getCurrentItem().equals(this.plugin.managementInterface.aboutPluginItem)) {
                e.setCancelled(true);
            }
        }
    }
}
