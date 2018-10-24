package com.uc.training.smadmin.sms.dao.impl;

import com.uc.training.smadmin.sms.dao.SmsDao;
import com.uc.training.smadmin.sms.model.Sms;
import com.uc.training.smadmin.sms.vo.SmsListVO;
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
    /**
     * 查询短信列表
     * @param smsListVO
     * @return
     */
    @Override
    public List<Sms> getList(SmsListVO smsListVO) {
        return this.queryForList("com.uc.training.smadmin.sms.dao.SmsDao.querySmsList", smsListVO);
    }

    /**
     * 查询记录总数
     * @param smsListVO
     * @return
     */
    @Override
    public Long querySmsCount(SmsListVO smsListVO) {
        return (Long) this.queryForObject("com.uc.training.smadmin.sms.dao.SmsDao.querySmsCount", smsListVO);
    }

    /**
     * 查询单条短信记录
     * @param id
     * @return
     */
    @Override
    public Sms getSms(Long id) {
        return (Sms) this.queryForObject("com.uc.training.smadmin.sms.dao.SmsDao.getSms", id);
    }


}
