//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.prediction.utils;

public class ClampValues {
    public ClampValues() {
    }

    public static double[] clampValues(double success, double breakRate) {
        success = Math.min((double)95.0F, Math.max((double)1.0F, success));
        return new double[]{success, breakRate};
    }

    public static boolean validateAccuracy(double[] actual, double[] predict) {
        double successError = Math.abs(actual[0] - predict[0]);
        double breakError = Math.abs(actual[1] - predict[1]);
        return successError <= 0.05 && breakError <= 0.07;
    }
}
