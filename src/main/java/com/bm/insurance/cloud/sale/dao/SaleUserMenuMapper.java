package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleUserMenu;
import com.bm.insurance.cloud.sale.model.SaleUserMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleUserMenuMapper {
    int countByExample(SaleUserMenuExample example);

    int deleteByExample(SaleUserMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleUserMenu record);

    int insertSelective(SaleUserMenu record);

    List<SaleUserMenu> selectByExample(SaleUserMenuExample example);

    SaleUserMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleUserMenu record, @Param("example") SaleUserMenuExample example);

    int updateByExample(@Param("record") SaleUserMenu record, @Param("example") SaleUserMenuExample example);

    int updateByPrimaryKeySelective(SaleUserMenu record);

    int updateByPrimaryKey(SaleUserMenu record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(List<SaleUserMenu> list);
}