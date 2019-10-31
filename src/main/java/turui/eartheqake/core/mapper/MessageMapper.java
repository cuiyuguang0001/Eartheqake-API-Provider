package turui.eartheqake.core.mapper;

import org.apache.ibatis.annotations.*;
import turui.eartheqake.core.pojo.message.*;

import java.util.List;

@Mapper
public interface MessageMapper {

    /**
     * 查询所有消息
     * @param
     * @return
     */
    @Select("select mid, gid, dateline, readtime, uuid, id from " +
            "(SELECT A.*, ROWNUM RN FROM ZF_MSG_RECORD A WHERE ROWNUM <= #{n} " +
            "and uuid = #{uid} " +
            "and gid = #{gid}) " +
            "where RN > #{p} " +
            "order by dateline desc")
    @Results(value = {
            @Result(column = "uuid", property = "uid")
    })
    List<MessageRecord> messageList(String uid, String gid, int p, int n);

    /**
     * 查询所有信息个数
     * @param uid
     * @param gid
     * @return
     */
    @Select("select count(0) from ZF_MSG_RECORD " +
            "where uuid = #{uid} " +
            "and gid = #{gid}")
    int messageListCount(String uid, String gid);

    /**
     * 查询符合标准的message信息
     * @param uid
     * @param gid
     * @return
     */
    @Select("select id, gid, title, content, dateline, tpl, uuid, status from zf_msg " +
            "where uuid = #{uid} " +
            "and gid = #{gid} " +
            "and status = 1")
    @Results(value = {
            @Result(column = "uuid", property = "uid")
    })
    List<Message> messageListData(String uid, String gid);

    /**
     * 查询一条地震信息
     * @param id
     * @return
     */
    @Select("select mid, gid, dateline, readtime, uuid, id from ZF_MSG_record where id = #{id}")
    @Results(value = {
            @Result(column = "uuid", property = "uid")
    })
    MessageRecord messageModel(String id);


    //分组信息

    /**
     * 阅读一条数据
     * @param readtime
     */
    @Update("update zf_msg_record set readtime = #{readtime} where id = #{id} and uuid = #{uid}")
    int readMessage(String readtime, String id, String uid);


    /**
     * 查询所有信息分组
     * @param
     * @return
     */
    @Select("select id gid, name, title, status from zf_msg_group where status = 1")
    List<MessageGroup> messageGroupList();

    @Select("select id, gid, utype, uuid, status from zf_msg_group_follow " +
            "where ((uuid = #{uid} and utype = 1) " +
            "or (uuid = #{gid} and utype = 0) " +
            "or (uuid = 0 and utype = 0)) " +
            "and status = 1")
    List<MessageGroupFollow> messageGroupFollowList(String uid, String gid);

    /**
     * 按用户查询所有消息分组内容
     * @return
     */
    @Select("SELECT gid, uuid, count, content, dateLine FROM  \n" +
            "(  \n" +
            "SELECT A.*, ROWNUM RN  \n" +
            "FROM ZF_MSG_GROUP_RECORD A  \n" +
            "WHERE ROWNUM <= #{n} and uuid = #{uid}\n" +
            ")  \n" +
            "WHERE RN > #{p}")
    @Results(value = {
            @Result(column = "uuid", property = "uid")
    })
    List<MessageGroupRecord> messageGroupListModel(String uid, int p, int n);
    @Select("SELECT count(0) FROM ZF_MSG_GROUP_RECORD A WHERE uuid = #{uid}")
    int messageGroupListCount(String uid);

    /**
     * 清空分组表未读信息
     * @param gid
     */
    @Update("update zf_msg_group_record set count = 0 where gid = #{gid} and uuid = #{uid}")
    void cleanMessageCount(String gid, String uid);



}
