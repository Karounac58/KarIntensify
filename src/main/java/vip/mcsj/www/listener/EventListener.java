//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.listener;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.mcsj.www.function.ItemStackFunction;
import vip.mcsj.www.object.IntensifyBook;

public class EventListener implements Listener {
    public EventListener() {
    }

    @EventHandler
    public void onPlayerIntensify(InventoryClickEvent e) {
        if (e.getAction() == InventoryAction.SWAP_WITH_CURSOR) {
            IntensifyBook iBook = IntensifyBook.fromItemStack(e.getCursor());
            if (iBook == null) {
                return;
            }

            ItemStack iItem = e.getCurrentItem();
            iBook.intensifyUpItem((Player)e.getWhoClicked(), iItem);
            e.setCancelled(true);
        }

    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onPlayerDamagedByPlayerProjectile(EntityDamageByEntityEvent e) {
        if (e.getCause().equals(DamageCause.PROJECTILE) && e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            double armorReduceDamage = (double)0.0F;
            if (e.isApplicable(DamageModifier.ARMOR)) {
                armorReduceDamage = e.getDamage(DamageModifier.ARMOR);
                e.setDamage(DamageModifier.ARMOR, (double)0.0F);
            }

            if (e.isApplicable(DamageModifier.MAGIC)) {
                e.setDamage(DamageModifier.MAGIC, (double)0.0F);
            }

            double originDamage = e.getDamage();
            double realDamage = ItemStackFunction.realDamage(p, originDamage, armorReduceDamage, Enchantment.PROTECTION_PROJECTILE, ItemStackFunction.showValue);
            e.setDamage(realDamage);
            if (ItemStackFunction.showValue) {
                p.sendMessage("§c理论受到伤害=" + realDamage);
            }
        }

    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onPlayerDamagedByPlayerProtection(EntityDamageByEntityEvent e) {
        if (e.getCause().equals(DamageCause.ENTITY_ATTACK) && e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player p = (Player)e.getEntity();
            double armorReduceDamage = (double)0.0F;
            if (e.isApplicable(DamageModifier.ARMOR)) {
                armorReduceDamage = e.getDamage(DamageModifier.ARMOR);
                e.setDamage(DamageModifier.ARMOR, (double)0.0F);
            }

            if (e.isApplicable(DamageModifier.MAGIC)) {
                e.setDamage(DamageModifier.MAGIC, (double)0.0F);
            }

            double originDamage = e.getDamage();
            double realDamage = ItemStackFunction.realDamage(p, originDamage, armorReduceDamage, Enchantment.PROTECTION_ENVIRONMENTAL, ItemStackFunction.showValue);
            e.setDamage(DamageModifier.BASE, realDamage);
            if (ItemStackFunction.showValue) {
                p.sendMessage("§c理论受到伤害=" + realDamage);
            }
        }

    }

    public void enchantmentStronger(Enchantment myway, ItemStack itemother) {
        int i = itemother.getEnchantmentLevel(myway);
        ItemMeta im = itemother.getItemMeta();
        im.addEnchant(myway, i + 1, true);
        itemother.setItemMeta(im);
    }

    public int enchantmentremoveStronger(Enchantment myway, ItemStack itemother) {
        ItemMeta im = itemother.getItemMeta();
        int i = itemother.getEnchantmentLevel(myway);
        if (i == 1) {
            itemother.removeEnchantment(myway);
            return 1;
        } else if (i == 0) {
            return 0;
        } else {
            im.addEnchant(myway, i - this.judgeEnchantmentRemoveLevel(i), true);
            itemother.setItemMeta(im);
            return this.judgeEnchantmentRemoveLevel(i);
        }
    }

    public void removeItemBookStrongered(ItemStack itembook) {
        int j = itembook.getAmount();
        --j;
        itembook.setAmount(j);
    }

    public void addNumbetToList(ItemStack itembook, int[] successandlose) {
        NBTItem nbtItem = new NBTItem(itembook);
        successandlose[0] = nbtItem.getInteger("success");
        successandlose[1] = nbtItem.getInteger("breakage");
    }

    public int judgeEnchantmentRemoveLevel(int level) {
        return level / 50 + 1;
    }
}
