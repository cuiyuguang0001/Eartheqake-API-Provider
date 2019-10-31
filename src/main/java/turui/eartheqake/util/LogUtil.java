package turui.eartheqake.util;

import java.util.logging.Logger;

public class LogUtil {

    private static Logger log = Logger.getLogger("tesglog");

    public static void doLog(String str)
    {
        log.info(str);
    }

}
