package com.bm.insurance.cloud.sale.extend;

import com.bm.insurance.cloud.sale.common.Constant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * session过期拦截器
 */
public class SessionTimeoutInterceptor implements HandlerInterceptor {
    private static final String LOGIN_URL = "/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession(true);
        Object obj = session.getAttribute(Constant.CURRENT_USER);

        if (obj == null || "".equals(obj.toString())) {
            String loginPath = request.getSession().getServletContext().getContextPath() + LOGIN_URL;
            response.sendRedirect(loginPath);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
