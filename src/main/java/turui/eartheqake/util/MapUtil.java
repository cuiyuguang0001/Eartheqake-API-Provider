package turui.eartheqake.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MapUtil {

    /**
     * 用于普通返回
     * @param list
     * @param msg
     * @param ret
     * @return
     */
    public static Map<String, Object> requestMap(List list, String msg, int ret)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("msg", msg);
        map.put("ret", ret);
        return map;
    }

    /**
     * 用于普通单条信息返回
     * @param o
     * @param msg
     * @param ref
     * @return
     */
    public static Map<String, Object> requestMap(Object o, String msg, int ref)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("data", o);
        map.put("msg", msg);
        map.put("ret", ref);
        return map;
    }

//    public static Map<String, Object> requestMap(List list, String msg, int ret, int count)
//    {
//        Map<String, Object> map = new HashMap<>();
//        map.put("data", list);
//        map.put("msg", msg);
//        map.put("ret", ret);
//        map.put("count", count);
//        return map;
//    }

    /**
     * 用于返回查询接口
     * @param list
     * @param msg
     * @param ret
     * @param count
     * @return
     */
    public static Map<String, Object> requestMap(List list, String msg, int ret, int count)
    {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapList = new HashMap<>();
        mapList.put("list", list);
        mapList.put("count", count);
        map.put("data", mapList);
        map.put("msg", msg);
        map.put("ret", ret);
        return map;
    }

    /**
     * 用于返回更新或添加的操作状态
     * @param msg
     * @param ret
     * @param status
     * @param tip
     * @return
     */
    public static Map<String, Object> requestUpdateMap(String msg, int ret, int status, String tip)
    {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapList = new HashMap<>();
        mapList.put("status", status);
        mapList.put("tip", tip);
        map.put("data", mapList);
        map.put("msg", msg);
        map.put("ret", ret);
        return map;
    }

    public static Map<String ,Object> requestUserMap(Map data, String msg, int ret)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("msg", msg);
        map.put("ret", ret);
        return map;
    }

}
