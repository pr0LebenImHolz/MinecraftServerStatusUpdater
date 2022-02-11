package com.github.fivekwbassmachine.minecraftserverstatusupdater;

import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.Exception;
import com.github.fivekwbassmachine.minecraftserverstatusupdater.util.*;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.logging.log4j.Logger;

/**
 * The API to communicate with the bot.
 * @author 5kWBassMachine
 * @version 1.0.0
 */
public class API {

    private static final String VERSION = "1.0.0";

    private final String url;

    /**
     * Initiates the API with credentials;<br>
     * Credentials: <code>token@url</code><br>
     * URL: <code>https://example.com/foo/bar</code>
     * @param credentials The credentials.
     * @throws com.github.fivekwbassmachine.minecraftserverstatusupdater.util.Exception
     * @since 1.0.0
     */
    public API(String credentials) throws Exception {
        try {
            String[] strings = credentials.split("@");
            if (strings.length != 2 || strings[0].isEmpty() || strings[1].isEmpty()) throw Exception.ERROR_API_CREDENTIALS_INVALID;
            this.url = strings[1] + "?token=" + strings[0] + "&target=";
        }
        catch (java.lang.Exception e) {
            throw Exception.ERROR_API_CREDENTIALS_INVALID;
        }
        checkVersion();
    }

    /**
     * Performs an HTTP request.
     * @param url The url with query.
     * @param method The request method.
     * @return An object containing the HTTP response code and the response body.
     * @throws IOException
     * @since 1.0.0
     */
    private HttpResponse request(String url, RequestMethod method) throws IOException, Exception {
        try {
            return HttpUtils.request(url, method);
        } catch (MalformedURLException e) {
            throw Exception.ERROR_API_URL_INVALID;
        }
    }

    /**
     * Checks whether the mod API version matches with the server API version.
     * @return true when it matches, false otherwise.
     * @throws com.github.fivekwbassmachine.minecraftserverstatusupdater.util.Exception
     * @since 1.0.0
     */
    private boolean checkVersion() throws Exception {
        try {
            HttpResponse response = request(url + "version", RequestMethod.GET);
            if (response.getCode() == 200) {
                return response.getResponse().equals(VERSION);
            } else {
                throw Exception.ERROR_API_VERSION;
            }
        } catch (SSLHandshakeException e) {
            e.printStackTrace();
            throw (Exception.ERROR_API_TLS);
        } catch (IOException e) {
            throw Exception.ERROR_API_CONNECTION;
        }
    }

    /**
     * Sends the passed status to the API.
     * @param status The server status to pass.
     * @throws Exception
     * @since 1.0.0
     */
    public void setStatus(ServerStatus status) throws Exception {
        try {
            if (request(url + "update&status=" + status.name(), RequestMethod.POST).getCode() != 204) throw Exception.ERROR_API_CONNECTION;
        } catch (IOException e) {
            e.printStackTrace();
            throw Exception.ERROR_API_CONNECTION;
        }
    }
}
