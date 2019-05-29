package com.gly.uppis.common.util;

import com.gly.uppis.common.cache.RedisHelper;
import com.gly.uppis.common.exception.ValidException;

/**
 * @author GuLiyun
 * @date 2019/5/22 15:22
 */
public class UserUtil {

    public static String getCurrentUserId() {
        String userId = RedisHelper.getRedisUtil().get("userId");
        if (userId == null) {
            throw new ValidException("获取不到当前用户，请重新登录");
        }
        return userId;
    }
}
