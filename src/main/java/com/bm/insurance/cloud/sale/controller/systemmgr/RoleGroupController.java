package com.bm.insurance.cloud.sale.controller.systemmgr;

import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.DataGridDto;
import com.bm.insurance.cloud.sale.model.SaleGroup;
import com.bm.insurance.cloud.sale.model.SaleMenu;
import com.bm.insurance.cloud.sale.model.SaleOperate;
import com.bm.insurance.cloud.sale.model.SaleRole;
import com.bm.insurance.cloud.sale.service.systemmgr.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 角色组controller
 */
@Controller
@RequestMapping("/roleGroup")
public class RoleGroupController extends BaseController {

    @Autowired
    private RoleGroupService roleGroupService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private OperateService operateService;

    /**
     * 角色组管理页面
     */
    @RequestMapping(value = "/roleGroupListPage")
    public String groupListPage(Model model) {
        return "group/roleGroupList";
    }


    /**
     * 查询组的角色
     *
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/findRoleGroupDataGrid")
    @ResponseBody
    public DataGridDto findRoleGroupDataGrid(Long groupId) {
        if (groupId == null) {
            return null;
        }
        return roleGroupService.findRoleGroupDataGrid(groupId);
    }

    /**
     * 组角色菜单
     *
     * @param groupId
     * @param roleId
     * @param model
     */
    @RequestMapping(value = "/showRoleMenuPage")
    public String showRoleMenu(Long groupId, Long roleId, Model model) {
        SaleRole roleInfo = roleService.getSaleRole(roleId);
        model.addAttribute("roleInfo", roleInfo); //角色对象

        List<SaleMenu> menuList = menuService.findSecondLevelMenuList();
        model.addAttribute("menuList", menuList); //二级菜单

        List<SaleOperate> operList = operateService.findEnableOperateList();
        model.addAttribute("operList", operList); //操作按钮

        SaleGroup group = groupService.getGroupById(groupId);
        model.addAttribute("groupInfo",group); //团队组

        return "group/groupMenu";
    }

}
