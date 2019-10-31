package turui.eartheqake.core.pojo.message;

/**
 * 灾情急报信息实体类
 */
public class MessageRecord {

    //主键
    private String id;
    //用户编号
    private String uid;
    //消息编号
    private String mid;
    //分组编号
    private String gid;
    //接收信息时间戳
    private String dateline;
    //阅读时间
    private String readtime = "0";
    //一条消息信息
    private Message data;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getReadtime() {
        return readtime;
    }

    public void setReadtime(String readtime) {
        this.readtime = readtime;
    }

    public Message getData() {
        return data;
    }

    public void setData(Message data) {
        this.data = data;
    }
}
