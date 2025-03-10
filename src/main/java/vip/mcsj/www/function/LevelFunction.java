//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import vip.mcsj.www.main.KarIntensify;
import vip.mcsj.www.object.Level;

public class LevelFunction {
    public static Map<Enchantment, Level> maxLevels = new HashMap();

    public LevelFunction() {
    }

    public static List<Level> getLevelFromIItem(ItemStack iItem) {
        if (!isItemValid(iItem)) {
            return null;
        } else {
            List<Level> levels = new ArrayList();

            for(Enchantment enchant : KarIntensify.enchants) {
                if (iItem.containsEnchantment(enchant)) {
                    int enchantLevel = iItem.getEnchantmentLevel(enchant);
                    levels.add(new Level(enchantLevel, enchant));
                }
            }

            return levels.isEmpty() ? null : levels;
        }
    }

    public static Level getLevelFromIItem(ItemStack iItem, Enchantment enchant) {
        if (!isItemValid(iItem)) {
            return null;
        } else {
            return iItem.containsEnchantment(enchant) ? new Level(iItem.getEnchantmentLevel(enchant), enchant) : null;
        }
    }

    private static boolean isItemValid(ItemStack iItem) {
        return iItem != null && iItem.getType() != Material.AIR;
    }

    public static void initMaxLevels() {
        if (!maxLevels.isEmpty()) {
            maxLevels.clear();
        }

        KarIntensify.instance.saveDefaultConfig();
        FileConfiguration config = KarIntensify.instance.getConfig();
        ConfigurationSection cs = config.getConfigurationSection("level");

        for(String key : cs.getKeys(false)) {
            Enchantment enchant = Enchantment.getByKey(new NamespacedKey("minecraft", key));
            int level = cs.getInt(key);
            Level maxLevel = new Level(level, enchant);
            maxLevels.put(enchant, maxLevel);
        }

    }
}
