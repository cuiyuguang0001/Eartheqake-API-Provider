package turui.eartheqake.core.pojo.file;

//zf_file_follow表
public class ZF_file_follow {

    //主键
    private int id;
    //file表编号
    private String fid;
    //表名
    private String mod;
    //表名中表编号
    private String mid;
    //时间戳
    private String dateline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
