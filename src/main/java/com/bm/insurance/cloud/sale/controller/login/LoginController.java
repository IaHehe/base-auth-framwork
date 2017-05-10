package com.bm.insurance.cloud.sale.controller.login;

import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.service.login.LonginService;
import com.bm.insurance.cloud.sale.utils.Md5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登陆controller
 * Created by hubo on 2017/4/18.
 */
@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LonginService longinService;

    /**
     * 登陆成功跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String loginPage() {
        return "login";
    }

    /**
     * 登陆成功跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    /**
     * 登陆
     *
     * @param loginName 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto userLogin(String loginName, String password) {
        try {
            char[] passwords = Md5.md5(password).toCharArray();
            UsernamePasswordToken token = new UsernamePasswordToken(loginName, passwords);

            Subject subject = SecurityUtils.getSubject();
            subject.login(token); // 登陆

            if (subject.isAuthenticated()) {//身份认证成功
                super.success(true);
            }
        } catch (UnknownAccountException e) {
            return super.error(StatusCodeEnum.NO_USER);
        }

        return super.success(true);
    }

    /**
     * 退出登陆跳转到首页
     */
    @RequestMapping(value = "/loginout")
    public String loginout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout(); //退出

        return "login";
    }
}