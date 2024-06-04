package vip.mcsj.www.function;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;


public class BookDataManager {
    //锋利Lore列表
    //共享变量，要保证线程安全性，所以用Vector来写。
    public static List<String> flLore = new ArrayList<>();
    //保护Lore列表
    public static List<String> bhLore = new ArrayList<>();
    //力量Lore列表
    public static List<String> llLore = new ArrayList<>();
    //弹射物保护Lore列表
    public static List<String> tsLore = new ArrayList<>();
    //耐久Lore列表
    public static List<String> njLore = new ArrayList<>();

    //0破Lore列表
    public static List<String> flZeroLore = new ArrayList<>();
    public static List<String> bhZeroLore = new ArrayList<>();
    public static List<String> llZeroLore = new ArrayList<>();
    public static List<String> tsZeroLore = new ArrayList<>();
    public static List<String> njZeroLore = new ArrayList<>();

    //定义可强化耐久的装备列表
    public static final List<String> nJQHZB = new ArrayList<>();
    //定义可强化锋利的装备列表
    public static final List<String> fLQHZB = new ArrayList<>();
    //定义可强化保护的装备列表
    public static final List<String> bHQHZB = new ArrayList<>();
    //书本下标
//    public static String bookSubScript;
    //强化成功信息
    public static String intensifySuccessMsg;
    //强化失败信息
    public static String intensifyfailedMsg;
    //强化破碎信息
    public static String intensifyBreakedMsg;
    //强化满级信息
    public static String intensifyfulllevel;
    //强化满级等级
    public static int njfulllevel;
    public static int tswbhfulllevel;
    public static int llfulllevel;
    public static int flfulllevel;
    public static int bhfulllevel;

    //成功与失败概率的String所在Lore列表中的位置
    public static final int[] flPerLoc = new int[2];
    public static final int[] bhPerLoc = new int[2];
    public static final int[] llPerLoc = new int[2];
    public static final int[] tsPerLoc = new int[2];
    public static final int[] njPerLoc = new int[2];

    public static final int[] flZeroPerLoc = new int[2];
    public static final int[] bhZeroPerLoc = new int[2];
    public static final int[] llZeroPerLoc = new int[2];
    public static final int[] tsZeroPerLoc = new int[2];
    public static final int[] njZeroPerLoc = new int[2];


    public static String flMaterial;
    public static String bhMaterial;
    public static String llMaterial;
    public static String tsMaterial;
    public static String njMaterial;

    public static String flZeroMaterial;
    public static String bhZeroMaterial;
    public static String llZeroMaterial;
    public static String tsZeroMaterial;
    public static String njZeroMaterial;

    public static String flName;
    public static String bhName;
    public static String llName;
    public static String tsName;
    public static String njName;

    public static String flZeroName;
    public static String bhZeroName;
    public static String llZeroName;
    public static String tsZeroName;
    public static String njZeroName;

    public static int flModelData;
    public static int bhModelData;
    public static int llModelData;
    public static int tsModelData;
    public static int njModelData;

    public static int flZeroModelData;
    public static int bhZeroModelData;
    public static int llZeroModelData;
    public static int tsZeroModelData;
    public static int njZeroModelData;
    /*
    虽然方法是静态，但由于没有传入参数，所以无需过于担心线程安全性
    只有当方法里有改变共享变量的语句，才需注意线程安全性
    ----------------------------
    方法属于一个程序块，只有当别人调用它时才会调到内存里面去执行，
    也就是说当前有多少个线程在执行就有多少组方法块里的局部变量
    ----------------------------
     java中多个用户在访问同一段代码的时候，后台会为每一个请求分配一个单独的线程来处理，
     线程之间是相互独立的，互不干扰，当然也可以相互通信。
     ☆并发问题只有在多个线程之间可能修改同一资源的时候才会出现，并发问题可以通过加锁来解决。
     */

