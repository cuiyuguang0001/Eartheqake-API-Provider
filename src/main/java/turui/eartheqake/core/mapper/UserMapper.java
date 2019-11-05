package turui.eartheqake.core.mapper;

import org.apache.ibatis.annotations.*;
import turui.eartheqake.core.pojo.user.Session;
import turui.eartheqake.core.pojo.user.User;
import turui.eartheqake.core.pojo.user.User_group;
import turui.eartheqake.core.pojo.user.User_profile;

@Mapper
public interface UserMapper {

    /**
     * 查询一条session 按sid
     * @param sid
     * @return
     */
    @Select("select sid, ip, uuid, dateline, platform from zf_user_session where sid = #{sid}")
    @Results(value = {
            @Result(column = "uuid", property = "uid")
    })
    public Session sessionModelBySid(String sid);

    /**
     * 查询一条session 按uuid
     * @param uid
     * @return
     */
    @Select("select sid, ip, uuid, dateline, platform from zf_user_session where uuid = #{uid}")
    @Results(value = {
            @Result(column = "uuid", property = "uid")
    })
    public Session sessionModelByUuid(String uid);

    /**
     * 插入一条session
     * @param session
     * @return
     */
    @Insert("insert into zf_user_session(sid, ip, uuid, dateline, platform) values(#{sid}, #{ip}, #{uid}, #{dateline}, #{platform})")
    public boolean sessionAdd(Session session);

    /**
     * 删除一条session，也就是用户登出
     * @param session
     * @return
     */
    @Delete("delete from zf_user_session where sid = #{sid}")
    public boolean sessionRemove(Session session);

    /**
     * 查找是否是注册用户
     * @param user
     * @return
     */
    @Select("select id, username, gid, password, salt from zf_user where username = #{username} or id = #{id}")
    public User login(User user);

    /**
     * 更新一条session登陆信息（设置uuid）
     * @param uid
     * @param sid
     */
    @Update("update zf_user_session set uuid = #{uid} where sid = #{sid}")
    public void sessionEditUuid(String uid, String sid);

    /**
     * 查询一个用户group表信息
     * @param id
     * @return
     */
    @Select("select id,name,list from zf_user_group where id = #{id}")
    User_group groupModel(String id);

    /**
     * 查询一个用户profile表信息
     * @param uid
     * @return
     */
    @Select("select id, uuid, nickname, headimg, udesc from zf_user_profile where uuid = #{uid}")
    @Results(value = {
            @Result(column = "uuid", property = "uid"),
            @Result(column = "udesc", property = "desc")
    })
    User_profile profileModel(String uid);

    /**
     * 修改用户密码
     * @param id
     * @param pwd
     * @return
     */
    @Update("update zf_user set username = #{username}, password = #{pwd}, salt = #{salt} where id = #{id}")
    boolean updateUserPwd(String id, String pwd, String salt, String username);

    /**
     * 修改用户信息
     * @param user_profile
     * @return
     */
    @Update("<script>" +
            "update zf_user_profile<set>" +
            "<if test='nickname != null'>nickname = #{nickname}, </if>" +
            "<if test='headimg != null'>headimg = #{headimg}, </if>" +
            "<if test='desc != null'>udesc = #{desc}</if>" +
            "</set>" +
            "where uuid = #{uid}" +
            "</script>")
    boolean userProfileEdit(User_profile user_profile);
}
