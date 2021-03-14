package com.github.fivekwbassmachine.minecraftserverstatusupdater.util;

/**
 * @author 5kWBassMachine
 * @version 1.0.0
 */
public class Exception extends java.lang.Exception {

    public static final Exception UNKNOWN = new Exception("0x00", "Unknown Exception");
    public static final Exception NOT_IMPLEMENTED = new Exception("0x01", "Not Implemented");
    public static final Exception API_CREDENTIALS_EMPTY = new Exception("0x10", "Can't Connect To API: Missing Credentials");
    public static final Exception API_CREDENTIALS_INVALID = new Exception("0x11", "Can't Connect To API: Invalid Credentials");
    public static final Exception API_TOKEN_INVALID = new Exception("0x12", "Can't Connect To API: Invalid Token");
    public static final Exception API_URL_INVALID = new Exception("0x13", "Can't Connect To API: Invalid Token");
    public static final Exception API_CONNECTION_ERROR = new Exception("0x14", "Can't Connect To API: Connection Failure");
    public static final Exception API_VERSION = new Exception("0x15", "Can't Connect To API: Incompatible API Version");
    public static final Exception API_SSL_HANDSHAKE_EXCEPTION = new Exception("0x16", "Can't Connect To API: SSLHandshakeException: Self-Signed Certificates Are Not Supported ;)");
    public static final Exception API_NOT_INITIALIZED = new Exception("0x20", "Can't Update Status: API Not Initialized. Check Logs, Configs And Restart The Server");
    public static final Exception UPDATE_EXCEPTION = new Exception("0x21", "Can't Update Status: %e");
    public static final Exception REGISTER_PLAYER_EVENTS_EXCEPTION = new Exception("0x22", "Didn't Register PlayerEvents To Save Resources: API Not Initialized. Check Logs, Configs And Restart The Server");

    public static final Exception SUCCESS_API_CONNECTION = new Exception("1x14", "Connected To API: Performed Request");
    public static final Exception SUCCESS_API_VERSION = new Exception("1x15", "Connected To API: Compatible API Version");
    public static final Exception SUCCESS_UPDATED = new Exception("1x21", "Updated Status");
    public static final Exception SUCCESS_REGISTER_PLAYER_EVENTS = new Exception("1x22", "Registered PlayerEvents");

    public final String code;
    public final String message;

    public Exception(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return code + " " + message;
    }
}
