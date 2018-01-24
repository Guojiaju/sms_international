package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SendingVo;
import com.sms.international.admin.model.SmsUserSendingRelease;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/12/14
 * Description 审核队列sql映射
 */
@Repository
public interface SmsUserSendingReleaseMapper extends BaseMapper<Integer,SmsUserSendingRelease>{
    /**
     * 勾选ID
     * @param release
     * @return
     */
     List<Integer> findPassId(SmsUserSendingRelease release);

    /**
     * 修改
     */
     int selUpdate(Map<String, Object> map);

    /**
     * 单个集合修改
     * @param map
     * @return
     */
     int selUpdateOne(Map<String, Object> map);

    /**
     * 修改审核短信
     * @param map
     * @return
     */
     int updateReleaseMessage(Map<String, Object> map);

     int updateReleaseMessageToIds(Map<String, Object> map);

     int updateReleaseMessageBysearch(Map<String, Object> map);

    /**
     * 搜索修改
     */
     int searchUpdate(SmsUserSendingRelease release);

    /**
     * 搜索ID
     * @param release
     * @return
     */
     List<Integer> searchPassId(SmsUserSendingRelease release);

    /**
     * 按IDs查询
     * @param ids
     * @return
     */
     List<SmsUserSendingRelease> findByIds(Integer[] ids);

    /**
     * 按Md5str删除
     * @param Md5str
     * @return
     */
     int deleteByMd5str(String Md5str);

    /**
     * 按IDs删除
     * @param ids
     * @return
     */
     int deleteByIds(Integer[] ids);

    /**
     * 根据md5值查看号码详情
     * @param release
     * @return
     */
     List<SmsUserSendingRelease> lookDetail(SmsUserSendingRelease release);

    /**
     * 统计号码数量
     * @param release
     * @return
     */
    int countLookDetailTotal(SmsUserSendingRelease release);

    List<SendingVo> findT(SmsUserSendingRelease release);

    List<SendingVo> findTToCount(SmsUserSendingRelease release);

    int updateReleaseContent(Map<String, Object> map);

}
