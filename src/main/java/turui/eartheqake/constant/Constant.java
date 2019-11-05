package turui.eartheqake.constant;

public class Constant {

    //返回状态
    public static final int BAD_REQUEST = 500;

    public static final int NOT_URL = 404;

    //地址无效
    public static final String BAD_AC  = "参数非法";

    //返回信息
    public static final String SUCCESS_REQUEST = "成功响应";


    public static final String NOT_SUCCESS_SESSION = "错误的站点信息";

    public static final String SUCCESS_LOGIN = "登陆成功";
    public static final String NOT_SUCCESS_LOGIN = "登陆失败，没有此用户";
    public static final String NOT_SUCCESS_LOGINERROR = "登陆失败，用户名或密码错误";
    public static final String NOT_SUCCESS_KEEP_LOGIN = "非常抱歉，您的登陆信息失效，请重新登陆";

    public static final String SUCCESS_ADD = "信息添加成功";
    public static final String NOT_SUCCESS_ADD = "信息添加失败";

    public static final String SUCCESS_UPDATE = "信息修改成功";
    public static final String NOT_SUCCESS_UPDATE = "信息修改失败";

    public static final String SUCCESS_REMOVE = "信息删除成功";
    public static final String NOT_SUCCESS_REMOVE = "信息删除失败";

    public static final String KEEP_LOGIN = "用户已经登陆";
    public static final String NOT_KEEP_LOGIN = "登陆信息已失效，请重新登录";
    public static final String SUCCESS_LOGINOUT = "退出登陆成功";

    public static final String NOT_SUCCESS_UPLOAD = "文件接收失败";
    public static final String EXIST_FILE = "文件存在";

    public static final String NOT_SUCCESS_SELECT = "查询失败，此信息可能已失效，请刷新数据";


    //文件上传地址
    public static final String FILE_PATH = "/static/public";
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources";

    //项目port路径
    public static final String PROJECT_IP = "http://192.168.1.149:9001";
}
