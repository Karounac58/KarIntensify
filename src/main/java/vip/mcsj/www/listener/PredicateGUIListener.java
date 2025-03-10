//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.listener;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import vip.mcsj.www.events.GetIntensifyBookEvent;
import vip.mcsj.www.function.PlayerSettings;
import vip.mcsj.www.gui.PredictGUI;
import vip.mcsj.www.gui.PredictGUIHolder;
import vip.mcsj.www.object.IBookType;
import vip.mcsj.www.object.PlayerParams;
import vip.mcsj.www.prediction.PlayerRandomCache;

public class PredicateGUIListener implements Listener {
    public PredicateGUIListener() {
    }

    @EventHandler
    public void onParamAdjust(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            Inventory inv = e.getClickedInventory();
            if (inv.getHolder() instanceof PredictGUIHolder) {
                e.setCancelled(true);
                if (e.getSlot() == 38) {
                    double v = this.adjustParam(e.getWhoClicked().getUniqueId(), true, e.getClick());
                    ItemStack item = inv.getItem(38);
                    List<String> lore = item.getItemMeta().getLore();
                    lore.set(0, "§d§l当前值：" + v);
                    inv.setItem(38, PredictGUI.setItemNameAndLore(item, "§a§l参数1", lore));
                    Bukkit.getPluginManager().callEvent(new GetIntensifyBookEvent((Player)e.getWhoClicked(), IBookType.NORMAL));
                } else if (e.getSlot() == 42) {
                    double v = this.adjustParam(e.getWhoClicked().getUniqueId(), false, e.getClick());
                    ItemStack item = inv.getItem(42);
                    List<String> lore = item.getItemMeta().getLore();
                    lore.set(0, "§d§l当前值：" + v);
                    inv.setItem(42, PredictGUI.setItemNameAndLore(item, "§a§l参数2", lore));
                    Bukkit.getPluginManager().callEvent(new GetIntensifyBookEvent((Player)e.getWhoClicked(), IBookType.NORMAL));
                } else if (e.getSlot() == 50) {
                    double predictSuccess = ((PlayerRandomCache.PerturbationData)PlayerRandomCache.cache.get(e.getWhoClicked().getUniqueId())).getPredictValue();
                    inv.setItem(22, PredictGUI.setItemNameAndLore(e.getInventory().getItem(22), "§c§l预测值", Arrays.asList("§c§l成功率：§a§l" + predictSuccess + "%", "§c§l破碎率：§a§k000")));
                } else if (e.getSlot() == 48) {
                    Bukkit.getPluginManager().callEvent(new GetIntensifyBookEvent((Player)e.getWhoClicked(), IBookType.NORMAL));
                    double predictSuccess = ((PlayerRandomCache.PerturbationData)PlayerRandomCache.cache.get(e.getWhoClicked().getUniqueId())).getPredictValue();
                    inv.setItem(22, PredictGUI.setItemNameAndLore(e.getInventory().getItem(22), "§c§l预测值", Arrays.asList("§c§l成功率：§a§l" + predictSuccess + "%", "§c§l破碎率：§a§k000")));
                }

            }
        }
    }

    public double adjustParam(UUID pUUID, boolean isParam1, ClickType clickType) {
        double returnValue = (double)0.0F;
        if (clickType.isLeftClick()) {
            returnValue = this.adjustParamMethod(pUUID, isParam1, 0.01);
        } else if (clickType.isRightClick()) {
            returnValue = this.adjustParamMethod(pUUID, isParam1, -0.01);
        }

        if (clickType.isLeftClick() && clickType.isShiftClick()) {
            returnValue = this.adjustParamMethod(pUUID, isParam1, (double)1.0F);
        } else if (clickType.isRightClick() && clickType.isShiftClick()) {
            returnValue = this.adjustParamMethod(pUUID, isParam1, (double)-1.0F);
        }

        return returnValue;
    }

    private double adjustParamMethod(UUID pUUID, boolean isParam1, double value) {
        PlayerParams params = PlayerSettings.getParams(pUUID);
        if (isParam1) {
            if (!(params.getParam1() + value > (double)0.5F) && !(params.getParam1() + value < 0.2)) {
                params.setParam1(params.getParam1() + value);
                return params.getParam1();
            } else {
                return params.getParam1();
            }
        } else if (!(params.getParam2() + value > (Math.PI * 2D)) && !(params.getParam2() + value < (double)0.0F)) {
            params.setParam2(params.getParam2() + value);
            return params.getParam2();
        } else {
            return params.getParam2();
        }
    }
}
