package com.github.fivekwbassmachine.minecraftserverstatusupdater;

import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.Exception;
import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.FileUtils;
import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.ServerStatus;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.*;

import java.io.File;
import java.io.IOException;

/**
 * Main Class
 * @author 5kWBassMachine
 * @version 1.0.0
 */
@Mod(modid = ServerStatusUpdater.MOD_ID, version = ServerStatusUpdater.VERSION, acceptableRemoteVersions = "*", name = ServerStatusUpdater.NAME)
public class ServerStatusUpdater {
    public static final String MOD_ID = "serverstatusupdater";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "ServerStatusUpdater";

    public static ServerStatusUpdater INSTANCE;

    private API api;
    private boolean debugging;

    /**
     * Requests the API to update the server status.
     * @param status The status.
     * @since 1.0.0
     */
    public static void updateStatus(ServerStatus status) {
        if (INSTANCE.api == null) {
            System.err.println(Exception.API_NOT_INITIALIZED);
        }
        else {
            try {
                INSTANCE.api.setStatus(status);
                System.out.println(Exception.SUCCESS_UPDATED);
            } catch (Exception e) {
                System.err.println(Exception.UPDATE_EXCEPTION.toString().replaceAll("%e", e.toString()));
                if (INSTANCE.debugging) e.printStackTrace();
            }
        }
    }

    /**
     * @since 1.0.0
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException {
        // Read config
        INSTANCE = this;
        debugging = true;
        File file = new File("./config/ServerStatusUpdater.txt");
        String credentials = FileUtils.readFile(file);
        credentials = FileUtils.removeSpace(credentials);
        if (credentials.isEmpty()) {
            System.err.println(Exception.API_CREDENTIALS_EMPTY);
        }
        else {
            try {
                api = new API(credentials);
            }
            catch (Exception e) {
                System.err.println(e);
            }

        }
        updateStatus(ServerStatus.STARTING);
    }

    /**
     * @since 1.0.0
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (api == null) {
            System.err.println(Exception.REGISTER_PLAYER_EVENTS_EXCEPTION);
        }
        else {
            FMLCommonHandler.instance().bus().register(new PlayerEvents());
            System.out.println(Exception.SUCCESS_REGISTER_PLAYER_EVENTS);
        }
    }

    /**
     * @since 1.0.0
     */
    @EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        updateStatus(ServerStatus.RUNNING);
    }

    @EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        updateStatus(ServerStatus.STOPPING);
    }

    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        updateStatus(ServerStatus.STOPPED);
    }
}
