//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.prediction;

public interface ProbabilityModel {
    double[] generateActual();

    double[] generatePredict(double var1, double var3);
}
