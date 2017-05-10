package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleMenuOperate;
import com.bm.insurance.cloud.sale.model.SaleMenuOperateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleMenuOperateMapper {
    int countByExample(SaleMenuOperateExample example);

    int deleteByExample(SaleMenuOperateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleMenuOperate record);

    int insertSelective(SaleMenuOperate record);

    List<SaleMenuOperate> selectByExample(SaleMenuOperateExample example);

    SaleMenuOperate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleMenuOperate record, @Param("example") SaleMenuOperateExample example);

    int updateByExample(@Param("record") SaleMenuOperate record, @Param("example") SaleMenuOperateExample example);

    int updateByPrimaryKeySelective(SaleMenuOperate record);

    int updateByPrimaryKey(SaleMenuOperate record);
}