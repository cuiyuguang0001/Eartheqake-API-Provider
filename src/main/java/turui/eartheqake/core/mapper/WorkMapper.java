package turui.eartheqake.core.mapper;

import org.apache.ibatis.annotations.*;
import turui.eartheqake.core.pojo.file.ZF_file_follow;
import turui.eartheqake.core.pojo.work.EQ;
import turui.eartheqake.core.pojo.work.EQ_form;
import turui.eartheqake.core.pojo.work.EQ_form_record;

import java.util.List;

@Mapper
public interface WorkMapper {

    /**
     * 返回所有地震信息
     * @return
     */
    @Select("select id, dzid, dzmc, dateline, dzzj from " +
            "(SELECT A.*, ROWNUM RN FROM zf_eq A WHERE ROWNUM <= #{n}) " +
            "where RN > #{p} order by dateline desc")
    List<EQ> eqList(int p, int n);
    @Select("select count(0) from zf_eq")
    int eqListCount();

    /**
     * 返回单个地震信息
     * @return
     */
    @Select("select id, title, mod, list, name from zf_eq_form where 1 = 1 or mod = #{mod} order by list asc")
    List<EQ_form> eqFormList(String mod);
    @Select("select count(0) from zf_eq_form")
    int eqFormListCount();

    @Select("select id, eqid, formid, uuid, title, address, dateline from " +
            "(SELECT A.*, ROWNUM RN FROM zf_eq_form_record A WHERE ROWNUM <= #{n} " +
            "and eqid = #{eqid} " +
            "and formid = #{formid} " +
            "and uuid = #{uid})" +
            "where RN > #{p}")
    @Results(value = {
            @Result(column = "uuid", property = "uid")
    })
    List<EQ_form_record> eqFormRecordList(String eqid, String formid, String uid, int p, int n);
    @Select("select count(0) from zf_eq_form_record where eqid = #{eqid} and formid = #{formid} and uuid = #{uid}")
    int eqFormRecordListCount(String eqid, String formid, String uid);

    /**
     * 插入一条zf_file_follow表信息
     * @param zf
     * @return
     */
    @Insert("insert into zf_file_follow(fid, mod, mid, dateline) values(#{fid}, #{mod}, #{mid}, #{dateline})")
    boolean fileFollowAdd(ZF_file_follow zf);

    /**
     * 插入一条re_from_record信息
     * @param eq
     * @return
     */
    @Insert("insert into zf_eq_form_record(eqid, formid, uuid, title, address, dateline) " +
            "values(#{eqid}, #{formid}, #{uid}, #{title}, #{address}, #{dateline})")
    boolean EQFormRecordAdd(EQ_form_record eq);
}
