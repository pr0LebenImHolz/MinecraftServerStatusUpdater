package com.github.fivekwbassmachine.minecraftserverstatusupdater.util;

import java.io.*;
import java.net.*;

/**
 * Some utils all around HTTP requests.
 * @author 5kWBassMachine
 * @version 1.0.0
 */
public class HttpUtils {

    private static final String CHARSET = "UTF-8";

    /**
     * Performs an HTTP request.
     * @param url The url with query.
     * @param method The HTTP request method to access the server with.
     * @return The response from the server.
     * @throws IOException
     * @since 1.0.0
     */
    public static HttpResponse request(String url, RequestMethod method) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(method.name());
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuffer lines = new StringBuffer();
        while ((line = in.readLine()) != null) {
            lines.append(line);
        }
        in.close();
        return new HttpResponse(con.getResponseCode(), lines.toString());
    }

    /**
     * Encodes a String with UTF-8 charset (depends on {@link HttpUtils#CHARSET}).
     * @param s The String to encode.
     * @return The encoded String.
     * @since 1.0.0
     */
    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Decodes a String with UTF-8 charset (depends on {@link HttpUtils#CHARSET}).
     * @param s The String to decode.
     * @return The decoded String.
     * @since 1.0.0
     */
    public static String urlDecode(String s) {
        try {
            return URLDecoder.decode(s, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
}
