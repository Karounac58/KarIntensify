package vip.mcsj.www.main;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import vip.mcsj.www.command.Commands;
import vip.mcsj.www.function.FileFunction;
import vip.mcsj.www.function.ItemStackFunction;
import vip.mcsj.www.function.LevelFunction;
import vip.mcsj.www.function.MessageFunction;
import vip.mcsj.www.listener.EventListener;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class KarIntensify extends JavaPlugin {
    public static final Logger log = Logger.getLogger("Minecraft");
    public static JavaPlugin instance;
    private final int pluginId = 24897;

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
        log.info(String.format("[%s] - 插件启动中...",getDescription().getName()));
        //配置文件生成和读取
        init();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginCommand("karintensify").setExecutor(new Commands());
        log.info("==========================================================================================");
        log.info("");
        log.info(" _   _        ___   _____    _   __   _   _____   _____   __   _   _____   _   _____  __    __ ");
        log.info("| | / /      /   | |  _  \\  | | |  \\ | | |_   _| | ____| |  \\ | | /  ___/ | | |  ___| \\ \\  / / ");
        log.info("| |/ /      / /| | | |_| |  | | |   \\| |   | |   | |__   |   \\| | | |___  | | | |__    \\ \\/ /  ");
        log.info("| |\\ \\     / / | | |  _  /  | | | |\\   |   | |   |  __|  | |\\   | \\___  \\ | | |  __|    \\  /   ");
        log.info("| | \\ \\   / /  | | | | \\ \\  | | | | \\  |   | |   | |___  | | \\  |  ___| | | | | |       / /    ");
        log.info("|_|  \\_\\ /_/   |_| |_|  \\_\\ |_| |_|  \\_|   |_|   |_____| |_|  \\_| /_____/ |_| |_|      /_/     ");
        log.info("");
        log.info("==========================================================================================");
        //GuiAPI
        log.info(String.format("[%s] - 插件启动成功！",getDescription().getName()));

        Metrics metrics = new Metrics(this,pluginId);
    }
    public void onDisable(){
        log.info(String.format("[%s] - 插件卸载成功！",getDescription().getName()));
    }

    public void init(){
        try {
            MessageFunction.initMessages();
            ItemStackFunction.initIntensifyItems();
            ItemStackFunction.initIntensifyBooks();
            ItemStackFunction.initProtectEffectAndDebugInfo();
            LevelFunction.initMaxLevels();
            FileFunction.initCustomYaml("data.yml");
            Commands.initMap();
        }catch (Exception e){
            log.warning(String.format("[%s] - 配置文件加载出错，请检查配置文件格式！",getDescription().getName()));
            e.printStackTrace();
        }
    }

}
