package com.github.fivekwbassmachine.minecraftserverstatusupdater;

import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.ServerStatus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * All events triggered by players.<br>
 * @author 5kWBassMachine
 * @version 1.0.0
 */
public class PlayerEvents {

    /**
     * @since 1.0.0
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        ServerStatusUpdater.updateStatus(ServerStatus.RUNNING);
    }

    /**
     * @since 1.0.0
     */
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerStatusUpdater.updateStatus(ServerStatus.RUNNING);
    }
}
