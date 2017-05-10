package com.bm.insurance.cloud.sale.service.systemmgr;


import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.dao.SaleRoleMapper;
import com.bm.insurance.cloud.sale.enums.RoleEnum;
import com.bm.insurance.cloud.sale.enums.StatusEnum;
import com.bm.insurance.cloud.sale.model.SaleRole;
import com.bm.insurance.cloud.sale.model.SaleRoleExample;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RoleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleRoleMapper saleRoleMapper;

    /**
     * 查询销管角色列表
     */
    public List<SaleRole> findSaleRoleList() {
        SaleRoleExample example = new SaleRoleExample();
        return saleRoleMapper.selectByExample(example);
    }

    public List<SaleRole> findEnableSaleRoleList() {
        SaleRoleExample example = new SaleRoleExample();
        example.createCriteria().andStatusEqualTo((byte) StatusEnum.EABLE.getCode());
        return saleRoleMapper.selectByExample(example);
    }

    /**
     * 查询非管理员及超级管理员的启用角色
     *
     * @return
     */
    public List<SaleRole> findNotAdminRoleList() {
        SaleRoleExample example = new SaleRoleExample();
        example.createCriteria().andIdGreaterThan(RoleEnum.ADMIN.getId())
                .andStatusEqualTo((byte) StatusEnum.EABLE.getCode());
        return saleRoleMapper.selectByExample(example);
    }


    /**
     * 插入销管角色
     *
     * @param saleRole
     * @return
     */
    @Transactional
    public boolean saveOrUpdateSaleRole(SaleRole saleRole) {
        logger.info("编辑参数,{}", JSON.toJSONString(saleRole));

        boolean result = false;
        if (saleRole.getId() != null) {
            saleRole.setRoleCode(saleRole.getRoleCode().toUpperCase());
            result = this.updateSaleRole(saleRole);
        } else {
            saleRole.setId(null);
            saleRole.setCreateTime(new Date());

            result = saleRoleMapper.insertSelective(saleRole) > 0;
        }
        return result;
    }

    /**
     * 更新销管角色
     *
     * @param saleRole
     * @return
     */
    @Transactional
    public boolean updateSaleRole(SaleRole saleRole) {
        saleRole.setUpdateTime(new Date());

        SaleRoleExample example = new SaleRoleExample();
        example.createCriteria().andIdEqualTo(saleRole.getId());
        int result = saleRoleMapper.updateByExampleSelective(saleRole, example);

        return result > 0;
    }

    /**
     * 禁用角色
     *
     * @param roleId
     * @return
     */
    public boolean deleteRole(long roleId) {
        SaleRole saleRole = new SaleRole();
        saleRole.setId(roleId);
        saleRole.setStatus((byte) 2);

        return this.updateSaleRole(saleRole);
    }

    /**
     * 检验编码是否唯一
     *
     * @param roleCode
     * @return true表是不存在，false则存在
     */
    public boolean checkUniqueCode(String roleCode) {
        SaleRoleExample example = new SaleRoleExample();
        example.createCriteria().andRoleCodeEqualTo(roleCode.toUpperCase().trim());
        List<SaleRole> list = saleRoleMapper.selectByExample(example);

        return CollectionUtils.isEmpty(list);
    }

    /**
     * 根据id查询对象
     *
     * @param id
     * @return
     */
    public SaleRole getSaleRole(long id) {
        return saleRoleMapper.selectByPrimaryKey(id);
    }
}
