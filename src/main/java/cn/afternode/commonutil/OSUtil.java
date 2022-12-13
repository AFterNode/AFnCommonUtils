package cn.afternode.commonutil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OSUtil {
    /**
     * May cause error on not Windows platforms
     * If you are making a authenticator with this, you should obfuscate this class or  method
     * @return Hardware identity md5 digest
     * @throws NoSuchAlgorithmException Md5 digest error
     */
    public static String getHardwareIdentity() throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        String main = System.getenv("PROCESS_IDENTIFIER") + System.getenv("COMPUTERNAME");
        byte[] bytes = main.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5 = md.digest(bytes);
        int i = 0;
        for (byte b: md5) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x300), 0, 3);
            if (i != md5.length - 1) {
                sb.append("-");
            }
            i++;
        }
        return sb.toString();
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }
}
