package com.bm.insurance.cloud.sale.controller.systemmgr;

import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.MenuTreeDto;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.dto.TreegridDto;
import com.bm.insurance.cloud.sale.enums.RoleEnum;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleMenu;
import com.bm.insurance.cloud.sale.model.SaleUser;
import com.bm.insurance.cloud.sale.service.systemmgr.MenuService;
import com.bm.insurance.cloud.sale.service.systemmgr.UserGroupService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单controller
 *
 * @author hubo
 * @date 2017/4/17
 */
@Controller
@RequestMapping("/menu")
public class ＭenuController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserGroupService userGroupService;


    /**
     * 菜单管理页面
     */
    @RequestMapping(value = "/menuListPage")
    public String menuListPage(Model model) {
        return "menu/menuList";
    }

    /**
     * 加载菜单
     */
    @RequestMapping(value = "/loadMenu", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto loadMenuTree(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        SaleUser user = super.getUser();

        boolean notInGroup = userGroupService.checkUserExistsGroup(user.getId());
        if(notInGroup){//用户没有团队
            return super.error(StatusCodeEnum.NO_GROUP);
        }

        Subject subject = SecurityUtils.getSubject();
        List<MenuTreeDto> list;
        if (subject.hasRole(RoleEnum.SUPER_ADMIN.getCode())) {
            list = menuService.findMenuList(contextPath);
        } else {
            list = menuService.findUserMenuPermission(contextPath, user);
        }
        return super.success(list);
    }

    /**
     * 树形菜单管理
     *
     * @return
     */
    @RequestMapping(value = "/loadTreeGrid")
    @ResponseBody
    public TreegridDto loadTreeGrid() {
        return menuService.loadTreeGrid();
    }

    /**
     * 禁用此菜单
     *
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/disableMenu/{menuId}")
    @ResponseBody
    public ResponseDto disableMenu(@PathVariable long menuId, int status) {
        boolean result = menuService.disableMenu(menuId, status);
        return super.success(result);
    }

    /**
     * 加载下拉菜单树
     *
     * @return
     */
    @RequestMapping(value = "/loadComboTree")
    @ResponseBody
    public List<MenuTreeDto> loadComboTree() {
        return menuService.loadComboTree();
    }

    /**
     * 添加或者更新菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateMenu")
    @ResponseBody
    public ResponseDto saveOrUpdateMenu(SaleMenu menu) {
        boolean result = menuService.saveOrUpdateMenu(menu);
        return super.success(result);
    }


}
