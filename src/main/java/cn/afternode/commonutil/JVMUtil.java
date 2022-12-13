package cn.afternode.commonutil;

public class JVMUtil {
    /**
     * Exit JVM
     * Not works in FML
     * @param code Exit code
     */
    public static void forceExit(int code) {
        Runtime.getRuntime().halt(code);
    }
}
