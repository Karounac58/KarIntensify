//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.prediction.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;

public class RandomUtil {
    public static double dailyAmplitude;
    public static double dailyPhase;

    public RandomUtil() {
    }

    public static double generateAmplitude() {
        long seed = LocalDate.now().toEpochDay();
        Random random = new Random(seed);
        double randomValue = 0.2 + random.nextDouble() * 0.3;
        if (randomValue == (double)0.0F) {
            randomValue = 0.4;
        }

        BigDecimal bd = (new BigDecimal(randomValue)).setScale(2, RoundingMode.DOWN);
        dailyAmplitude = bd.doubleValue();
        return randomValue;
    }

    public static double generatePhase() {
        long seed = LocalDate.now().toEpochDay();
        Random random = new Random(seed);
        double randomValue = (double)random.nextInt(629) * 0.01;
        dailyPhase = randomValue;
        return randomValue;
    }
}
