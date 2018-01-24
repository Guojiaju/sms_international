package com.sms.international.admin.service.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sms.international.admin.mapper.SmsUserSendingReleaseMapper;
import com.sms.international.admin.model.SendingVo;
import com.sms.international.admin.model.SmsUserSendingRelease;
import com.sms.international.admin.mongodb.HistoryMongoUtil;
import com.sms.international.admin.mongodb.MongoDBUtil;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsUserSendingReleaseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/12/14
 * Description 审核队列接口实现类
 */
@Service
@Transactional
public class SmsUserSendingReleaseServiceImpl extends BaseService<SmsUserSendingRelease,SmsUserSendingReleaseMapper> implements SmsUserSendingReleaseService {

    @Override
    public JSONObject findByPage(SmsUserSendingRelease release) {
        int count = mapper.countTotal(release);
        List<SmsUserSendingRelease> list = mapper.findByT(release);
        /*for(SmsUserSendingRelease temp : list){
            SmsBlackWordsType type = (SmsBlackWordsType) CodeTable.dictionaryInfoMap.get("blackWordsType").get(smsUserSendingRelease.getScreenType());
            if(type != null) {
                switch (type.getLevel()) {
                    case 10:
                        smsUserSendingRelease.setScreenType_display("<span style=\"background-color: red; color:white;\">" + type.getName() + "</span>");
                        break;
                    case 9:
                        smsUserSendingRelease.setScreenType_display("<span style=\"background-color: orange; color:white;\">" + type.getName() + "</span>");
                        break;
                    case 8:
                        smsUserSendingRelease.setScreenType_display("<span style=\"background-color: yellow; color:black;\">" + type.getName() + "</span>");
                        break;
                    case 6:
                        smsUserSendingRelease.setScreenType_display("<span style=\"background-color: blue; color:white;\">" + type.getName() + "</span>");
                        break;
                }
            }
        }*/
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", count);
        obj.put("data", JSONArray.fromObject(list));
        return obj;
    }

