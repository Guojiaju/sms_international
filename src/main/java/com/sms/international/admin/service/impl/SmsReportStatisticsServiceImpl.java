package com.sms.international.admin.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sms.international.admin.mapper.SmsReportStatisticsMapper;
import com.sms.international.admin.model.SmsCountryInfos;
import com.sms.international.admin.model.SmsGatewayChannel;
import com.sms.international.admin.model.SmsReportChannel;
import com.sms.international.admin.mongodb.MongoDBUtil;
import com.sms.international.admin.service.SmsReportStatisticsService;
import com.sms.international.admin.utils.ChannelCountUtil;
import com.sms.international.admin.utils.GlobalParams;
import com.sms.international.admin.utils.MyUtils_Time;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service("smsReportStatisticsService")
public class SmsReportStatisticsServiceImpl implements SmsReportStatisticsService{
	 @Autowired
	 private SmsReportStatisticsMapper smsReportStatisticsMapper;
	
	/**
	 * 【查询网管通道信息】【无分页】
	 * @return
	 */
	@Override
	public Map<Integer,String> findGatewayChannelList(SmsGatewayChannel smsGatewayChannel) {
			Map<Integer,String> channelNameMap=new HashMap<Integer, String>();
		   List<SmsGatewayChannel> channelList=smsReportStatisticsMapper.findGatewayChannelList(smsGatewayChannel);
			 if(channelList!=null&&channelList.size()>0){
				 for(SmsGatewayChannel channelItem:channelList){
					 channelNameMap.put(channelItem.getId(),channelItem.getChannel_name());
				 }
			 }
		 return channelNameMap;
	}
	
