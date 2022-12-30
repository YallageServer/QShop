package yallage.qshop.prompts;

import net.kyori.adventure.text.Component;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yallage.qshop.QShop;
import yallage.qshop.shops.Shop;
import yallage.qshop.shops.ShopManager;

public class ShopNamePrompt implements Prompt {

    private QShop plugin;

    public ShopNamePrompt(QShop plugin) {
        this.plugin = plugin;
    }
    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return this.plugin.languageConfig.getString("SHOP_NAME_PROMPT");
    }

    @Override
    public boolean blocksForInput(@NotNull ConversationContext context) {
        return true;
    }

    @Override
    public @Nullable Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
        String shopName = input;
        Player player = (Player) context.getForWhom();
        if (input == null || input.equals("") || input.equals("cancel")) {
            // 是否取消创建
            player.sendMessage(Component.text(this.plugin.languageConfig.getString("SHOP_NAME_PROMPT_CANCEL")));
            return Prompt.END_OF_CONVERSATION;
        }
        ShopManager shopManager = new ShopManager(this.plugin);
        Shop shop = shopManager.getShop(shopName);
        if (shop != null) {
            // 商店已存在
            player.sendMessage(Component.text(this.plugin.languageConfig.getString("SHOP_NAME_PROMPT_EXIST").replace("%shop_name%", shopName)));
            return Prompt.END_OF_CONVERSATION;
        }
        // 创建商店
        shopManager.createShop(shopName);
        player.sendMessage(Component.text(this.plugin.languageConfig.getString("SHOP_NAME_PROMPT_SUCCESS").replace("%shop_name%", shopName)));
        return Prompt.END_OF_CONVERSATION;
    }
}