    //创建随机书的方法
    public static ItemStack createAllBookMethod(String modify){
        Random random = new Random();
        if(modify.equals("锋利")) {
            ItemStack item = new ItemStack(Material.matchMaterial(flMaterial));
            List<String> tempList = createNewList(flLore.size());
            Collections.copy(tempList,flLore);
            return setAllInfToItem(item,flModelData,flName,"damage_all",tempList,flPerLoc,random.nextInt(100),random.nextInt(100));
        }
        if(modify.equals("保护")){
            ItemStack item = new ItemStack(Material.matchMaterial(bhMaterial));
            List<String> tempList = createNewList(bhLore.size());
            Collections.copy(tempList,bhLore);
            return setAllInfToItem(item,bhModelData,bhName,"protect",tempList,bhPerLoc,random.nextInt(100),random.nextInt(100));
        }

        if(modify.equals("力量")){
            ItemStack item = new ItemStack(Material.matchMaterial(llMaterial));
            List<String> tempList = createNewList(llLore.size());
            Collections.copy(tempList,llLore);
            return setAllInfToItem(item,llModelData,llName,"power",tempList,llPerLoc,random.nextInt(100),random.nextInt(100));
        }

        if(modify.equals("弹射物保护")){
            ItemStack item = new ItemStack(Material.matchMaterial(tsMaterial));
            List<String> tempList = createNewList(tsLore.size());
            Collections.copy(tempList,tsLore);
            return setAllInfToItem(item,tsModelData,tsName,"catapult",tempList,tsPerLoc,random.nextInt(100),random.nextInt(100));
        }

        if(modify.equals("耐久")){
            ItemStack item = new ItemStack(Material.matchMaterial(njMaterial));
            List<String> tempList = createNewList(njLore.size());
            Collections.copy(tempList,njLore);
            return setAllInfToItem(item,njModelData,njName,"durability",tempList,njPerLoc,random.nextInt(100),random.nextInt(100));
        }
        if(modify.equals("锋利99")) {
            ItemStack item = new ItemStack(Material.matchMaterial(flMaterial));
            List<String> tempList = createNewList(flLore.size());
            Collections.copy(tempList,flLore);
            return setAllInfToItem(item,flModelData,flName,"damage_all",tempList,flPerLoc,99,random.nextInt(100));
        }
        if(modify.equals("保护99")){
            ItemStack item = new ItemStack(Material.matchMaterial(bhMaterial));
            List<String> tempList = createNewList(bhLore.size());
            Collections.copy(tempList,bhLore);
            return setAllInfToItem(item,bhModelData,bhName,"protect",tempList,bhPerLoc,99,random.nextInt(100));
        }

        if(modify.equals("力量99")){
            ItemStack item = new ItemStack(Material.matchMaterial(llMaterial));
            List<String> tempList = createNewList(llLore.size());
            Collections.copy(tempList,llLore);
            return setAllInfToItem(item,llModelData,llName,"power",tempList,llPerLoc,99,random.nextInt(100));
        }

        if(modify.equals("弹射物保护99")){
            ItemStack item = new ItemStack(Material.matchMaterial(tsMaterial));
            List<String> tempList = createNewList(tsLore.size());
            Collections.copy(tempList,tsLore);
            return setAllInfToItem(item,tsModelData,tsName,"catapult",tempList,tsPerLoc,99,random.nextInt(100));
        }

        if(modify.equals("耐久99")){
            ItemStack item = new ItemStack(Material.matchMaterial(njMaterial));
            List<String> tempList = createNewList(njLore.size());
            Collections.copy(tempList,njLore);
            return setAllInfToItem(item,njModelData,njName,"durability",tempList,njPerLoc,99,random.nextInt(100));
        }
        //已改
        if(modify.equals("锋利0破")) {
            ItemStack item = new ItemStack(Material.matchMaterial(flZeroMaterial));
            List<String> tempList = createNewList(flZeroLore.size());
            Collections.copy(tempList,flZeroLore);
            return setAllInfToItem(item,flZeroModelData,flZeroName,"damage_all",tempList,flZeroPerLoc,random.nextInt(100),0);
        }
        //已改
        if(modify.equals("保护0破")){
            ItemStack item = new ItemStack(Material.matchMaterial(bhZeroMaterial));
            List<String> tempList = createNewList(bhZeroLore.size());
            Collections.copy(tempList,bhZeroLore);
            return setAllInfToItem(item,bhZeroModelData,bhZeroName,"protect",tempList,bhZeroPerLoc,random.nextInt(100),0);
        }
        //已改
        if(modify.equals("力量0破")){
            ItemStack item = new ItemStack(Material.matchMaterial(llZeroMaterial));
            List<String> tempList = createNewList(llZeroLore.size());
            Collections.copy(tempList,llZeroLore);
            return setAllInfToItem(item,llZeroModelData,llZeroName,"power",tempList,llZeroPerLoc,random.nextInt(100),0);
        }
        //已改
        if(modify.equals("弹射物保护0破")){
            ItemStack item = new ItemStack(Material.matchMaterial(tsZeroMaterial));
            List<String> tempList = createNewList(tsZeroLore.size());
            Collections.copy(tempList,tsZeroLore);
            return setAllInfToItem(item,tsZeroModelData,tsZeroName,"catapult",tempList,tsZeroPerLoc,random.nextInt(100),0);
        }

        if(modify.equals("耐久0破")){
            ItemStack item = new ItemStack(Material.matchMaterial(njZeroMaterial));
            List<String> tempList = createNewList(njZeroLore.size());
            Collections.copy(tempList,njZeroLore);
            return setAllInfToItem(item,njZeroModelData,njZeroName,"durability",tempList,njZeroPerLoc,random.nextInt(100),0);
        }
        return new ItemStack(Material.AIR);
    }

