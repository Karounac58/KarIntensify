package vip.mcsj.www.listener;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.mcsj.www.function.ItemStackFunction;
import vip.mcsj.www.object.IntensifyBook;

public class EventListener implements org.bukkit.event.Listener {

//    @EventHandler
//    @ParametersAreNonnullByDefault
    //强化监听
//    public void onInventoryClickItemEvent(InventoryClickEvent e) {
//        int[] successandlose = new int[2];
//        ItemStack itembook;
//        ItemStack itemother;
//        Player p = (Player)e.getWhoClicked();
//        if(e.getAction().equals(InventoryAction.SWAP_WITH_CURSOR)){
//            //获取点击前在光标上的物品(书)
//            itembook = e.getCursor();
//            //获取点击前在所点击格子上的物品(待强化装备)
//            itemother = e.getCurrentItem();
////            NMSDataManager nmsDataManager = new NMSDataManager();
////            nmsDataManager.getSlimeFunNMS();
//            Random shit = new Random();
//            //判断是什么种类的强化书。
//            if("耐久".equals(BookDataManager.KindOfBook(itembook))){
////                for(String key:nmsDataManager.asNMSCopy(itemother).split(",")){
////                    if(key.length() > 43) {
////                        if (key.substring(21, 43).equals("slimefun:slimefun_item")){
////                            p.sendMessage(ChatColor.RED+"粘液物品不能强化！");
////                            e.setCancelled(true);
////                            return;
////                        }
////                    }
////                }
//                if(BookDataManager.nJQHZB.contains(itemother.getType().name())) {
//                    if(itemother.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) >= njfulllevel){
//                        e.getWhoClicked().sendMessage(intensifyfulllevel);
//                        return;
//                    }
//                    addNumbetToList(itembook,successandlose);
//                    /*
//                    假设获取到的强化书成功率为80%
//                    那么，成功率可类比为随机出现一个数(<100)大于20的概率
//                    所以，算法如下。
//                     */
//                    if(shit.nextInt(100) > (99-successandlose[0])) {
//                        enchantmentStronger(Enchantment.DURABILITY,itemother);//enchantmentStronger方法传出修改后的ItemMeta.
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifySuccessMsg);
//                        e.setCancelled(true);
//                    }else if(shit.nextInt(100) > (99-successandlose[1])){
//                        removeItemOtherBreaked(itemother,successandlose,p.getName());
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyBreakedMsg);
//                        e.setCancelled(true);
//                    }else{
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyfailedMsg);
//                        e.setCancelled(true);
//                    }/*else{
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(ChatColor.WHITE+"强化失败，物品未降级");
//                        e.setCancelled(true);
//                    }
//                    */
//                }
//            }else if("锋利".equals(BookDataManager.KindOfBook(itembook))){
////                for(String key:nmsDataManager.asNMSCopy(itemother).split(",")){
////                    if(key.length() > 43) {
////                        if (key.substring(21, 43).equals("slimefun:slimefun_item")){
////                            p.sendMessage(ChatColor.RED+"粘液物品不能强化！");
////                            e.setCancelled(true);
////                            return;
////                        }
////                    }
////                }
//                if(BookDataManager.fLQHZB.contains(itemother.getType().name())){
//                    if(itemother.getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL) >= flfulllevel){
//                        e.getWhoClicked().sendMessage(intensifyfulllevel);
//                        return;
//                    }
//                    addNumbetToList(itembook,successandlose);
//                    if(shit.nextInt(100) > (99-successandlose[0])) {
//                        enchantmentStronger(Enchantment.DAMAGE_ALL,itemother);
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifySuccessMsg);
//                        e.setCancelled(true);
//                        //不成功则直接降级
//                    }else if(shit.nextInt(100) > (99-successandlose[1])){
//                        removeItemOtherBreaked(itemother,successandlose,p.getName());
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyBreakedMsg);
//                        e.setCancelled(true);
//                    }else{
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyfailedMsg);
//                        e.setCancelled(true);
//                    }/*else{
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(ChatColor.WHITE+"强化失败，物品未降级");
//                        e.setCancelled(true);
//                    }
//                    */
//                }
//            }else if("弹射物保护".equals(BookDataManager.KindOfBook(itembook))){
////                for(String key:nmsDataManager.asNMSCopy(itemother).split(",")){
////                    if(key.length() > 43) {
////                        if (key.substring(21, 43).equals("slimefun:slimefun_item")){
////                            p.sendMessage(ChatColor.RED+"粘液物品不能强化！");
////                            e.setCancelled(true);
////                            return;
////                        }
////                    }
////                }
//                if(BookDataManager.bHQHZB.contains(itemother.getType().name())){
//                    if(itemother.getItemMeta().getEnchantLevel(Enchantment.PROTECTION_PROJECTILE) >= tswbhfulllevel){
//                        e.getWhoClicked().sendMessage(intensifyfulllevel);
//                        return;
//                    }
//                    addNumbetToList(itembook,successandlose);
//                    if(shit.nextInt(100) > (99-successandlose[0])) {
//                        enchantmentStronger(Enchantment.PROTECTION_PROJECTILE,itemother);
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifySuccessMsg);
//                        e.setCancelled(true);
//                        //不成功则直接降级
//                    }else if(shit.nextInt(100) > (99-successandlose[1])){
//                        removeItemOtherBreaked(itemother,successandlose,p.getName());
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyBreakedMsg);
//                        e.setCancelled(true);
//                    }else{
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyfailedMsg);
//                        e.setCancelled(true);
//                    }/*else{
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(ChatColor.WHITE+"强化失败，物品未降级");
//                        e.setCancelled(true);
//                    }
//                    */
//                    /*
//                    if(BookDataManager.successandlose[1] > shit.nextInt()){
//                        int k = itemother.getAmount();
//                    }
//                     */
//                }
//            }else if("力量".equals(BookDataManager.KindOfBook(itembook))){
////                for(String key:nmsDataManager.asNMSCopy(itemother).split(",")){
////                    if(key.length() > 43) {
////                        if (key.substring(21, 43).equals("slimefun:slimefun_item")){
////                            p.sendMessage(ChatColor.RED+"粘液物品不能强化！");
////                            e.setCancelled(true);
////                            return;
////                        }
////                    }
////                }
//                if(itemother.getType().name().equals("BOW")){
//                    if(itemother.getItemMeta().getEnchantLevel(Enchantment.ARROW_DAMAGE) >= llfulllevel){
//                        e.getWhoClicked().sendMessage(intensifyfulllevel);
//                        return;
//                    }
//                    addNumbetToList(itembook,successandlose);
//                    if(shit.nextInt(100) > (99-successandlose[0])) {
//                        enchantmentStronger(Enchantment.ARROW_DAMAGE, itemother);
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifySuccessMsg);
//                        e.setCancelled(true);
//                    }else if(shit.nextInt(100) > (99-successandlose[1])){
//                        removeItemOtherBreaked(itemother,successandlose,p.getName());
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyBreakedMsg);
//                        e.setCancelled(true);
//                    }else{
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyfailedMsg);
//                        e.setCancelled(true);
//                    }
//                }
//            }else if("保护".equals(BookDataManager.KindOfBook(itembook))){
////                for(String key:nmsDataManager.asNMSCopy(itemother).split(",")){
////                    if(key.length() > 43) {
////                        if (key.substring(21, 43).equals("slimefun:slimefun_item")){
////                            p.sendMessage(ChatColor.RED+"粘液物品不能强化！");
////                            e.setCancelled(true);
////                            return;
////                        }
////                    }
////                }
//                if(BookDataManager.bHQHZB.contains(itemother.getType().name())){
//                    if(itemother.getItemMeta().getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) >= bhfulllevel){
//                        e.getWhoClicked().sendMessage(intensifyfulllevel);
//                        return;
//                    }
//                    addNumbetToList(itembook,successandlose);
//                    if(shit.nextInt(100) > (99-successandlose[0])) {
//                        enchantmentStronger(Enchantment.PROTECTION_ENVIRONMENTAL, itemother);
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifySuccessMsg);
//                        e.setCancelled(true);
//                    }else if(shit.nextInt(100) > (99-successandlose[1])){
//                        removeItemOtherBreaked(itemother,successandlose,p.getName());
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyBreakedMsg);
//                        e.setCancelled(true);
//                    }else{
//                        removeItemBookStrongered(itembook);
//                        e.getWhoClicked().sendMessage(intensifyfailedMsg);
//                        e.setCancelled(true);
//                    }
//                }
//            }
//        }
//    }
    //淬炼监听
    @EventHandler
    public void onPlayerIntensify(InventoryClickEvent e){
        if(e.getAction() == InventoryAction.SWAP_WITH_CURSOR) {
            IntensifyBook iBook = IntensifyBook.fromItemStack(e.getCursor());
            if (iBook == null) {
                return;
            }
            ItemStack iItem = e.getCurrentItem();
            iBook.intensifyUpItem((Player) e.getWhoClicked(),iItem);
            e.setCancelled(true);
        }
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onPlayerDamagedByPlayerProjectile(EntityDamageByEntityEvent e) {
        if (e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            if (e.getEntity() instanceof Player) {
                Player p = (Player)e.getEntity();
                double armorReduceDamage = (double)0.0F;
                if (e.isApplicable(EntityDamageEvent.DamageModifier.ARMOR)) {
                    armorReduceDamage = e.getDamage(EntityDamageEvent.DamageModifier.ARMOR);
                    e.setDamage(EntityDamageEvent.DamageModifier.ARMOR, (double)0.0F);
                }

                if (e.isApplicable(EntityDamageEvent.DamageModifier.MAGIC)) {
                    e.setDamage(EntityDamageEvent.DamageModifier.MAGIC, (double)0.0F);
                }

                double originDamage = e.getDamage();
                double realDamage = ItemStackFunction.realDamage(p, originDamage, armorReduceDamage, Enchantment.PROTECTION_PROJECTILE, ItemStackFunction.showValue);
                e.setDamage(realDamage);
                if (ItemStackFunction.showValue) {
                    p.sendMessage("§c理论受到伤害=" + realDamage);
                }
            }

        }
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onPlayerDamagedByPlayerProtection(EntityDamageByEntityEvent e) {
        if (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
                Player p = (Player)e.getEntity();
                double armorReduceDamage = (double)0.0F;
                if (e.isApplicable(EntityDamageEvent.DamageModifier.ARMOR)) {
                    armorReduceDamage = e.getDamage(EntityDamageEvent.DamageModifier.ARMOR);
                    e.setDamage(EntityDamageEvent.DamageModifier.ARMOR, (double)0.0F);
                }

                if (e.isApplicable(EntityDamageEvent.DamageModifier.MAGIC)) {
                    e.setDamage(EntityDamageEvent.DamageModifier.MAGIC, (double)0.0F);
                }

                double originDamage = e.getDamage();
                double realDamage = ItemStackFunction.realDamage(p, originDamage, armorReduceDamage, Enchantment.PROTECTION_ENVIRONMENTAL, ItemStackFunction.showValue);
                e.setDamage(EntityDamageEvent.DamageModifier.BASE, realDamage);
                if (ItemStackFunction.showValue) {
                    p.sendMessage("§c理论受到伤害=" + realDamage);
                }
            }

        }
    }

//    @EventHandler(priority = EventPriority.HIGH)
//    public void onPlayerDamagedByPlayerProjectile(EntityDamageByEntityEvent e){
//        //判断是否为玩家
//        if(!e.getCause().toString().equals("PROJECTILE")){
//            return;
//        }
//        if(e.getEntity() instanceof Player){
//            Player p = (Player)e.getEntity();
//            //头盔减伤
//            ItemStack helmet = p.getInventory().getHelmet();
//            ItemStack chestplate = p.getInventory().getChestplate();
//            ItemStack leggings = p.getInventory().getLeggings();
//            ItemStack boots = p.getInventory().getBoots();
//            int helmetLevel = 0;
//            int chestplateLevel = 0;
//            int leggingsLevel = 0;
//            int bootsLevel = 0;
//            if(helmet != null) {
//                helmetLevel = helmet.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);
//            }
//            if(chestplate != null) {
//                chestplateLevel = chestplate.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);
//            }
//            if(leggings != null) {
//                leggingsLevel = leggings.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);
//            }
//            if(boots != null) {
//                bootsLevel = boots.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);
//            }
//
//            if(EquipmentDataManager.judgeEquipmentOneToTwo(helmetLevel,chestplateLevel,leggingsLevel,bootsLevel)){
//                List list = EquipmentDataManager.getEquipmentReduceInjury(helmetLevel,chestplateLevel,leggingsLevel,bootsLevel);
//                double reduceinjury = (Double)list.get(0);
//                double originalPro = (Double)list.get(1);
//                double damage = e.getDamage();
////                System.out.println("==============KarIntensify================");
////                System.out.println("原版免伤："+originalPro);
////                System.out.println("总伤害："+damage);
////                System.out.println("免伤："+reduceinjury);
////                System.out.println("减免后伤害："+damage * (1-reduceinjury));
//                e.setDamage(damage * (1-reduceinjury) / (1-originalPro));
//            }
//        }
//
//    }
//
//
//    @EventHandler(priority = EventPriority.HIGH)
//    public void onPlayerDamagedByPlayerProtection(EntityDamageByEntityEvent e){
//        //判断是否为玩家
//        if(!e.getCause().toString().equals("ENTITY_ATTACK")){
//            return;
//        }
//        if(e.getEntity() instanceof Player){
//            Player p = (Player)e.getEntity();
//            //头盔减伤
//            ItemStack helmet = p.getInventory().getHelmet();
//            ItemStack chestplate = p.getInventory().getChestplate();
//            ItemStack leggings = p.getInventory().getLeggings();
//            ItemStack boots = p.getInventory().getBoots();
//            int helmetLevel = 0;
//            int chestplateLevel = 0;
//            int leggingsLevel = 0;
//            int bootsLevel = 0;
//            if(helmet != null) {
//                helmetLevel = helmet.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
//            }
//            if(chestplate != null) {
//                chestplateLevel = chestplate.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
//            }
//            if(leggings != null) {
//                leggingsLevel = leggings.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
//            }
//            if(boots != null) {
//                bootsLevel = boots.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
//            }
//
//            if(EquipmentDataManager.judgeEquipmentOneToTwo(helmetLevel,chestplateLevel,leggingsLevel,bootsLevel)){
//                List list = EquipmentDataManager.getProtectionEquipmentReduceInjury(helmetLevel,chestplateLevel,leggingsLevel,bootsLevel);
//                double reduceinjury = (Double)list.get(0);
//                double originalPro = (Double)list.get(1);
//                double damage = e.getDamage();
////                System.out.println("==============KarIntensify================");
////                System.out.println("原版免伤："+originalPro);
////                System.out.println("总伤害："+damage);
////                System.out.println("免伤："+reduceinjury);
////                System.out.println("减免后伤害："+damage * (1-reduceinjury));
//                e.setDamage(damage * (1-reduceinjury) / (1-originalPro));
//            }
//
//        }
//
//    }
//







    public void enchantmentStronger(Enchantment myway,ItemStack itemother){
        int i = itemother.getEnchantmentLevel(myway);
        ItemMeta im = itemother.getItemMeta();
        im.addEnchant(myway, i + 1, true);
        itemother.setItemMeta(im);
        //把已经修改好的ItemMeta返回去设置，在外面设置ItemMeta
    }

    public int enchantmentremoveStronger(Enchantment myway,ItemStack itemother){
        ItemMeta im = itemother.getItemMeta();
        int i = itemother.getEnchantmentLevel(myway);
        if(i == 1) {
            itemother.removeEnchantment(myway);
            return 1;
        }else if(i == 0){   //如果没附魔等级
            return 0;
        }
        im.addEnchant(myway, i - judgeEnchantmentRemoveLevel(i), true);
        itemother.setItemMeta(im);
        return judgeEnchantmentRemoveLevel(i);
    }
    //移除强化过的物品
    public void removeItemBookStrongered(ItemStack itembook){
        /*
           方法一：用原版指令移除强化书。
           缺点：可能不能移除除原版外的物品
           ShitStronger.instance.getServer().dispatchCommand(ShitStronger.instance.getServer().getConsoleSender(),
           "minecraft:clear "+((Player)e.getWhoClicked()).getName()+" minecraft:book 2");
                     */
        //方法二：setAmount方法(有的物品不能setAmount(0)，那时候就得条件分支讨论)
        int j = itembook.getAmount();
        j--;
        itembook.setAmount(j);
    }
//    //物品破碎方法
//    public void removeItemOtherBreaked(ItemStack itemother,int[] successAndLose,String playerName){
//        try {
//            FileDataManager.setStringToDataFile(playerName + ".BookSuccess", String.valueOf(successAndLose[0]));
//            FileDataManager.setStringToDataFile(playerName + ".BookBreaked", String.valueOf(successAndLose[1]));
//            FileDataManager.setStringToDataFile(playerName + ".item", FileDataManager.itemStackSerialize(itemother));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        itemother.setAmount(0);
//
//    }
    //获取书本数据方法
    public void addNumbetToList(ItemStack itembook,int[] successandlose){
        //获取成功率
//        try {
//            successandlose[0] = Integer.valueOf(itembook.getItemMeta().getLore().get(1).substring(6, 8));
//        }catch(NumberFormatException e){
//            successandlose[0] = Integer.valueOf(itembook.getItemMeta().getLore().get(1).substring(6, 7));
//        }
//        //获取失败率
//        try{
//            successandlose[1] = Integer.valueOf(itembook.getItemMeta().getLore().get(2).substring(6, 8));
//        }catch(NumberFormatException e){
//            successandlose[1] = Integer.valueOf(itembook.getItemMeta().getLore().get(2).substring(6, 7));
//        }
        NBTItem nbtItem = new NBTItem(itembook);
        successandlose[0] = nbtItem.getInteger("success");
        successandlose[1] = nbtItem.getInteger("breakage");
    }
    //根据待强化武器的等级来判断需要减几级
    public int judgeEnchantmentRemoveLevel(int level){
        return level/50 + 1;
    }
}
