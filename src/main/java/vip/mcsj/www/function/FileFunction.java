//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.function;

import java.io.File;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import vip.mcsj.www.main.KarIntensify;

public class FileFunction {
    public FileFunction() {
    }

    public static String getStringFromCustomFile(String customYmlFileName, String key) {
        File dataFile = new File(KarIntensify.instance.getDataFolder(), customYmlFileName);
        FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        return dataConfig.getString(key);
    }

    public static void setStringToDataFile(String customYmlFileName, String key, String value) throws Exception {
        File dataFile = new File(KarIntensify.instance.getDataFolder(), customYmlFileName);
        FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        dataConfig.set(key, value);
        dataConfig.save(dataFile);
    }

    public static String itemStackSerialize(ItemStack itemStack) {
        YamlConfiguration yml = new YamlConfiguration();
        yml.set("item", itemStack);
        return yml.saveToString();
    }

    public static ItemStack itemStackDeserialize(String str) {
        YamlConfiguration yml = new YamlConfiguration();

        ItemStack item;
        try {
            yml.loadFromString(str);
            item = yml.getItemStack("item");
        } catch (InvalidConfigurationException ex) {
            item = new ItemStack(Material.AIR, 1);
            ex.printStackTrace();
        }

        return item;
    }

    public static void initCustomYaml(String cFileName) {
        File dataFile = new File(KarIntensify.instance.getDataFolder(), cFileName);
        if (!dataFile.exists()) {
            try {
                FileConfiguration fC = YamlConfiguration.loadConfiguration(dataFile);
                KarIntensify.instance.saveResource(cFileName, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static YamlConfiguration getCustomYaml(String cFileName) {
        File dataFile = new File(KarIntensify.instance.getDataFolder(), cFileName);
        return !dataFile.exists() ? null : YamlConfiguration.loadConfiguration(dataFile);
    }
}
