package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleUserRole;
import com.bm.insurance.cloud.sale.model.SaleUserRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleUserRoleMapper {
    int countByExample(SaleUserRoleExample example);

    int deleteByExample(SaleUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleUserRole record);

    int insertSelective(SaleUserRole record);

    List<SaleUserRole> selectByExample(SaleUserRoleExample example);

    SaleUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleUserRole record, @Param("example") SaleUserRoleExample example);

    int updateByExample(@Param("record") SaleUserRole record, @Param("example") SaleUserRoleExample example);

    int updateByPrimaryKeySelective(SaleUserRole record);

    int updateByPrimaryKey(SaleUserRole record);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int insertBatch(List<SaleUserRole> list);
}