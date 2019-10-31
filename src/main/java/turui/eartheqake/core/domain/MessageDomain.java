package turui.eartheqake.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import turui.eartheqake.constant.Constant;
import turui.eartheqake.core.mapper.MessageMapper;
import turui.eartheqake.core.pojo.message.Message;
import turui.eartheqake.core.pojo.message.MessageGroup;
import turui.eartheqake.core.pojo.message.MessageGroupRecord;
import turui.eartheqake.core.pojo.message.MessageRecord;
import turui.eartheqake.core.pojo.user.Session;
import turui.eartheqake.util.CommonUtil;
import turui.eartheqake.util.LogUtil;
import turui.eartheqake.util.MapUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class MessageDomain {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    UserDomain userDomain;

    /**
     * 返回所有信息分组
     * @param httpServletRequest
     * @return
     */
    public Map<String ,Object> MessageGroupRecordList(HttpServletRequest httpServletRequest)
    {
        LogUtil.doLog("开始MessageGroupRecordList方法");
        //查询是否登陆状态
        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if(session == null || session.getUid().equals("0"))
        {
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);
        }
        int n = Integer.valueOf(httpServletRequest.getParameter("n"));
        int p = (Integer.valueOf(httpServletRequest.getParameter("p")) - 1) * n;
//        //通过uid获得用户分组gid
//        List<MessageGroupFollow> messageGroupFollows = messageMapper.messageGroupFollowList(session.getUid(), "1");
        //查询zf_msg_group表
        List<MessageGroup> messageGroups = messageMapper.messageGroupList();
        //查询zf_msg_record表
        List<MessageGroupRecord> messageGroupRecords = messageMapper.messageGroupListModel(session.getUid(), p, p + n);
        for(MessageGroupRecord mgr : messageGroupRecords)
        {
            for(MessageGroup mg : messageGroups)
            {
                if(mg.getGid().equals(mgr.getGid()))
                {
                    mgr.setData(mg);
                    mgr.setDateline(CommonUtil.getShortTime(mgr.getDateline()));
                }
            }
        }

        return MapUtil.requestMap(messageGroupRecords,
                Constant.SUCCESS_REQUEST,
                Constant.GOOD_REQUEST,
                messageMapper.messageGroupListCount(session.getUid()));
    }

    /**
     * 返回所有信息,并且清空分组表中固定分组的未读信息
     * @param httpServletRequest //问：gid uuid groupid的必要性
     * @return
     */

    public Map<String ,Object> MessageRecordList(HttpServletRequest httpServletRequest)
    {
        LogUtil.doLog("进入MessageRecordList方法");
        //查询是否登陆状态
        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if(session == null || session.getUid().equals("0"))
        {
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);
        }
        int n = Integer.valueOf(httpServletRequest.getParameter("n"));
        int p = (Integer.valueOf(httpServletRequest.getParameter("p")) - 1) * n;
        String gid = httpServletRequest.getParameter("gid");//获取消息分组编号
        String uuid = session.getUid();
        LogUtil.doLog("gid = " + gid + " uuid = " + uuid);
        if(gid == null){
            return null;
        }
        //查询符合标准得msg_record信息
        List<MessageRecord> messageRecords = messageMapper.messageList(uuid, gid, p, n + p);
        //查询符合标准得msg信息
        List<Message> messages = messageMapper.messageListData(uuid, gid);
        //数据拼装
        for(Message m : messages)
        {
            for(MessageRecord mr : messageRecords)
            {
                if(m.getId().equals(mr.getMid()))
                {
                    mr.setDateline(CommonUtil.timestampToStr(Long.valueOf(mr.getDateline())));
//                    if(Long.valueOf(CommonUtil.getTineLine()) - Long.valueOf(mr.getDateline()) < 2592000)
                    m.setDateline(CommonUtil.timestampToStr(Long.valueOf(m.getDateline())));
                    mr.setData(m);
                }
            }
        }

        Map<String, Object> reqMap = MapUtil.requestMap(messageRecords, Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST,messageMapper.messageListCount(session.getUid(), gid));
        messageMapper.cleanMessageCount(gid, uuid); //清除未读信息
        gid = null;
        uuid = null;
        return reqMap;
    }

    /**
     * 返回一条特定信息,并且更新信息为已读
     * @param httpServletRequest
     * @return
     */
    public Map<String, Object> MessageRecordEdit(HttpServletRequest httpServletRequest)
    {
        LogUtil.doLog("MessageModel");

        Session session = userDomain.getSessionBySid(httpServletRequest.getParameter("sid"));
        if(session == null || session.getUid().equals("0"))
        {
            return MapUtil.requestMap(null,Constant.NOT_SUCCESS_KEEP_LOGIN, Constant.BAD_REQUEST);
        }

        String id = httpServletRequest.getParameter("id");

        if(id == null)
        {
            return null;
        }
        MessageRecord message_record = messageMapper.messageModel(id);
        if(message_record != null){
            if(message_record.getReadtime().equals("0"))
            {
                //将该数据设置阅读时间
                String tineLine = CommonUtil.getTineLine();
                messageMapper.readMessage(CommonUtil.getTineLine(), id, session.getUid());
                message_record.setReadtime(tineLine);
            }
            LogUtil.doLog(message_record.toString());
            return MapUtil.requestUpdateMap(Constant.SUCCESS_REQUEST, Constant.GOOD_REQUEST, 1, Constant.SUCCESS_UPDATE);
        }else
        {
            return MapUtil.requestMap(null, Constant.NOT_SUCCESS_SELECT, Constant.BAD_REQUEST);
        }
    }



}
