package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleLog;
import com.bm.insurance.cloud.sale.model.SaleLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleLogMapper {
    int countByExample(SaleLogExample example);

    int deleteByExample(SaleLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleLog record);

    int insertSelective(SaleLog record);

    List<SaleLog> selectByExample(SaleLogExample example);

    SaleLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleLog record, @Param("example") SaleLogExample example);

    int updateByExample(@Param("record") SaleLog record, @Param("example") SaleLogExample example);

    int updateByPrimaryKeySelective(SaleLog record);

    int updateByPrimaryKey(SaleLog record);
}