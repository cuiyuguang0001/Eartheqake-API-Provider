package turui.eartheqake.core.mapper;

import org.apache.ibatis.annotations.*;
import turui.eartheqake.core.pojo.AppModel;

import java.util.List;

@Mapper
public interface SiteMapper {

    /**
     * 查询一条站点信息
     * @param appModel
     * @return
     */
    @Select("select * from ZF_APP where appid = #{appid} and platform = #{platform}")
    List<AppModel> appModel(AppModel appModel);

}
