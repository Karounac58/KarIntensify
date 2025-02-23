package vip.mcsj.www.object;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
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

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class IntensifyBook implements Serializable {

    static final long serialVersionUID = 1L;

    private Material material;
    private String name;
    private List<String> lores;
    private int customModelData;

    private ItemStack intensifyBook;

    private int success;
    private int destroy;
    /**
     *
     * @param material 材料
     * @param name 显示名
     * @param lores 未赋予成功率的lore
     * @param customModelData customModelData值
     * @param type 正常：normal, 99:ninty-nine, 0破：zero
     */
    public IntensifyBook(Material material, String name, List<String> lores, int customModelData, String type,Enchantment enchant) {
        this.material = material;
        this.name = name;
        this.lores = lores;
        this.customModelData = customModelData;
//        generateNormalIntensifyBook(type,enchant);
    }

    public IntensifyBook(Material material,String name,List<String> lores,int customModelData,ItemStack item,int success,int destroy){
        this.material = material;
        this.name = name;
        this.lores = lores;
        this.customModelData = customModelData;
        this.intensifyBook = item;
        this.success = success;
        this.destroy= destroy;
    }

    /**
     * 克隆方法，用于创新书
     * @return
     */

    public IntensifyBook myClone() {
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        IntensifyBook iBook = null;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bais);
            oos.writeObject(this);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bais.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            iBook = (IntensifyBook) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return iBook;
    }

    public int getSuccess() {
        return success;
    }

    public int getDestroy() {
        return destroy;
    }

    public void generateNormalIntensifyBook(String type, Enchantment enchant){
        ItemStack iBook = new ItemStack(material);
        ItemMeta iBookIM = iBook.getItemMeta();
        //显示名
        iBookIM.setDisplayName(name);

        //lore设置显示概率
        int successLineIndex = -1;
        int brokenLineIndex = -1;
        String lore;
        Random rand = new Random();
        for (int i = 0; i < lores.size(); i++) {
            lore = lores.get(i);
            if(lore.contains("{success}")){
                successLineIndex = i;
            }else if(lore.contains("{breakage}")){
                brokenLineIndex = i;
            }
        }
        String successLine = lores.get(successLineIndex);
        String brokenLine = lores.get(brokenLineIndex);
        int success = rand.nextInt(100);
        int destroy = rand.nextInt(100);
        switch (type){
            case "normal":
                lores.set(successLineIndex,successLine.replace("{success}",String.valueOf(success)));
                lores.set(brokenLineIndex,brokenLine.replace("{breakage}",String.valueOf(destroy)));
                this.success = success;
                this.destroy = destroy;
                break;
            case "ninety_nine":
                lores.set(successLineIndex,successLine.replace("{success}",String.valueOf(99)));
                lores.set(brokenLineIndex,brokenLine.replace("{breakage}",String.valueOf(destroy)));
                this.success = 99;
                this.destroy = destroy;
                break;
            case "zero":
                lores.set(successLineIndex,successLine.replace("{success}",String.valueOf(success)));
                lores.set(brokenLineIndex,brokenLine.replace("{breakage}",String.valueOf(0)));
                this.success = success;
                this.destroy = 0;
                break;
        }
        iBookIM.setLore(lores);

        //设置CMD
        iBookIM.setCustomModelData(customModelData);

        //设置附魔标识
        iBookIM.addEnchant(enchant,1,true);

        //完成
        iBook.setItemMeta(iBookIM);

        //设置NBT
        NBT.modify(iBook,i -> {
            i.setInteger("success",success);
            i.setInteger("destroy",destroy);
            i.setInteger("HideFlags",4);
        });

        intensifyBook = iBook;

    }

    public ItemStack getItemStack() {
        return intensifyBook;
    }

    public static IntensifyBook fromItemStack(ItemStack iBItem){

        NBTItem iBNBTItem = new NBTItem(iBItem);
        //不是强化书
        if(!iBNBTItem.hasTag("success")){
            return null;
        }

        Map<Enchantment, Integer> enchantMap = iBItem.getEnchantments();
        Set<Enchantment> enchantSet = enchantMap.keySet();
        Enchantment enchant;
        for (Enchantment enchant1 : enchantSet) {
            enchant = enchant1;
        }
        int success = iBNBTItem.getInteger("success");
        int destroy = iBNBTItem.getInteger("destroy");

        IntensifyBook iB = new IntensifyBook(
                iBItem.getType(),
                iBItem.getItemMeta().getDisplayName(),
                iBItem.getItemMeta().getLore(),
                iBItem.getItemMeta().getCustomModelData(),
                iBItem,
                success,
                destroy
        );

        return iB;

    }


    public void intensifyUpItem(Player p,ItemStack iItem){
        Enchantment enchant = ItemStackFunction.getiBookSingleEnchant(this.intensifyBook);
        //如果物品不在对应附魔可强化物品列表里
        if(!ItemStackFunction.isiItemValid(enchant,iItem)){
            return;
        }
        Random rand = new Random();
        if(this.success > rand.nextInt(100)){
            Level level = LevelFunction.maxLevels.get(enchant);
            int maxLevel = level.getLevel();
            //强化成功
            int enchantmentLevel = iItem.getEnchantmentLevel(enchant);
            if(maxLevel == enchantmentLevel){
                p.sendMessage(MessageFunction.messages.get("max_level"));
                return;
            }
            ItemMeta itemMeta = iItem.getItemMeta();
            itemMeta.addEnchant(enchant,enchantmentLevel+1,true);
            iItem.setItemMeta(itemMeta);
            p.sendMessage(MessageFunction.messages.get("success"));
        } else if(this.destroy > rand.nextInt(100)){
            //保存破碎数据至data.yml
            try {
                FileFunction.setStringToDataFile("data.yml", p.getName() + ".Book", FileFunction.itemStackSerialize(this.intensifyBook));
                FileFunction.setStringToDataFile("data.yml", p.getName() + ".Equipment", FileFunction.itemStackSerialize(iItem));
            }catch (Exception e){
                KarIntensify.log.warning(String.format("[%s] - 保存玩家"+p.getName()+"的破碎物品数据失败，请联系插件作者!",KarIntensify.instance.getDescription().getName()));
                e.printStackTrace();
            }
            //强化破碎
            int itemAmount = iItem.getAmount();
            iItem.setAmount(itemAmount-1);
            p.sendMessage(MessageFunction.messages.get("broken"));
        }else{
            //强化失败
            p.sendMessage(MessageFunction.messages.get("failed"));
        }
        int amount = this.intensifyBook.getAmount();
        this.intensifyBook.setAmount(amount-1);
    }


}
