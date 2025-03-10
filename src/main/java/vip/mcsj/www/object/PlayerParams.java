//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.object;

import java.util.UUID;

public class PlayerParams {
    private UUID pUUID;
    private double param1;
    private double param2;

    public PlayerParams() {
    }

    public PlayerParams(UUID pUUID, double param1, double param2) {
        this.pUUID = pUUID;
        this.param1 = param1;
        this.param2 = param2;
    }

    public UUID getPUUID() {
        return this.pUUID;
    }

    public void setPUUID(UUID pUUID) {
        this.pUUID = pUUID;
    }

    public double getParam1() {
        return this.param1;
    }

    public void setParam1(double param1) {
        this.param1 = param1;
    }

    public double getParam2() {
        return this.param2;
    }

    public void setParam2(double param2) {
        this.param2 = param2;
    }
}
