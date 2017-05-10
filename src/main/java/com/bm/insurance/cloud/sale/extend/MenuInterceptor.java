package com.bm.insurance.cloud.sale.extend;

import com.bm.insurance.cloud.sale.common.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session过期拦截器
 */
public class MenuInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String menuId = request.getParameter("menuId");
        if(StringUtils.isNotBlank(menuId)){
            String menuCode = Constant.MENU_CODE_PREFIX + menuId;
            modelAndView.addObject("MENUCODE",menuCode); //菜单的编码为页面按钮权限标识
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
