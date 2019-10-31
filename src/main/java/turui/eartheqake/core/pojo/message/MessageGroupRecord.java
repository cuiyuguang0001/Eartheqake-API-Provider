package turui.eartheqake.core.pojo.message;

public class MessageGroupRecord {

    //分组编号
    private String gid;
    //用户编号
    private String uid;
    //未读消息数量
    private String count;
    //最新消息内容
    private String content;
    //最新消息时间
    private String dateline;
    //分组信息
    private MessageGroup data;

    public MessageGroup getData() {
        return data;
    }

    public void setData(MessageGroup data) {
        this.data = data;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
