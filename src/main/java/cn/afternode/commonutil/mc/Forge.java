package cn.afternode.commonutil.mc;

import cn.afternode.commonutil.reflection.ReflectionUtil;

public class Forge {
    public static boolean isForgeEnvironment() {
        return ReflectionUtil.findClass("cpw.mods.fml.common.FMLCommonHandler") != null;
    }
}
