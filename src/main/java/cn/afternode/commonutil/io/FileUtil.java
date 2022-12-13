package cn.afternode.commonutil.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static String readString(File file, Charset c) throws IOException {
        return new String(readBytes(file), c);
    }

    /**
     * Read file with UTF-8 charsets and convert to string
     */
    public static String readString(File file) throws IOException {
        return readString(file, StandardCharsets.UTF_8);
    }

    public static String utfString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] readBytes(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }
    
    public static void writeBytes(byte[] bytes, File file) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
        bos.write(bytes);
        bos.close();
    }
}
