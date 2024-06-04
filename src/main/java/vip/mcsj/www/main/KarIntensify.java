package vip.mcsj.www.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import vip.mcsj.www.function.BookDataManager;
import vip.mcsj.www.listener.EventListener;
import vip.mcsj.www.object.IntensifyBook;
import vip.mcsj.www.object.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class KarIntensify extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");
    public static JavaPlugin instance;

    public static List<IntensifyBook> iBooks = new ArrayList<>();

    public static List<Level> maxLevels = new ArrayList<>();

    public static List<Enchantment> enchants = Arrays.asList(
            Enchantment.DAMAGE_ALL,
            Enchantment.PROTECTION_ENVIRONMENTAL,
            Enchantment.PROTECTION_PROJECTILE,
            Enchantment.DURABILITY,
            Enchantment.ARROW_DAMAGE
    );
    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        saveDataFile();
        log.info(String.format("[%s] - 插件启动中...",getDescription().getName()));
        //强化装备信息加载
        loadList();

        loadSubscriptAndMessage();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginCommand("karintensify").setExecutor(new ShitExecutor());
        //GuiAPI
        log.info(String.format("[%s] - 插件启动成功！",getDescription().getName()));
    }
    public void onDisable(){
        System.out.println("KarIntensify已经成功卸载");
    }

    public void loadList(){
        //耐久可强化装备列表
        if(BookDataManager.nJQHZB.isEmpty()){
            BookDataManager.nJQHZB.add("IRON_AXE");
            BookDataManager.nJQHZB.add("IRON_BOOTS");
            BookDataManager.nJQHZB.add("IRON_CHESTPLATE");
            BookDataManager.nJQHZB.add("IRON_HELMET");
            BookDataManager.nJQHZB.add("IRON_HOE");
            BookDataManager.nJQHZB.add("IRON_LEGGINGS");
            BookDataManager.nJQHZB.add("IRON_PICKAXE");
            BookDataManager.nJQHZB.add("IRON_SHOVEL");
            BookDataManager.nJQHZB.add("IRON_SWORD");
            //全套铁护甲及武器及工具
            BookDataManager.nJQHZB.add("GOLDEN_AXE");
            BookDataManager.nJQHZB.add("GOLDEN_BOOTS");
            BookDataManager.nJQHZB.add("GOLDEN_CHESTPLATE");
            BookDataManager.nJQHZB.add("GOLDEN_HELMET");
            BookDataManager.nJQHZB.add("GOLDEN_HOE");
            BookDataManager.nJQHZB.add("GOLDEN_LEGGINGS");
            BookDataManager.nJQHZB.add("GOLDEN_PICKAXE");
            BookDataManager.nJQHZB.add("GOLDEN_SHOVEL");
            BookDataManager.nJQHZB.add("GOLDEN_SWORD");
            //全套金护甲及武器及工具
            BookDataManager.nJQHZB.add("DIAMOND_AXE");
            BookDataManager.nJQHZB.add("DIAMOND_BOOTS");
            BookDataManager.nJQHZB.add("DIAMOND_CHESTPLATE");
            BookDataManager.nJQHZB.add("DIAMOND_HELMET");
            BookDataManager.nJQHZB.add("DIAMOND_HOE");
            BookDataManager.nJQHZB.add("DIAMOND_LEGGINGS");
            BookDataManager.nJQHZB.add("DIAMOND_PICKAXE");
            BookDataManager.nJQHZB.add("DIAMOND_SHOVEL");
            BookDataManager.nJQHZB.add("DIAMOND_SWORD");
            //全套钻石护甲及武器及工具
            BookDataManager.nJQHZB.add("NETHERITE_SWORD");
            BookDataManager.nJQHZB.add("NETHERITE_AXE");
            BookDataManager.nJQHZB.add("NETHERITE_HELMET");
            BookDataManager.nJQHZB.add("NETHERITE_CHESTPLATE");
            BookDataManager.nJQHZB.add("NETHERITE_LEGGINGS");
            BookDataManager.nJQHZB.add("NETHERITE_BOOTS");
            BookDataManager.nJQHZB.add("NETHERITE_HOE");
            BookDataManager.nJQHZB.add("NETHERITE_PICKAXE");
            BookDataManager.nJQHZB.add("NETHERITE_SHOVEL");
            //全套残骸护甲及武器及工具
            BookDataManager.nJQHZB.add("ELYTRA");
            //鞘翅
            BookDataManager.nJQHZB.add("BOW");
            //弓
        }

        if(BookDataManager.fLQHZB.isEmpty()){
            BookDataManager.fLQHZB.add("DIAMOND_SWORD");
            BookDataManager.fLQHZB.add("DIAMOND_AXE");
            BookDataManager.fLQHZB.add("IRON_SWORD");
            BookDataManager.fLQHZB.add("IRON_AXE");
            BookDataManager.fLQHZB.add("GOLDEN_SWORD");
            BookDataManager.fLQHZB.add("GOLDEN_AXE");
            BookDataManager.fLQHZB.add("NETHERITE_SWORD");
            BookDataManager.fLQHZB.add("NETHERITE_AXE");
            //钻石金铁残骸之剑和斧
        }

        if(BookDataManager.bHQHZB.isEmpty()){
            BookDataManager.bHQHZB.add("LEATHER_HELMET");
            BookDataManager.bHQHZB.add("LEATHER_CHESTPLATE");
            BookDataManager.bHQHZB.add("LEATHER_LEGGINGS");
            BookDataManager.bHQHZB.add("LEATHER_BOOTS");
            //皮革套
            BookDataManager.bHQHZB.add("IRON_HELMET");
            BookDataManager.bHQHZB.add("IRON_CHESTPLATE");
            BookDataManager.bHQHZB.add("IRON_LEGGINGS");
            BookDataManager.bHQHZB.add("IRON_BOOTS");
            //铁套
            BookDataManager.bHQHZB.add("GOLDEN_HELMET");
            BookDataManager.bHQHZB.add("GOLDEN_CHESTPLATE");
            BookDataManager.bHQHZB.add("GOLDEN_LEGGINGS");
            BookDataManager.bHQHZB.add("GOLDEN_BOOTS");
            //金套
            BookDataManager.bHQHZB.add("DIAMOND_HELMET");
            BookDataManager.bHQHZB.add("DIAMOND_CHESTPLATE");
            BookDataManager.bHQHZB.add("DIAMOND_LEGGINGS");
            BookDataManager.bHQHZB.add("DIAMOND_BOOTS");
            //钻石套
            BookDataManager.bHQHZB.add("NETHERITE_HELMET");
            BookDataManager.bHQHZB.add("NETHERITE_CHESTPLATE");
            BookDataManager.bHQHZB.add("NETHERITE_LEGGINGS");
            BookDataManager.bHQHZB.add("NETHERITE_BOOTS");
            //残骸套
        }
    }

    private void loadSubscriptAndMessage(){
        try {
            FileConfiguration config = getConfig();
//            BookDataManager.bookSubScript = config.getString("strengthen-the-book-subscript");
            BookDataManager.intensifySuccessMsg = config.getString("message.intensify-success");
            BookDataManager.intensifyBreakedMsg = config.getString("message.intensify-breaked");
            BookDataManager.intensifyfailedMsg = config.getString("message.intensify-failed");

            BookDataManager.intensifyfulllevel = config.getString("message.intensify-fulllevel");
            BookDataManager.llfulllevel = config.getInt("level.power");
            BookDataManager.tswbhfulllevel = config.getInt("level.tswprotect");
            BookDataManager.flfulllevel = config.getInt("level.damageall");
            BookDataManager.njfulllevel = config.getInt("level.durability");
            BookDataManager.bhfulllevel = config.getInt("level.protect");

            //设置锋利Lore
            BookDataManager.flLore = (List<String>) config.getList("message.intensify-information.damage-all.normal.lore");
            BookDataManager.bhLore = (List<String>) config.getList("message.intensify-information.protect.normal.lore");
            BookDataManager.llLore = (List<String>) config.getList("message.intensify-information.power.normal.lore");
            BookDataManager.tsLore = (List<String>) config.getList("message.intensify-information.catapult.normal.lore");
            BookDataManager.njLore = (List<String>) config.getList("message.intensify-information.durability.normal.lore");
            //设置锋利0破Lore
            BookDataManager.flZeroLore = (List<String>) config.getList("message.intensify-information.damage-all.zero-breakage.lore");
            BookDataManager.bhZeroLore = (List<String>) config.getList("message.intensify-information.protect.zero-breakage.lore");
            BookDataManager.llZeroLore = (List<String>) config.getList("message.intensify-information.power.zero-breakage.lore");
            BookDataManager.tsZeroLore = (List<String>) config.getList("message.intensify-information.catapult.zero-breakage.lore");
            BookDataManager.njZeroLore = (List<String>) config.getList("message.intensify-information.durability.zero-breakage.lore");


            //锋利Lore提升性能
            BookDataManager.flPerLoc[0] = config.getInt("message.intensify-information.damage-all.normal.success-line");
            BookDataManager.flPerLoc[1] = config.getInt("message.intensify-information.damage-all.normal.breakage-line");
            BookDataManager.bhPerLoc[0] = config.getInt("message.intensify-information.protect.normal.success-line");
            BookDataManager.bhPerLoc[1] = config.getInt("message.intensify-information.protect.normal.breakage-line");
            BookDataManager.llPerLoc[0] = config.getInt("message.intensify-information.power.normal.success-line");
            BookDataManager.llPerLoc[1] = config.getInt("message.intensify-information.power.normal.breakage-line");
            BookDataManager.tsPerLoc[0] = config.getInt("message.intensify-information.catapult.normal.success-line");
            BookDataManager.tsPerLoc[1] = config.getInt("message.intensify-information.catapult.normal.breakage-line");
            BookDataManager.njPerLoc[0] = config.getInt("message.intensify-information.durability.normal.success-line");
            BookDataManager.njPerLoc[1] = config.getInt("message.intensify-information.durability.normal.breakage-line");

            //锋利0破Lore提升性能
            BookDataManager.flZeroPerLoc[0] = config.getInt("message.intensify-information.damage-all.zero-breakage.success-line");
            BookDataManager.flZeroPerLoc[1] = config.getInt("message.intensify-information.damage-all.zero-breakage.breakage-line");
            BookDataManager.bhZeroPerLoc[0] = config.getInt("message.intensify-information.protect.zero-breakage.success-line");
            BookDataManager.bhZeroPerLoc[1] = config.getInt("message.intensify-information.protect.zero-breakage.breakage-line");
            BookDataManager.llZeroPerLoc[0] = config.getInt("message.intensify-information.power.zero-breakage.success-line");
            BookDataManager.llZeroPerLoc[1] = config.getInt("message.intensify-information.power.zero-breakage.breakage-line");
            BookDataManager.tsZeroPerLoc[0] = config.getInt("message.intensify-information.catapult.zero-breakage.success-line");
            BookDataManager.tsZeroPerLoc[1] = config.getInt("message.intensify-information.catapult.zero-breakage.breakage-line");
            BookDataManager.njZeroPerLoc[0] = config.getInt("message.intensify-information.durability.zero-breakage.success-line");
            BookDataManager.njZeroPerLoc[1] = config.getInt("message.intensify-information.durability.zero-breakage.breakage-line");

            //设置锋利Material
            BookDataManager.flMaterial = config.getString("message.intensify-information.damage-all.normal.material");
            BookDataManager.bhMaterial = config.getString("message.intensify-information.protect.normal.material");
            BookDataManager.llMaterial = config.getString("message.intensify-information.power.normal.material");
            BookDataManager.tsMaterial = config.getString("message.intensify-information.catapult.normal.material");
            BookDataManager.njMaterial = config.getString("message.intensify-information.durability.normal.material");

            //设置锋利0破Material
            BookDataManager.flZeroMaterial = config.getString("message.intensify-information.damage-all.zero-breakage.material");
            BookDataManager.bhZeroMaterial = config.getString("message.intensify-information.protect.zero-breakage.material");
            BookDataManager.llZeroMaterial = config.getString("message.intensify-information.power.zero-breakage.material");
            BookDataManager.tsZeroMaterial = config.getString("message.intensify-information.catapult.zero-breakage.material");
            BookDataManager.njZeroMaterial = config.getString("message.intensify-information.durability.zero-breakage.material");

            //设置锋利Name
            BookDataManager.flName = config.getString("message.intensify-information.damage-all.normal.name");
            BookDataManager.bhName = config.getString("message.intensify-information.protect.normal.name");
            BookDataManager.llName = config.getString("message.intensify-information.power.normal.name");
            BookDataManager.tsName = config.getString("message.intensify-information.catapult.normal.name");
            BookDataManager.njName = config.getString("message.intensify-information.durability.normal.name");

            //设置锋利0破Name
            BookDataManager.flZeroName = config.getString("message.intensify-information.damage-all.zero-breakage.name");
            BookDataManager.bhZeroName = config.getString("message.intensify-information.protect.zero-breakage.name");
            BookDataManager.llZeroName = config.getString("message.intensify-information.power.zero-breakage.name");
            BookDataManager.tsZeroName = config.getString("message.intensify-information.catapult.zero-breakage.name");
            BookDataManager.njZeroName = config.getString("message.intensify-information.durability.zero-breakage.name");

            //设置锋利ModelData
            BookDataManager.flModelData = config.getInt("message.intensify-information.damage-all.normal.model-data");
            BookDataManager.bhModelData = config.getInt("message.intensify-information.protect.normal.model-data");
            BookDataManager.llModelData = config.getInt("message.intensify-information.power.normal.model-data");
            BookDataManager.tsModelData = config.getInt("message.intensify-information.catapult.normal.model-data");
            BookDataManager.njModelData = config.getInt("message.intensify-information.durability.normal.model-data");

            //设置锋利0破ModelData
            BookDataManager.flZeroModelData = config.getInt("message.intensify-information.damage-all.zero-breakage.model-data");
            BookDataManager.bhZeroModelData = config.getInt("message.intensify-information.protect.zero-breakage.model-data");
            BookDataManager.llZeroModelData = config.getInt("message.intensify-information.power.zero-breakage.model-data");
            BookDataManager.tsZeroModelData = config.getInt("message.intensify-information.catapult.zero-breakage.model-data");
            BookDataManager.njZeroModelData = config.getInt("message.intensify-information.durability.zero-breakage.model-data");
        }catch(Exception e){
            System.out.println("获取配置文件失败，请检查配置文件!");
        }
    }

    public void saveDataFile(){
        File dataFile = new File(instance.getDataFolder(),"data.yml");
        if(dataFile.exists()){
            return;
        }else{
            try{
                FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(dataFile);
                dataConfig.save(dataFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
