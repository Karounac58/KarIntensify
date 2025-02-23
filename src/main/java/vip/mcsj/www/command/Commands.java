package vip.mcsj.www.command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.mcsj.www.function.FileFunction;
import vip.mcsj.www.function.ItemStackFunction;
import vip.mcsj.www.function.LevelFunction;
import vip.mcsj.www.function.MessageFunction;
import vip.mcsj.www.main.KarIntensify;
import vip.mcsj.www.object.IBookType;
import vip.mcsj.www.object.IntensifyBook;


import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

import static vip.mcsj.www.main.KarIntensify.log;

public class Commands implements CommandExecutor{

    public static Map<String,Enchantment> map = new HashMap<>();
    public static Map<String, IBookType> map2 = new HashMap<>();
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        if(args[0].equals("give")){
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                return false;
            }
            if (args[2] != null) {
                Enchantment enchantment = map.get(args[2]);
                IBookType iBookType = map2.get(args[2]);
                IntensifyBook iBook = ItemStackFunction.intensifyBooks.get(enchantment).get(iBookType);

                IntensifyBook clone = iBook.myClone();
                clone.generateNormalIntensifyBook(iBookType.name().toLowerCase(),enchantment);
                player.getInventory().addItem(clone.getItemStack());

            } else {
                return true;
            }
        }else if(args[0].equals("getBook")){
            if(!(sender instanceof Player)){
                sender.sendMessage("此命令只能由玩家执行");
                return true;
            }
            Player send = (Player) sender;
            if(args[1] == null || Bukkit.getPlayer(args[1]) == null){
                sender.sendMessage("参数输入有误或此玩家不存在");
                return true;
            }
            ItemStack iBItem = FileFunction.itemStackDeserialize(FileFunction.getStringFromCustomFile("data.yml", args[1] + ".Book"));
            send.getInventory().addItem(iBItem);
            IntensifyBook iBook = IntensifyBook.fromItemStack(iBItem);

            String success = String.valueOf(iBook.getSuccess());
            String breaked = String.valueOf(iBook.getDestroy());
            sender.sendMessage("玩家"+args[1]+"最后一次破碎所用的书成功率为"+success+",破碎率为"+breaked);
        }else if(args[0].equals("getItem")){
            if(!(sender instanceof Player)){
                sender.sendMessage("此命令只能由玩家执行");
                return true;
            }
            if(args[1] == null || Bukkit.getPlayer(args[1]) == null){
                sender.sendMessage("参数输入有误或此玩家不存在");
                return true;
            }
            ItemStack iItem = FileFunction.itemStackDeserialize(FileFunction.getStringFromCustomFile("data.yml", args[1] + ".Equipment"));
            Player p = (Player)sender;
            p.getInventory().addItem(iItem);
            p.sendMessage("获取玩家"+args[1]+"的破碎装备成功！");
        }else if(args[0].equals("test")){
            Random rd = new Random();
            int success = Integer.parseInt(args[1]);
            int breaked = Integer.parseInt(args[2]);
            //循环次数
            int number = Integer.parseInt(args[3]);
            int successNumber = 0;
            int BreakedNumber = 0;
            int loseNumber = 0;
            for(int i = 0;i < Integer.parseInt(args[3]);i++) {
                if (rd.nextInt(100) > (99 - success)) {
                    successNumber += 1;
                } else if (rd.nextInt(100) > (99 - breaked)) {
                    BreakedNumber += 1;
                } else {
                    loseNumber += 1;
                }
            }
            sender.sendMessage("试验"+number+"次，成功"+successNumber+"次,破碎"+BreakedNumber+"次，失败"+loseNumber+"次");
        }else if(args[0].equals("set")){
            if(!(sender instanceof Player)){
                sender.sendMessage("此命令只能由玩家执行");
                return true;
            }
            Player p = (Player)sender;
            ItemStack item = p.getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            if(args[1].equals("保护")){
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,Integer.parseInt(args[2]),true);
            }else if(args[1].equals("弹射物保护")){
                itemMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE,Integer.parseInt(args[2]),true);
            }else if(args[1].equals("锋利")){
                itemMeta.addEnchant(Enchantment.DAMAGE_ALL,Integer.parseInt(args[2]),true);
            }else if(args[1].equals("力量")){
                itemMeta.addEnchant(Enchantment.ARROW_DAMAGE,Integer.parseInt(args[2]),true);
            }else if(args[1].equals("耐久")){
                itemMeta.addEnchant(Enchantment.DURABILITY,Integer.parseInt(args[2]),true);
            }
            item.setItemMeta(itemMeta);
        }else if(args[0].equals("reload")){
            try {
                MessageFunction.initMessages();
                ItemStackFunction.initIntensifyItems();
                ItemStackFunction.initIntensifyBooks();
                ItemStackFunction.initProtectEffectAndDebugInfo();
                LevelFunction.initMaxLevels();
                FileFunction.initCustomYaml("data.yml");
                Commands.initMap();
                sender.sendMessage(ChatColor.GREEN+"重载配置文件成功！");
            }catch (Exception e){
                log.warning(String.format("[%s] - 配置文件加载出错，请检查配置文件格式！", KarIntensify.instance.getDescription().getName()));
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void initMap(){
        if(!map.isEmpty()){
            map.clear();
        }
        map.put("锋利",Enchantment.DAMAGE_ALL);
        map.put("保护",Enchantment.PROTECTION_ENVIRONMENTAL);
        map.put("弹射物保护",Enchantment.PROTECTION_PROJECTILE);
        map.put("耐久",Enchantment.DURABILITY);
        map.put("力量",Enchantment.ARROW_DAMAGE);

        map.put("锋利99",Enchantment.DAMAGE_ALL);
        map.put("保护99",Enchantment.PROTECTION_ENVIRONMENTAL);
        map.put("弹射物保护99",Enchantment.PROTECTION_PROJECTILE);
        map.put("耐久99",Enchantment.DURABILITY);
        map.put("力量99",Enchantment.ARROW_DAMAGE);

        map.put("锋利0破",Enchantment.DAMAGE_ALL);
        map.put("保护0破",Enchantment.PROTECTION_ENVIRONMENTAL);
        map.put("弹射物保护0破",Enchantment.PROTECTION_PROJECTILE);
        map.put("耐久0破",Enchantment.DURABILITY);
        map.put("力量0破",Enchantment.ARROW_DAMAGE);

        if(!map2.isEmpty()){
            map2.clear();
        }

        map2.put("锋利",IBookType.NORMAL);
        map2.put("保护",IBookType.NORMAL);
        map2.put("弹射物保护",IBookType.NORMAL);
        map2.put("耐久",IBookType.NORMAL);
        map2.put("力量",IBookType.NORMAL);

        map2.put("锋利99",IBookType.NINETY_NINE);
        map2.put("保护99",IBookType.NINETY_NINE);
        map2.put("弹射物保护99",IBookType.NINETY_NINE);
        map2.put("耐久99",IBookType.NINETY_NINE);
        map2.put("力量99",IBookType.NINETY_NINE);

        map2.put("锋利0破",IBookType.ZERO);
        map2.put("保护0破",IBookType.ZERO);
        map2.put("弹射物保护0破",IBookType.ZERO);
        map2.put("耐久0破",IBookType.ZERO);
        map2.put("力量0破",IBookType.ZERO);
    }
}
