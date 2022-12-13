package cn.afternode.commonutil.jar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class ExternalJar extends JarFile {
    private final List<String> classEntries;
    private final File file;

    private boolean loaded = false;
    private final List<String> loadedEntries = new ArrayList<>();
    private List<Class<?>> classes;

    public ExternalJar(File s) throws IOException {
        super(s);
        file = s;

        Enumeration<JarEntry> entries = entries();

        classEntries = new ArrayList<>();
        while (entries.hasMoreElements()) {
            JarEntry je = entries.nextElement();
            if (je.getName().endsWith(".class")) {
                classEntries.add(je.getName().split("\\.class")[0].replace("/", "."));
            }
        }
    }

    public List<String> getClassEntries() {
        return classEntries;
    }

    public void loadAll(ClassLoader cl, boolean ignoreErrors) throws MalformedURLException, ClassNotFoundException {
        if (loaded) return;
        URLClassLoader ucl = new URLClassLoader(new URL[]{new URL("file:" + file.getAbsolutePath())},
                cl);

        for (String c: classEntries) {
            if (loadedEntries.contains(c)) continue;
            try {
                classes.add(ucl.loadClass(c));
                loadedEntries.add(c);
            } catch (Throwable e) {
                if (!ignoreErrors) throw e;
            }
        }

        loaded = true;
    }

    /**
     * Get the resource as an InputStream
     * Read with ZipEntry, not must be loaded
     * @param path Resource path
     */
    public InputStream getResourceAsStream(String path) throws IOException {
        ZipEntry ze = getEntry(path);
        if (ze == null) return null;
        return getInputStream(ze);
    }

    /**
     * Get the resource as an URL
     * The jar must be loaded
     * @param path Resource path
     */
    public URL getResource(String path) {
        if (!loaded) return null;
        if (classes.size() == 0) return null;
        return classes.get(0).getResource(path);
    }

    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Get all loaded classes
     * Will be null if the jar not loaded
     */
    public List<Class<?>> getClasses() {
        return classes;
    }
}
