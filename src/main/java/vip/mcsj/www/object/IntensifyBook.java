//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.object;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.mcsj.www.function.FileFunction;
import vip.mcsj.www.function.ItemStackFunction;
import vip.mcsj.www.function.LevelFunction;
import vip.mcsj.www.function.MessageFunction;
import vip.mcsj.www.main.KarIntensify;
import vip.mcsj.www.prediction.PlayerRandomCache;

public class IntensifyBook implements Serializable {
    static final long serialVersionUID = 1L;
    private Material material;
    private String name;
    private List<String> lores;
    private int customModelData;
    private ItemStack intensifyBook;
    private int success;
    private int destroy;

    public IntensifyBook(Material material, String name, List<String> lores, int customModelData, String type, Enchantment enchant) {
        this.material = material;
        this.name = name;
        this.lores = lores;
        this.customModelData = customModelData;
    }

    public IntensifyBook(Material material, String name, List<String> lores, int customModelData, ItemStack item, int success, int destroy) {
        this.material = material;
        this.name = name;
        this.lores = lores;
        this.customModelData = customModelData;
        this.intensifyBook = item;
        this.success = success;
        this.destroy = destroy;
    }

    public IntensifyBook myClone() {
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        IntensifyBook iBook = null;

        try {
            ObjectOutputStream oos = new ObjectOutputStream(bais);
            oos.writeObject(this);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bais.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            iBook = (IntensifyBook)ois.readObject();
            return iBook;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSuccess() {
        return this.success;
    }

    public int getDestroy() {
        return this.destroy;
    }

    public void generateNormalIntensifyBook(String type, Enchantment enchant, UUID uuid) {
        ItemStack iBook = new ItemStack(this.material);
        ItemMeta iBookIM = iBook.getItemMeta();
        iBookIM.setDisplayName(this.name);
        int successLineIndex = -1;
        int brokenLineIndex = -1;
        Random rand = new Random();

        for(int i = 0; i < this.lores.size(); ++i) {
            String lore = (String)this.lores.get(i);
            if (lore.contains("{success}")) {
                successLineIndex = i;
            } else if (lore.contains("{breakage}")) {
                brokenLineIndex = i;
            }
        }

        String successLine = (String)this.lores.get(successLineIndex);
        String brokenLine = (String)this.lores.get(brokenLineIndex);
        int success = rand.nextInt(100);
        int destroy = rand.nextInt(100);
        switch (type) {
            case "normal":
                this.lores.set(successLineIndex, successLine.replace("{success}", String.valueOf((PlayerRandomCache.cache.get(uuid)).getActualValue())));
                this.lores.set(brokenLineIndex, brokenLine.replace("{breakage}", String.valueOf(destroy)));
                this.success = success;
                this.destroy = destroy;
                break;
            case "ninety_nine":
                this.lores.set(successLineIndex, successLine.replace("{success}", String.valueOf(99)));
                this.lores.set(brokenLineIndex, brokenLine.replace("{breakage}", String.valueOf(destroy)));
                this.success = 99;
                this.destroy = destroy;
                break;
            case "zero":
                this.lores.set(successLineIndex, successLine.replace("{success}", String.valueOf(success)));
                this.lores.set(brokenLineIndex, brokenLine.replace("{breakage}", String.valueOf(0)));
                this.success = success;
                this.destroy = 0;
        }

        iBookIM.setLore(this.lores);
        iBookIM.setCustomModelData(this.customModelData);
        iBookIM.addEnchant(enchant, 1, true);
        iBook.setItemMeta(iBookIM);
        NBT.modify(iBook, (i) -> {
            i.setInteger("success", success);
            i.setInteger("destroy", destroy);
            i.setInteger("HideFlags", 4);
        });
        this.intensifyBook = iBook;
    }

    public ItemStack getItemStack() {
        return this.intensifyBook;
    }

    public static IntensifyBook fromItemStack(ItemStack iBItem) {
        NBTItem iBNBTItem = new NBTItem(iBItem);
        if (!iBNBTItem.hasTag("success")) {
            return null;
        } else {
            Map<Enchantment, Integer> enchantMap = iBItem.getEnchantments();

            for(Enchantment enchant1 : enchantMap.keySet()) {
                ;
            }

            int success = iBNBTItem.getInteger("success");
            int destroy = iBNBTItem.getInteger("destroy");
            IntensifyBook iB = new IntensifyBook(iBItem.getType(), iBItem.getItemMeta().getDisplayName(), iBItem.getItemMeta().getLore(), iBItem.getItemMeta().getCustomModelData(), iBItem, success, destroy);
            return iB;
        }
    }

    public void intensifyUpItem(Player p, ItemStack iItem) {
        Enchantment enchant = ItemStackFunction.getiBookSingleEnchant(this.intensifyBook);
        if (ItemStackFunction.isiItemValid(enchant, iItem)) {
            Random rand = new Random();
            if (this.success > rand.nextInt(100)) {
                Level level = (Level)LevelFunction.maxLevels.get(enchant);
                int maxLevel = level.getLevel();
                int enchantmentLevel = iItem.getEnchantmentLevel(enchant);
                if (maxLevel == enchantmentLevel) {
                    p.sendMessage((String)MessageFunction.messages.get("max_level"));
                    return;
                }

                ItemMeta itemMeta = iItem.getItemMeta();
                itemMeta.addEnchant(enchant, enchantmentLevel + 1, true);
                iItem.setItemMeta(itemMeta);
                p.sendMessage((String)MessageFunction.messages.get("success"));
            } else if (this.destroy > rand.nextInt(100)) {
                try {
                    FileFunction.setStringToDataFile("data.yml", p.getName() + ".Book", FileFunction.itemStackSerialize(this.intensifyBook));
                    FileFunction.setStringToDataFile("data.yml", p.getName() + ".Equipment", FileFunction.itemStackSerialize(iItem));
                } catch (Exception e) {
                    KarIntensify.log.warning(String.format("[%s] - 保存玩家" + p.getName() + "的破碎物品数据失败，请联系插件作者!", KarIntensify.instance.getDescription().getName()));
                    e.printStackTrace();
                }

                int itemAmount = iItem.getAmount();
                iItem.setAmount(itemAmount - 1);
                p.sendMessage((String)MessageFunction.messages.get("broken"));
            } else {
                p.sendMessage((String)MessageFunction.messages.get("failed"));
            }

            int amount = this.intensifyBook.getAmount();
            this.intensifyBook.setAmount(amount - 1);
        }
    }
}
