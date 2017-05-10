package com.bm.insurance.cloud.sale.service.systemmgr;

import com.bm.insurance.cloud.sale.dao.SaleUserRoleMapper;
import com.bm.insurance.cloud.sale.enums.RoleEnum;
import com.bm.insurance.cloud.sale.model.SaleRole;
import com.bm.insurance.cloud.sale.model.SaleUserRole;
import com.bm.insurance.cloud.sale.model.SaleUserRoleExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private SaleUserRoleMapper saleUserRoleMapper;
    @Autowired
    private RoleService roleService;

    /**
     * 更新用户的角色
     *
     * @param userRole
     * @return
     */
    @Transactional
    public boolean updateUserRole(SaleUserRole userRole) {
        SaleUserRoleExample example = new SaleUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userRole.getUserId());

        return saleUserRoleMapper.updateByExampleSelective(userRole, example) > 0;
    }

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    @Transactional
    public boolean insertBatch(List<SaleUserRole> list) {
        return saleUserRoleMapper.insertBatch(list) > 0;
    }

    /**
     * 查询用户的角色
     *
     * @param userId 用户id
     * @return
     */
    public SaleRole getRoleByUserId(final long userId) {
        SaleRole role;

        SaleUserRoleExample example = new SaleUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);

        List<SaleUserRole> list = saleUserRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            role = roleService.getSaleRole(list.get(0).getRoleId());
        } else {
            role = roleService.getSaleRole(RoleEnum.MEMBER.getId());
        }

        return role;
    }

}
