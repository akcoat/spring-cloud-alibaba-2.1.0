//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.netintech.login.Config;

import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeataHandlerInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(SeataHandlerInterceptor.class);

    public SeataHandlerInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String xid = RootContext.getXID();
        String rpcXid = request.getHeader("TX_XID");
        if (log.isDebugEnabled()) {
            log.debug("xid in RootContext {} xid in RpcContext {}", xid, rpcXid);
        }

        if (xid == null && rpcXid != null) {
            RootContext.bind(rpcXid);
            if (log.isDebugEnabled()) {
                log.debug("bind {} to RootContext", rpcXid);
            }
        }

        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        String rpcXid = request.getHeader("TX_XID");
        if (!StringUtils.isEmpty(rpcXid)) {
            String unbindXid = RootContext.unbind();
            if (log.isDebugEnabled()) {
                log.debug("unbind {} from RootContext", unbindXid);
            }

            if (!rpcXid.equalsIgnoreCase(unbindXid)) {
                log.warn("xid in change during RPC from {} to {}", rpcXid, unbindXid);
                if (unbindXid != null) {
                    RootContext.bind(unbindXid);
                    log.warn("bind {} back to RootContext", unbindXid);
                }
            }

        }
    }
}
