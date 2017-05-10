package com.bm.insurance.cloud.sale.service.systemmgr;

import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.dao.SaleGroupMapper;
import com.bm.insurance.cloud.sale.dao.SaleUserGroupsMapper;
import com.bm.insurance.cloud.sale.enums.StatusEnum;
import com.bm.insurance.cloud.sale.model.SaleGroup;
import com.bm.insurance.cloud.sale.model.SaleGroupExample;
import com.bm.insurance.cloud.sale.model.SaleUserGroupExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 分组管理
 */
@Service
public class GroupService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleGroupMapper saleGroupMapper;
    @Autowired
    private SaleUserGroupsMapper saleUserGroupsMapper;
    @Autowired
    private RoleGroupService roleGroupService;


    /**
     * 加载分组树
     *
     * @return
     */
    public List<SaleGroup> loadgroupTree() {
        SaleGroupExample example = new SaleGroupExample();
        example.createCriteria().andStatusEqualTo((byte) StatusEnum.EABLE.getCode());

        return saleGroupMapper.selectByExample(example);
    }

    /**
     * 查询用户所在的组及父组的
     *
     * @param groupId
     * @return
     */
    public List<SaleGroup> loadUserGroupTree(final long groupId) {
        return saleGroupMapper.loadUserGroupTree(groupId);
    }

    /**
     * 编辑组
     * <ol>
     * <li>插入组时默认分配所有可用的角色到组</li>
     * </ol>
     *
     * @param saleGroup
     * @param flag      1表示添加;2表示更新
     * @return
     */
    @Transactional
    public boolean saveOrUpdateSaleGroup(SaleGroup saleGroup, int flag) {
        logger.info("编辑参数,{}", JSON.toJSONString(saleGroup));

        boolean result;
        if (flag == 2) {//更新
            result = this.updateSaleGroup(saleGroup);
        } else {//添加
            saleGroup.setId(null);
            saleGroup.setCreateTime(new Date());
            saleGroupMapper.insertSelective(saleGroup); //插入组

            result = roleGroupService.saveRoleToGroup(saleGroup.getId()); //默认分配组角色
        }

        return result;
    }


    /**
     * 更新销管组
     *
     * @param saleGroup
     * @return
     */
    @Transactional
    public boolean updateSaleGroup(SaleGroup saleGroup) {
        saleGroup.setUpdateTime(new Date());

        SaleGroupExample example = new SaleGroupExample();
        example.createCriteria().andIdEqualTo(saleGroup.getId());
        int result = saleGroupMapper.updateByExampleSelective(saleGroup, example);

        return result > 0;
    }

    /**
     * 判断当前组下是否存在用户
     *
     * @param groupId 组id
     * @return true存在，false则不存在
     */
    public boolean checkExistsUsers(final long groupId) {
        SaleUserGroupExample example = new SaleUserGroupExample();
        example.createCriteria().andGroupIdEqualTo(groupId);
        return saleUserGroupsMapper.countByExample(example) > 0;
    }

    /**
     * 根据id查询组
     *
     * @param groupId
     * @return
     */
    public SaleGroup getGroupById(final long groupId) {
        return saleGroupMapper.selectByPrimaryKey(groupId);
    }
}
