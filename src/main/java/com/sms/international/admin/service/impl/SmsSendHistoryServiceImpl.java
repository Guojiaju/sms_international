package com.sms.international.admin.service.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sms.hy.cmpp.vo.SendingVo;
import com.sms.international.admin.mapper.ReCountedToUidMapper;
import com.sms.international.admin.mapper.SmsChannelMapper;
import com.sms.international.admin.mapper.SmsSendHistoryMapper;
import com.sms.international.admin.model.SmsChannel;
import com.sms.international.admin.model.SmsSendHistory;
import com.sms.international.admin.mongodb.HistoryMongoUtil;
import com.sms.international.admin.mongodb.MongoDBUtil;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsSendHistoryService;
import com.sms.international.admin.utils.DateUtil;
import com.sms.international.admin.utils.HistoryReportUtil;
import com.sms.international.admin.utils.Page;
import com.sms.international.admin.utils.RabbitMQProducerUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Author guojiaju
 * Date 2017/12/6
 * Description 发送记录接口实现类
 */
@Service
@Transactional
public class SmsSendHistoryServiceImpl extends BaseService<SmsSendHistory,SmsSendHistoryMapper> implements SmsSendHistoryService {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(SmsSendHistoryServiceImpl.class);

    @Autowired
	private SmsSendHistoryMapper smsSendHistoryMapper;
	@Autowired
	private ReCountedToUidMapper reCountedToUidMapper;
    @Autowired
    private SmsChannelMapper smsChannelMapper;

