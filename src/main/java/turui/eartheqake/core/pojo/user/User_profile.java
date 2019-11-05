package turui.eartheqake.core.pojo.user;

public class User_profile {

    //主键
    private String id;
    //用户编号
    private String uid;
    //昵称
    private String nickname;
    //头像
    private String headimg;
    //简介
    private String desc;

    public User_profile() {
    }

    public User_profile(String nickname, String headimg, String desc) {
        this.nickname = nickname;
        this.headimg = headimg;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String udesc) {
        this.desc = udesc;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
}
