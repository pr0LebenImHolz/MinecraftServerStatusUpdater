package com.github.fivekwbassmachine.minecraftserverstatusupdater.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Some Utils for the filesystem.
 * @author 5kWBassMachine
 * @version 1.0.1
 */
public class FileUtils {

    /**
     * Reads the contents of a file and creates it if it doesn't exist.
     * @param f The file to read.
     * @return The content of the file.
     * @throws IOException {@link File#createNewFile()} {@link Files#newBufferedReader(Path)} {@link BufferedReader#readLine()}
     * @since 1.0.0
     */
    public static String readFile(File f) throws IOException {
        if (!f.exists()) {
            f.createNewFile();
            return "";
        }
        Charset charset = StandardCharsets.UTF_8;
        BufferedReader reader = Files.newBufferedReader(f.toPath(), charset);
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) sb.append(line);
        return sb.toString();
    }

    /**
     * Writes the string to a file.
     * @param f The file to write to.
     * @param s The string to write.
     * @throws IOException {@link Files#newBufferedReader(Path)} {@link BufferedWriter#write(String, int, int)}
     * @since 1.0.0
     */
    public static void writeFile(File f, String s) throws IOException {
        if (f.exists()) f.createNewFile();
        Charset charset = StandardCharsets.UTF_8;
        BufferedWriter writer = Files.newBufferedWriter(f.toPath(), charset);
        writer.write(s, 0, s.length());
    }

    /**
     * Removes leading and tailing spaces and line breaks from a String.
     * @param s The String with spaces or line breaks.
     * @return The modified String.
     * @since 1.0.1
     */
    public static String removeSpace(String s) {
        return s.replaceAll("(^([ \n]+))|(([ \n]+)$)", "");
    }
}
