import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * 描述：
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON          REASON
 *  1    2015-11-17       089245          Create
 * ****************************************************************************
 * </pre>
 *
 * @author 089245
 * @since 1.0
 */
public class Printer {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(Printer.class);
    public static void main(String[] args) {
        logger.info("printer syspro = " + System.getProperties());
        logger.info("");
        logger.info(System.getenv().toString());
        int i = 0;
        while (true) {
            logger.info(" " + i++);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
