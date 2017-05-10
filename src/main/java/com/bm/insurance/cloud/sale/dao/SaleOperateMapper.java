package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleOperate;
import com.bm.insurance.cloud.sale.model.SaleOperateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleOperateMapper {
    int countByExample(SaleOperateExample example);

    int deleteByExample(SaleOperateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleOperate record);

    int insertSelective(SaleOperate record);

    List<SaleOperate> selectByExample(SaleOperateExample example);

    SaleOperate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleOperate record, @Param("example") SaleOperateExample example);

    int updateByExample(@Param("record") SaleOperate record, @Param("example") SaleOperateExample example);

    int updateByPrimaryKeySelective(SaleOperate record);

    int updateByPrimaryKey(SaleOperate record);
}