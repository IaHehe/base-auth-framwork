package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleDepartment;
import com.bm.insurance.cloud.sale.model.SaleDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleDepartmentMapper {
    int countByExample(SaleDepartmentExample example);

    int deleteByExample(SaleDepartmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleDepartment record);

    int insertSelective(SaleDepartment record);

    List<SaleDepartment> selectByExample(SaleDepartmentExample example);

    SaleDepartment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleDepartment record, @Param("example") SaleDepartmentExample example);

    int updateByExample(@Param("record") SaleDepartment record, @Param("example") SaleDepartmentExample example);

    int updateByPrimaryKeySelective(SaleDepartment record);

    int updateByPrimaryKey(SaleDepartment record);
}