package cn.afternode.commonutil.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class WinProcess {
    public static HashMap<String, Integer> getProcesses() throws IOException {
        Process proc = Runtime.getRuntime().exec("chcp 65001 | tasklist");
        BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(proc.getInputStream()), StandardCharsets.UTF_8));
        HashMap<String, Integer> result = new HashMap<>();
        String line;
        while ((line = out.readLine()) != null) {
            try {
                while (line.contains("  ")) line = line.replace("  ", " ");
                String[] data = line.split(" ");
                int pid = Integer.parseInt(data[1]);
                result.put(data[0], pid);
            } catch (Throwable ignored) {
            }
        }
        return result;
    }
}
