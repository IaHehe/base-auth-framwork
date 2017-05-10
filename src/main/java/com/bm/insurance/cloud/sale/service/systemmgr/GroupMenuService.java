package com.bm.insurance.cloud.sale.service.systemmgr;

import com.bm.insurance.cloud.sale.dao.SaleGroupMenuMapper;
import com.bm.insurance.cloud.sale.model.SaleGroupMenu;
import com.bm.insurance.cloud.sale.model.SaleGroupMenuExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupMenuService {

    @Autowired
    private SaleGroupMenuMapper saleGroupMenuMapper;

    /**
     * 查询组角色的菜单资源操作权限
     *
     * @param groupId 组id
     * @param roleId  角色id
     * @return
     */
    public List<SaleGroupMenu> loadGroupMenuOpers(final long groupId, final long roleId) {
        SaleGroupMenuExample example = new SaleGroupMenuExample();
        example.createCriteria().andGroupIdEqualTo(groupId).andRoleIdEqualTo(roleId);
        List<SaleGroupMenu> list = saleGroupMenuMapper.selectByExample(example);

        return list;
    }

    /**
     * 批量插入组色菜单操作资源
     * <ol>
     * <li>首先清除已经分配的记录</li>
     * <li>再新插入</li>
     * </ol>
     *
     * @param list
     * @return
     */
    @Transactional
    public boolean batchInsert(List<SaleGroupMenu> list) {
        boolean result = false;
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }

        SaleGroupMenu groupMenu = list.get(0);
        this.deleteGroupMenu(groupMenu); //直接清除

        return saleGroupMenuMapper.batchInsert(list) > 0;
    }

    /**
     * 删除
     *
     * @param groupMenu
     * @return
     */
    @Transactional
    public boolean deleteGroupMenu(SaleGroupMenu groupMenu) {
        SaleGroupMenuExample example = new SaleGroupMenuExample();
        example.createCriteria()
                .andRoleIdEqualTo(groupMenu.getRoleId())
                .andGroupIdEqualTo(groupMenu.getGroupId());

        return saleGroupMenuMapper.deleteByExample(example) > 0;
    }
}
