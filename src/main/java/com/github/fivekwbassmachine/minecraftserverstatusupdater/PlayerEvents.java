package com.github.fivekwbassmachine.minecraftserverstatusupdater;

import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.ServerStatus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

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
