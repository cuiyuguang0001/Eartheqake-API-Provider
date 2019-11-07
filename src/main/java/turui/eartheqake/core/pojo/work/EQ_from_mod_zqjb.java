package turui.eartheqake.core.pojo.work;

//zf_eq_from_mod_zqjb表
public class EQ_from_mod_zqjb {

    //主键
    private int id;
    //行政区
    private String xzq;
    //详细名称
    private String xxmc;
    //经度
    private String jd;
    //纬度
    private String wd;
    //灾情类型
    private String zqlx;
    //灾情情况
    private String zhqk;
    //附件是否存在
    private String fj;
    //json
    private String data;

    //用于record表联动
    private String mid;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXzq() {
        return xzq;
    }

    public void setXzq(String xzq) {
        this.xzq = xzq;
    }

    public String getXxmc() {
        return xxmc;
    }

    public void setXxmc(String xxmc) {
        this.xxmc = xxmc;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getZqlx() {
        return zqlx;
    }

    public void setZqlx(String zqlx) {
        this.zqlx = zqlx;
    }

    public String getZhqk() {
        return zhqk;
    }

    public void setZhqk(String zhqk) {
        this.zhqk = zhqk;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }
}
