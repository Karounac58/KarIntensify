package vip.mcsj.www.function;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import vip.mcsj.www.object.Level;

import java.util.ArrayList;
import java.util.List;
import static vip.mcsj.www.main.KarIntensify.enchants;

public class ItemStackFunction {
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
}
