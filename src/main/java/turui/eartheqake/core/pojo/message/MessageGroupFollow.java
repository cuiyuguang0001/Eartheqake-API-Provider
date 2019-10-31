package turui.eartheqake.core.pojo.message;

public class MessageGroupFollow {

    //主键
    private String id;
    //分组编号
    private String gid;
    //受众类型 0用户组 1用户
    private String utype;
    //用户组编号或用户编号
    private String uid;
    //状态
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