	/**
	 * 【百分比计算】
	 * @param args
	 */
	public String getPercent(long num1,long num2){
		DecimalFormat decima=new DecimalFormat("#0.00");
		double baifen =((num1/(double)num2)*100); 
		String rate=decima.format(baifen);
		return rate;
	}
	
	
	/**
	 * 【通道实时报表】
	 */
	public JSONObject findRealTimeChannelReport(SmsReportChannel smsReportChannel){ 
		try{
			Map<Integer,String> channelNameMap=findGatewayChannelList(null);
			Map<Integer,SmsCountryInfos> countryMap=GlobalParams.COUNTRY_ID_MAP;
			MongoDBUtil mongo = new MongoDBUtil();
			BasicDBObject queryMap = new BasicDBObject();
			if(smsReportChannel!=null){
				if (smsReportChannel.getChannelid() != null && smsReportChannel.getChannelid() > 0) {
					queryMap.put(ChannelCountUtil.getCid(), smsReportChannel.getChannelid());
				}
			}
			if (smsReportChannel.getStartTime() != null && smsReportChannel.getStartTime() > 0) {
				BasicDBObject qm = new BasicDBObject();
				if (smsReportChannel.getEndTime() != null && smsReportChannel.getEndTime() > 0) {
					qm.put("$gte", smsReportChannel.getStartTime());
					qm.put("$lte", smsReportChannel.getEndTime());
				} else {
					qm.put("$gte", smsReportChannel.getStartTime());
				}
				queryMap.append(ChannelCountUtil.getTime(), qm);
			}else{
				BasicDBObject qm = new BasicDBObject();
				qm.put("$gte", Long.valueOf(MyUtils_Time.getTimeFormat(new Date(), "yyyyMMdd")));
				smsReportChannel.setStartTime(Long.valueOf(MyUtils_Time.getTimeFormat(new Date(), "yyyyMMdd")));
				queryMap.append(ChannelCountUtil.getTime(), qm);
			}
			
			BasicDBObject orderbyMap = new BasicDBObject();
			orderbyMap.put(ChannelCountUtil.getSubmit_count(), -1);
			// 统计总数
			int num = mongo.countTotal("channel_day_count", queryMap);
			List<SmsReportChannel> channelDayCountList = new ArrayList<SmsReportChannel>();
			if (num > 0) {
				List<DBObject> obj = mongo.findPageList("channel_day_count", queryMap, orderbyMap, smsReportChannel.getCurrentPageIndex(), smsReportChannel.getPageSize());
				for (DBObject o : obj) {
					SmsReportChannel sc=new SmsReportChannel();
					sc.setChannelid(o.containsField(ChannelCountUtil.getCid())? (Integer) o.get(ChannelCountUtil.getCid()):0);
					sc.setSubmit_count( o.containsField(ChannelCountUtil.getSubmit_count())? Integer.parseInt(o.get(ChannelCountUtil.getSubmit_count()).toString()):0);
					sc.setSubmit_succ( o.containsField(ChannelCountUtil.getSubmit_succ())?Integer.parseInt(o.get(ChannelCountUtil.getSubmit_succ()).toString()):0);
					sc.setSubmit_fail(o.containsField(ChannelCountUtil.getSubmit_fail())?Integer.parseInt( o.get(ChannelCountUtil.getSubmit_fail()).toString()):0);
					sc.setReport_count(o.containsField(ChannelCountUtil.getReport_count()) ? Integer.parseInt(o.get(ChannelCountUtil.getReport_count()).toString()):0);
					sc.setReport_succ(o.containsField(ChannelCountUtil.getReport_succ()) ? Integer.parseInt(o.get(ChannelCountUtil.getReport_succ()).toString()):0);
					sc.setReport_fail(o.containsField(ChannelCountUtil.getReport_fail()) ? Integer.parseInt(o.get(ChannelCountUtil.getReport_fail()).toString()):0);
					sc.setCreate_time(o.containsField(ChannelCountUtil.getTime()) ? Integer.parseInt(o.get(ChannelCountUtil.getTime()).toString()):0);
					sc.setCountryId(o.containsField(ChannelCountUtil.getCountryId())? Integer.parseInt(o.get(ChannelCountUtil.getCountryId()).toString()):0);
					if(countryMap.containsKey(sc.getCountryId())){
						sc.setCountry(countryMap.get(sc.getCountryId()).getCountry());//通道名称
					}
					sc.setTemp_time(sc.getCreate_time()+"");
					sc.setChannelName(channelNameMap.get(sc.getChannelid()));//得到通道名称
					if(sc.getSubmit_count()==0&&sc.getReport_count()==0){
						sc.setSuccess_rate("0%");
					}else if(sc.getSubmit_count()>0&&sc.getReport_count()>0){
						sc.setSuccess_rate(getPercent(sc.getReport_succ(),sc.getSubmit_succ())+"%");//设置成功率
					}
					
					if(sc.getSubmit_count()==0&&sc.getReport_fail()==0){
						sc.setFail_rate("0%");
					}else if(sc.getSubmit_count()>0&&sc.getReport_fail()>0){
						sc.setFail_rate(getPercent(sc.getReport_fail(),sc.getSubmit_succ())+"%");//失败率
					}
					sc.setUnknow_count(sc.getSubmit_succ()-sc.getReport_count());//未知量
					
					if(sc.getSubmit_count()==0&&sc.getReport_count()==0){
						sc.setUnknow_rate("0%");//未知率
					}else if(sc.getSubmit_count()>0&&sc.getReport_count()>0){
						sc.setUnknow_rate(getPercent((sc.getSubmit_succ()-sc.getReport_count()),sc.getSubmit_succ())+"%");
					}
					
					
					channelDayCountList.add(sc);
				}
			}
			 JSONObject resultJson = new JSONObject();
			 			resultJson.put("code", 0);
			 			resultJson.put("msg", 0);
			 			resultJson.put("count", num);
			 			resultJson.put("data", JSONArray.fromObject(channelDayCountList));
			return resultJson;
			
		}catch(Exception e){
			return null;
		}
	}

	//汇总通道日报表统计
	@Override
	public SmsReportChannel findChannelDaySum(SmsReportChannel smsReportChannel) {
		return smsReportStatisticsMapper.findChannelDaySum(smsReportChannel);
	}

