package com.bm.insurance.cloud.sale.controller.systemmgr;


import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.DataGridDto;
import com.bm.insurance.cloud.sale.dto.PageDto;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleDepartment;
import com.bm.insurance.cloud.sale.model.SaleUser;
import com.bm.insurance.cloud.sale.service.systemmgr.DepartmentService;
import com.bm.insurance.cloud.sale.service.systemmgr.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 角色管理controller
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 用户管理页面
     */
    @RequestMapping(value = "/userListPage")
    public String userListPage(Model model) {
        List<SaleDepartment> deptList = departmentService.loadDepartmentTree();
        model.addAttribute("deptList", deptList);

        return "user/userList";
    }

    /**
     * 分页查询用户
     *
     * @param pageDto
     * @return
     */
    @RequestMapping(value = "/findUserPage")
    @ResponseBody
    public DataGridDto findUserPage(PageDto pageDto, SaleUser saleUser) {
        return userService.findUserPage(pageDto, saleUser);
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/deleteUser/{userId}/{status}")
    @ResponseBody
    public ResponseDto deleteUser(@PathVariable long userId,@PathVariable int status) {
        boolean result = userService.deleteUser(userId,status);
        return super.success(result);
    }

    /**
     * 校验用户名是否唯一
     *
     * @param userName 用户名
     * @return
     */
    @RequestMapping(value = "/checkUniqueUserName")
    @ResponseBody
    public ResponseDto checkUniqueUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return super.error(StatusCodeEnum.NEED_ARGS);
        }

        boolean result = userService.checkUniqueUserName(userName);
        return super.success(result);
    }

    /**
     * 更新或者插入用户数据
     *
     * @param saleUser
     * @return
     */
    @RequestMapping(value = "/editUser")
    @ResponseBody
    public ResponseDto editUser(SaleUser saleUser) {
        SaleUser loginUser = super.getUser();
        boolean result = userService.saveOrUpdateUser(saleUser, loginUser);
        return super.success(result);
    }

    /**
     * 根据用户id查找对象
     *
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/getUserById/{userId}")
    @ResponseBody
    public ResponseDto getUserById(@PathVariable long userId) {
        SaleUser saleUser = userService.getUserById(userId);
        return super.success(saleUser);
    }

    /**
     * 根据用户id查找对象
     *
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/resetPwd")
    @ResponseBody
    public ResponseDto resetPwd(long userId) {
        boolean result = userService.resetPwd(userId);
        return super.success(result);
    }


    @RequestMapping(value = "/dev")
    public String dev() {
        return "dev";
    }

}
