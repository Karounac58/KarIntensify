package vip.mcsj.www.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.mcsj.www.function.BookDataManager;
import vip.mcsj.www.function.FileDataManager;


import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

public class ShitExecutor implements CommandExecutor{
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
                player.getInventory().addItem(BookDataManager.createAllBookMethod(args[2]));
            } else {
                return false;
            }
        }else if(args[0].equals("getBook")){
            if(args[1] == null || Bukkit.getPlayer(args[1]) == null){
                sender.sendMessage("参数输入有误或此玩家不存在");
                return true;
            }
            String success = FileDataManager.getStringFromDataFile(args[1]+".BookSuccess");
            String breaked = FileDataManager.getStringFromDataFile(args[1]+".BookBreaked");
            sender.sendMessage("此玩家最后一次破碎所用的书成功率为"+success+",破碎率为"+breaked);
        }else if(args[0].equals("getItem")){
            if(!(sender instanceof Player)){
                sender.sendMessage("此命令只能由玩家执行");
                return true;
            }
            if(args[1] == null || Bukkit.getPlayer(args[1]) == null){
                sender.sendMessage("参数输入有误或此玩家不存在");
                return true;
            }
            String itemString = FileDataManager.getStringFromDataFile(args[1]+".item");
            ItemStack item = FileDataManager.itemStackDeserialize(itemString);
            Player p = (Player)sender;
            p.getInventory().addItem(item);
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
        }
        return true;
    }


}
