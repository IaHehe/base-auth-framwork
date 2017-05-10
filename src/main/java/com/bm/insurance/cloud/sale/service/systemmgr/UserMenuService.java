package com.bm.insurance.cloud.sale.service.systemmgr;

import com.bm.insurance.cloud.sale.dao.SaleUserMenuMapper;
import com.bm.insurance.cloud.sale.model.SaleUserMenu;
import com.bm.insurance.cloud.sale.model.SaleUserMenuExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserMenuService {

    @Autowired
    private SaleUserMenuMapper saleUserMenuMapper;

    @Transactional
    public boolean batchInsert(List<SaleUserMenu> list) {
        boolean result = false;
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }

        SaleUserMenu userMenu = list.get(0);
        this.deleteUserMenu(userMenu); //删除用户的所有菜单

        return saleUserMenuMapper.batchInsert(list) > 0;
    }

    @Transactional
    public boolean deleteUserMenu(SaleUserMenu userMenu) {
        SaleUserMenuExample example = new SaleUserMenuExample();
        example.createCriteria().andUserIdEqualTo(userMenu.getUserId());

        return saleUserMenuMapper.deleteByExample(example) > 0;
    }

    /**
     * 查询用户的菜单资源
     *
     * @param userId 用户id
     * @return
     */
    public List<SaleUserMenu> findMyMenus(final long userId) {
        SaleUserMenuExample example = new SaleUserMenuExample();
        example.createCriteria().andUserIdEqualTo(userId);

        return saleUserMenuMapper.selectByExample(example);
    }
}