	@Override
	public JSONObject findChannelDayList(SmsReportChannel smsReportChannel) {
		try{
			Map<Integer,String> channelNameMap=findGatewayChannelList(null);
			Integer num=smsReportStatisticsMapper.findChannelDayCount(smsReportChannel);
			 JSONObject resultJson = new JSONObject();
						 resultJson.put("code", 0);
						 resultJson.put("msg", 0);
						 resultJson.put("count", num);
		      List<SmsReportChannel> channelDayList= smsReportStatisticsMapper.findChannelDayList(smsReportChannel);
		      		for(SmsReportChannel temp:channelDayList){
		      			temp.setTemp_time(temp.getCreate_time()+"");
		      			temp.setChannelName(channelNameMap.get(temp.getChannelid()));//得到通道名称
						if(temp.getSubmit_count()==0&&temp.getReport_count()==0){
							temp.setSuccess_rate("0%");
						}else if(temp.getSubmit_count()>0&&temp.getReport_count()>0){
							temp.setSuccess_rate(getPercent(temp.getReport_succ(),temp.getSubmit_succ())+"%");//设置成功率
						}
						
						if(temp.getSubmit_count()==0&&temp.getReport_fail()==0){
							temp.setFail_rate("0%");
						}else if(temp.getSubmit_count()>0&&temp.getReport_fail()>0){
							temp.setFail_rate(getPercent(temp.getReport_fail(),temp.getSubmit_succ())+"%");//失败率
						}
						temp.setUnknow_count(temp.getSubmit_succ()-temp.getReport_count());//未知量
						
						if(temp.getSubmit_count()==0&&temp.getReport_count()==0){
							temp.setUnknow_rate("0%");//未知率
						}else if(temp.getSubmit_count()>0&&temp.getReport_count()>0){
							temp.setUnknow_rate(getPercent((temp.getSubmit_succ()-temp.getReport_count()),temp.getSubmit_succ())+"%");
						}
		      		}
		      		resultJson.put("data", JSONArray.fromObject(channelDayList));
		      return resultJson;
		}catch(Exception e){
			return null;
		}
		
	}

	
	public JSONObject findChannelMonthList(SmsReportChannel smsReportChannel){
		try{
			Map<Integer,String> channelNameMap=findGatewayChannelList(null);
			Integer num=smsReportStatisticsMapper.findChannelMonthCount(smsReportChannel);
			 JSONObject resultJson = new JSONObject();
			 resultJson.put("code", 0);
			 resultJson.put("msg", 0);
			 resultJson.put("count", num);
			 List<SmsReportChannel> channelDayList= smsReportStatisticsMapper.findChannelMonthList(smsReportChannel);
	      		for(SmsReportChannel temp:channelDayList){
	      			temp.setMonth(smsReportChannel.getMonth()+"");
	      			temp.setChannelName(channelNameMap.get(temp.getChannelid()));//得到通道名称
					if(temp.getSubmit_count()==0&&temp.getReport_count()==0){
						temp.setSuccess_rate("0%");
					}else if(temp.getSubmit_count()>0&&temp.getReport_count()>0){
						temp.setSuccess_rate(getPercent(temp.getReport_succ(),temp.getSubmit_succ())+"%");//设置成功率
					}
					
					if(temp.getSubmit_count()==0&&temp.getReport_fail()==0){
						temp.setFail_rate("0%");
					}else if(temp.getSubmit_count()>0&&temp.getReport_fail()>0){
						temp.setFail_rate(getPercent(temp.getReport_fail(),temp.getSubmit_succ())+"%");//失败率
					}
					temp.setUnknow_count(temp.getSubmit_succ()-temp.getReport_count());//未知量
					
					if(temp.getSubmit_count()==0&&temp.getReport_count()==0){
						temp.setUnknow_rate("0%");//未知率
					}else if(temp.getSubmit_count()>0&&temp.getReport_count()>0){
						temp.setUnknow_rate(getPercent((temp.getSubmit_succ()-temp.getReport_count()),temp.getSubmit_succ())+"%");
					}
	      		}
	      		resultJson.put("data", JSONArray.fromObject(channelDayList));
			 return resultJson;
		}catch(Exception e){
			return null;
		}
	}
	
	//月报表汇总统计
	public SmsReportChannel findChannelMonthSum(SmsReportChannel smsReportChannel){
		return smsReportStatisticsMapper.findChannelMonthSum(smsReportChannel);
	}
	
