package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.model.SaleDictionary;
import com.bm.insurance.cloud.sale.model.SaleDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleDictionaryMapper {
    int countByExample(SaleDictionaryExample example);

    int deleteByExample(SaleDictionaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleDictionary record);

    int insertSelective(SaleDictionary record);

    List<SaleDictionary> selectByExample(SaleDictionaryExample example);

    SaleDictionary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleDictionary record, @Param("example") SaleDictionaryExample example);

    int updateByExample(@Param("record") SaleDictionary record, @Param("example") SaleDictionaryExample example);

    int updateByPrimaryKeySelective(SaleDictionary record);

    int updateByPrimaryKey(SaleDictionary record);
}