package com.bm.insurance.cloud.sale.service.systemmgr;


import com.bm.insurance.cloud.sale.dao.SaleRoleMenuMapper;
import com.bm.insurance.cloud.sale.model.SaleRoleMenu;
import com.bm.insurance.cloud.sale.model.SaleRoleMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色资源菜单权限
 */
@Service
public class RoleMenuService {

    @Autowired
    private SaleRoleMenuMapper saleRoleMenuMapper;

    /**
     * 插入角色菜单操作
     * <ol>
     * <li>先删除角色的所有菜单</li>
     * <li>批量插入</li>
     * </ol>
     */
    @Transactional
    public boolean saveRoleMenuOperates(final long roleId, List<SaleRoleMenu> list) {
        SaleRoleMenuExample example = new SaleRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);

        boolean result = saleRoleMenuMapper.deleteByExample(example) > 0; //删除角色的所有资源
        result = saleRoleMenuMapper.batchInsert(list) > 0;

        return result;
    }

    /**
     * 根据角色id查其有的菜单与操作
     *
     * @param roleId 角色id
     * @return
     */
    public List<SaleRoleMenu> loadRoleMenuOpers(final long roleId) {
        SaleRoleMenuExample example = new SaleRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return saleRoleMenuMapper.selectByExample(example);
    }
}
