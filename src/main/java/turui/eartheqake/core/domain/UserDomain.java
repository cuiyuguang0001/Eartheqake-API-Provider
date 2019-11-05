package turui.eartheqake.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import turui.eartheqake.constant.Constant;
import turui.eartheqake.core.pojo.user.Session;
import turui.eartheqake.core.mapper.UserMapper;
import turui.eartheqake.core.pojo.user.User;
import turui.eartheqake.core.pojo.user.User_profile;
import turui.eartheqake.util.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDomain {

    @Autowired
    UserMapper userMapper;




//    /**
//     * 添加一条session信息(在登录的时候)
//     * @param httpServletRequest
//     * @return 添加结果
//     */
//    private Map<String, Object> SessionAdd(HttpServletRequest httpServletRequest){
//        Session session = new Session();
//        session.setSid(httpServletRequest.getParameter("sid"));
//        System.out.println(httpServletRequest.getHeader("x-forwarded-for"));
////        session.setIp(httpServletRequest.getParameter("ip"));
//        session.setPlatform(httpServletRequest.getParameter("platform"));
//        session.setUuid(httpServletRequest.getParameter("uuid"));
//
//        //添加一条session信息
//        boolean b = userMapper.sessionAdd(session);
//        session = null;
//        return b
//                ? MapUtil.requestMap(null, Constant.SUCCESS_ADD, Constant.GOOD_REQUEST)
//                : MapUtil.requestMap(null, Constant.NOT_SUCCESS_ADD, Constant.BAD_REQUEST);
//
//    }

    /**
     * 检查用户在线情况
     * @param httpServletRequest
     * @return 是否退出了登陆
     */
    public Map<String, Object> SessionModel(HttpServletRequest httpServletRequest)
    {
        String sid = httpServletRequest.getParameter("sid");
        if(sid != null)
        {
            Session session = userMapper.sessionModelBySid(sid);
            if(session != null && !session.getUid().equals("0"))
            {
                User user = new User();
                user.setId(session.getUid());
                return MapUtil.requestMap(userMessage(session, user), Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST);
            }
        }
        Session session = addSession(httpServletRequest, "0", UUIDUtil.getUUID());
        return MapUtil.requestMap(session, Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);

    }


    /**
     * 删除一条登陆信息(再退出的时候)
     * @param httpServletRequest
     * @return 删除结果
     */
    public Map<String, Object> LoginOut(HttpServletRequest httpServletRequest)
    {
        LogUtil.doLog("进入LoginOut方法");
        Session session = new Session();
        String sid = httpServletRequest.getParameter("sid");
        if(sid == null)
        {
            return null;
        }
        session.setSid(sid);
        boolean b = userMapper.sessionRemove(session);
        return b
                ? MapUtil.requestUpdateMap(Constant.SUCCESS_REQUEST,Constant.GOOD_REQUEST,1,Constant.SUCCESS_LOGINOUT)
                : MapUtil.requestMap(null, Constant.NOT_SUCCESS_REMOVE, Constant.BAD_REQUEST);
    }

    /**
     * 用户登陆方法
     * @return
     */
    public Map<String ,Object> Login(HttpServletRequest httpServletRequest){
        LogUtil.doLog("进入login方法");
        User user = new User();
        user.setUsername(httpServletRequest.getParameter("username"));
        //登陆信息验证
        User loginUser = userMapper.login(user);
        if(loginUser == null)
        {
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_LOGIN, Constant.BAD_REQUEST);
        }
        String truePwd = MD5Util.md5Encode(httpServletRequest.getParameter("password")) + loginUser.getSlat();
        String reqPwd = loginUser.getPassword();
       // reqPwd = MD5Util.md5Encode(reqPwd);//测试使用
        //todo 将reqPwd进行md5解密验证
        truePwd = MD5Util.md5Encode(truePwd);//md5加密算法
        //当验证成功的时候
        if(truePwd.equals(reqPwd))
        {
            LogUtil.doLog("用户密码验证成功");

            Session session = userMapper.sessionModelByUuid(loginUser.getId());
            if(session != null)
            {
                return MapUtil.requestMap(userMessage(session, loginUser), Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST);
            }
            Session reqSession = addSession(httpServletRequest, loginUser.getId(), UUIDUtil.getUUID());

            return MapUtil.requestMap(userMessage(reqSession, loginUser), Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST);
        }else {
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_LOGINERROR, Constant.BAD_REQUEST);
        }
    }

    /**
     * 修改账号密码
     * @param httpServletRequest
     * @return
     */// TODO: 2019/11/5  
    public Map<String ,Object> UserLoginEdit(HttpServletRequest httpServletRequest)
    {
        LogUtil.doLog("UserLoginEdit");
        //查询是否登陆状态
        Session session = getSessionBySid(httpServletRequest.getParameter("sid"));
        if(session == null || session.getUid().equals("0"))
        {
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);
        }
        String pwd =  httpServletRequest.getParameter("password");
        String salt = UUIDUtil.getUUID().substring(0, 6);
        String mdPwd = MD5Util.md5Encode(MD5Util.md5Encode(pwd) + salt);

        if(userMapper.updateUserPwd(session.getUid(), mdPwd, salt,
                httpServletRequest.getParameter("username")))
            return MapUtil.requestUpdateMap(Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST, 1, Constant.SUCCESS_UPDATE);
        return null;

    }

    public Map<String ,Object> UserProfileEdit(HttpServletRequest httpServletRequest)
    {
        LogUtil.doLog("UserProfileEdit");
        //检查登陆
        Session session = getSessionBySid(httpServletRequest.getParameter("sid"));
        if(session == null || session.getUid().equals("0"))
        {
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);
        }

        User_profile user_profile = new User_profile(httpServletRequest.getParameter("nickname"),
                httpServletRequest.getParameter("headimg"),
                httpServletRequest.getParameter("desc"));
        user_profile.setUid(session.getUid());

        if(!userMapper.userProfileEdit(user_profile))
            return MapUtil.requestUpdateMap(Constant.NOT_SUCCESS_UPDATE, Constant.BAD_REQUEST, 0, Constant.NOT_SUCCESS_UPDATE);

        return MapUtil.requestUpdateMap(Constant.SUCCESS_UPDATE, Constant.GOOD_REQUEST, 1, Constant.SUCCESS_UPDATE);
    }


    //通用方法

    /**
     * 插入一条session
     * @param httpServletRequest
     * @param uuid
     * @return
     */
    public Session addSession(HttpServletRequest httpServletRequest, String uuid, String sid)
    {
        Session session = new Session();
        String platform = httpServletRequest.getParameter("platform");
        if(platform == null)
        {
            platform = "unknow";
        }
        session.setPlatform(platform);
        //获取客户端ip
        String remoteAddr = httpServletRequest.getHeader("x-forwarded-for");
        if(remoteAddr == null)
        {
            remoteAddr = httpServletRequest.getRemoteAddr();
        }
        session.setIp(remoteAddr);
        session.setUid(uuid);
        session.setDateline(CommonUtil.getTineLine());
        session.setSid(sid);


        //添加一条session信息
        if(userMapper.sessionAdd(session))//todo
        {
            LogUtil.doLog("---------------------");
            return userMapper.sessionModelBySid(sid);
        }else
        {
            LogUtil.doLog("Session表添加失败");
            LogUtil.doLog("---------------------");
            return null;
        }
    }


    /**
     * 按sid获取session
     * @param sid
     * @return
     */
    public Session getSessionBySid(String sid)
    {
        if(sid != null)
        {
            return userMapper.sessionModelBySid(sid);
        }
        return null;
    }

    /**
     * 处理user返回信息
     * @return
     */
    public Session userMessage(Session session, User user)
    {
        Map<String ,Object> info = new HashMap<>();

        Map<String ,Object> basic = new HashMap<>();
        basic.put("gid", user.getGid());
        basic.put("uid", user.getId());
        basic.put("username", user.getUsername());

        info.put("group", userMapper.groupModel(user.getId()));
        info.put("profile", userMapper.profileModel(user.getId()));
        info.put("basic", basic);
        session.setInfo(info);

        return session;
    }


}
