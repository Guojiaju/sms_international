package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsUserReportStatisticsMapper;
import com.sms.international.admin.model.SmsCountryInfos;
import com.sms.international.admin.model.SmsUserReport;
import com.sms.international.admin.service.SmsUserReportService;
import com.sms.international.admin.utils.GlobalParams;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * 【用户报表】
 * @author yannannan
 * @date 2017-12-13
 * @desc
 */
@Service("smsUserReportService")
public class SmsUserReportServiceImpl implements SmsUserReportService{
	@Autowired
	private SmsUserReportStatisticsMapper smsUserReportStatisticsMapper;
	
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
	
	@Override
	public JSONObject findSmsUserDayReportList(SmsUserReport smsUserReport) {
		try{
			Integer num=smsUserReportStatisticsMapper.findSmsUserDayReportCount(smsUserReport);
			 JSONObject resultJson = new JSONObject();
			 resultJson.put("code", 0);
			 resultJson.put("msg", 0);
			 resultJson.put("count", num);
			 List<SmsUserReport> smsUserReportList=smsUserReportStatisticsMapper.findSmsUserDayReportList(smsUserReport);
			 for(SmsUserReport temp:smsUserReportList){
				 if(temp.getTotal()!=null&&temp.getTotal()>0){
					 temp.setSubmit_success(temp.getTotal()-temp.getFail());//提交成功数
					 temp.setSuccess_rate(getPercent(temp.getArrive_succ(),temp.getTotal())+"%");//成功率
					 temp.setFail_rate(getPercent(temp.getArrive_fail(),temp.getTotal())+"%");//失败率
					 
					 //无结果
					 if((temp.getTotal()-temp.getFail()-temp.getArrive_succ()-temp.getArrive_fail())>=0){
						 temp.setNorpt_count(temp.getTotal()-temp.getFail()-temp.getArrive_succ()-temp.getArrive_fail());
						 temp.setUnknow_rate(getPercent(temp.getNorpt_count(),temp.getTotal())+"%");//未知率
					 }else{
						 temp.setNorpt_count(0);
						 temp.setUnknow_rate(0+"%");
					 }
				 }
				
			 }
			 resultJson.put("data", JSONArray.fromObject(smsUserReportList));
			 return resultJson;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 【用户日报表详情】
	 */
	public JSONObject findUserDayDetail(SmsUserReport smsUserReport){
		try{
			Map<Integer,SmsCountryInfos> countryMap=GlobalParams.COUNTRY_ID_MAP;
			Integer num=smsUserReportStatisticsMapper.findUserDayDetailCount(smsUserReport);
			 JSONObject resultJson = new JSONObject();
			 resultJson.put("code", 0);
			 resultJson.put("msg", 0);
			 resultJson.put("count", num);
			 List<SmsUserReport> smsUserReportList=smsUserReportStatisticsMapper.findUserDayDetailList(smsUserReport);
			 for(SmsUserReport temp:smsUserReportList){
				 if(countryMap.containsKey(temp.getCountryId())){
					 temp.setCountry(countryMap.get(temp.getCountryId()).getCountry());
				 }
				 if(temp.getTotal()!=null&&temp.getTotal()>0){
					 temp.setSubmit_success(temp.getTotal()-temp.getFail());//提交成功数
					 temp.setSuccess_rate(getPercent(temp.getArrive_succ(),temp.getTotal())+"%");//成功率
					 temp.setFail_rate(getPercent(temp.getArrive_fail(),temp.getTotal())+"%");//失败率
					 //无结果
					 if(countryMap.containsKey(temp.getCountryId())){
						 temp.setCountry(countryMap.get(temp.getCountryId()).getCountry());//国家名称
					 }
					 
					 if((temp.getTotal()-temp.getFail()-temp.getArrive_succ()-temp.getArrive_fail())>=0){
						 temp.setNorpt_count(temp.getTotal()-temp.getFail()-temp.getArrive_succ()-temp.getArrive_fail());
						 temp.setUnknow_rate(getPercent(temp.getNorpt_count(),temp.getTotal()));//未知率
					 }else{
						 temp.setNorpt_count(0);
						 temp.setUnknow_rate(0+"%");
					 }
				 }
				
			 }
			 resultJson.put("data", JSONArray.fromObject(smsUserReportList));
			 return resultJson;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public SmsUserReport findSmsUserDayReportSum(SmsUserReport smsUserReport) {
		return smsUserReportStatisticsMapper.findSmsUserDayReportSum(smsUserReport);
	}

	@Override
	public SmsUserReport findSmsUserDayReportSumSend(SmsUserReport smsUserReport) {
		return smsUserReportStatisticsMapper.findSmsUserDayReportSumSend(smsUserReport);
	}
	
	
	
	
	/*********【用户月报表】**********/
	public JSONObject findSmsUserMonthReportList(SmsUserReport smsUserReport){
			try{
				Integer num=smsUserReportStatisticsMapper.findSmsUserMonthReportCount(smsUserReport);
				 JSONObject resultJson = new JSONObject();
				 resultJson.put("code", 0);
				 resultJson.put("msg", 0);
				 resultJson.put("count", num);
			List<SmsUserReport> userReportMonthList=smsUserReportStatisticsMapper.findSmsUserMonthReportList(smsUserReport);
			for(SmsUserReport tempMonth:userReportMonthList){
				tempMonth.setMonth(smsUserReport.getMonth());
				tempMonth.setFail_rate(getPercent(tempMonth.getFail(),tempMonth.getTotal())+"%");
				tempMonth.setSuccess_rate(getPercent(tempMonth.getArrive_succ(),tempMonth.getTotal())+"%");
				tempMonth.setArrive_rate(getPercent(tempMonth.getArrive_fail(),tempMonth.getTotal())+"%");
				if(tempMonth.getTotal()-tempMonth.getFail()-tempMonth.getArrive_succ()-tempMonth.getArrive_fail()>=0){
					tempMonth.setNorpt_count(tempMonth.getTotal()-tempMonth.getFail()-tempMonth.getArrive_succ()-tempMonth.getArrive_fail());
					tempMonth.setUnknow_rate(getPercent(tempMonth.getTotal()-tempMonth.getFail()-tempMonth.getArrive_succ()-tempMonth.getArrive_fail(),tempMonth.getTotal())+"%");
				}else{
					tempMonth.setNorpt_count(0);
					tempMonth.setUnknow_rate("0%");
				}
			} 
			 resultJson.put("data", JSONArray.fromObject(userReportMonthList));
			 return resultJson;
			}catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}
	
	
	
	
	/*************【用户账单】***************/
	public JSONObject findUserBillList(SmsUserReport smsUserReport){
		try{
			Integer num=smsUserReportStatisticsMapper.findUserBillCount(smsUserReport);
			 JSONObject resultJson = new JSONObject();
			 resultJson.put("code", 0);
			 resultJson.put("msg", 0);
			 resultJson.put("count", num);
		List<SmsUserReport> userBillList=smsUserReportStatisticsMapper.findUserBillList(smsUserReport);
			for(SmsUserReport tempBill:userBillList){
				if(tempBill.getSend()!=null){
					tempBill.setSuccess_rate(getPercent(tempBill.getArrive_succ(),tempBill.getSend())+"%");
					tempBill.setFailRptCount(tempBill.getFail()-tempBill.getArrive_fail());
					tempBill.setNorpt_count(tempBill.getSend()-tempBill.getFail()-tempBill.getArrive_succ()-tempBill.getArrive_fail());
					tempBill.setUnknow_rate(getPercent(tempBill.getSend()-tempBill.getFail()-tempBill.getArrive_succ()-tempBill.getArrive_fail(),tempBill.getSend())+"%");
				}else{
					tempBill.setSuccess_rate("0%");
					tempBill.setFailRptCount(0);
					tempBill.setNorpt_count(0);
					tempBill.setUnknow_rate("0%");
				}
			} 
		 resultJson.put("data", JSONArray.fromObject(userBillList));
		 return resultJson;
		}catch(Exception e){
			e.printStackTrace();
		}
	return null;
	}

	
	
	
	
	public JSONObject findUserMonthDetail(SmsUserReport smsUserReport){
		try{
			Map<Integer,SmsCountryInfos> countryMap=GlobalParams.COUNTRY_ID_MAP;
			Integer num=smsUserReportStatisticsMapper.findUserMonthDetailCount(smsUserReport);
			 JSONObject resultJson = new JSONObject();
			 resultJson.put("code", 0);
			 resultJson.put("msg", 0);
			 resultJson.put("count", num);
		List<SmsUserReport> userReportMonthList=smsUserReportStatisticsMapper.findUserMonthDetailList(smsUserReport);
		for(SmsUserReport tempMonth:userReportMonthList){
			tempMonth.setMonth(smsUserReport.getMonth());
			tempMonth.setFail_rate(getPercent(tempMonth.getFail(),tempMonth.getTotal())+"%");
			tempMonth.setSuccess_rate(getPercent(tempMonth.getArrive_succ(),tempMonth.getTotal())+"%");
			tempMonth.setArrive_rate(getPercent(tempMonth.getArrive_fail(),tempMonth.getTotal())+"%");
			 if(countryMap.containsKey(tempMonth.getCountryId())){
				 tempMonth.setCountry(countryMap.get(tempMonth.getCountryId()).getCountry());
			 }
			if(tempMonth.getTotal()-tempMonth.getFail()-tempMonth.getArrive_succ()-tempMonth.getArrive_fail()>=0){
				tempMonth.setNorpt_count(tempMonth.getTotal()-tempMonth.getFail()-tempMonth.getArrive_succ()-tempMonth.getArrive_fail());
				tempMonth.setUnknow_rate(getPercent(tempMonth.getTotal()-tempMonth.getFail()-tempMonth.getArrive_succ()-tempMonth.getArrive_fail(),tempMonth.getTotal())+"%");
			}else{
				tempMonth.setNorpt_count(0);
				tempMonth.setUnknow_rate("0%");
			}
		} 
		 resultJson.put("data", JSONArray.fromObject(userReportMonthList));
		 return resultJson;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
