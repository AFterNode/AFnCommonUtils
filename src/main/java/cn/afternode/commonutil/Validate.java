package cn.afternode.commonutil;

public class Validate {
    public static void notNull(Object v, String m) {
        if (v == null) {
            System.out.println(m);
        }
    }

    public static void isTrue(boolean v, String m) {
        if (v) {
            System.out.println(m);
        }
    }
}
