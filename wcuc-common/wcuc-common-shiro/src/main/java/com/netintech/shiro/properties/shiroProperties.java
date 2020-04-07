package com.netintech.shiro.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("shiro-session.session-manager")
public class shiroProperties {



    // prefix of session
    private String session_key;

    //  prefix of cookie
    private String simple_cookie;

    // max exist time  of cookie
    private int  cookie_maxAge;


    private int globalSessionTimeout  ;

    private Boolean sessionValidationSchedulerEnabled=true;

    // period time of checking unused session and swapping it
    private int sessionValidationInterval ;

    private Boolean deleteInvalidSessions =true;

    private Boolean sessionIdUrlRewritingEnabled =false;

    //
    private String principalIdFieldName = "id" ;

    //    permission  expire time
    private int persExpireTime;


}
