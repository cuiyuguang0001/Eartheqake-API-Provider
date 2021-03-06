package turui.eartheqake.core.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import turui.eartheqake.core.pojo.work.EQ_from_mod_zqjb;

@Mapper
public interface ZQJBMapper {

    /**
     * 添加一条zf_eq_from_mod_zqjb表信息
     * @param eq
     * @return
     */
    @Insert("insert into zf_eq_form_mod_zqjb(xzq, xxmc, jd, wd, zqlx, zhqk, fj, data) " +
            "values(#{xzq}, #{xxmc}, #{jd}, #{wd}, #{zqlx}, #{zhqk}, #{fj}, #{data})")
    @SelectKey(keyProperty = "id",resultType = int.class, before = false, statement = "select max(id) id from zf_eq_form_mod_zqjb")
    boolean zqjbAdd(EQ_from_mod_zqjb eq);

    @Update("update zf_eq_form_mod_zqjb set xzq = #{xzq}, xxmc = #{xxmc}, jd = #{jd}, wd = #{wd}," +
            "zqlx = #{zqlx}, zhqk = #{zhqk}, fj = #{fj}, data = #{data} where id = #{mid}")
    boolean zqjbEdit(EQ_from_mod_zqjb eq);

}