	/**
	 * 【查询通道单日详情信息国家】
	 */
	public JSONObject findChannelDayDetail(SmsReportChannel smsReportChannel){
		try{
			Map<Integer,SmsCountryInfos> countryMap=GlobalParams.COUNTRY_ID_MAP;
			Map<Integer,String> channelNameMap=findGatewayChannelList(null);
			Integer num=smsReportStatisticsMapper.findChannelDayDetailCount(smsReportChannel);
			 JSONObject resultJson = new JSONObject();
						 resultJson.put("code", 0);
						 resultJson.put("msg", 0);
						 resultJson.put("count", num);
		      List<SmsReportChannel> channelDayList= smsReportStatisticsMapper.findChannelDayDetailList(smsReportChannel);
		      		for(SmsReportChannel temp:channelDayList){
		      			temp.setTemp_time(temp.getCreate_time()+"");
		      			temp.setChannelName(channelNameMap.get(temp.getChannelid()));//得到通道名称
						if(temp.getSubmit_count()==0&&temp.getReport_count()==0){
							temp.setSuccess_rate("0%");
						}else if(temp.getSubmit_count()>0&&temp.getReport_count()>0){
							temp.setSuccess_rate(getPercent(temp.getReport_succ(),temp.getSubmit_succ())+"%");//设置成功率
						}
						if(countryMap.containsKey(temp.getCountryId())){
							temp.setCountry(countryMap.get(temp.getCountryId()).getCountry());
						}
						if(temp.getSubmit_count()==0&&temp.getReport_fail()==0){
							temp.setFail_rate("0%");
						}else if(temp.getSubmit_count()>0&&temp.getReport_fail()>0){
							temp.setFail_rate(getPercent(temp.getReport_fail(),temp.getSubmit_succ())+"%");//失败率
						}
						temp.setUnknow_count(temp.getSubmit_succ()-temp.getReport_count());//未知量
						
						if(temp.getSubmit_count()==0&&temp.getReport_count()==0){
							temp.setUnknow_rate("0%");//未知率
						}else if(temp.getSubmit_count()>0&&temp.getReport_count()>0){
							temp.setUnknow_rate(getPercent((temp.getSubmit_succ()-temp.getReport_count()),temp.getSubmit_succ())+"%");
						}
		      		}
		      		resultJson.put("data", JSONArray.fromObject(channelDayList));
		      return resultJson;
		}catch(Exception e){
			return null;
		}
	}
	
	
	
	
	/**
	 * 【通道月详情信息国家】
	 */
	public JSONObject findChannelMonthDetail(SmsReportChannel smsReportChannel){
		try{
			Map<Integer,SmsCountryInfos> countryMap=GlobalParams.COUNTRY_ID_MAP;
			Map<Integer,String> channelNameMap=findGatewayChannelList(null);
			Integer num=smsReportStatisticsMapper.findChannelMonthDetailCount(smsReportChannel);
			 JSONObject resultJson = new JSONObject();
			 resultJson.put("code", 0);
			 resultJson.put("msg", 0);
			 resultJson.put("count", num);
			 List<SmsReportChannel> channelDayList= smsReportStatisticsMapper.findChannelMonthDetailList(smsReportChannel);
	      		for(SmsReportChannel temp:channelDayList){
	      			if(countryMap.containsKey(temp.getCountryId())){
						temp.setCountry(countryMap.get(temp.getCountryId()).getCountry());
					}
	      			temp.setMonth(smsReportChannel.getMonth()+"");
	      			temp.setChannelName(channelNameMap.get(temp.getChannelid()));//得到通道名称
					if(temp.getSubmit_count()==0&&temp.getReport_count()==0){
						temp.setSuccess_rate("0%");
					}else if(temp.getSubmit_count()>0&&temp.getReport_count()>0){
						temp.setSuccess_rate(getPercent(temp.getReport_succ(),temp.getSubmit_succ())+"%");//设置成功率
					}
					
					if(temp.getSubmit_count()==0&&temp.getReport_fail()==0){
						temp.setFail_rate("0%");
					}else if(temp.getSubmit_count()>0&&temp.getReport_fail()>0){
						temp.setFail_rate(getPercent(temp.getReport_fail(),temp.getSubmit_succ())+"%");//失败率
					}
					temp.setUnknow_count(temp.getSubmit_succ()-temp.getReport_count());//未知量
					
					if(temp.getSubmit_count()==0&&temp.getReport_count()==0){
						temp.setUnknow_rate("0%");//未知率
					}else if(temp.getSubmit_count()>0&&temp.getReport_count()>0){
						temp.setUnknow_rate(getPercent((temp.getSubmit_succ()-temp.getReport_count()),temp.getSubmit_succ())+"%");
					}
	      		}
	      		resultJson.put("data", JSONArray.fromObject(channelDayList));
			 return resultJson;
		}catch(Exception e){
			return null;
		}
		
	}
	
	
}
