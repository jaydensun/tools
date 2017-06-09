import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 089245 on 2017/5/9.
 */
public class Test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("/(\\w+)");
        Matcher matcher = pattern.matcher("/machine/amaa/111");
        List<String> nodes = new ArrayList<>();
        while (matcher.find()) {
            nodes.add(matcher.group(1));
        }
        System.out.println(nodes);
    }
}
