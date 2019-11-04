package turui.eartheqake.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil {

    public static String getTineLine()
    {
        return (new Date().getTime() + "").substring(0,10);
    }

    private static Date getDateByString(String time) {
        Date date = null;
        if (time == null)
            return date;
        String date_format = "yyyy年MM月dd日 HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(date_format);
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间戳转换方法
     * @param dateline
     * @return
     */
    public static String getShortTime(String dateline) {
        if(dateline.equals("0"))
        {
            dateline = CommonUtil.getTineLine();
        }
        String shortstring = null;
        String time = timestampToStr(Long.valueOf(dateline));
        Date date = getDateByString(time);
        if(date == null) return shortstring;

        long now = Calendar.getInstance().getTimeInMillis();
        long deltime = (now - date.getTime())/1000;
        if(deltime > 365*24*60*60) {
            //shortstring = (int)(deltime/(365*24*60*60)) + "年前";
            return time;
        } else if(deltime > 24*60*60) {
            shortstring = (int)(deltime/(24*60*60)) + "天前";
        } else if(deltime > 60*60) {
            shortstring = (int)(deltime/(60*60)) + "小时前";
        } else if(deltime > 60) {
            shortstring = (int)(deltime/(60)) + "分钟前";
        } else if(deltime > 10) {
            shortstring = deltime + "秒前";
        }else if(deltime >= 0)
        {
            shortstring = "刚刚";
        }
        else {
            shortstring = "未知";
        }
        return shortstring;
    }

    //Timestamp转化为String:
    public static String timestampToStr(long dateline){

        Timestamp timestamp = new Timestamp(dateline*1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//定义格式，不显示毫秒
        return df.format(timestamp);
    }

    /**
     * JSON转换为Map
     * @param jsonStr
     * @return
     */
    public static Map<String, String> parseToMap(String jsonStr){
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        Set<String> jsonKeySet = jsonObj.keySet();
        Map<String, String> resultMap = new HashMap<>();
        Iterator<String> it = jsonKeySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            resultMap.put(key, jsonObj.getString(key));
        }
        return resultMap;
    }

}
