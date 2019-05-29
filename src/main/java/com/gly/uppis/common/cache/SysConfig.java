package com.gly.uppis.common.cache;

public class SysConfig {

    public static final String IP = "192.168.43.38";

    public static final int REDIS_PORT = 6379;

    public static final String REDIS_PASSWORD = "941012";

    public static final int MAX_TOTAL = 50;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    public static final int MAX_IDLE = 8;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    public static final int MAX_WAIT = 200000;

    public static final int TIMEOUT = 10000;

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    public static final boolean TEST_ON_BORROW = true;


}
