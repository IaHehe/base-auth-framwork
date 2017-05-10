package com.bm.insurance.cloud.sale.service.systemmgr;

import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.dao.SaleUserGroupsMapper;
import com.bm.insurance.cloud.sale.dto.DataGridDto;
import com.bm.insurance.cloud.sale.dto.PageDto;
import com.bm.insurance.cloud.sale.dto.SearchUserGroupDto;
import com.bm.insurance.cloud.sale.enums.RoleEnum;
import com.bm.insurance.cloud.sale.model.*;
import com.bm.insurance.cloud.sale.service.BaseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户组管理
 */
@Service
public class UserGroupService extends BaseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleUserGroupsMapper saleUserGroupsMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserMenuService userMenuService;
    @Autowired
    private UserDataAuthService userDataAuthService;

    /*组员角色id*/
    private final static long MEMBER_ROLE_ID = RoleEnum.MEMBER.getId();

    public DataGridDto findUserGroupPage(PageDto pageDto, SearchUserGroupDto searchUserGroupDto) {
        logger.info("搜索条件: {}", JSON.toJSONString(searchUserGroupDto));

        if (searchUserGroupDto.getGroupId() == null) {
            return null;
        }

        Page<SaleUser> page = PageHelper.startPage(pageDto.getPage(), pageDto.getRows());
        List<SearchUserGroupDto> userGroupList = this.findUserRoleGroupinfo(searchUserGroupDto);

        return super.getDataGridDto(page, userGroupList);
    }

    /**
     * 用户的组角色信息
     *
     * @param searchUserGroupDto
     * @return
     */
    public List<SearchUserGroupDto> findUserRoleGroupinfo(SearchUserGroupDto searchUserGroupDto) {
        return saleUserGroupsMapper.findUserGroupinfo(searchUserGroupDto);
    }

    /**
     * 根据用户id查询查询其角色分组详情
     *
     * @param userId
     * @return
     */
    public SearchUserGroupDto getUserRoleGroupByUserId(long userId) {
        SearchUserGroupDto userRoleGroup = new SearchUserGroupDto();
        userRoleGroup.setUserId(userId);
        List<SearchUserGroupDto> list = this.findUserRoleGroupinfo(userRoleGroup);
        SearchUserGroupDto baseUser = CollectionUtils.isEmpty(list) ? null : list.get(0);

        return baseUser;
    }


    /**
     * 检验一个用户只能一个组
     *
     * @param userId 用户id
     * @return true则不存在, false则存在
     */
    public boolean checkUserExistsGroup(final long userId) {
        SaleUserGroupExample example = new SaleUserGroupExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<SaleUserGroup> list = saleUserGroupsMapper.selectByExample(example);

        return CollectionUtils.isEmpty(list);
    }

    /**
     * 批量插入
     *
     * @param groupId
     * @param userIds
     * @return
     */
    @Transactional
    public boolean insertBatch(final long groupId, String userIds) {
        if (StringUtils.isBlank(userIds)) {
            throw new NullPointerException();
        }

        boolean result;
        List<SaleUserGroup> userGroupList = new ArrayList<>();
        List<SaleUserRole> roleGroupList = new ArrayList<>();

        String[] arr = userIds.split(",");
        for (String uId : arr) {
            long userId = Long.valueOf(uId);
            SaleUserGroup userGroup = new SaleUserGroup();//用户组
            userGroup.setGroupId(groupId);
            userGroup.setUserId(userId);
            userGroupList.add(userGroup);

            SaleUserRole userRole = new SaleUserRole();//用户角色
            userRole.setUserId(userId);
            userRole.setRoleId(MEMBER_ROLE_ID);
            roleGroupList.add(userRole);
        }
        result = saleUserGroupsMapper.insertBatch(userGroupList) > 0;
        result = userRoleService.insertBatch(roleGroupList);

        return result;
    }

    /**
     * 删除用户的组以及更新用户的角色为组员
     *
     * @param groupId
     * @param userId
     * @return
     */
    public boolean deleteUserFromGroup(final long groupId, final long userId) {
        logger.info("删除用户的组以及更新用户的角色为组员,组id={},用户id={}", groupId, userId);
        boolean reslut;

        this.deleteUserMenu(userId);//删除用户的菜单权限

        SaleUserGroupExample example = new SaleUserGroupExample();
        example.createCriteria().andUserIdEqualTo(userId).andGroupIdEqualTo(groupId);
        reslut = saleUserGroupsMapper.deleteByExample(example) > 0; //删除用户所在的组

        //更新用户的角色为组员
        SaleUserRole userRole = new SaleUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(MEMBER_ROLE_ID);
        reslut = userRoleService.updateUserRole(userRole);

        return reslut;
    }


    /**
     * 为用户分配角色
     *
     * @param roleId 角色id
     * @param userId 用户id
     * @return
     */
    @Transactional
    public boolean acceptUserRole(final long roleId, final long userId) {
        logger.info("更新用户的角色,更新为:{}", RoleEnum.getDesc(roleId));

        SaleUserRole userRole = new SaleUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

        this.deleteUserMenu(userId); //角色变更后删除用户的菜单权限
        userDataAuthService.deleteByUserId(userId); //删除用户的数据权限

        return userRoleService.updateUserRole(userRole);
    }

    private boolean deleteUserMenu(long userId) {
        SaleUserMenu userMenu = new SaleUserMenu();
        userMenu.setUserId(userId);
        boolean result = userMenuService.deleteUserMenu(userMenu);

        logger.info("删除用户id={}的所有菜单权限成功", userId);

        return result;
    }

    /**
     * 校验当前组中是否存在团队长角色
     *
     * @param groupId 组id
     * @return
     */
    public boolean existsManagerInGroup(final long groupId) {
        SearchUserGroupDto dto = new SearchUserGroupDto();
        dto.setGroupId(groupId);

        List<SearchUserGroupDto> userGroupList = saleUserGroupsMapper.findUserGroupinfo(dto);
        List<SearchUserGroupDto> list = (List<SearchUserGroupDto>)
                CollectionUtils.select(userGroupList, new Predicate<SearchUserGroupDto>() {
                    @Override
                    public boolean evaluate(SearchUserGroupDto searchUserGroupDto) {
                        return searchUserGroupDto.getRoleId() == RoleEnum.MANAGER.getId();
                    }
                });

        return CollectionUtils.isNotEmpty(list);
    }


}
