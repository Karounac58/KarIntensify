//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.prediction;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerRandomCache {
    public static Map<UUID, PerturbationData> cache = new ConcurrentHashMap();

    public PlayerRandomCache() {
    }

    public static class PerturbationData {
        long timestamp;
        double predictValue;
        double actualValue;

        public PerturbationData(long timestamp, double predictValue, double actualValue) {
            this.timestamp = timestamp;
            this.predictValue = predictValue;
            this.actualValue = actualValue;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public double getPredictValue() {
            return this.predictValue;
        }

        public void setPredictValue(double predictValue) {
            this.predictValue = predictValue;
        }

        public double getActualValue() {
            return this.actualValue;
        }

        public void setActualValue(double actualValue) {
            this.actualValue = actualValue;
        }
    }
}
