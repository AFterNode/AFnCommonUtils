package cn.afternode.commonutil.jar;

import java.io.File;
import java.io.IOException;

/**
 * Can be use to make a plugin loader
 */
public class JarLoader {
    /**
     * Load a jar with a specified class loader
     * @param f Jar file
     * @param cl Specified class loader
     * @param ignoreErrors Ignore when exception thrown
     * @return The loaded jar
     * @throws IOException Failed loading jar file
     * @throws ClassNotFoundException Failed loading class
     */
    public static ExternalJar loadJar(File f, ClassLoader cl, boolean ignoreErrors) throws IOException, ClassNotFoundException {
        ExternalJar ej = new ExternalJar(f);
        ej.loadAll(cl, ignoreErrors);
        return ej;
    }

    /**
     * Load a jar with system class loader
     * @param f Jar file
     * @param ignoreErrors Ignore when exceptions thrown
     * @return The loaded jar
     */
    public static ExternalJar loadJar(File f, boolean ignoreErrors) throws Exception {
        return loadJar(f, ClassLoader.getSystemClassLoader(), ignoreErrors);
    }
}
