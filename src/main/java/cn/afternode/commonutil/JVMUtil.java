package cn.afternode.commonutil;

import cn.afternode.commonutil.error.NMSLException;
import cn.afternode.commonutil.reflection.ReflectionUtil;

import java.lang.reflect.Method;

public class JVMUtil {
    /**
     * Exit JVM
     * Not works in FML
     * @param code Exit code
     */
    public static void forceExit(int code) {
        Runtime.getRuntime().halt(code);
    }

    /**
     * Exit java with FMLCommonHandler
     * If you use this and this is not a Minecraft Forge environment, NMSL
     */
    public static void fmlExit(int code, boolean force) throws NMSLException {
        Class<?> fml = ReflectionUtil.findClass("cpw.mods.fml.common.FMLCommonHandler");
        if (fml == null) throw new NMSLException();

        try {
            Method exitJava = fml.getMethod("exitJava", int.class, boolean.class);
            exitJava.setAccessible(true);
            exitJava.invoke(null, code, force);
        } catch (Throwable t) {
            throw new NMSLException();
        }
    }
}
