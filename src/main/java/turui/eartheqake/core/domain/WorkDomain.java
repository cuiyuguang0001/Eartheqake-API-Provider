package turui.eartheqake.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turui.eartheqake.constant.Constant;
import turui.eartheqake.core.mapper.FWDCMapper;
import turui.eartheqake.core.mapper.WorkMapper;
import turui.eartheqake.core.mapper.ZQJBMapper;
import turui.eartheqake.core.pojo.file.ZF_file_follow;
import turui.eartheqake.core.pojo.user.Session;
import turui.eartheqake.core.pojo.work.*;
import turui.eartheqake.util.CommonUtil;
import turui.eartheqake.util.LogUtil;
import turui.eartheqake.util.MapUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WorkDomain {

    @Autowired
    WorkMapper workMapper;
    @Autowired
    UserDomain userDomain;
    @Autowired
    ZQJBMapper zqjbMapper;
    @Autowired
    FWDCMapper fwdcMapper;
    @Autowired
    FileDomain fileDomain;

    /**
     * 显示所有eq表数据
     *
     * @param httpServletRequest
     * @return
     */
    public Map<String, Object> EqList(HttpServletRequest httpServletRequest) {
        LogUtil.doLog("EqList");
        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if (session == null || session.getUid().equals("0"))
            return MapUtil.requestMap(null, Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);

        int n = Integer.valueOf(httpServletRequest.getParameter("n"));
        int p = (Integer.valueOf(httpServletRequest.getParameter("p")) - 1) * n;
        List<EQ> eqs = workMapper.eqList(p, n);
        for (EQ eq : eqs) {
            eq.setDateline(CommonUtil.timestampToStr(Long.valueOf(eq.getDateline())));
        }
        return MapUtil.requestMap(eqs,
                Constant.SUCCESS_REQUEST,
                Constant.GOOD_REQUEST,
                workMapper.eqListCount());
    }

    /**
     * 显示所有eq_form表数据
     *
     * @param httpServletRequest
     * @return
     */
    public Map<String, Object> EqFormList(HttpServletRequest httpServletRequest) {
        LogUtil.doLog("进入EqFormList");
        //查询是否登陆状态
        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if (session == null || session.getUid().equals("0"))
            return MapUtil.requestMap(null, Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);

        return MapUtil.requestMap(workMapper.eqFormList(null),
                Constant.SUCCESS_REQUEST,
                Constant.GOOD_REQUEST,
                workMapper.eqListCount());
    }

    /**
     * 显示所有eq_form_record
     *
     * @param httpServletRequest
     * @return
     */
    public Map<String, Object> EqFormRecordList(HttpServletRequest httpServletRequest) {
        LogUtil.doLog("进入EqFormRecordList");
        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if (session == null || session.getUid().equals("0"))
            return MapUtil.requestMap(null, Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);

        int n = Integer.valueOf(httpServletRequest.getParameter("n"));
        int p = (Integer.valueOf(httpServletRequest.getParameter("p")) - 1) * n;

        String formid = httpServletRequest.getParameter("formid");
        if (formid == null || formid.equals("0"))
            formid = workMapper.eqFormList(httpServletRequest.getParameter("formname")).get(0).getId() + "";


        return MapUtil.requestMap(workMapper.eqFormRecordList(httpServletRequest.getParameter("eqid"),
                formid,
                session.getUid(), p, n),
                Constant.SUCCESS_REQUEST,
                Constant.GOOD_REQUEST,
                workMapper.eqFormRecordListCount(httpServletRequest.getParameter("eqid"),
                        formid,
                        session.getUid()));
    }


    /**
     * ==================================zqjb======================================
     */

    /**
     * 提交灾情急报zqjb-----添加一条zqjb信息
     *
     * @param httpServletRequest
     * @return
     */
    public Map<String, Object> EqFormModZQJBAdd(HttpServletRequest httpServletRequest) {
        LogUtil.doLog("EqFormModelZQJBAdd");
        //检查登陆状态
        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if (session == null || session.getUid().equals("0"))
            return MapUtil.requestMap(null, Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);
        String data = httpServletRequest.getParameter("data");
        Map<String, String> dataMap = CommonUtil.parseToMap(data);
        //解析location
        Map<String, String> locationDataMap = CommonUtil.parseToMap(dataMap.get("location"));
        String files = dataMap.get("files");
        String[] fileArray = (files.substring(1, files.length() - 1)).split(",");

        //创建实体对象
        EQ_from_mod_zqjb eq = new EQ_from_mod_zqjb();
        eq.setXzq(locationDataMap.get("address"));
        eq.setXxmc(locationDataMap.get("poiName"));
        eq.setJd(locationDataMap.get("latitude"));
        eq.setWd(locationDataMap.get("longitude"));
        //解析data
        locationDataMap = CommonUtil.parseToMap(dataMap.get("data"));
        eq.setZhqk(locationDataMap.get("zhqk"));
        eq.setZqlx(locationDataMap.get("zqlx"));
        eq.setData(data);

        eq.setFj((fileArray == null || fileArray.length == 0 || fileArray[0].equals("")) ? "0" : "1");

//        添加zqjb表信息
        if (zqjbMapper.zqjbAdd(eq)) {
//            添加eq_form_mod_record表信息
            if (!eqFormModRecordAdd(eq.getXzq(), eq.getXxmc(), eq.getId() + "",
                    session.getUid(), dataMap.get("eqid"),
                    httpServletRequest.getParameter("datakey")))
                return null;
//            添加filefollow表信息
            if(eq.getFj().equals("0"))
                if(!fileDomain.fileFollowAdd(eq.getId() + "", "eq_form_mod_zqjb", fileArray))
                    return null;
            return MapUtil.requestUpdateMap(Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST, 1, Constant.SUCCESS_ADD);
        } else {
            return null;
        }
    }



    /**
     * ========================================fwdc==========================================
     */

    /**
     * 添加一条房屋调查信息
     * @param httpServletRequest
     * @return
     */
    public Map<String, Object> EqFormModFWDCAdd(HttpServletRequest httpServletRequest)
    {
        LogUtil.doLog("EqFormModelFWDCAdd");
        //检查登陆状态
        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if(session == null || session.getUid().equals("0"))
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);

        String data = httpServletRequest.getParameter("data");

        Map<String, String> dataMap = CommonUtil.parseToMap(data);
        //解析location
        Map<String, String> locationDataMap = CommonUtil.parseToMap(dataMap.get("location"));
        String files = dataMap.get("files");
        String[] fileArray = (files.substring(1, files.length() - 1)).split(",");
        String dtdcs = dataMap.get("dtdcs");
        String[] dtdcArray = (dtdcs.substring(1, dtdcs.length() - 1)).split(",");

        //创建fwdc实体类
        EQ_form_mod_fwdc fwdc = new EQ_form_mod_fwdc();
        fwdc.setXzq(locationDataMap.get("address"));
        fwdc.setXxmc(locationDataMap.get("poiName"));
        fwdc.setJd(locationDataMap.get("latitude"));
        fwdc.setWd(locationDataMap.get("longitude"));

        //解析data
        locationDataMap = CommonUtil.parseToMap(dataMap.get("data"));
        fwdc.setLdnd(locationDataMap.get("ldnd"));
        fwdc.setQtdc(locationDataMap.get("qtdc"));
        fwdc.setZqms(locationDataMap.get("zqms"));

        fwdc.setFj((fileArray == null || fileArray.length == 0 || fileArray[0].equals("")) ? "0" : "1");
        fwdc.setDtdc((dtdcArray == null || dtdcArray.length == 0 || dtdcArray[0].equals("")) ? "0" : "1");

        //fwdc表添加一条信息，filefollow表添加多条信息
        if(fwdcMapper.fwdcAdd(fwdc))
        {
            if(fwdc.getFj().equals("1"))
                if(!fileDomain.fileFollowAdd(fwdc.getId() + "", "eq_form_mod_fwdc", fileArray))
                    return null;

            if(!eqFormModRecordAdd(fwdc.getXzq(), fwdc.getXxmc(), fwdc.getId() + "",
                    session.getUid(), dataMap.get("eqid"),
                    httpServletRequest.getParameter("datakey")))
                return null;

            for(int i = 0; i < dtdcArray.length; i++)
            {
                EQ_form_mod_dtdc_follow dtdc_follow = new EQ_form_mod_dtdc_follow();
                dtdc_follow.setDid(dtdcArray[i]);
                dtdc_follow.setFid(fwdc.getId() + "");
                if(!fwdcMapper.dtdcFollowAdd(dtdc_follow))
                    return null;
            }
            return MapUtil.requestUpdateMap(Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST, 1, Constant.SUCCESS_ADD);
        }

        return null;
    }

    /**
     * 添加一条dtdc表信息
     * @param httpServletRequest
     * @return
     */
    public Map<String ,Object> EqFormModDTDCAdd(HttpServletRequest httpServletRequest)
    {
        //检查登陆状态
        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if(session == null || session.getUid().equals("0"))
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);

        EQ_form_mod_dtdc dtdc = new EQ_form_mod_dtdc();
        dtdc.setXzq(httpServletRequest.getParameter("xzq"));
        dtdc.setXxmc(httpServletRequest.getParameter("xxmc"));
        dtdc.setJd(httpServletRequest.getParameter("jd"));
        dtdc.setWd(httpServletRequest.getParameter("wd"));
        dtdc.setJglx(httpServletRequest.getParameter("jglx"));
        dtdc.setJzmj(httpServletRequest.getParameter("jzmj"));
        dtdc.setPhlx(httpServletRequest.getParameter("phlx"));
        dtdc.setYtlx(httpServletRequest.getParameter("ytlx"));

        if(fwdcMapper.dtdcAdd(dtdc)){
            return MapUtil.requestMap(dtdc, Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST);
        }

        return null;
    }



    //通用方法
    /**
     * 添加zf_eq_form_record表信息
     * @param
     * @return
     */
    public boolean eqFormModRecordAdd(String xzq, String xxmc, String id, String uid, String eqid, String datakey)
    {
        EQ_form_record form = new EQ_form_record();
        form.setAddress(xzq);
        form.setTitle(xxmc);
        form.setDateline(CommonUtil.getTineLine());
        form.setUid(uid);
        form.setEqid(eqid);
        form.setFormid(workMapper.eqFormList("zqjb").get(0).getId() + "");
        form.setMid(id);
        form.setDatakey(datakey);
        return workMapper.EQFormRecordAdd(form);
    }

}
