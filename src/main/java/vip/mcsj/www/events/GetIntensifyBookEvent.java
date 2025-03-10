//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.mcsj.www.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import vip.mcsj.www.object.IBookType;

public class GetIntensifyBookEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player p;
    private IBookType bookType;

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public GetIntensifyBookEvent(Player p, IBookType bookType) {
        this.p = p;
        this.bookType = bookType;
    }

    public GetIntensifyBookEvent(boolean isAsync, Player p, IBookType bookType) {
        super(isAsync);
        this.p = p;
        this.bookType = bookType;
    }

    public Player getPlayer() {
        return this.p;
    }

    public void setPlayer(Player p) {
        this.p = p;
    }

    public IBookType getBookType() {
        return this.bookType;
    }

    public void setBookType(IBookType bookType) {
        this.bookType = bookType;
    }
}
