package turui.eartheqake.core.pojo.user;

/**
 * 用户实体类
 */
public class User {

    //用户编号
    private String id = "";
    //用户账号
    private String username;
    //用户密码
    private String password;
    //md5随机字符串
    private String salt;
    //用户分组
    private String gid;



    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSlat() {
        return salt;
    }

    public void setSlat(String slat) {
        this.salt = slat;
    }
}
