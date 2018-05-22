import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;

/**
 * Created by 089245 on 2017/11/3.
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        for (int i = 0; i < 1; i++) {
            Element data = root.addElement("data").addAttribute("id", String.valueOf(i));
            Element main = data.addElement("main");
            for (int j = 0; j < 80; j++) {
                main.addElement("字段" + j).setText("字段的值字段的值" + j);
            }
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        String fileName = "C:\\Users\\Administrator\\Desktop\\xml\\xml-writer.xml";
        XMLWriter xmlWriter = new XMLWriter(new FileWriter(fileName), format);
        xmlWriter.write(document);
        xmlWriter.close();

        System.out.println(System.currentTimeMillis() - start);

    }

}
