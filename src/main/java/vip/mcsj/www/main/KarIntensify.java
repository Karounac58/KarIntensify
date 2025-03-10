//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.main;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import vip.mcsj.www.command.Commands;
import vip.mcsj.www.function.FileFunction;
import vip.mcsj.www.function.ItemStackFunction;
import vip.mcsj.www.function.LevelFunction;
import vip.mcsj.www.function.MessageFunction;
import vip.mcsj.www.function.PlayerSettings;
import vip.mcsj.www.listener.EventListener;
import vip.mcsj.www.listener.PredicateGUIListener;
import vip.mcsj.www.listener.PredictListener;
import vip.mcsj.www.prediction.ProbabilityModel;
import vip.mcsj.www.prediction.utils.RandomUtil;

public class KarIntensify extends JavaPlugin {
    public static final Logger log = Logger.getLogger("Minecraft");
    public static JavaPlugin instance;
    private final int pluginId = 24897;
    public static ProbabilityModel model;
    public static List<Enchantment> enchants;

    public KarIntensify() {
    }

    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        log.info(String.format("[%s] - 插件启动中...", this.getDescription().getName()));
        this.init();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginManager().registerEvents(new PredicateGUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new PredictListener(), this);
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
        log.info(String.format("[%s] - 插件启动成功！", this.getDescription().getName()));
        RandomUtil.generateAmplitude();
        RandomUtil.generatePhase();
        PlayerSettings.getAllPlayerSettings();
    }

    public void onDisable() {
        try {
            PlayerSettings.saveAllPlayerSettings();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info(String.format("[%s] - 插件卸载成功！", this.getDescription().getName()));
    }

    public void init() {
        try {
            MessageFunction.initMessages();
            ItemStackFunction.initIntensifyItems();
            ItemStackFunction.initIntensifyBooks();
            ItemStackFunction.initProtectEffectAndDebugInfo();
            LevelFunction.initMaxLevels();
            FileFunction.initCustomYaml("data.yml");
            FileFunction.initCustomYaml("predict.yml");
            Commands.initMap();
        } catch (Exception e) {
            log.warning(String.format("[%s] - 配置文件加载出错，请检查配置文件格式！", this.getDescription().getName()));
            e.printStackTrace();
        }

    }

    public void dailyGenerate() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            public void run() {
                RandomUtil.generateAmplitude();
                RandomUtil.generatePhase();
                Bukkit.getServer().broadcastMessage((String)MessageFunction.messages.get("predict_refresh"));
            }
        }, 2000L, 86400000L);
    }

    static {
        enchants = Arrays.asList(Enchantment.DAMAGE_ALL, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_PROJECTILE, Enchantment.DURABILITY, Enchantment.ARROW_DAMAGE);
    }
}
