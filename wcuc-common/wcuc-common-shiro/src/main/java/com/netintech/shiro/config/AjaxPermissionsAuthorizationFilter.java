package com.netintech.shiro.config;//package com.netintech.ers.config;
//
//import com.alibaba.fastjson.JSONObject;
//import com.netintech.ers.utls.ErrorEnum;
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//import org.springframework.stereotype.Repository;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//
//@Repository
//public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("code", ErrorEnum.E_20011.getErrorCode());
//        jsonObject.put("msg", ErrorEnum.E_20011.getErrorMsg());
//        PrintWriter out = null;
//        HttpServletResponse res = (HttpServletResponse) response;
//        try {
//            res.setCharacterEncoding("UTF-8");
//            res.setContentType("application/json");
//            out = response.getWriter();
//            out.println(jsonObject);
//        } catch (Exception e) {
//        } finally {
//            if (null != out) {
//                out.flush();
//                out.close();
//            }
//        }
//        return false;
//    }
//
//   /* @Bean
//    public FilterRegistrationBean registration(AjaxPermissionsAuthorizationFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }*/
//}