package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleGroup;
import com.bm.insurance.cloud.sale.model.SaleGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleGroupMapper {
    int countByExample(SaleGroupExample example);

    int deleteByExample(SaleGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleGroup record);

    int insertSelective(SaleGroup record);

    List<SaleGroup> selectByExample(SaleGroupExample example);

    SaleGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleGroup record, @Param("example") SaleGroupExample example);

    int updateByExample(@Param("record") SaleGroup record, @Param("example") SaleGroupExample example);

    int updateByPrimaryKeySelective(SaleGroup record);

    int updateByPrimaryKey(SaleGroup record);

    List<SaleGroup> loadUserGroupTree(long groupId);
}