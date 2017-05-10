package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleUserDataAuth;
import com.bm.insurance.cloud.sale.model.SaleUserDataAuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleUserDataAuthMapper {
    int countByExample(SaleUserDataAuthExample example);

    int deleteByExample(SaleUserDataAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleUserDataAuth record);

    int insertSelective(SaleUserDataAuth record);

    List<SaleUserDataAuth> selectByExample(SaleUserDataAuthExample example);

    SaleUserDataAuth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleUserDataAuth record, @Param("example") SaleUserDataAuthExample example);

    int updateByExample(@Param("record") SaleUserDataAuth record, @Param("example") SaleUserDataAuthExample example);

    int updateByPrimaryKeySelective(SaleUserDataAuth record);

    int updateByPrimaryKey(SaleUserDataAuth record);

    int insertBatch(List<SaleUserDataAuth> list);
}