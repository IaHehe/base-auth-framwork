package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.dto.MenuHelpDto;
import com.bm.insurance.cloud.sale.model.SaleMenu;
import com.bm.insurance.cloud.sale.model.SaleMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleMenuMapper {
    int countByExample(SaleMenuExample example);

    int deleteByExample(SaleMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleMenu record);

    int insertSelective(SaleMenu record);

    List<SaleMenu> selectByExample(SaleMenuExample example);

    SaleMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleMenu record, @Param("example") SaleMenuExample example);

    int updateByExample(@Param("record") SaleMenu record, @Param("example") SaleMenuExample example);

    int updateByPrimaryKeySelective(SaleMenu record);

    int updateByPrimaryKey(SaleMenu record);

    /**
     * 树形菜单
     * @return
     */
    List<MenuHelpDto> loadTreeGrid();
}