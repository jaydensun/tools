import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by 089245 on 2017/11/3.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        doWork(Test::fileWriter);
        doWork(Test::xmlWriterCompact);
        doWork(Test::xmlWriterPretty);
        doWork(Test::xmlWriter);

        doWork(Test::fileWriter);
        doWork(Test::xmlWriterCompact);
        doWork(Test::xmlWriterPretty);
        doWork(Test::xmlWriter);
    }

    @FunctionalInterface
    public interface Consumer2<T> {
        void accept(T t) throws Exception;
    }

    private static void doWork(Consumer2<Document> documentConsumer) throws Exception {
        long start = System.currentTimeMillis();
        // 第二种方式:创建文档并设置文档的根元素节点
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        // 添加属性
        //        root.addAttribute("data", "zhangsan");
        // 添加子节点:add之后就返回这个元素

        for (int i = 0; i < 10000; i++) {
            Element data = root.addElement("data").addAttribute("id", String.valueOf(i));
            Element main = data.addElement("main");
            for (int j = 0; j < 100; j++) {
                main.addElement("字段" + j).setText("字段的值字段的值" + j);
            }
        }

        documentConsumer.accept(document);

        System.out.println(System.currentTimeMillis() - start);

    }

    private static void xmlWriterCompact(Document document) throws IOException {
        OutputFormat format = OutputFormat.createCompactFormat();
        String fileName = "C:\\Users\\Administrator\\Desktop\\xml\\xml-writer-compact.xml";
        XMLWriter xmlWriter = new XMLWriter(new FileWriter(fileName), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    private static void xmlWriterPretty(Document document) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        String fileName = "C:\\Users\\Administrator\\Desktop\\xml\\xml-writer-pretty.xml";
        XMLWriter xmlWriter = new XMLWriter(new FileWriter(fileName), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    private static void fileWriter(Document document) throws IOException {
        FileWriter out = new FileWriter("C:\\Users\\Administrator\\Desktop\\xml\\file-writer.xml");
        document.write(out);
        out.close();
    }

    private static void xmlWriter(Document document) throws IOException {
        OutputFormat format = new OutputFormat();
        format.setNewlines(false);
        format.setNewLineAfterNTags(2);
        String fileName = "C:\\Users\\Administrator\\Desktop\\xml\\xml-writer.xml";
        XMLWriter xmlWriter = new XMLWriter(new FileWriter(fileName), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }
}
