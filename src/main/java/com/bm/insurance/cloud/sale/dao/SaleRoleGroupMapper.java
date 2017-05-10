package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.dto.RoleGroupDto;
import com.bm.insurance.cloud.sale.model.SaleRoleGroup;
import com.bm.insurance.cloud.sale.model.SaleRoleGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleRoleGroupMapper {
    int countByExample(SaleRoleGroupExample example);

    int deleteByExample(SaleRoleGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleRoleGroup record);

    int insertSelective(SaleRoleGroup record);

    List<SaleRoleGroup> selectByExample(SaleRoleGroupExample example);

    SaleRoleGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleRoleGroup record, @Param("example") SaleRoleGroupExample example);

    int updateByExample(@Param("record") SaleRoleGroup record, @Param("example") SaleRoleGroupExample example);

    int updateByPrimaryKeySelective(SaleRoleGroup record);

    int updateByPrimaryKey(SaleRoleGroup record);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int insertBatch(List<SaleRoleGroup> list);

    List<RoleGroupDto> findRoleGroupDto(Long groupId);
}