    /**
     *
     * @param item      要设置的物品
     * @param itemName  物品名
     * @param lore      待修改的物品lore
     * @param lineLoc   传入该物品成功率和破碎率的String在lore中的位置
     * @return
     */
    private static ItemStack setAllInfToItem(ItemStack item,int modelData,String itemName,String nbtName,List<String> lore,int[] lineLoc,int success,int breakage){
        Random random = new Random();
        int sucLoc = lineLoc[0]-1;
        int breLoc = lineLoc[1]-1;
        //替换lore中的占位符为实际成功率破碎率
        String sucStr = lore.get(sucLoc);
        lore.set(sucLoc,sucStr.replace("{success}", String.valueOf(success)));
        String breStr = lore.get(breLoc);
        lore.set(breLoc,breStr.replace("{breakage}", String.valueOf(breakage)));

        //获取物品ItemMeta，设置lore和name
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(itemName);
        itemMeta.setCustomModelData(modelData);
        item.setItemMeta(itemMeta);

        //设置nbt
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("intensify",nbtName);
        nbtItem.setInteger("success",success);
        nbtItem.setInteger("breakage",breakage);
        return nbtItem.getItem();

    }

    public static List<String> createNewList(int n){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    public static String KindOfBook(ItemStack book){
        NBTItem nbtItem = new NBTItem(book);
        return getBookKind(nbtItem);
    }
    private static String getBookKind(NBTItem nbtItem){
        if(nbtItem.getKeys().contains("intensify")){
            if (nbtItem.getString("intensify").equals("durability")) {
                return "耐久";
            } else if (nbtItem.getString("intensify").equals("damage_all")) {
                return "锋利";
            } else if (nbtItem.getString("intensify").equals("catapult")) {
                return "弹射物保护";
            } else if (nbtItem.getString("intensify").equals("power")) {
                return "力量";
            }else if (nbtItem.getString("intensify").equals("protect")) {
                return "保护";
            }
        }
        return "false";
    }
}
