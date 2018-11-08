package com.uc.training.smadmin.sms.dao.impl;

import com.uc.training.smadmin.sms.dao.SmsDao;
import com.uc.training.smadmin.sms.model.Sms;
import com.uc.training.smadmin.sms.vo.SmsListVO;
import com.uc.training.smadmin.sms.vo.SmsVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 12:21
 * @Description:
 */
@Repository
public class SmsDaoImpl extends CarIsIbatisDaoImpl implements SmsDao {
    private static final String NAMESPACE = "com.uc.training.smadmin.sms.dao.SmsDao.";
    /**
     * 查询短信列表
     * @param smsListVO
     * @return
     */
    @Override
    public List<SmsVO> getList(SmsListVO smsListVO) {
        return this.queryForList(NAMESPACE + "querySmsList", smsListVO);
    }

    /**
     * 查询记录总数
     * @param smsListVO
     * @return
     */
    @Override
    public Long querySmsCount(SmsListVO smsListVO) {
        return (Long) this.queryForObject(NAMESPACE + "querySmsCount", smsListVO);
    }

    /**
     * 查询单条短信记录
     * @param id
     * @return
     */
    @Override
    public Sms getSms(Long id) {
        return (Sms) this.queryForObject( NAMESPACE + "getSms", id);
    }

    /**
     * 新增短信
     *
     * @param sms
     * @return
     */
    @Override
    public Long insertSms(Sms sms) {
        return (Long) this.insert(NAMESPACE + "insertSms", sms);
    }


}
