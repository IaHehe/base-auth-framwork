package com.bm.insurance.cloud.sale.service.systemmgr;


import com.bm.insurance.cloud.sale.dao.SaleRoleGroupMapper;
import com.bm.insurance.cloud.sale.dto.DataGridDto;
import com.bm.insurance.cloud.sale.dto.RoleGroupDto;
import com.bm.insurance.cloud.sale.model.SaleRole;
import com.bm.insurance.cloud.sale.model.SaleRoleGroup;
import com.bm.insurance.cloud.sale.service.BaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleGroupService extends BaseService{

    @Autowired
    private RoleService roleService;
    @Autowired
    private SaleRoleGroupMapper saleRoleGroupMapper;

    public DataGridDto findRoleGroupDataGrid(final long groupId) {
        List<RoleGroupDto> roleGroupList = saleRoleGroupMapper.findRoleGroupDto(groupId);
        return super.dataGridDto(roleGroupList);
    }


    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    @Transactional
    public boolean batchInsert(List<SaleRoleGroup> list) {
        return saleRoleGroupMapper.insertBatch(list) > 0;
    }

    /**
     * 批量插入组角色
     *
     * @param groupId 组id
     * @return
     */
    @Transactional
    public boolean saveRoleToGroup(final long groupId) {
        boolean result = false;

        List<SaleRole> roleList = roleService.findNotAdminRoleList();
        if (CollectionUtils.isNotEmpty(roleList)) {
            List<SaleRoleGroup> roleGroupList = new ArrayList<>();

            for (SaleRole role : roleList) {
                SaleRoleGroup roleGroup = new SaleRoleGroup();
                roleGroup.setRoleId(role.getId());
                roleGroup.setGroupId(groupId);

                roleGroupList.add(roleGroup);
            }

            result = this.batchInsert(roleGroupList);
        }

        return result;
    }

}
