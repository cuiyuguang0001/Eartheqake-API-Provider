package turui.eartheqake.core.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import turui.eartheqake.core.pojo.work.EQ_form_mod_dtdc;
import turui.eartheqake.core.pojo.work.EQ_form_mod_dtdc_follow;
import turui.eartheqake.core.pojo.work.EQ_form_mod_fwdc;

@Mapper
public interface FWDCMapper {

    /**
     * 添加一条房屋调查信息
     * @return
     */
    @Insert("insert into zf_eq_form_mod_fwdc(xzq, xxmc, jd, wd, ldnd, zqms, qtdc, dtdc, fj) " +
            "values(#{xzq}, #{xxmc}, #{jd}, #{wd}, #{ldnd}, #{zqms}, #{qtdc}, #{dtdc}, #{fj})")
    @SelectKey(keyProperty = "id",resultType = int.class, before = false, statement = "select max(id) id from zf_eq_form_mod_fwdc")
    boolean fwdcAdd(EQ_form_mod_fwdc fwdc);

    @Insert("insert into zf_eq_form_mod_dtdc(xzq, xxmc, jd, wd, ytlx, jglx, phlx, jzmj) " +
            "values(#{xzq}, #{xxmc}, #{jd}, #{wd}, #{ytlx}, #{jglx}, #{phlx}, #{jzmj})")
    @SelectKey(keyProperty = "id",resultType = int.class, before = false, statement = "select max(id) id from zf_eq_form_mod_dtdc")
    boolean dtdcAdd(EQ_form_mod_dtdc dtdc);

    @Insert("insert into zf_eq_form_mod_dtdc_follow(did, fid) " +
            "values(#{did}, #{fid})")
    boolean dtdcFollowAdd(EQ_form_mod_dtdc_follow dtdc_follow);

}
