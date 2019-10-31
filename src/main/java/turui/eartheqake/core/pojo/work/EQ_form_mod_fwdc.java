package turui.eartheqake.core.pojo.work;

//房屋调查表
public class EQ_form_mod_fwdc {

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
    //烈度拟定
    private String ldnd;
    //灾情描述
    private String zqms;
    //群体调查
    private String qtdc;
    //单体调查
    private String dtdc;
    //附件
    private String fj;

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

    public String getLdnd() {
        return ldnd;
    }

    public void setLdnd(String ldnd) {
        this.ldnd = ldnd;
    }

    public String getZqms() {
        return zqms;
    }

    public void setZqms(String zqms) {
        this.zqms = zqms;
    }

    public String getQtdc() {
        return qtdc;
    }

    public void setQtdc(String qtdc) {
        this.qtdc = qtdc;
    }

    public String getDtdc() {
        return dtdc;
    }

    public void setDtdc(String dtdc) {
        this.dtdc = dtdc;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }
}
