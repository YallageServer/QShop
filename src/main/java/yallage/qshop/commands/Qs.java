package yallage.qshop.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yallage.qshop.QListener;
import yallage.qshop.QShop;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Qs implements CommandExecutor, TabCompleter {

    public QShop plugin;

    public Qs(QShop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(this.plugin.languageConfig.getString("COMMAND_NOT_FOUND"));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("qshop.reload")) {
                this.plugin.reloadConfig();
                // get config's language
                String language = this.plugin.getConfig().getString("language");
                // detect language file exists
                File languageFile = new File(this.plugin.getDataFolder() + "/languages/" + language + ".yml");
                if (!languageFile.exists()) {
                    sender.sendMessage("Language file not found, using default language file.");
                    this.plugin.saveResource("languages/zh_CN.yml", false);
                    languageFile = new File(this.plugin.getDataFolder() + "/languages/zh_CN.yml");
                }
                this.plugin.languageConfig = YamlConfiguration.loadConfiguration(languageFile);
                // reload management interface
                this.plugin.managementInterface.init();
                // unregister all listeners
                HandlerList.unregisterAll();
                // register listeners
                this.plugin.getServer().getPluginManager().registerEvents(new QListener(this.plugin), this.plugin);

                sender.sendMessage(this.plugin.languageConfig.getString("RELOAD_SUCCESS"));
            } else {
                sender.sendMessage(this.plugin.languageConfig.getString("PERMISSION_DENIED"));
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("admin")) {
            if (sender.hasPermission("qshop.admin")) {
                ((Player) sender).openInventory(this.plugin.managementInterface.getInventory());
            } else {
                sender.sendMessage(this.plugin.languageConfig.getString("PERMISSION_DENIED"));
            }
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            if (sender.hasPermission("qshop.reload")) {
                list.add("reload");
            }
            if (sender.hasPermission("qshop.admin")) {
                list.add("admin");
            }
            return list;

        }
        return Collections.emptyList();
    }
}
