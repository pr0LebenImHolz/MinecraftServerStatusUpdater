package com.github.fivekwbassmachine.minecraftserverstatusupdater.util;

/**
 * @author 5kWBassMachine
 * @version 1.0.0
 */
public class Exception extends java.lang.Exception {

    // SUCCESS
    public static final Exception SUCCESS_API_CONNECTION = new Exception("0x14", "Connected To API: Performed Request");
    public static final Exception SUCCESS_API_VERSION = new Exception("0x15", "Connected To API: Compatible API Version");
    public static final Exception SUCCESS_UPDATE = new Exception("0x21", "Updated Status");
    public static final Exception SUCCESS_REGISTER_PLAYER_EVENTS = new Exception("0x30", "Registered PlayerEvents");
    // ERROR
    // generic
    public static final Exception ERROR_UNKNOWN = new Exception("1x00", "Unknown Exception");
    public static final Exception ERROR_NOT_IMPLEMENTED = new Exception("1x01", "Not Implemented");
    // api - init
    public static final Exception ERROR_API_CREDENTIALS_EMPTY = new Exception("1x10", "Can't Connect To API: Missing Credentials");
    public static final Exception ERROR_API_CREDENTIALS_INVALID = new Exception("1x11", "Can't Connect To API: Invalid Credentials");
    public static final Exception ERROR_API_URL_INVALID = new Exception("1x12", "Can't Connect To API: Invalid URL");
    public static final Exception ERROR_API_VERSION = new Exception("1x13", "Can't Connect To API: Incompatible API Version");
    // api - calls
    public static final Exception ERROR_API_CONNECTION = new Exception("1x14", "Can't Connect To API: Connection Failure");
    public static final Exception ERROR_API_TLS = new Exception("1x15", "Can't Connect To API: SSLHandshakeException: Self-Signed Certificates Are Not Supported ;)");
    public static final Exception ERROR_UPDATE = new Exception("1x20", "Can't Update Status: %e");
    public static final Exception ERROR_UPDATE_NO_API = new Exception("1x21", "Can't Update Status: API Not Initialized. Check Logs, Configs And Restart The Server");
    public static final Exception ERROR_REGISTER_PLAYER_EVENTS = new Exception("1x30", "Didn't Register PlayerEvents To Save Resources: API Not Initialized. Check Logs, Configs And Restart The Server");

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