	/**
	 * 查询3天内的发送记录
	 *
	 * @param history
	 * @return
	 */
	@Override
	public JSONObject findByPageIn3(SmsSendHistory history) {
		MongoDBUtil util = new MongoDBUtil();
		BasicDBObject queryMap = new BasicDBObject();
		if (history.getUid() != null && history.getUid() > 0) {
			queryMap.put(HistoryMongoUtil.getUid(), history.getUid());
		}
		if (history.getHisId() != null && history.getHisId() > 0) {
			queryMap.put(HistoryMongoUtil.getId(), history.getHisId());
		}
		if (history.getStartTime() != null && history.getStartTime() > 0) {
			BasicDBObject qm = new BasicDBObject();
			if (history.getEndTime() != null && history.getEndTime() > 0) {
				qm.put("$gte", history.getStartTime());
				qm.put("$lte", history.getEndTime());
			} else {
				qm.put("$gte", history.getStartTime());
			}
			queryMap.append(HistoryMongoUtil.getSenddate(), qm);
		}
		if (history.getMobile() != null && history.getMobile() > 0) {
			queryMap.put(HistoryMongoUtil.getMobile(), history.getMobile());
		}
		if (history.getChannel() != null && history.getChannel() > 0) {
			queryMap.put(HistoryMongoUtil.getChannel(), history.getChannel());
		}
		if (history.getContent() != null && history.getContent().trim().length() > 0) {
			Pattern pattern = Pattern.compile("^.*" + history.getContent() + ".*$", Pattern.CASE_INSENSITIVE);
			BasicDBObject qm = new BasicDBObject();
			qm.put("$regex", pattern);
			qm.put("$options", "mi");
			queryMap.put(HistoryMongoUtil.getContent(), qm);
		}
		if (history.getStat() != null) {
			queryMap.put(HistoryMongoUtil.getStat(), history.getStat());
		}
		if (history.getMtstat() != null && !"".equals(history.getMtstat())) {
			queryMap.put(HistoryMongoUtil.getMtStat(), history.getMtstat());
		}
		if (history.getPid() != null && history.getPid() > 0) {
			queryMap.put(HistoryMongoUtil.getPid(), history.getPid());
		}
		if (history.getExpid() != null) {
			queryMap.put(HistoryMongoUtil.getExpid(), history.getExpid());
		}
		if (history.getLocation() !=null && !"".equals(history.getLocation())){
			queryMap.put(HistoryMongoUtil.getLocation(),history.getLocation());
		}
		if (history.getSucc() != null) {
			BasicDBObject qm = new BasicDBObject();
			if (history.getSucc() == 1) {
				qm.put("$gt", 0);
				queryMap.append(HistoryMongoUtil.getArrive_succ(), qm);
			} else if (history.getSucc() == -1) {
				qm.put("$gt", 0);
				queryMap.append(HistoryMongoUtil.getArrive_fail(), qm);
			} else {
				queryMap.append(HistoryMongoUtil.getArrive_succ(), 0);
				queryMap.append(HistoryMongoUtil.getArrive_fail(), 0);
			}
		}

		BasicDBObject orderbyMap = new BasicDBObject();
		orderbyMap.put(HistoryMongoUtil.getSenddate(), -1);
		// 统计总数
		List<SmsSendHistory> historys = new ArrayList<SmsSendHistory>();
		int num = util.countTotal("sms_send_history_unknown", queryMap);
		if (num > 0) {
			List<DBObject> obj = util.findPageList("sms_send_history_unknown", queryMap, orderbyMap, history.getCurrentPageIndex(), history.getPageSize());
			for (DBObject o : obj) {
				SmsSendHistory h = new SmsSendHistory();
				h.setId(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getId()))));
				h.setSenddate(Long.parseLong(String.valueOf(o.get(HistoryMongoUtil.getSenddate()))));
				h.setUid(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getUid()))));
				h.setMobile(Long.parseLong(String.valueOf(o.get(HistoryMongoUtil.getMobile()))));
				h.setChannel(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getChannel()))));
				h.setContent(String.valueOf(o.get(HistoryMongoUtil.getContent())));
				h.setContentNum(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getContentNum()))));
				h.setGrade(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getGrade()))));
				h.setMtstat(String.valueOf(o.get(HistoryMongoUtil.getMtStat())));
				h.setPid(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getPid()))));
				h.setExpid(String.valueOf(o.get(HistoryMongoUtil.getExpid())));
				h.setStat(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getStat()))));
				h.setLocation(String.valueOf(o.get(HistoryMongoUtil.getLocation())));
				h.setSucc(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getSucc()))));
				h.setFail(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getFail()))));
				h.setArrive_succ(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getArrive_succ()))));
				h.setArrive_fail(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getArrive_fail()))));
				historys.add(h);
			}
		}
		util.close();
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", 0);
		obj.put("count", num);
		obj.put("data", JSONArray.fromObject(historys));
		return obj;
	}

	/**
     * 查询发送记录
     *
     * @param smsSendHistory
     * @return
     */
    @Override
    public JSONObject findByPage(SmsSendHistory smsSendHistory) {
        int num = mapper.countTotal(smsSendHistory);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        List<SmsSendHistory> histories = mapper.findByT(smsSendHistory);
        obj.put("data", JSONArray.fromObject(histories));
        return obj;
    }

    @Override
    public JSONObject findResendByPage(SmsSendHistory t) {
        MongoDBUtil util = new MongoDBUtil();

		Page<SmsSendHistory> pageList = new Page<SmsSendHistory>();

		BasicDBObject queryMap = new BasicDBObject();
		if (t.getUid() != null)
			queryMap.put(HistoryMongoUtil.getUid(), t.getUid());
		if (t.getHisId() != null)
			queryMap.put(HistoryMongoUtil.getId(), t.getHisId());

		BasicDBObject timeQm = new BasicDBObject();
		if (t.getStartTime() != null)
            timeQm.put("$gte", t.getStartTime());
		if(t.getEndTime() != null)
		    timeQm.put("$lte", t.getEndTime());
		queryMap.append(HistoryMongoUtil.getSenddate(), timeQm);

		if (t.getMobile() != null)
			queryMap.put(HistoryMongoUtil.getMobile(), t.getMobile());
		if (t.getChannel() != null)
			queryMap.put(HistoryMongoUtil.getChannel(), t.getChannel());
		if (StringUtils.isNotBlank(t.getContent())) {
			Pattern pattern = Pattern.compile("^.*" + t.getContent() + ".*$", Pattern.CASE_INSENSITIVE);
			BasicDBObject qm = new BasicDBObject();
			qm.put("$regex", pattern);
			qm.put("$options", "mi");
			queryMap.put(HistoryMongoUtil.getContent(), qm);
		}
		if (t.getStat() != null)
			queryMap.put(HistoryMongoUtil.getStat(), t.getStat());
		if (t.getMtstat() != null && !"".equals(t.getMtstat()))
			queryMap.put(HistoryMongoUtil.getMtStat(), t.getMtstat());
		if (t.getSucc() != null) {
			BasicDBObject qm = new BasicDBObject();
			if (t.getSucc() == 1) {
				qm.put("$gt", 0);
				queryMap.append(HistoryMongoUtil.getArrive_succ(), qm);
			} else if (t.getSucc() == -1) {
				qm.put("$gt", 0);
				queryMap.append(HistoryMongoUtil.getArrive_fail(), qm);
			} else {
				queryMap.append(HistoryMongoUtil.getArrive_succ(), 0);
				queryMap.append(HistoryMongoUtil.getArrive_fail(), 0);
			}
		}

		BasicDBObject orderbyMap = new BasicDBObject();
		orderbyMap.put(HistoryMongoUtil.getSenddate(), -1);
		// 统计总数
		int num = util.countTotal("sms_send_history_unknown", queryMap);
		List<DBObject> mongoList = util.findPageList("sms_send_history_unknown", queryMap, orderbyMap, t.getCurrentPageIndex(), t.getPageSize());

		List<SmsSendHistory> result = new ArrayList<SmsSendHistory>();
        for (DBObject o : mongoList) {
            SmsSendHistory h = new SmsSendHistory();
            h.setId(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getId()))));
            h.setSenddate(Long.parseLong(String.valueOf(o.get(HistoryMongoUtil.getSenddate()))));
            h.setUid(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getUid()))));
            h.setMobile(Long.parseLong(String.valueOf(o.get(HistoryMongoUtil.getMobile()))));
            h.setChannel(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getChannel()))));
            h.setContent(String.valueOf(o.get(HistoryMongoUtil.getContent())));
            h.setContentNum(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getContentNum()))));
            h.setGrade(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getGrade()))));
            h.setMtstat(String.valueOf(o.get(HistoryMongoUtil.getMtStat())));
            h.setPid(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getPid()))));
            h.setExpid(String.valueOf(o.get(HistoryMongoUtil.getExpid())));
            h.setStat(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getStat()))));
            h.setLocation(String.valueOf(o.get(HistoryMongoUtil.getLocation())));
            h.setSucc(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getSucc()))));
            h.setFail(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getFail()))));
            h.setArrive_succ(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getArrive_succ()))));
            h.setArrive_fail(Integer.parseInt(String.valueOf(o.get(HistoryMongoUtil.getArrive_fail()))));
            result.add(h);
        }

        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        obj.put("data", JSONArray.fromObject(result));
		util.close();
        return obj;
    }

    @Override
    public int resend(SmsSendHistory smsSendHistory, SmsChannel smsChannel) {
        int result = 1;
		MongoDBUtil mongo = new MongoDBUtil();
		RabbitMQProducerUtil util = new RabbitMQProducerUtil();
		try {
            if(smsSendHistory.getIds().length == 0){
                    return 0;
            }
            String[] ids = smsSendHistory.getIds();
            Map<Integer, Integer> uids = new HashMap<Integer, Integer>();

		    List<SmsSendHistory> list=new ArrayList<SmsSendHistory>();
            BasicDBList rsb = new BasicDBList();
			for (int i = 0; i < ids.length; i++) {
				long id = Long.parseLong(ids[i]);

				BasicDBObject obj = new BasicDBObject();

				obj.put(HistoryMongoUtil.getId(), id);

				DBObject db = mongo.findOne("sms_send_history_unknown", obj);

				uids.put(Integer.parseInt(db.get("e").toString()), 1);
				rsb.add(id);

				int channel = smsSendHistory.getChannel() == null ? (Integer) db.get(HistoryMongoUtil.getChannel()) : smsSendHistory.getChannel();

			    SendingVo vo = new SendingVo();
				vo.setId(id);
				vo.setUid((Integer) db.get(HistoryMongoUtil.getUid()));
				vo.setContent((String) db.get(HistoryMongoUtil.getContent()));
				vo.setChannel(channel);
				vo.setMobile((Long) db.get(HistoryMongoUtil.getMobile()));
				vo.setContentNum((Integer) db.get(HistoryMongoUtil.getContentNum()));
				vo.setLocation((String) db.get(HistoryMongoUtil.getLocation()));
				vo.setGrade((Integer) db.get(HistoryMongoUtil.getGrade()));
				vo.setPid((Integer) db.get(HistoryMongoUtil.getPid()));
				vo.setMtype((Integer) db.get(HistoryMongoUtil.getMtype()));
				vo.setExpid((String) db.get(HistoryMongoUtil.getExpid()));
				vo.setSenddate((Long) db.get(HistoryMongoUtil.getSenddate()));

				if(smsChannel != null){
					//如果通道不正常
					if(smsChannel.getStatus() != 0){
						SmsSendHistory t = new SmsSendHistory();
						t.setChannel(channel);
						t.setContent((String) db.get(HistoryMongoUtil.getContent()));
						t.setContentNum((Integer) db.get(HistoryMongoUtil.getContentNum()));
						t.setHisId((Long) db.get(HistoryMongoUtil.getId()));
						t.setUid((Integer) db.get(HistoryMongoUtil.getUid()));
						t.setExpid((String) db.get(HistoryMongoUtil.getExpid()));
						t.setGrade((Integer) db.get(HistoryMongoUtil.getGrade()));
						t.setSenddate((Long) db.get(HistoryMongoUtil.getSenddate()));
						t.setMobile((Long) db.get(HistoryMongoUtil.getMobile()));
						t.setPid((Integer) db.get(HistoryMongoUtil.getPid()));
						t.setLocation((String) db.get(HistoryMongoUtil.getLocation()));
						list.add(t);
					}else{
						util.send("EXAMINE_QUEUE_TEMP", vo);
					}
				}else{
					//补发到原通到
					util.send("EXAMINE_QUEUE_TEMP", vo);
				}
                logger.info("sendVo:" + JSONObject.fromObject(vo));
				BasicDBObject setobj = new BasicDBObject();
				setobj.put(HistoryMongoUtil.getSucc(), 0);
				setobj.put(HistoryMongoUtil.getFail(), 0);
				setobj.put(HistoryMongoUtil.getArrive_succ(), 0);
				setobj.put(HistoryMongoUtil.getArrive_fail(), 0);
				setobj.put(HistoryMongoUtil.getStat(), 0);
				setobj.put(HistoryMongoUtil.getChannel(), channel);
				mongo.update2("sms_send_history_unknown", setobj, obj);
			}
			//1000条提交一次，不足1000条全部提交
			if(null!=list && list.size()>0) {
				if (list.size() < 1000) {
					smsSendHistoryMapper.insertToSendingBatch(list);
				} else {
					int commitTimes;
					if (list.size() % 1000 != 0) {
						commitTimes = list.size() / 1000 + 1;
					} else {
						commitTimes = list.size() / 1000;
					}
					for (int i = 1; i <= commitTimes; i++) {
						if (1000 * (i - 1) < list.size()) {
							smsSendHistoryMapper.insertToSendingBatch(list.subList(1000 * (i - 1), 1000 * i));
						} else {
							// 剩余不足1000条全部提交
							smsSendHistoryMapper.insertToSendingBatch(list.subList(1000 * (i - 1), list.size()));
						}
					}
				}
			}
			if(uids.size() > 0){
				List<Integer> uuid = new LinkedList<Integer>();
				for (Map.Entry<Integer, Integer> entry : uids.entrySet()) {
					uuid.add(entry.getKey());
				}
				reCountedToUidMapper.insert(uuid);
			}
			if(rsb.size() > 0){//标记状态已补发,使用f字段
				BasicDBObject setobj = new BasicDBObject();
				setobj.put(HistoryReportUtil.getStatus(), 1);
				mongo.updateBatchByHitIds("sms_report", new BasicDBObject("$in", rsb), setobj);
			}
			return result;
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
		}finally {
		    mongo.close();
			util.close();
        }
        return result;
    }

    @Override
    public Map<String, Integer> resendByQuery(JSONObject jo) {
        Map<String,Integer> resultMap = new HashMap<String,Integer>();

		MongoDBUtil mongo = new MongoDBUtil();
		RabbitMQProducerUtil util = new RabbitMQProducerUtil();
		BasicDBObject queryMap = new BasicDBObject();
		BasicDBList rsb = new BasicDBList();

		if (StringUtils.isNotBlank(jo.getString("uid"))) {
			queryMap.put(HistoryMongoUtil.getUid(), jo.getInt("uid"));
		}
		BasicDBObject mq = new BasicDBObject();
		if (StringUtils.isNotBlank(jo.getString("startTime"))) {
			mq.put("$gte", jo.getLong("startTime"));
		}
		if (StringUtils.isNotBlank(jo.getString("endTime"))) {
			mq.put("$lte", jo.getLong("endTime"));
		}
		queryMap.put(HistoryMongoUtil.getSenddate(), mq);

		if (StringUtils.isNotBlank(jo.getString("mobile"))) {
			queryMap.put(HistoryMongoUtil.getMobile(), jo.getLong("mobile"));
		}
		if (StringUtils.isNotBlank(jo.getString("channel"))) {
			queryMap.put(HistoryMongoUtil.getChannel(), jo.getInt("channel"));
		}
		if (StringUtils.isNotBlank(jo.getString("content"))) {
			Pattern pattern = Pattern.compile("^.*" + jo.getString("content") + ".*$", Pattern.CASE_INSENSITIVE);
			BasicDBObject qm = new BasicDBObject();
			qm.put("$regex", pattern);
			qm.put("$options", "mi");
			queryMap.put(HistoryMongoUtil.getContent(), qm);
		}
		if (StringUtils.isNotBlank("stat")) {
			queryMap.put(HistoryMongoUtil.getStat(), jo.getInt("stat"));
		}
		if (StringUtils.isNotBlank(jo.getString("mtstat"))) {
			queryMap.put(HistoryMongoUtil.getMtStat(), jo.getInt("mtstat"));
		}
		if (StringUtils.isNotBlank(jo.getString("succ"))) {
		    int succ = jo.getInt("succ");
			BasicDBObject qm = new BasicDBObject();
			if (succ == 1) {
				qm.put("$gt", 0);
				queryMap.append(HistoryMongoUtil.getArrive_succ(), qm);
			} else if (succ == -1) {
				qm.put("$gt", 0);
				queryMap.append(HistoryMongoUtil.getArrive_fail(), qm);
			} else {
				queryMap.append(HistoryMongoUtil.getArrive_succ(), 0);
				queryMap.append(HistoryMongoUtil.getArrive_fail(), 0);
			}
		}
		List<DBObject> obj;
		try {
			obj = mongo.find("sms_send_history_unknown", queryMap);
            resultMap.put("count" , obj.size());
			//通道状态暂停就放到队里表
			Map<Integer, Integer> uids = new HashMap<Integer, Integer>();
            SmsChannel s = null;
            if(StringUtils.isNotBlank(jo.getString("changeChannel"))){
                 s = smsChannelMapper.findOne(jo.getInt("changeChannel"));
            }
			if(s != null){
				List<SmsSendHistory> list = new ArrayList<SmsSendHistory>();
				  if(s.getStatus() != 0 && obj.size() > 0){
					  for (DBObject db : obj) {
						  rsb.add(db.get("_id"));
						  uids.put(Integer.parseInt(db.get("e").toString()), 1);
						  SmsSendHistory t=new SmsSendHistory();
						  t.setChannel(s.getId());
						  t.setContent((String) db.get(HistoryMongoUtil.getContent()));
						  t.setContentNum((Integer) db.get(HistoryMongoUtil.getContentNum()));
						  t.setHisId((Long) db.get(HistoryMongoUtil.getId()));
						  t.setUid((Integer) db.get(HistoryMongoUtil.getUid()));
						  t.setExpid((String) db.get(HistoryMongoUtil.getExpid()));
						  t.setGrade((Integer) db.get(HistoryMongoUtil.getGrade()));
						  t.setSenddate((Long) db.get(HistoryMongoUtil.getSenddate()));
						  t.setMobile((Long) db.get(HistoryMongoUtil.getMobile()));
						  t.setPid((Integer) db.get(HistoryMongoUtil.getPid()));
						  t.setLocation((String) db.get(HistoryMongoUtil.getLocation()));
						  list.add(t);

						  if(list.size() >= 200){
							  smsSendHistoryMapper.insertToSendingBatch(list);
							  BasicDBObject setobj = new BasicDBObject();
							  setobj.put(HistoryMongoUtil.getSucc(), 0);
							  setobj.put(HistoryMongoUtil.getFail(), 0);
							  setobj.put(HistoryMongoUtil.getArrive_succ(), 0);
							  setobj.put(HistoryMongoUtil.getArrive_fail(), 0);
							  setobj.put(HistoryMongoUtil.getStat(), 0);
							  mongo.updateBatchByIDs("sms_send_history_unknown", new BasicDBObject("$in", rsb), setobj);
							  list.clear();
							  rsb.clear();
						  }
					  }
					  if(uids.size() > 0){
							List<Integer> uuid = new LinkedList<Integer>();
							for (Map.Entry<Integer, Integer> entry : uids.entrySet()) {
								uuid.add(entry.getKey());
							}
							reCountedToUidMapper.insert(uuid);
						}
					  if(list.size() > 0){
						  smsSendHistoryMapper.insertToSendingBatch(list);
						  BasicDBObject setobj = new BasicDBObject();
						  setobj.put(HistoryMongoUtil.getSucc(), 0);
						  setobj.put(HistoryMongoUtil.getFail(), 0);
						  setobj.put(HistoryMongoUtil.getArrive_succ(), 0);
						  setobj.put(HistoryMongoUtil.getArrive_fail(), 0);
						  setobj.put(HistoryMongoUtil.getStat(), 0);
						  mongo.updateBatchByIDs("sms_send_history_unknown", new BasicDBObject("$in", rsb), setobj);
					  }
                      resultMap.put("result" , 1);
					  return resultMap;
				  }
			}

			for (DBObject db : obj) {
				uids.put(Integer.parseInt(db.get("e").toString()), 1);
				int channel = (Integer) db.get(HistoryMongoUtil.getChannel());
				SendingVo vo = new SendingVo();
				vo.setId((Long) db.get(HistoryMongoUtil.getId()));
				vo.setUid((Integer) db.get(HistoryMongoUtil.getUid()));
				vo.setContent((String) db.get(HistoryMongoUtil.getContent()));
                if(s == null)
                    vo.setChannel(channel);
                else
                    vo.setChannel(s.getId());
				vo.setMobile((Long) db.get(HistoryMongoUtil.getMobile()));
				vo.setContentNum((Integer) db.get(HistoryMongoUtil.getContentNum()));
				vo.setLocation((String) db.get(HistoryMongoUtil.getLocation()));
				vo.setGrade((Integer) db.get(HistoryMongoUtil.getGrade()));
				vo.setPid((Integer) db.get(HistoryMongoUtil.getPid()));
				vo.setMtype((Integer) db.get(HistoryMongoUtil.getMtype()));
				vo.setExpid((String) db.get(HistoryMongoUtil.getExpid()));
				vo.setSenddate((Long) db.get(HistoryMongoUtil.getSenddate()));
				util.send("EXAMINE_QUEUE_TEMP", vo);
				BasicDBObject setobj = new BasicDBObject();
				setobj.put(HistoryMongoUtil.getSucc(), 0);
				setobj.put(HistoryMongoUtil.getFail(), 0);
				setobj.put(HistoryMongoUtil.getArrive_succ(), 0);
				setobj.put(HistoryMongoUtil.getArrive_fail(), 0);
				setobj.put(HistoryMongoUtil.getStat(), 0);
				if(s != null){
					setobj.put(HistoryMongoUtil.getChannel(), s.getId());
				}else{
					setobj.put(HistoryMongoUtil.getChannel(), channel);
				}
				BasicDBObject whereobj = new BasicDBObject();
				whereobj.put(HistoryMongoUtil.getId(), (Long) db.get(HistoryMongoUtil.getId()));
				mongo.update2("sms_send_history_unknown", setobj, whereobj);
			}
			if(uids.size() > 0){
				List<Integer> uuid = new LinkedList<Integer>();
				for (Map.Entry<Integer, Integer> entry : uids.entrySet()) {
					uuid.add(entry.getKey());
				}
				reCountedToUidMapper.insert(uuid);
			}
			if(rsb.size() > 0){//标记状态已补发,使用f字段
				BasicDBObject setobj = new BasicDBObject();
				setobj.put(HistoryReportUtil.getStatus(), 1);
				mongo.updateBatchByHitIds("sms_report", new BasicDBObject("$in", rsb), setobj);
			}
			resultMap.put("result" , 1);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    mongo.close();
		    util.close();
        }
        resultMap.put("result" , 0);
        return resultMap;
    }

	/**
	 * 查询3天内的详情
	 *
	 * @param smsSendHistory
	 * @return
	 */
	@Override
	public JSONObject findDetailIn3(SmsSendHistory smsSendHistory) {
		MongoDBUtil util = new MongoDBUtil();
		BasicDBObject queryMap = new BasicDBObject();
		if (smsSendHistory.getUid() != null && smsSendHistory.getUid() > 0) {
			queryMap.put(HistoryReportUtil.getUid(), smsSendHistory.getUid());
		}
		if (smsSendHistory.getHisId() != null && smsSendHistory.getHisId() > 0) {
			queryMap.put(HistoryReportUtil.getHisId(), smsSendHistory.getHisId());
		}
		if (smsSendHistory.getPid() != null && smsSendHistory.getPid() > 0) {
			queryMap.put(HistoryReportUtil.getPid(), smsSendHistory.getPid());
		}

		BasicDBObject orderbyMap = new BasicDBObject();
		orderbyMap.put(HistoryReportUtil.getHisId(), -1);
		// 统计总数
		int num = util.countTotal("sms_report", queryMap);
		List<SmsSendHistory> historys = new ArrayList<SmsSendHistory>();
		if (num > 0) {
			List<DBObject> obj = util.findPageList("sms_report", queryMap, orderbyMap, smsSendHistory.getCurrentPageIndex(), smsSendHistory.getPageSize());

			for (DBObject o : obj) {
				SmsSendHistory h = new SmsSendHistory();
				h.setId(Integer.parseInt(String.valueOf(o.get(HistoryReportUtil.getHisId()))));
				h.setRptstat(String.valueOf(o.get(HistoryReportUtil.getStat())));
				h.setMobile(Long.valueOf(String.valueOf(o.get(HistoryReportUtil.getMobile()))));
				h.setUid(Integer.parseInt(String.valueOf(o.get(HistoryReportUtil.getUid()))));
				h.setRpttime(DateUtil.getDateString(String.valueOf(o.get(HistoryReportUtil.getRpt_time()))));
				h.setChannel(Integer.parseInt(String.valueOf(o.containsField(HistoryReportUtil.getChannel()) ? o.get(HistoryReportUtil.getChannel()) : "0")));
				h.setSenddate((DateUtil.getTime(Long.valueOf(String.valueOf(o.get(HistoryReportUtil.getRpt_time()))))-DateUtil.getTime(smsSendHistory.getSenddate()))/1000);
				historys.add(h);
			}
		}
		util.close();
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", 0);
		obj.put("count", num);
		obj.put("data", JSONArray.fromObject(historys));
		return obj;
	}
}
