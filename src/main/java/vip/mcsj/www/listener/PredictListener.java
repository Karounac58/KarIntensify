//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.listener;

import java.util.Arrays;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import vip.mcsj.www.events.GetIntensifyBookEvent;
import vip.mcsj.www.function.PlayerSettings;
import vip.mcsj.www.object.PlayerParams;
import vip.mcsj.www.prediction.PlayerRandomCache;
import vip.mcsj.www.prediction.models.SinModel;

public class PredictListener implements Listener {
    public PredictListener() {
    }

    @EventHandler
    public void onGetBook(GetIntensifyBookEvent e) {
        List<double[]> doubles = generateValues(e.getPlayer());
        PlayerRandomCache.PerturbationData pd = new PlayerRandomCache.PerturbationData(System.currentTimeMillis(), ((double[])doubles.get(0))[0], ((double[])doubles.get(1))[0]);
        PlayerRandomCache.cache.put(e.getPlayer().getUniqueId(), pd);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        List<double[]> doubles = generateValues(e.getPlayer());
        PlayerRandomCache.PerturbationData pd = new PlayerRandomCache.PerturbationData(System.currentTimeMillis(), ((double[])doubles.get(0))[0], ((double[])doubles.get(1))[0]);
        System.out.println(((double[])doubles.get(0))[0]);
        System.out.println(((double[])doubles.get(1))[0]);
        PlayerRandomCache.cache.put(e.getPlayer().getUniqueId(), pd);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        PlayerSettings.saveSinglePlayerSettings(e.getPlayer());
    }

    public static List<double[]> generateValues(Player player) {
        SinModel sm = new SinModel();
        PlayerParams params = PlayerSettings.getParams(player.getUniqueId());
        double[] actualValues = sm.generateActual();
        double[] predictValues = sm.generatePredict(params.getParam1(), params.getParam2());
        return Arrays.asList(predictValues, actualValues);
    }
}
