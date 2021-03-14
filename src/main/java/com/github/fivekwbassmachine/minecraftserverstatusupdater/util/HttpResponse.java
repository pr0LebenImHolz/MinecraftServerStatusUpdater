package com.github.fivekwbassmachine.minecraftserverstatusupdater.util;

/**
 * The response from {@link HttpUtils#request(String, RequestMethod)}.
 * @author 5kWBassMachine
 * @version 1.0.0
 */
public class HttpResponse {

    private final int code;
    private final String response;

    /**
     * Creates a new response with a HTTP response code and the response body.
     * @param code The HTTP response code.
     * @param response The response body.
     * @since 1.0.0
     */
    HttpResponse(int code, String response) {
        this.code = code;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public String getResponse() {
        return response;
    }
}
