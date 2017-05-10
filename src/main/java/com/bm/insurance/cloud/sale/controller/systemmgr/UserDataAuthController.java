package com.bm.insurance.cloud.sale.controller.systemmgr;

import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.dto.UserRoleGroupDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleGroup;
import com.bm.insurance.cloud.sale.model.SaleUserDataAuth;
import com.bm.insurance.cloud.sale.service.systemmgr.GroupService;
import com.bm.insurance.cloud.sale.service.systemmgr.UserDataAuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户数据权限
 */
@Controller
@RequestMapping("/userDataAuth")
public class UserDataAuthController extends BaseController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserDataAuthService userDataAuthService;

    /**
     * 用户数据权限页面
     */
    @RequestMapping(value = "/dataAuthTreePage")
    public String userListPage(Model model,UserRoleGroupDto userRoleGroupDto) {
        model.addAttribute("userRoleGroupDto", userRoleGroupDto);
        return "user/userDataAuth";
    }

    /**
     * 查用户所在的组及父组树
     */
    @RequestMapping(value="/loadUserGroupTree/{groupId}")
    @ResponseBody
    public ResponseDto loadUserGroupTree(@PathVariable  Long groupId) {
        List<SaleGroup> list = groupService.loadUserGroupTree(groupId);
        return super.success(list);
    }

    /**
     * 查用户拥有的数据权限
     */
    @RequestMapping(value="/findUserDataAuth/{userId}")
    @ResponseBody
    public ResponseDto findUserDataAuth(@PathVariable Long userId) {
        List<SaleUserDataAuth> list = userDataAuthService.findUserDataAuth(userId);
        return super.success(list);
    }

    /**
     * 保存用户权限数据
     */
    @RequestMapping(value="/saveUserDataAuth")
    @ResponseBody
    public ResponseDto saveUserDataAuth(Long userId,String dataAuthStr) {
        if(userId == null){
            return super.error(StatusCodeEnum.NEED_ARGS);
        }
        if(StringUtils.isBlank(dataAuthStr)){
            return super.error(StatusCodeEnum.NEED_ARGS);
        }

        boolean result = userDataAuthService.saveUserDataAuth(userId, dataAuthStr);
        return super.success(result);
    }

}
