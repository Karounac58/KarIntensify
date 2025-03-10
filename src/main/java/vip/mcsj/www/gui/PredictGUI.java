//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.gui;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.mcsj.www.function.PlayerSettings;
import vip.mcsj.www.object.PlayerParams;
import vip.mcsj.www.prediction.PlayerRandomCache;

public class PredictGUI {
    private Inventory inv;
    private Player p;
    private double param1;
    private double param2;

    public PredictGUI(Inventory inv, Player p, boolean isFind) {
        if (!isFind) {
            this.inv = inv;
            this.p = p;
        } else {
            this.inv = inv;
            this.p = p;
            PlayerParams params = PlayerSettings.getParams(p.getUniqueId());
            this.param1 = params.getParam1();
            this.param2 = params.getParam2();
        }

    }

    public PredictGUI(Inventory inv, Player p, double param1, double param2) {
        this.inv = inv;
        this.p = p;
        this.param1 = param1;
        this.param2 = param2;
    }

    public void initial() {
        for(int i = 0; i < 54; ++i) {
            if (i == 38) {
                ItemStack itemStack = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                this.inv.setItem(i, setItemNameAndLore(itemStack, "§c参数1", Arrays.asList("§d§l当前值：" + this.param1, "§e§l左键:+0.01    |    右键:-0.01", "§e§lshift+左键:+1 | shift+右键:-0.1", "§b§l范围：0.01-0.50")));
            } else if (i == 42) {
                ItemStack itemStack = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                this.inv.setItem(i, setItemNameAndLore(itemStack, "§a参数2", Arrays.asList("§d§l当前值：" + this.param2, "§e§l左键:+0.01    |    右键:-0.01", "§e§lshift+左键:+1 | shift+右键:-0.1", "§b§l范围：0.01-3.14")));
            } else if (i == 22) {
                ItemStack itemStack = new ItemStack(Material.BOOK);
                double predictSuccess = (PlayerRandomCache.cache.get(this.p.getUniqueId())).getPredictValue();
                this.inv.setItem(22, setItemNameAndLore(itemStack, "§c§l预测值", Arrays.asList("§c§l成功率：§a§l" + predictSuccess + "%", "§c§l破碎率：§a§k000")));
            } else if (i == 50) {
                ItemStack itemStack = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
                this.inv.setItem(50, setItemNameAndLore(itemStack, "§c刷新预测成功率", List.of()));
            } else if (i == 48) {
                ItemStack itemStack = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                this.inv.setItem(48, setItemNameAndLore(itemStack, "§c跳过这次预测值", List.of()));
            }
        }

    }

    public void openGUIForPlayer() {
        this.initial();
        this.p.openInventory(this.inv);
    }

    public static ItemStack setItemNameAndLore(ItemStack item, String name, List<String> lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
}
