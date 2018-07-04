package com.yuntongxun.weixin.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by liugang on 2018/7/4.
 */
public class CacheUtil {

    final static Cache<String, String> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(10)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为2 小时
            .expireAfterWrite(2, TimeUnit.HOURS)
            //构建cache实例
            .build();
}
