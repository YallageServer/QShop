package yallage.qshop;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import yallage.qshop.commands.Qs;
import yallage.qshop.inventories.ManagementInterface;
import yallage.qshop.inventories.ShopListInterface;

import java.io.File;

public final class QShop extends JavaPlugin {

    public YamlConfiguration languageConfig;

    public ManagementInterface managementInterface;
    public ShopListInterface shopListInterface;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // save default config
        saveDefaultConfig();
        // save defaultLanguages file
        File defaultLanguageFile = new File(getDataFolder() + "/languages/zh_CN.yml");
        if (!defaultLanguageFile.exists()) {
            saveResource("languages/zh_CN.yml", false);
        }
        // save default shop file
        File defaultShopFile = new File(getDataFolder() + "/shops/default.json");
        if (!defaultShopFile.exists()) {
            saveResource("shops/default.json", false);
        }
        // get config's language
        String language = getConfig().getString("language");
        // detect language file exists
        File languageFile = new File(getDataFolder() + "/languages/" + language + ".yml");
        if (!languageFile.exists()) {
            getLogger().warning("Language file not found, using default language file.");
            saveResource("languages/zh_CN.yml", false);
            languageFile = new File(getDataFolder() + "/languages/zh_CN.yml");
        }
        this.languageConfig = YamlConfiguration.loadConfiguration(languageFile);
        // register command
        getCommand("qs").setExecutor(new Qs(this));
        // register event
        Bukkit.getPluginManager().registerEvents(new QListener(this), this);
        // create a new ManagementInterface
        this.managementInterface = new ManagementInterface(this);
        // create a new ShopListInterface
        this.shopListInterface = new ShopListInterface(this);

        getLogger().info("Language file loaded.");
        getLogger().info("QShop has been enabled.");
    }

    @Override
    public void onLoad() {
        // Plugin load logic
        getLogger().info("§b   *******     ******** **      **   *******   ******* ");
        getLogger().info("§b  **/////**   **////// /**     /**  **/////** /**////**");
        getLogger().info("§b **     //** /**       /**     /** **     //**/**   /**");
        getLogger().info("§b/**      /** /*********/**********/**      /**/*******    §2QShop §bv" + getDescription().getVersion());
        getLogger().info("§b/**    **/** ////////**/**//////**/**      /**/**////     §2by Qianyiovo");
        getLogger().info("§b//**  // **         /**/**     /**//**     ** /**");
        getLogger().info("§b //******* ** ******** /**     /** //*******  /**");
        getLogger().info("§b  /////// // ////////  //      //   ///////   //");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getPluginManager().disablePlugin(this);
        getLogger().info("QShop has been disabled!");
    }
}
