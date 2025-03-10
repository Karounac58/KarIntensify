//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.function;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.mcsj.www.main.KarIntensify;
import vip.mcsj.www.object.IBookType;
import vip.mcsj.www.object.IntensifyBook;

public class ItemStackFunction {
    public static final Map<Enchantment, List<Material>> intensifyItems = new HashMap();
    public static final Map<Enchantment, Map<IBookType, IntensifyBook>> intensifyBooks = new HashMap();
    public static final double[] protectEffect = new double[2];
    public static boolean showValue;

    public ItemStackFunction() {
    }

    public static void initIntensifyItems() throws Exception {
        initItemsYaml();
        YamlConfiguration itemsYaml = FileFunction.getCustomYaml("items.yml");
        if (itemsYaml == null) {
            throw new Exception("items.yml未正确初始化，请联系作者!");
        } else {
            if (!intensifyItems.isEmpty()) {
                intensifyItems.clear();
            }

            for(String key : itemsYaml.getKeys(false)) {
                List<Material> items = new ArrayList();

                for(String s : itemsYaml.getStringList(key)) {
                    items.add(Material.valueOf(s.toUpperCase()));
                }

                System.out.println(Enchantment.getByKey(new NamespacedKey("minecraft", key)));
                intensifyItems.put(Enchantment.getByKey(new NamespacedKey("minecraft", key)), items);
            }

        }
    }

    private static void initItemsYaml() {
        FileFunction.initCustomYaml("items.yml");
    }

    public static void initIntensifyBooks() {
        if (!intensifyBooks.isEmpty()) {
            intensifyBooks.clear();
        }

        KarIntensify.instance.saveDefaultConfig();
        KarIntensify.instance.reloadConfig();
        FileConfiguration config = KarIntensify.instance.getConfig();
        ConfigurationSection enchantTypes = config.getConfigurationSection("books");

        for(String key : enchantTypes.getKeys(false)) {
            Enchantment enchant = Enchantment.getByKey(new NamespacedKey("minecraft", key));
            ConfigurationSection bookTypes = enchantTypes.getConfigurationSection(key);
            Set<String> bTKeys = bookTypes.getKeys(false);
            Map<IBookType, IntensifyBook> iBs = new HashMap();

            for(String bTKey : bTKeys) {
                IBookType iBT = IBookType.valueOf(bTKey.toUpperCase());
                String name = bookTypes.getString(bTKey + ".name");
                Material material = Material.valueOf(bookTypes.getString(bTKey + ".material").toUpperCase());
                int cmd = bookTypes.getInt(bTKey + ".custom_model_data");
                List<String> lore = bookTypes.getStringList(bTKey + ".lore");
                IntensifyBook iB = new IntensifyBook(material, name, lore, cmd, bTKey, enchant);
                iBs.put(iBT, iB);
            }

            intensifyBooks.put(enchant, iBs);
        }

    }

    public static void initProtectEffectAndDebugInfo() {
        protectEffect[1] = KarIntensify.instance.getConfig().getDouble("protect.projectile_per_level");
        protectEffect[0] = KarIntensify.instance.getConfig().getDouble("protect.environmental_per_level");
        showValue = KarIntensify.instance.getConfig().getBoolean("protect.debug_info");
    }

    public static String colorStr(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static Enchantment getiBookSingleEnchant(ItemStack iItem) {
        Map<Enchantment, Integer> enchantMap = iItem.getEnchantments();
        Set<Enchantment> enchantSet = enchantMap.keySet();
        Enchantment enchant = null;

        for(Enchantment enchant1 : enchantSet) {
            enchant = enchant1;
        }

        return enchant;
    }

    public static boolean isiItemValid(Enchantment enchant, ItemStack iItem) {
        List<Material> materials = (List)intensifyItems.get(enchant);
        return materials.contains(iItem.getType());
    }

    public static double realDamage(Player p, double originDamage, double armorReduceDamage, Enchantment enchant, boolean showInfo) {
        int levels = getArmorEnchantLevels(p, enchant);
        double reduce2 = getProtectionEquipmentReduceInjury(levels, enchant) * 0.01;
        double realDamage = originDamage + armorReduceDamage;
        if (showInfo) {
            p.sendMessage("未抵消前的总伤害伤害=" + originDamage);
            p.sendMessage("盔甲减伤=" + armorReduceDamage);
            p.sendMessage("保护减伤率=" + reduce2 * (double)100.0F);
            p.sendMessage("保护减伤=" + realDamage * reduce2);
        }

        realDamage *= (double)1.0F - reduce2;
        return realDamage;
    }

    private static double getProtectionEquipmentReduceInjury(int levels, Enchantment enchantment) {
        BigDecimal db;
        if (enchantment.equals(Enchantment.PROTECTION_ENVIRONMENTAL)) {
            db = BigDecimal.valueOf(protectEffect[0]);
        } else {
            db = BigDecimal.valueOf(protectEffect[1]);
        }

        BigDecimal level = BigDecimal.valueOf((long)levels);
        return db.multiply(level).doubleValue();
    }

    public static int getArmorEnchantLevels(Player player, Enchantment enchant) {
        int levels = 0;
        ItemStack[] armorContents = player.getInventory().getArmorContents();

        for(ItemStack armor : armorContents) {
            if (armor != null && armor.getType() != Material.AIR) {
                levels += carifyEnchantmentLevel(armor, enchant);
            }
        }

        return levels;
    }

    public static int carifyEnchantmentLevel(ItemStack equipItem, Enchantment enchant) {
        ItemMeta itemMeta = equipItem.getItemMeta();
        return itemMeta.hasEnchant(enchant) ? itemMeta.getEnchantLevel(enchant) : 0;
    }
}
