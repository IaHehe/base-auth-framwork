package com.bm.insurance.cloud.sale.dao;

import com.bm.insurance.cloud.sale.dto.SearchUserGroupDto;
import com.bm.insurance.cloud.sale.model.SaleUserGroup;
import com.bm.insurance.cloud.sale.model.SaleUserGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleUserGroupsMapper {
    int countByExample(SaleUserGroupExample example);

    int deleteByExample(SaleUserGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleUserGroup record);

    int insertSelective(SaleUserGroup record);

    List<SaleUserGroup> selectByExample(SaleUserGroupExample example);

    SaleUserGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleUserGroup record, @Param("example") SaleUserGroupExample example);

    int updateByExample(@Param("record") SaleUserGroup record, @Param("example") SaleUserGroupExample example);

    int updateByPrimaryKeySelective(SaleUserGroup record);

    int updateByPrimaryKey(SaleUserGroup record);

    /**
     * 批量插入
     * @return 条数
     */
    int insertBatch(List<SaleUserGroup> userGroupList);

    /**
     * 查询用户的所有角色组信息
     * @param searchUserGroupDto
     * @return
     */
    List<SearchUserGroupDto> findUserGroupinfo(SearchUserGroupDto searchUserGroupDto);
}