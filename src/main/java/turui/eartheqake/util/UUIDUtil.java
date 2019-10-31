package turui.eartheqake.util;

import java.util.UUID;

public class UUIDUtil {

    public static String getUUID()
    {
        return UUID.randomUUID().toString().substring(0,8);
    }

}
