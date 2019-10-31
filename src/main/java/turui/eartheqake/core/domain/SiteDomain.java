package turui.eartheqake.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import turui.eartheqake.constant.Constant;
import turui.eartheqake.core.pojo.AppModel;
import turui.eartheqake.core.mapper.SiteMapper;
import turui.eartheqake.util.MapUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class SiteDomain {

    @Autowired
    SiteMapper siteMapper;

    /**
     * 返回一条站点信息
     * @param httpServletRequest
     * @return
     */
    public Map<String, Object> AppModel(HttpServletRequest httpServletRequest)
    {
        AppModel appModel = new AppModel();
        String appid = httpServletRequest.getParameter("appid");
        String platform = httpServletRequest.getParameter("platform");
        if(appid == null)
        {
            return null;
        }
        if(platform == null)
        {
            return null;
        }
        appModel.setAppid(appid);
        appModel.setPlatform(platform);
        List<AppModel> appModels = siteMapper.appModel(appModel);
        return appModels.size() == 0
                ? MapUtil.requestMap(null, Constant.NOT_SUCCESS_SESSION, Constant.BAD_REQUEST)
                : MapUtil.requestMap(siteMapper.appModel(appModel).get(0), Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST);
    }
}
