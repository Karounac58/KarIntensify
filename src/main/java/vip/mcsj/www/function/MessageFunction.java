//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.function;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.file.FileConfiguration;

public class MessageFunction {
    public static Map<String, String> messages = new HashMap();

    public MessageFunction() {
    }

    public static void initMessages() throws Exception {
        initMessageYaml();
        FileConfiguration messageYaml = FileFunction.getCustomYaml("message.yml");
        if (messageYaml == null) {
            throw new Exception("message.yml未正确初始化，请联系作者!");
        } else {
            for(String key : messageYaml.getKeys(false)) {
                messages.put(key, messageYaml.getString(key));
            }

        }
    }

    private static void initMessageYaml() {
        FileFunction.initCustomYaml("message.yml");
    }
}
