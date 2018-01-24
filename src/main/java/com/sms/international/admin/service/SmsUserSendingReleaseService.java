package com.sms.international.admin.service;

import com.sms.international.admin.model.SendingVo;
import com.sms.international.admin.model.SmsUserSendingRelease;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/12/14
 * Description 审核队列接口
 */
public interface SmsUserSendingReleaseService {

    /**
     * 分页查询
     * @param release
     * @return
     */
    JSONObject findByPage(SmsUserSendingRelease release);

    /**
     * 勾选通过
     * @param release
     * @return
     */
    Map<String,Integer> passToCount(SmsUserSendingRelease release);

    /**
     * 勾选通过
     * @param release
     * @return
     */
    Map<String,Integer> pass(SmsUserSendingRelease release);

    /**
     * 勾选驳回
     * @param release
     * @return
     */
    int reject(SmsUserSendingRelease release);

    /**
     * 搜索通过
     * @param release
     * @return
     */
    Map<String,Integer> searchPass(SmsUserSendingRelease release, int to_channel);

    /**
     * 搜索驳回
     * @param release
     * @return
     */
    Map<String,Integer> searchReject(SmsUserSendingRelease release);

    /**
     * 根据md5值查看号码详情
     * @param release
     * @return
     */
    JSONObject lookDetail(SmsUserSendingRelease release);

    List<SendingVo> findT(SmsUserSendingRelease release);

    int updateReleaseContent(Map<String, Object> map);
}
