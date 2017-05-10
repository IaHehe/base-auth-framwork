package com.bm.insurance.cloud.sale.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MySessionListener implements SessionListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onStart(Session session) {//会话创建触发 已进入shiro的过滤连就触发这个方法
        logger.info("会话创建：{}", session.getId());

        session.setTimeout(2 * 60 * 60 * 1000);
    }

    @Override
    public void onStop(Session session) {//退出
        logger.info("退出会话：{}", session.getId());
    }

    @Override
    public void onExpiration(Session session) {//会话过期时触发
        logger.info("会话过期：{}", session.getId());
    }

}
