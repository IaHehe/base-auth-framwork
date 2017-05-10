package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleRoleMenu;
import com.bm.insurance.cloud.sale.model.SaleRoleMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleRoleMenuMapper {
    int countByExample(SaleRoleMenuExample example);

    int deleteByExample(SaleRoleMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleRoleMenu record);

    int insertSelective(SaleRoleMenu record);

    List<SaleRoleMenu> selectByExample(SaleRoleMenuExample example);

    SaleRoleMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleRoleMenu record, @Param("example") SaleRoleMenuExample example);

    int updateByExample(@Param("record") SaleRoleMenu record, @Param("example") SaleRoleMenuExample example);

    int updateByPrimaryKeySelective(SaleRoleMenu record);

    int updateByPrimaryKey(SaleRoleMenu record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(List<SaleRoleMenu> list);
}