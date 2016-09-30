import java.util.List;

public class ShellTest {
    public static void main(String[] args) throws Exception {
        List<String> strings = ShellUtil.runShell("atop -n 5 2");
        strings.forEach(System.out::println);

//        long pid = ShellUtil.runShellReturnPid("java -cp .:lib/* Printer", new String[]{"a=222"}, "/app/test/testshellcmd");
//        System.out.println("pid = " + pid);
//        System.out.println();
//        System.out.println(System.getenv());
//        System.out.println(ShellUtil.findProcessByPid(65093, 40022, 62136));
//        System.out.println();
//        System.out.println(NetHogs.netLoad());
    }
}
