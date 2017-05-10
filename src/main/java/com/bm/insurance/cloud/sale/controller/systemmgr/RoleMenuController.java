package com.bm.insurance.cloud.sale.controller.systemmgr;

import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleMenu;
import com.bm.insurance.cloud.sale.model.SaleOperate;
import com.bm.insurance.cloud.sale.model.SaleRole;
import com.bm.insurance.cloud.sale.model.SaleRoleMenu;
import com.bm.insurance.cloud.sale.service.systemmgr.MenuService;
import com.bm.insurance.cloud.sale.service.systemmgr.OperateService;
import com.bm.insurance.cloud.sale.service.systemmgr.RoleMenuService;
import com.bm.insurance.cloud.sale.service.systemmgr.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 角色功能菜单管理controller
 */
@Controller
@RequestMapping("/roleMenu")
public class RoleMenuController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private OperateService operateService;
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 角色分配功能列表
     *
     * @param roleId 角色id
     * @param model
     * @return
     */
    @RequestMapping(value = "/showRoleMenuPage/{roleId}")
    public String showRoleMenuPage(@PathVariable long roleId, Model model) {
        SaleRole roleInfo = roleService.getSaleRole(roleId);
        model.addAttribute("roleInfo", roleInfo); //角色对象

        List<SaleMenu> menuList = menuService.findSecondLevelMenuList();
        model.addAttribute("menuList", menuList); //二级菜单

        List<SaleOperate> operList = operateService.findEnableOperateList();
        model.addAttribute("operList", operList); //操作按钮

        return "role/roleMenu";
    }

    /**
     * 获取当前角色的所有菜单与操作按钮
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/loadRoleMenuOpers/{roleId}")
    @ResponseBody
    public ResponseDto loadRoleMenuOpers(@PathVariable long roleId) {
        List<SaleRoleMenu> list = roleMenuService.loadRoleMenuOpers(roleId);
        return super.success(list);
    }


    /**
     * 保存用户菜单操作权限
     *
     * @param roleMenuOpers
     * @return
     */
    @RequestMapping(value = "/saveRoleMenuOperates/{roleId}")
    @ResponseBody
    public ResponseDto saveRoleMenuOperates(@PathVariable long roleId, String roleMenuOpers) {
        if (StringUtils.isBlank(roleMenuOpers)) {
            return super.error(StatusCodeEnum.NEED_ARGS);
        }

        List<SaleRoleMenu> list = JSON.parseArray(roleMenuOpers, SaleRoleMenu.class);
        boolean result = roleMenuService.saveRoleMenuOperates(roleId, list);
        return super.success(result);
    }
}
