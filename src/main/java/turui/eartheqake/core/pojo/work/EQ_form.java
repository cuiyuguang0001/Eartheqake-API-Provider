package turui.eartheqake.core.pojo.work;

/**
 * zf_eq_form表
 */
public class EQ_form {

    //主键编号
    private int id;
    //表单主题
    private String title;
    //表单模板
    private String mod;
    //表单排序
    private int list;
    //表单名称
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public int getList() {
        return list;
    }

    public void setList(int list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
