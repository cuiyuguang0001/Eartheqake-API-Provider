package turui.eartheqake.core.pojo.work;

/**
 * zf_eq表
 */
public class EQ {

    //主键编号
    private int id;
    //地震编号
    private int dzid;
    //地震名称
    private String dzmc;
    //时间戳
    private String dateline;
    //地震震级
    private float dzzj;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDzid() {
        return dzid;
    }

    public void setDzid(int dzid) {
        this.dzid = dzid;
    }

    public String getDzmc() {
        return dzmc;
    }

    public void setDzmc(String dzmc) {
        this.dzmc = dzmc;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public float getDzzj() {
        return dzzj;
    }

    public void setDzzj(float dzzj) {
        this.dzzj = dzzj;
    }
}
