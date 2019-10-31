package turui.eartheqake.core.pojo.message;

/**
 * 通知信息分组实体类
 */
public class MessageGroup {


    //分组编号
    private String gid;
    //消息分组英文名
    private String name;
    //消息分组名
    private String title;
    //状态
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public String toString() {
        return "MessageGroupFollow{" +
//                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
