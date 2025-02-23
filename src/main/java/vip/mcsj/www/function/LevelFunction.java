package vip.mcsj.www.function;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import vip.mcsj.www.main.KarIntensify;
import vip.mcsj.www.object.Level;

import java.util.*;

import static vip.mcsj.www.main.KarIntensify.enchants;

public class LevelFunction {

    public static Map<Enchantment,Level> maxLevels = new HashMap<>();

    public static List<Level> getLevelFromIItem(ItemStack iItem){
        if(!isItemValid(iItem)){
            return null;
        }
        List<Level> levels = new ArrayList<>();
        for (Enchantment enchant : enchants) {
            if(iItem.containsEnchantment(enchant)){
                int enchantLevel = iItem.getEnchantmentLevel(enchant);
                levels.add(new Level(enchantLevel,enchant));
            }
        }

        return levels.isEmpty() ? null : levels;
    }

    public static Level getLevelFromIItem(ItemStack iItem,Enchantment enchant){
        if(!isItemValid(iItem)){
            return null;
        }
        return iItem.containsEnchantment(enchant) ? new Level(iItem.getEnchantmentLevel(enchant),enchant) : null;
    }

    private static boolean isItemValid(ItemStack iItem){
        return iItem != null && iItem.getType() != Material.AIR;
    }

    public static void initMaxLevels(){
        if(!maxLevels.isEmpty()){
            maxLevels.clear();
        }
        KarIntensify.instance.saveDefaultConfig();
        FileConfiguration config = KarIntensify.instance.getConfig();
        ConfigurationSection cs = config.getConfigurationSection("level");
        Set<String> keys = cs.getKeys(false);
        for (String key : keys) {
            Enchantment enchant = Enchantment.getByKey(new NamespacedKey("minecraft", key));
            int level = cs.getInt(key);
            Level maxLevel = new Level(level, enchant);
            maxLevels.put(enchant,maxLevel);
        }
    }
}
