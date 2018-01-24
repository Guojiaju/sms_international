package com.sms.international.admin.service.impl;

import com.mongodb.BasicDBObject;
import com.sms.international.admin.mapper.SmsChannelMapper;
import com.sms.international.admin.mapper.SmsUserSendingMapper;
import com.sms.international.admin.model.SmsChannel;
import com.sms.international.admin.model.SmsUserSending;
import com.sms.international.admin.mongodb.HistoryMongoUtil;
import com.sms.international.admin.mongodb.MongoDBUtil;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsUserSendingService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SmsUserSendingServiceImpl extends BaseService<SmsUserSending, SmsUserSendingMapper> implements SmsUserSendingService {

	@Autowired
	private SmsChannelMapper smsChannelMapper;

    @Override
    public JSONObject findByPage(SmsUserSending smsUserSending) {
        int num = mapper.countTotal(smsUserSending);
        List<SmsUserSending> list = mapper.findByT(smsUserSending);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        obj.put("data", JSONArray.fromObject(list));

        return obj;
    }

    @Override
	public int batchUpdate(Map map) {
		//判断是否是切换通道操作，是则判断切换的通道是否暂停
		if(map.containsKey("toChannel")){
			int channel = Integer.parseInt(map.get("toChannel").toString());
			//通道状态暂停不操作
			SmsChannel s = smsChannelMapper.findOne(channel);
			if(s.getStatus()!=0){
				return -1;
			}
		}
		return mapper.batchUpdate(map);
	}

	@Override
	public int update(Map<String, Object> map) {
		MongoDBUtil mongo = new MongoDBUtil();
		try {
			BasicDBObject set = new BasicDBObject();
			set.put(HistoryMongoUtil.getContent(), map.get("changeContent"));
			BasicDBObject where = new BasicDBObject();
			where.put(HistoryMongoUtil.getUid(),map.get("uid"));
			where.put(HistoryMongoUtil.getContent(),map.get("oldContent"));
			mongo.update2("sms_send_history_unknown", set, where);
			mongo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapper.update(map);
	}

    @Override
    public void updateMongoSendingHistory(List<SmsUserSending> list,Map<String,Object> map) {
        MongoDBUtil mongo = new MongoDBUtil();
        try {
            for(SmsUserSending item : list){
                BasicDBObject where = new BasicDBObject();
                where.put(HistoryMongoUtil.getId(), Long.valueOf(item.getHisids()));
                BasicDBObject set = new BasicDBObject();
                set.put(HistoryMongoUtil.getChannel(), map.get("channel"));

				mongo.update2("sms_send_history_unknown", set, where);
			}
		}catch (Exception e) {
            e.printStackTrace();
        }finally {
            mongo.close();
        }
    }

    @Override
	public List<SmsUserSending> findByIdsToPart(SmsUserSending smsUserSending) {
		return mapper.findByIdsToPart(smsUserSending);
	}
}
