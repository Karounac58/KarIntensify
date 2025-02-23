package vip.mcsj.www.function;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MessageFunction {
    public static Map<String,String> messages = new HashMap<>();

    public static void initMessages() throws Exception {
        initMessageYaml();
        FileConfiguration messageYaml = FileFunction.getCustomYaml("message.yml");
        if(messageYaml == null){
            throw new Exception("message.yml未正确初始化，请联系作者!");
        }
        Set<String> keys = messageYaml.getKeys(false);
        for (String key : keys) {
            messages.put(key,messageYaml.getString(key));
        }
    }

    private static void initMessageYaml(){
        FileFunction.initCustomYaml("message.yml");
    }
}
