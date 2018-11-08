package com.uc.training.smadmin.sms.dao;

import com.uc.training.smadmin.sms.model.Sms;
import com.uc.training.smadmin.sms.vo.SmsListVO;
import com.uc.training.smadmin.sms.vo.SmsVO;

import java.util.List;

/**
 * @author 余旭东
 * @date 2018/10/30
 * @descripte
 */
public interface SmsDao {
    /**
     * 查询短信列表
     * @param smsListVO
     * @return
     */
    List<SmsVO> getList(SmsListVO smsListVO);

    /**
     * 查询记录总数
     * @param smsListVO
     * @return
     */
    Long querySmsCount(SmsListVO smsListVO);

    /**
     * 查询单条短信记录
     * @param id
     * @return
     */
    Sms getSms(Long id);

    /**
     * 新增短信
     * @param sms
     * @return
     */
    Long insertSms(Sms sms);

}
