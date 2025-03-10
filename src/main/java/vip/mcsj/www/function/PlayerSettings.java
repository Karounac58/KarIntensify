//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.function;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import vip.mcsj.www.main.KarIntensify;
import vip.mcsj.www.object.PlayerParams;

public class PlayerSettings {
    public static Map<UUID, PlayerParams> pParams = new ConcurrentHashMap();

    public PlayerSettings() {
    }

    public static void getAllPlayerSettings() {
        YamlConfiguration customYaml = FileFunction.getCustomYaml("predict.yml");

        for(String key : customYaml.getKeys(false)) {
            double param1 = customYaml.getDouble(key + ".param1");
            double param2 = customYaml.getDouble(key + ".param2");
            PlayerParams pp = new PlayerParams(UUID.fromString(key), param1, param2);
            pParams.put(UUID.fromString(key), pp);
        }

    }

    public static void getSinglePlayerSettings(Player player) {
        YamlConfiguration customYaml = FileFunction.getCustomYaml("predict.yml");
        if (customYaml.contains(player.getName())) {
            double param1 = customYaml.getDouble(player.getName() + ".param1");
            double param2 = customYaml.getDouble(player.getName() + ".param2");
            PlayerParams params = new PlayerParams(player.getUniqueId(), param1, param2);
            pParams.put(player.getUniqueId(), params);
        } else {
            double param1 = (double)0.0F;
            double param2 = (double)0.0F;
            PlayerParams params = new PlayerParams(player.getUniqueId(), param1, param2);
            pParams.put(player.getUniqueId(), params);
        }

    }

    public static void saveAllPlayerSettings() throws IOException {
        if (!pParams.isEmpty()) {
            Collection<PlayerParams> values = pParams.values();
            File file = new File(KarIntensify.instance.getDataFolder(), "predict.yml");
            YamlConfiguration customYaml = YamlConfiguration.loadConfiguration(file);

            for(PlayerParams value : values) {
                customYaml.set(value.getPUUID() + ".param1", value.getParam1());
                customYaml.set(value.getPUUID() + ".param2", value.getParam2());
            }

            customYaml.save(file);
        }
    }

    public static void saveSinglePlayerSettings(Player player) {
        if (pParams.containsKey(player.getUniqueId())) {
            PlayerParams params = pParams.get(player.getUniqueId());

            try {
                FileFunction.setStringToDataFile("predict.yml", player.getUniqueId() + ".param1", String.valueOf(params.getParam1()));
                FileFunction.setStringToDataFile("predict.yml", player.getUniqueId() + ".param2", String.valueOf(params.getParam2()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static PlayerParams getParams(UUID pUUID) {
        PlayerParams params = pParams.getOrDefault(pUUID, null);
        if (params == null) {
            double param1 = 0.2;
            double param2 = 0.0F;
            PlayerParams params1 = new PlayerParams(pUUID, param1, param2);
            pParams.put(pUUID, params1);
            return params1;
        } else {
            return params;
        }
    }
}
