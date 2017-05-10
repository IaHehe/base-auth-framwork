package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleRole;
import com.bm.insurance.cloud.sale.model.SaleRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleRoleMapper {
    int countByExample(SaleRoleExample example);

    int deleteByExample(SaleRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleRole record);

    int insertSelective(SaleRole record);

    List<SaleRole> selectByExample(SaleRoleExample example);

    SaleRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleRole record, @Param("example") SaleRoleExample example);

    int updateByExample(@Param("record") SaleRole record, @Param("example") SaleRoleExample example);

    int updateByPrimaryKeySelective(SaleRole record);

    int updateByPrimaryKey(SaleRole record);
}