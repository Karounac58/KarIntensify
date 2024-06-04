package vip.mcsj.www.object;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Level {
    private int level;
    private Enchantment enchant;

    public Level(int level, Enchantment enchant) {
        this.level = level;
        this.enchant = enchant;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Enchantment getEnchant() {
        return enchant;
    }

    public void setEnchant(Enchantment enchant) {
        this.enchant = enchant;
    }
}
