package com.bm.insurance.cloud.sale.controller.systemmgr;


import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleRole;
import com.bm.insurance.cloud.sale.service.systemmgr.RoleService;
import com.bm.insurance.cloud.sale.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 角色管理controller
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;

    /**
     * 角色管理页面
     */
    @RequestMapping(value = "/roleListPage")
    public String index(Model model) {
        return "role/roleList";
    }

    /**
     * 查询所有角色列表
     * @return
     */
    @RequestMapping(value = "/findRoleList")
    @ResponseBody
    public List<SaleRole> findRoleList() {
        return roleService.findSaleRoleList();
    }

    /**
     * 根据id获取销管角色信息
     */
    @RequestMapping(value = "/getRole/{id}")
    @ResponseBody
    public ResponseDto getSaleRole(@PathVariable long id) {
        SaleRole saleRole = roleService.getSaleRole(id);
        return ResponseUtil.success(saleRole);
    }

    /**
     * 插入销管角色信息
     *
     * @param saleRole
     * @return
     */
    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto editSaleRole(SaleRole saleRole) {
        boolean result = roleService.saveOrUpdateSaleRole(saleRole);
        return ResponseUtil.success(result);
    }

    /**
     * 删除角色
     *
     * @return
     */
    @RequestMapping(value = "/deleteRole/{roleId}")
    @ResponseBody
    public ResponseDto deleteRole(@PathVariable long roleId) {
        boolean result = roleService.deleteRole(roleId);
        return super.success(result);
    }

    /**
     * 检验编码是否唯一
     * @param roleCode
     * @return
     */
    @RequestMapping(value = "/checkUniqueCode")
    @ResponseBody
    public ResponseDto checkUniqueCode(String roleCode) {
        if(StringUtils.isBlank(roleCode)){
            return super.error(StatusCodeEnum.NEED_ARGS);
        }

        boolean result = roleService.checkUniqueCode(roleCode);
        return super.success(result);
    }

}
