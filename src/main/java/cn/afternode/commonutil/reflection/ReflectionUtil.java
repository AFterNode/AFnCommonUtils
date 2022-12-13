package cn.afternode.commonutil.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil {
    public static <T> T createInstanceNoArguments(Class<T> clazz)
            throws NoSuchMethodException,
            InvocationTargetException,
            InstantiationException, 
            IllegalAccessException {
        Constructor<T> cons = clazz.getDeclaredConstructor();
        if (!cons.isAccessible()) cons.setAccessible(true);
        return cons.newInstance();
    }
}
