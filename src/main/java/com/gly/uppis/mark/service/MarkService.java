package com.gly.uppis.mark.service;

import com.gly.uppis.common.entity.Period;
import com.gly.uppis.common.entity.User;
import com.gly.uppis.mark.controller.request.MarkReuqest;
import com.gly.uppis.mark.service.bo.MarkPersonBO;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/22 16:57
 */
public interface MarkService {
    MarkPersonBO findAllMarkPerson();

    void commitMark(MarkReuqest param);
}
