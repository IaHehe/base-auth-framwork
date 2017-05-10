package com.bm.insurance.cloud.sale.service.systemmgr;


import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.dao.SaleOperateMapper;
import com.bm.insurance.cloud.sale.enums.StatusEnum;
import com.bm.insurance.cloud.sale.model.SaleOperate;
import com.bm.insurance.cloud.sale.model.SaleOperateExample;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OperateService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleOperateMapper saleOperateMapper;


    public List<SaleOperate> findOperateList() {
        SaleOperateExample example = new SaleOperateExample();
        return saleOperateMapper.selectByExample(example);
    }

    public List<SaleOperate> findEnableOperateList() {
        SaleOperateExample example = new SaleOperateExample();
        example.createCriteria().andStatusEqualTo((byte) StatusEnum.EABLE.getCode());
        return saleOperateMapper.selectByExample(example);
    }

    /**
     * 插入按钮操作
     *
     * @param SaleOperate
     * @return
     */
    @Transactional
    public boolean saveOrUpdateOperate(SaleOperate SaleOperate) {
        logger.info("编辑参数,{}", JSON.toJSONString(SaleOperate));

        boolean result;
        if (SaleOperate.getId() != null) {
            SaleOperate.setOperCode(SaleOperate.getOperCode().toUpperCase());
            result = this.updateOperate(SaleOperate);
        } else {
            SaleOperate.setId(null);
            SaleOperate.setCreateTime(new Date());

            result = saleOperateMapper.insertSelective(SaleOperate) > 0;
        }
        return result;
    }

    /**
     * 更新按钮操作
     *
     * @param SaleOperate
     * @return
     */
    @Transactional
    public boolean updateOperate(SaleOperate SaleOperate) {
        SaleOperate.setUpdateTime(new Date());

        SaleOperateExample example = new SaleOperateExample();
        example.createCriteria().andIdEqualTo(SaleOperate.getId());
        int result = saleOperateMapper.updateByExampleSelective(SaleOperate, example);

        return result > 0;
    }

    /**
     * 禁用角色
     *
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteOperate(final long id) {
        return saleOperateMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 检验编码是否唯一
     *
     * @param operCode
     * @return true表是不存在，false则存在
     */
    public boolean checkUniqueCode(String operCode) {
        SaleOperateExample example = new SaleOperateExample();
        example.createCriteria().andOperCodeEqualTo(operCode.toUpperCase().trim());
        List<SaleOperate> list = saleOperateMapper.selectByExample(example);

        return CollectionUtils.isEmpty(list);
    }

    /**
     * 根据id查询对象
     *
     * @param id
     * @return
     */
    public SaleOperate getOperate(long id) {
        return saleOperateMapper.selectByPrimaryKey(id);
    }
}
