package vip.mcsj.www.function;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import vip.mcsj.www.object.IntensifyBook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static vip.mcsj.www.main.KarIntensify.instance;

public class FileFunction {
//
//    public static List<IntensifyBook> getIBooksFromConfig(){
//
//    }


    public static String getStringFromCustomFile(String customYmlFileName,String key){
        File dataFile = new File(instance.getDataFolder(),customYmlFileName);
        FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        return dataConfig.getString(key);
    }

    public static void setStringToDataFile(String customYmlFileName,String key,String value) throws Exception{
        File dataFile = new File(instance.getDataFolder(),customYmlFileName);
        FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        dataConfig.set(key,value);
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
        }
        return item;
    }
}
