import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShellUtil {

    public static List<String> runShell(String shell) {
        return runShell(shell, 0);
    }

    public static List<String> runShell(String shell, int waitSecond) {
        return runShell(shell, null, null, waitSecond);
    }

    public static List<String> runShell(String shell, String[] envp, String dir) {
        return runShell(shell, envp, dir, 0);
    }

    public static List<String> runShell(String shell, String[] envp, String dir, int waitSecond) {
        List<String> strList = new ArrayList<>();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(shell, envp, dir == null ? null : new File(dir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (waitSecond > 0) {
            final Process finalProcess = process;
            new Thread(() -> {
                try {
                    Thread.sleep(waitSecond * 1000);
                } catch (InterruptedException ignored) {
                }
                finalProcess.destroy();
            }).start();
        }
        String line;
        try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            while ((line = input.readLine()) != null) {
                strList.add(line);
            }
        } catch (Exception ignored) {
        }
        return strList;
    }

    public static long getPidOfProcess(Process p) {
        long pid = -1;
        try {
            if (p.getClass().getName().equals("java.lang.UNIXProcess")) {
                Field f = p.getClass().getDeclaredField("pid");
                f.setAccessible(true);
                pid = f.getLong(p);
                f.setAccessible(false);
            }
        } catch (Exception e) {
            pid = -1;
        }
        return pid;
    }


    public static long runShellReturnPid(String shell, String[] envp, String dir) {
        try {
            Process process = Runtime.getRuntime().exec(shell, envp, dir == null ? null : new File(dir));
            return getPidOfProcess(process);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Set<Integer> findProcessByPid(Collection<Integer> pids) {
        String cmd = "ps -o pid= -p";
        StringBuilder sb = new StringBuilder(cmd);
        for (int pid : pids) {
            sb.append(" ").append(pid);
        }
        List<String> rs = runShell(sb.toString());
        Set<Integer> set = new HashSet<>();
        rs.forEach(s -> set.add(Integer.parseInt(s.trim())));
        return set;
    }

}
