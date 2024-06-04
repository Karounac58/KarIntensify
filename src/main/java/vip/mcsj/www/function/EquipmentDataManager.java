package vip.mcsj.www.function;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EquipmentDataManager {
    //弹射物保护效果
    public static List<Double> getEquipmentReduceInjury(int helmetLevel,int chestplateLevel,int leggingsLevel,int bootsLevel){
        List<Integer> list = Arrays.asList(helmetLevel,chestplateLevel,leggingsLevel,bootsLevel);
        List<Integer> list2 = list.stream().filter(j -> j>0&&j<=4).collect(Collectors.toList());
        //超过原版保护的个数
        int i = list.stream().filter(j -> j>4).collect(Collectors.toList()).size();
        //未超过原版保护的个数
        int j = list2.size();
        int k = 0;
        double reduceInjury = 0;
        //未超过原版保护的等级累加
        for(int l :list2){
            k +=l;
        }
        BigDecimal originalpro = BigDecimal.valueOf(0.0165);
        BigDecimal pro = BigDecimal.valueOf(0.08*(k+i*4));
        BigDecimal pro2 = BigDecimal.valueOf(0.8);
        double proFinally = 0;
        BigDecimal proLevel = BigDecimal.valueOf(helmetLevel + chestplateLevel + leggingsLevel + bootsLevel - i*4 - k);
        if(0.08*(k+i*4) >= 0.8){
            reduceInjury = pro2.add(originalpro.multiply((proLevel)).multiply(BigDecimal.valueOf(0.01))).doubleValue();
            //System.out.println("进入原版抵消伤害80条件");
            proFinally = pro2.doubleValue();
        }else{
            reduceInjury = pro.add(originalpro.multiply((proLevel)).multiply(BigDecimal.valueOf(0.01))).doubleValue();
            //System.out.println("进入原版抵消伤害低于80条件");
            proFinally = pro.doubleValue();
        }
        List<Double> list3 = Arrays.asList(reduceInjury,proFinally);
        //System.out.println("总抵消伤害比例："+reduceInjury);
        //System.out.println("原版抵消伤害比例："+0.08*(k+i*4));
        return list3;
    }

    public static boolean judgeEquipmentOneToTwo(int helmetLevel,int chestplateLevel,int leggingsLevel,int bootsLevel){
        if(helmetLevel > 4 || chestplateLevel > 4 || leggingsLevel > 4 || bootsLevel > 4){
            return true;
        }
        return false;
    }

    //保护效果
    public static List<Double> getProtectionEquipmentReduceInjury(int helmetLevel,int chestplateLevel,int leggingsLevel,int bootsLevel){
        List<Integer> list = Arrays.asList(helmetLevel,chestplateLevel,leggingsLevel,bootsLevel);
        List<Integer> list2 = list.stream().filter(j -> j>0&&j<=5).collect(Collectors.toList());
        //超过原版保护的个数
        int i = list.stream().filter(j -> j>5).collect(Collectors.toList()).size();
        //未超过原版保护的个数
        int j = list2.size();
        int k = 0;
        double reduceInjury = 0;
        //未超过原版保护的等级累加
        for(int l :list2){
            k +=l;
        }
        BigDecimal originalpro = BigDecimal.valueOf(0.0114);
        BigDecimal pro = BigDecimal.valueOf(0.04*(k+i*5));
        BigDecimal pro2 = BigDecimal.valueOf(0.8);
        double proFinally = 0;
        BigDecimal proLevel = BigDecimal.valueOf(helmetLevel + chestplateLevel + leggingsLevel + bootsLevel - i*4 - k);
        if(0.04*(k+i*5) >= 0.8){
            reduceInjury = pro2.add(originalpro.multiply((proLevel)).multiply(BigDecimal.valueOf(0.01))).doubleValue();
//            System.out.println("进入原版抵消伤害80条件");
            proFinally = pro2.doubleValue();
        }else{
            reduceInjury = pro.add(originalpro.multiply((proLevel)).multiply(BigDecimal.valueOf(0.01))).doubleValue();
//            System.out.println("进入原版抵消伤害低于80条件");
            proFinally = pro.doubleValue();
        }
        List<Double> list3 = Arrays.asList(reduceInjury,proFinally);
//        System.out.println("总抵消伤害比例："+reduceInjury);
//        System.out.println("原版抵消伤害比例："+0.04*(k+i*4));
        return list3;
    }
}
