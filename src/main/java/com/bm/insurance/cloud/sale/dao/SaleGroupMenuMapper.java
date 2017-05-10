package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleGroupMenu;
import com.bm.insurance.cloud.sale.model.SaleGroupMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleGroupMenuMapper {
    int countByExample(SaleGroupMenuExample example);

    int deleteByExample(SaleGroupMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleGroupMenu record);

    int insertSelective(SaleGroupMenu record);

    List<SaleGroupMenu> selectByExample(SaleGroupMenuExample example);

    SaleGroupMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleGroupMenu record, @Param("example") SaleGroupMenuExample example);

    int updateByExample(@Param("record") SaleGroupMenu record, @Param("example") SaleGroupMenuExample example);

    int updateByPrimaryKeySelective(SaleGroupMenu record);

    int updateByPrimaryKey(SaleGroupMenu record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert( List<SaleGroupMenu> list);
}