package com.gly.uppis.mark.service.impl;

import com.gly.uppis.common.entity.Period;
import com.gly.uppis.mark.dao.PeriodDao;
import com.gly.uppis.mark.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HanYehong
 * @date 2019/5/23 1:46
 */
@Service
public class PeriodServiceImpl implements PeriodService {
    @Autowired
    private PeriodDao periodDao;

    @Override
    public Period getPeriodInfo() {
        Period period = periodDao.selectLastOne();
        return period == null ? new Period() : period;
    }
}
