import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @author sunyongjun
 * @since 2019/9/24
 */
public class DecryptTool {
    public static void main(String[] args) throws Exception {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJs/16nF9IIuPiY8d08rmoHpikzJ9KVajWPZz7YdVegjcjZk6/S6C/dbmbThFqPB+ajG0zPtOSj+PqRwSFZs660CAwEAAQ==";
        String password = "XUFHdSojCeKkZw5odxA047jFZPmfFNCLFWhY7tQxQQKOxXP6HE3RZqDRhWwUWZ38mGxkGrJC3fCpci76bNwk/g==";
        System.out.println(ConfigTools.decrypt(publicKey, password));
    }
}