    /**
     * 勾选通过
     *
     * @param release
     * @return
     */
    @Override
    public Map<String, Integer> passToCount(SmsUserSendingRelease release) {
        MongoDBUtil util = new MongoDBUtil();
        Map<String,Integer> resultMap = new HashMap<String,Integer>();
        if(release.getMdstr()!=null){
            release.setMdStrs(release.getMdstr().split(","));
            release.setMdstr(null);
        }
        int channelId =0;
        if(release.getChannel()!=null){
            channelId=release.getChannel();
            release.setChannel(0);
        }
        int num = release.getCount()/1000;
        if(release.getCount()%1000>0){
            num++;
        }
        int resultCount = 0;
        for (int i = 0; i < num; i++) {
            List<SendingVo> volist = mapper.findTToCount(release);
            if (volist.size() == 0) {
                resultMap.put("result",0);
                return resultMap;
            }
            resultCount+=volist.size();
            Map<String, Object> map = new HashMap<String, Object>();
            List<Long> list = new ArrayList<Long>();
            if (channelId > 0) {
                map.put("toChannel", channelId);
                for (SendingVo sendingVo : volist) {
                    list.add(sendingVo.getId());
                    BasicDBObject set = new BasicDBObject();
                    set.put(HistoryMongoUtil.getChannel(), channelId);
                    BasicDBObject where = new BasicDBObject();
                    where.put(HistoryMongoUtil.getId(), sendingVo.getHisids());
                    try {
                        util.update2("sms_send_history_unknown", set, where);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else{
                for (SendingVo sendingVo : volist) {
                    list.add(sendingVo.getId());
                }
            }
            map.put("list", list);
            mapper.updateReleaseMessageToIds(map);
        }
        util.close();
        resultMap.put("result",1);
        resultMap.put("resultCount",resultCount);
        return resultMap;
    }

    /**
     * 勾选通过
     *
     * @param release
     * @return
     */
    @Override
    public Map<String, Integer> pass(SmsUserSendingRelease release) {
        MongoDBUtil util = new MongoDBUtil();
        Map<String,Integer> resultMap = new HashMap<String,Integer>();
        if(release.getMdstr()!=null){
            release.setMdStrs(release.getMdstr().split(","));
            release.setMdstr(null);
        }
        int channelId =0;
        if(release.getChannel()!=null){
            channelId=release.getChannel();
            release.setChannel(0);
        }
        int resultCount = 0;
        List<SendingVo> volist = mapper.findT(release);
        if (volist.size() == 0) {
            resultMap.put("result",0);
            return resultMap;
        }
        resultCount+=volist.size();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("handstat", 1);
        if (channelId > 0) {
            map.put("toChannel", channelId);
            for (SendingVo sendingVo : volist) {
                BasicDBObject set = new BasicDBObject();
                set.put(HistoryMongoUtil.getChannel(), channelId);
                BasicDBObject where = new BasicDBObject();
                where.put(HistoryMongoUtil.getId(), sendingVo.getHisids());
                try {
                    util.update2("sms_send_history_unknown", set, where);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        util.close();
        map.put("ids", release.getMdStrs());
        int result = mapper.updateReleaseMessage(map);
        resultMap.put("result",result);
        resultMap.put("resultCount",resultCount);
        return resultMap;
    }

    /**
     * 勾选驳回
     *
     * @param release
     * @return
     */
    @Override
    public int reject(SmsUserSendingRelease release) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("handstat", 2);
        map.put("ids", release.getMdstr().split(","));
        return mapper.updateReleaseMessage(map);
    }

    /**
     * 搜索通过
     *
     * @param release
     * @param to_channel
     * @return
     */
    @Override
    public Map<String, Integer> searchPass(SmsUserSendingRelease release, int to_channel) {
        Map<String,Integer> resultMap = new HashMap<String,Integer>();
        List<SendingVo> volist = mapper.findT(release);
        resultMap.put("resultCount",volist.size());
        if (volist.size() == 0) {
            resultMap.put("result",1);
            return resultMap;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("handstat", 1);
        if (to_channel > 0) {
            map.put("toChannel", to_channel);
            MongoDBUtil mongo = new MongoDBUtil();
            //修改历史记录
            BasicDBList rsb = new BasicDBList();
            for(SendingVo vo : volist){
                rsb.add(Long.valueOf(vo.getHisids()));
            }
            if(rsb.size()>0){
                BasicDBObject set = new BasicDBObject();
                set.put(HistoryMongoUtil.getChannel(), to_channel);
                mongo.updateBatchByHitIds("sms_send_history_unknown",new BasicDBObject("$in", rsb), set);
            }
            mongo.close();
        }
        map.put("uid", release.getUid());
        map.put("content", release.getContent());
        map.put("mtype", release.getMtype());
        map.put("channel", release.getChannel());
        //更新数据库
        int result = mapper.updateReleaseMessageBysearch(map);
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * 搜索驳回
     *
     * @param release
     * @return
     */
    @Override
    public Map<String, Integer> searchReject(SmsUserSendingRelease release) {
        Map<String,Integer> resultMap = new HashMap<String,Integer>();
        List<SendingVo> volist = mapper.findT(release);
        resultMap.put("resultCount",volist.size());
        if (volist.size() == 0) {
            resultMap.put("result",1);
            return resultMap;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("handstat", 2);
        map.put("uid", release.getUid());
        map.put("content", release.getContent());
        map.put("mtype", release.getMtype());
        map.put("channel", release.getChannel());
        int result = mapper.updateReleaseMessageBysearch(map);
        resultMap.put("result",result);
        return resultMap;

    }

    /**
     * 根据md5值查看号码详情
     *
     * @param release@return
     */
    @Override
    public JSONObject lookDetail(SmsUserSendingRelease release) {
        int count = mapper.countLookDetailTotal(release);
        List<SmsUserSendingRelease> list = mapper.lookDetail(release);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", count);
        obj.put("data", JSONArray.fromObject(list));
        return obj;
    }

    @Override
    public List<SendingVo> findT(SmsUserSendingRelease release) {
        return mapper.findT(release);
    }

    @Override
    public int updateReleaseContent(Map<String, Object> map) {
        SmsUserSendingRelease t=new SmsUserSendingRelease();
        MongoDBUtil mongo= new MongoDBUtil();
        t.setMdstr((String) map.get("mdstr"));
        List<SendingVo> list= mapper.findT(t);
        for (SendingVo sendingVo : list) {
            try {
                DBObject set=new BasicDBObject();
                DBObject where=new BasicDBObject();
                set.put(HistoryMongoUtil.getContent(), map.get("content"));
                where.put(HistoryMongoUtil.getId(), sendingVo.getHisids());
                mongo.update2("sms_send_history_unknown", set, where);
                mongo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapper.updateReleaseContent(map);
    }
}
