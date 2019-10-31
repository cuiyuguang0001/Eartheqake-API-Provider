package turui.eartheqake.core.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import turui.eartheqake.constant.Constant;
import turui.eartheqake.core.mapper.FileMapper;
import turui.eartheqake.core.mapper.WorkMapper;
import turui.eartheqake.core.pojo.file.MyFile;
import turui.eartheqake.core.pojo.file.ZF_file_follow;
import turui.eartheqake.core.pojo.user.Session;
import turui.eartheqake.util.CommonUtil;
import turui.eartheqake.util.MD5Util;
import turui.eartheqake.util.MapUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class FileDomain {

    @Autowired
    FileMapper fileMapper;
    @Autowired
    UserDomain userDomain;
    @Autowired
    WorkMapper workMapper;


//    /**
//     * 入口方法
//     * @param ac
//     * @param httpServletRequest
//     * @return
//     */
//    public Map<String, Object> upload(@RequestParam("ac") String ac, HttpServletRequest httpServletRequest)
//    {
//        if(ac.equals("UploadFile"))
//        {
//            return uploadController.uploadFile(httpServletRequest);
//        }else {
//            return mapUtil.requestMap(null, Constant.BAD_AC, Constant.NOT_URL);
//        }
//    }

    /**
     * 上传文件
     * @param httpServletRequest
     * @return 上传结果
     */
    public Map<String, Object> UploadFile(HttpServletRequest httpServletRequest)
    {
        //查询是否登陆状态
        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if(session == null || session.getUid().equals("0"))
        {
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);
        }

        Part file = null;
        try {
            file = httpServletRequest.getPart("file");
            String server = httpServletRequest.getParameter("server");
            if(file == null || server == null)
                return null;

            //检测数据库是否存在
            long size = file.getSize();
            String md5 = MD5Util.fileMd5(file);
            MyFile reqFile = fileMapper.fileModel(md5);
            if(reqFile != null)
            {
                return MapUtil.requestMap(reqFile, Constant.EXIST_FILE, Constant.GOOD_REQUEST);
            }

            String content = file.getHeaders("content-disposition").toString();
            String end = content.substring(content.lastIndexOf(".") + 1, content.length() - 2);
            String dataStr = CommonUtil.timestampToStr(Long.valueOf(CommonUtil.getTineLine()));
            UUID uuidName = UUID.randomUUID();
            String path = Constant.FILE_PATH +
                    "\\" + dataStr.substring(0, 4) +
                    "\\" + dataStr.substring(5, 7) +
                    "\\" + dataStr.substring(8, 10) +
                    "\\" + end;

            //创建存储实体类
            MyFile target = new MyFile();
            target.setType(end);
            target.setServer(server);
            target.setDateline(CommonUtil.getTineLine());
            target.setMd5(md5);
            target.setUsize(size + "");
            target.setName(path + "\\" + uuidName + "." + end);
            target.setUid(session.getUid());

            //创建存储地址
            File dir = new File(path);
            if(!dir.exists())
                dir.mkdirs();

            String url = dir.getAbsolutePath() + "\\" + uuidName + "." + end;
            target.setUrl(url);

            if(!fileMapper.fileWrite(target))
            {
                return MapUtil.requestMap(null, Constant.NOT_SUCCESS_ADD, Constant.BAD_REQUEST);
            }

            //文件存储
            file.write(url);
            return MapUtil.requestMap(target, Constant.SUCCESS_ADD, Constant.GOOD_REQUEST);

        } catch (IOException e) {
            e.printStackTrace();
            return MapUtil.requestMap(null,"文件接收失败", Constant.BAD_REQUEST);
        } catch (ServletException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
                return MapUtil.requestMap(null,"文件接收失败", Constant.BAD_REQUEST);
            }

        }

    }

    /**
     * 添加file_follow表信息
     */
    public boolean fileFollowAdd(String fids, String id, String mod)
    {
        //file_follow表信息
        String[] fid = fids.split(",");
        for(int i = 0; i < fid.length; i++)
        {
            //创建实体类
            ZF_file_follow zf = new ZF_file_follow();
            zf.setDateline(CommonUtil.getTineLine());
            zf.setFid(fid[i]);
            zf.setMod(mod);
            zf.setMid(id);
            //添加zf_file_follow表信息
            if(!workMapper.fileFollowAdd(zf)){
                return false;
            }
        }
        return true;
    }

}
