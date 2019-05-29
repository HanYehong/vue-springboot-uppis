package com.gly.uppis.setting.service;

import com.gly.uppis.setting.controller.vo.TargetVO;

/**
 * @author HanYehong
 * @date 2019/5/27 21:13
 */
public interface SettingService {
    TargetVO getAllTarget();

    void saveTarget(TargetVO param);
}
