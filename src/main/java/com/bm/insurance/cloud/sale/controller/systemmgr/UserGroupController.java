package com.bm.insurance.cloud.sale.controller.systemmgr;

import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.DataGridDto;
import com.bm.insurance.cloud.sale.dto.PageDto;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.dto.SearchUserGroupDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleDepartment;
import com.bm.insurance.cloud.sale.model.SaleUser;
import com.bm.insurance.cloud.sale.service.systemmgr.DepartmentService;
import com.bm.insurance.cloud.sale.service.systemmgr.UserGroupService;
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
 * 用户组controller
 */
@Controller
@RequestMapping("/userGroup")
public class UserGroupController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;

    /**
     * 用户分组管理页面
     */
    @RequestMapping(value = "/userGroupListPage")
    public String groupListPage(Model model) {
        List<SaleDepartment> deptList = departmentService.loadDepartmentTree();
        model.addAttribute("deptList", deptList);

        return "group/userGroupList";
    }

    /**
     * 分页查询用户
     *
     * @param pageDto
     * @return
     */
    @RequestMapping(value = "/findUserGroupPage")
    @ResponseBody
    public DataGridDto findUserGroupPage(PageDto pageDto, SearchUserGroupDto searchUserGroupDto) {
        return userGroupService.findUserGroupPage(pageDto, searchUserGroupDto);
    }

    /**
     * 检验一个用户只能一个组
     *
     * @return
     */
    @RequestMapping(value = "/checkUserExistsGroup/{userId}")
    @ResponseBody
    public ResponseDto checkUserExistsGroup(@PathVariable long userId) {
        return super.success(userGroupService.checkUserExistsGroup(userId));
    }

    /**
     * 批量插入用户到组
     *
     * @param groupId
     * @param userIds
     * @return
     */
    @RequestMapping(value = "/insertBatch")
    @ResponseBody
    public ResponseDto insertBatch(long groupId, String userIds) {
        if (StringUtils.isBlank(userIds)) {
            return super.error(StatusCodeEnum.NEED_ARGS);
        }

        boolean result = userGroupService.insertBatch(groupId, userIds);
        return super.success(result);
    }

    /**
     * 删除用户的组以及更新用户的角色为组员
     *
     * @param groupId 组id
     * @param userId  用户id
     * @return
     */
    @RequestMapping(value = "/deleteUserFromGroup")
    @ResponseBody
    public ResponseDto deleteUserFromGroup(long groupId, long userId) {
        boolean result = userGroupService.deleteUserFromGroup(groupId, userId);
        return super.success(result);
    }

    /**
     * 为用户分配角色
     *
     * @param roleId 角色id
     * @param userId 用户id
     * @return DefaultWebSessionManager
     */
    @RequestMapping(value = "/acceptUserRole")
    @ResponseBody
    public ResponseDto updateUserRole(long roleId, long userId) {
        boolean result = userGroupService.acceptUserRole(roleId, userId);

        SaleUser loginUser = super.getUser();
        SaleUser user = new SaleUser();
        user.setCreateUserId(loginUser.getId());
        user.setCreateUserName(loginUser.getUserName());
        user.setId(userId);
        userService.updateUser(user);

        return super.success(result);
    }

    /**
     * 组角色是否已经分配了团队长
     *
     * @param groupId 组id
     * @return
     */
    @RequestMapping(value = "/existsManagerInGroup/{groupId}")
    @ResponseBody
    public ResponseDto existsManagerInGroup(@PathVariable long groupId) {
        return super.success(userGroupService.existsManagerInGroup(groupId));
    }


}
