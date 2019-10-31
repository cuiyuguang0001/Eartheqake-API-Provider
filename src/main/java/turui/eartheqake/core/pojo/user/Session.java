package turui.eartheqake.core.pojo.user;

import java.util.Map;

/**
 * 登陆信息实体类
 */
public class Session {


    //验证信息
    private String sid;
    //ip地址
    private String ip;
    //用户编号
    private String uid;
    //登陆时间时间戳
    private String dateline;
    //登陆平台
    private String platform;
    //返回用户信息
    private Map<String, Object> info;

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dataline) {
        this.dateline = dataline;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
