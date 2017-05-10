package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleUser;
import com.bm.insurance.cloud.sale.model.SaleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleUserMapper {
    int countByExample(SaleUserExample example);

    int deleteByExample(SaleUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleUser record);

    int insertSelective(SaleUser record);

    List<SaleUser> selectByExample(SaleUserExample example);

    SaleUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleUser record, @Param("example") SaleUserExample example);

    int updateByExample(@Param("record") SaleUser record, @Param("example") SaleUserExample example);

    int updateByPrimaryKeySelective(SaleUser record);

    int updateByPrimaryKey(SaleUser record);
}