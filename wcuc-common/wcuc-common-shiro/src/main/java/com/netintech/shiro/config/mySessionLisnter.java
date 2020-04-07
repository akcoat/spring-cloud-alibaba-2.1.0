package com.netintech.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

@Slf4j
public class mySessionLisnter implements SessionListener {

    @Override
    public void onStart(Session session) {
        log.info("会话创建："+session.getId());
    }

    @Override
    public void onStop(Session session) {
        log.info("会话结束："+session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        log.info("会话过期："+session.getId());
    }
}
