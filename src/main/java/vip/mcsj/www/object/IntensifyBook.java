package vip.mcsj.www.object;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class IntensifyBook {
    private Material material;
    private String name;
    private List<String> lores;
    private int customModelData;

    private ItemStack intensifyBook;

    /**
     *
     * @param material 材料
     * @param name 显示名
     * @param lores 未赋予成功率的lore
     * @param customModelData customModelData值
     * @param type 正常：normal, 99:ninty-nine, 0破：zero
     */
    public IntensifyBook(Material material, String name, List<String> lores, int customModelData,String type) {
        this.material = material;
        this.name = name;
        this.lores = lores;
        this.customModelData = customModelData;
        generateNormalIntensifyBook(type);
    }

    private void generateNormalIntensifyBook(String type){
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
        switch (type){
            case "normal":
                lores.set(successLineIndex,successLine.replace("{success}",String.valueOf(rand.nextInt(100))));
                lores.set(brokenLineIndex,brokenLine.replace("{breakage}",String.valueOf(rand.nextInt(100))));
                break;
            case "ninty-nine":
                lores.set(successLineIndex,successLine.replace("{success}",String.valueOf(99)));
                lores.set(brokenLineIndex,brokenLine.replace("{breakage}",String.valueOf(rand.nextInt(100))));
                break;
            case "zero":
                lores.set(successLineIndex,successLine.replace("{success}",String.valueOf(rand.nextInt(100))));
                lores.set(brokenLineIndex,brokenLine.replace("{breakage}",String.valueOf(0)));
                break;
        }
        iBookIM.setLore(lores);

        //设置CMD
        iBookIM.setCustomModelData(customModelData);

        //完成
        iBook.setItemMeta(iBookIM);

        intensifyBook = iBook;

    }

    public ItemStack getItemStack() {
        return intensifyBook;
    }
}
