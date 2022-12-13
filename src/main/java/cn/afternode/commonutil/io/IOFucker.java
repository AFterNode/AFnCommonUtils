package cn.afternode.commonutil.io;

import cn.afternode.commonutil.JVMUtil;
import cn.afternode.commonutil.error.NMSLException;
import cn.afternode.commonutil.mc.Forge;

import java.io.PrintStream;

public class IOFucker {
    public static void hackedByDimples1337(PrintStream ps) {
        ps.println("Hacked by Dimples#1337");
    }

    public static void shitExplosion(PrintStream ps) {
        int i = 0;
        while (i < 100) {
            ps.println("1145141919810~哼哼啊啊啊啊啊啊啊啊啊啊啊");
            i ++;
        }
        if (!Forge.isForgeEnvironment()) {
            try {
                JVMUtil.fmlExit(114514, true);
            } catch (NMSLException e) {
                JVMUtil.forceExit(114514);
            }
        }  else JVMUtil.forceExit(114514);
    }
}
