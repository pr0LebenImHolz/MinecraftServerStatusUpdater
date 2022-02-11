package com.github.fivekwbassmachine.minecraftserverstatusupdater;

import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.Exception;
import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.FileUtils;
import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.ServerStatus;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Main Class
 * @author 5kWBassMachine
 * @version 1.0.0
 */
@Mod(modid = ServerStatusUpdater.MOD_ID, name = ServerStatusUpdater.NAME, version = ServerStatusUpdater.VERSION)
public class ServerStatusUpdater {

    public static final String MOD_ID = "serverstatusupdater";
    public static final String VERSION = "1.0.1";
    public static final String NAME = "ServerStatusUpdater";

    private static Logger logger;
    private static API api;
    private static boolean debugging;

    /**
     * Requests the API to update the server status.
     * @param status The status.
     * @since 1.0.0
     */
    public static void updateStatus(ServerStatus status) {
        if (api == null) {
            logger.error(Exception.ERROR_UPDATE_NO_API);
        }
        else {
            try {
                api.setStatus(status);
                logger.info(Exception.SUCCESS_UPDATE);
            } catch (Exception e) {
                logger.error(Exception.ERROR_UPDATE.toString().replaceAll("%e", e.toString()));
                if (logger.getLevel().isLessSpecificThan(Level.DEBUG)) e.printStackTrace();
            }
        }
    }

    /**
     * @since 1.0.0
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException {
        logger = event.getModLog();
        // Read config
        debugging = true;
        File file = new File("./config/ServerStatusUpdater.txt");
        String credentials = FileUtils.readFile(file);
        credentials = FileUtils.removeSpace(credentials);
        if (credentials.isEmpty()) {
            logger.error(Exception.ERROR_API_CREDENTIALS_EMPTY);
        }
        else {
            try {
                api = new API(credentials);
                logger.info(Exception.SUCCESS_API_VERSION);
            }
            catch (Exception e) {
                logger.error(e);
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
            logger.warn(Exception.ERROR_REGISTER_PLAYER_EVENTS);
        }
        else {
            FMLCommonHandler.instance().bus().register(new PlayerEvents());
            logger.info(Exception.SUCCESS_REGISTER_PLAYER_EVENTS);
        }
    }

    /**
     * @since 1.0.0
     */
    @EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        updateStatus(ServerStatus.RUNNING);
    }

    /**
     * @since 1.0.0
     */
    @EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        updateStatus(ServerStatus.STOPPING);
    }

    /**
     * @since 1.0.0
     */
    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        updateStatus(ServerStatus.STOPPED);
    }
}
