package com.bm.insurance.cloud.sale.service.systemmgr;

import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.dao.SaleDepartmentMapper;
import com.bm.insurance.cloud.sale.enums.StatusEnum;
import com.bm.insurance.cloud.sale.model.SaleDepartment;
import com.bm.insurance.cloud.sale.model.SaleDepartmentExample;
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
public class DepartmentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleDepartmentMapper saleDepartmentMapper;

    /**
     * 加载未禁用的分组树
     * @return
     */
    public List<SaleDepartment> loadDepartmentTree() {
        SaleDepartmentExample example = new SaleDepartmentExample();
        example.createCriteria().andStatusEqualTo((byte) StatusEnum.EABLE.getCode());

        return saleDepartmentMapper.selectByExample(example);
    }

    /**
     * 编辑组
     * @param saleDepartment
     * @param flag 1表示添加;2表示更新
     * @return
     */
    @Transactional
    public boolean saveOrUpdateDepartment(SaleDepartment saleDepartment,int flag) {
        logger.info("编辑参数,{}", JSON.toJSONString(saleDepartment));

        boolean result;
        if (flag == 2) {//更新
            result = this.updateDepartment(saleDepartment);
        } else {//添加
            saleDepartment.setId(null);
            saleDepartment.setCreateTime(new Date());

            result = saleDepartmentMapper.insertSelective(saleDepartment) > 0;
        }
        return result;
    }

    /**
     * 更新销管组
     *
     * @param saleDepartment
     * @return
     */
    @Transactional
    public boolean updateDepartment(SaleDepartment saleDepartment) {
        saleDepartment.setUpdateTime(new Date());

        SaleDepartmentExample example = new SaleDepartmentExample();
        example.createCriteria().andIdEqualTo(saleDepartment.getId());
        int result = saleDepartmentMapper.updateByExampleSelective(saleDepartment, example);

        return result > 0;
    }

    public SaleDepartment getDeptById(long deptId){
        return saleDepartmentMapper.selectByPrimaryKey(deptId);
    }
}
