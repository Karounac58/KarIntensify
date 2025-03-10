//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.prediction.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import vip.mcsj.www.prediction.ProbabilityModel;
import vip.mcsj.www.prediction.utils.ClampValues;
import vip.mcsj.www.prediction.utils.RandomUtil;

public class SinModel implements ProbabilityModel {
    public SinModel() {
    }

    public double[] generateActual() {
        Random random = new Random();
        double baseSuccess = (double)50.0F;
        double timeFactor = RandomUtil.dailyAmplitude * Math.sin((double)System.currentTimeMillis() / (double)60000.0F + RandomUtil.dailyPhase) * (double)100.0F;
        double randomNoise = (double)(random.nextInt(100) - 50) * 0.1;
        double base = baseSuccess + timeFactor + randomNoise;
        double breakRate = Math.random() * (double)100.0F;
        BigDecimal baseBD = (new BigDecimal(base)).setScale(2, RoundingMode.DOWN);
        BigDecimal bRBD = (new BigDecimal(breakRate)).setScale(2, RoundingMode.DOWN);
        return ClampValues.clampValues(baseBD.doubleValue(), bRBD.doubleValue());
    }

    public double[] generatePredict(double amplitude, double phase) {
        new Random();
        double baseSuccess = (double)50.0F;
        double timeFactor = amplitude * Math.sin((double)System.currentTimeMillis() / (double)60000.0F + phase) * (double)100.0F;
        double predictSuccess = baseSuccess + timeFactor;
        double predictBreak = Math.random() * (double)100.0F;
        BigDecimal pSBD = (new BigDecimal(predictSuccess)).setScale(2, RoundingMode.DOWN);
        BigDecimal pBBD = (new BigDecimal(predictBreak)).setScale(2, RoundingMode.DOWN);
        return ClampValues.clampValues(pSBD.doubleValue(), pBBD.doubleValue());
    }
}
