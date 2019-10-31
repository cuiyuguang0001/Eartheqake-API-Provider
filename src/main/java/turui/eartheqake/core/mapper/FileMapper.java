package turui.eartheqake.core.mapper;

import org.apache.ibatis.annotations.*;
import turui.eartheqake.core.pojo.file.MyFile;


@Mapper
public interface FileMapper {

    @Insert("insert into zf_file(type, server, name, usize, url, uuid, dateline, md5) values(#{type}, #{server}, #{name}, #{usize}, #{url}, #{uid}, #{dateline}, #{md5})")
    @SelectKey(keyProperty = "id",resultType = int.class, before = false, statement = "select max(id) id from zf_file")
    boolean fileWrite(MyFile myFile);

    @Select("select id, type, server, name, usize, url, uuid, dateline, md5 from zf_file where md5 = #{md5}")
    @Results(value = {
            @Result(column = "uuid", property = "uid")
    })
    MyFile fileModel(String md5);

}
