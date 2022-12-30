package yallage.qshop.shops;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import yallage.qshop.QShop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShopManager {
    private QShop plugin;
    private String shopPath;
    public ShopManager(QShop plugin) {
        this.plugin = plugin;
        this.shopPath = plugin.getDataFolder() + "/shops/";
    }
    public void saveShop(Shop shop, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(shop);
        File file = new File(shopPath + fileName + ".json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void createShop(String fileName) {
        Shop shop = new Shop();
        shop.setShopName(fileName);
        saveShop(shop, fileName);
    }

    public Shop getShop(String fileName) {
        File file = new File(shopPath + fileName + ".json");
        if (!file.exists())
        {
            return null;
        }
        Gson gson = new Gson();
        try {
            return gson.fromJson(new JsonReader(new FileReader(file)), Shop.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteShop(String fileName) {
        File file = new File(shopPath + fileName + ".json");
        if (!file.exists())
        {
            return;
        }
        file.delete();
    }

    public List<Shop> getAllShops() {
        File file = new File(shopPath);
        if (!file.exists())
        {
            return null;
        }
        File[] files = file.listFiles();
        Gson gson = new Gson();
        List<Shop> shops = new ArrayList<Shop>();
        for (File f : files) {
            try {
                shops.add(gson.fromJson(new JsonReader(new FileReader(f)), Shop.class));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return shops;
    }
}
