package com.bm.insurance.cloud.sale.controller.systemmgr;

import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.dto.UserRoleGroupDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleMenu;
import com.bm.insurance.cloud.sale.model.SaleOperate;
import com.bm.insurance.cloud.sale.model.SaleUser;
import com.bm.insurance.cloud.sale.model.SaleUserMenu;
import com.bm.insurance.cloud.sale.service.systemmgr.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户组菜单资源权限controller
 */
@Controller
@RequestMapping("/userMenu")
public class UserMenuController extends BaseController {

    @Autowired
    private GroupMenuService groupMenuService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private OperateService operateService;
    @Autowired
    private UserMenuService userMenuService;
    @Autowired
    private UserService userService;


    /**
     * 用户权限页面
     *
     * @return
     */
    @RequestMapping(value = "/showUserPermissionPage")
    public String showUserPermission(Model model, UserRoleGroupDto userRoleGroupDto) {
        model.addAttribute("userRoleGroupDto", userRoleGroupDto);

        return "user/userPermission";
    }

    /**
     * 获取当前组中角色的所有菜单与操作按钮
     *
     * @return
     */
    @RequestMapping(value = "/loadUserMenuOpers")
    public String loadGroupMenuOpers(Model model, UserRoleGroupDto userRoleGroupDto) {
        List<SaleMenu> menuList = menuService.findSecondLevelMenuList();
        model.addAttribute("menuList", menuList); //二级菜单

        List<SaleOperate> operList = operateService.findEnableOperateList();
        model.addAttribute("operList", operList); //操作按钮

        model.addAttribute("userRoleGroupDto", userRoleGroupDto);

        return "user/userMenu";
    }

    /**
     * 查询用户的菜单
     *
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/findMyMenus/{userId}")
    @ResponseBody
    public ResponseDto findMyMenus(@PathVariable Long userId) {
        List<SaleUserMenu> list = userMenuService.findMyMenus(userId);
        return super.success(list);
    }

    /**
     * 保存组菜单资源
     */
    @RequestMapping(value = "/saveUserMenuOperates/{userId}")
    @ResponseBody
    public ResponseDto saveUserMenuOperates(@PathVariable long userId, String userMenuOpers) {
        if (StringUtils.isBlank(userMenuOpers)) {
            return super.error(StatusCodeEnum.NEED_ARGS);
        }

        List<SaleUserMenu> list = JSON.parseArray(userMenuOpers, SaleUserMenu.class);
        boolean result = userMenuService.batchInsert(list);

        SaleUser loginUser = super.getUser();
        SaleUser user = new SaleUser();
        user.setCreateUserId(loginUser.getId());
        user.setCreateUserName(loginUser.getUserName());
        user.setId(userId);
        userService.updateUser(user);

        return super.success(result);
    }

}
