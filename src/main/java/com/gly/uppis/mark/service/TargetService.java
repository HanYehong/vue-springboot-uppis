package com.gly.uppis.mark.service;

import com.gly.uppis.common.entity.Target;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/26 23:50
 */
public interface TargetService {
    List<Target> selectTargetByProperty(Integer userId);
}
