package com.bm.insurance.cloud.sale.service.systemmgr;

import com.bm.insurance.cloud.sale.dao.SaleUserDataAuthMapper;
import com.bm.insurance.cloud.sale.model.SaleUserDataAuth;
import com.bm.insurance.cloud.sale.model.SaleUserDataAuthExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据权限
 */
@Service
public class UserDataAuthService {

    @Autowired
    private SaleUserDataAuthMapper saleUserDataAuthMapper;

    /**
     * 查询用户的数据权限
     * @param userId
     * @param groupId
     * @return
     */
    public List<SaleUserDataAuth> findUserDataAuth(final long userId){
        SaleUserDataAuthExample example = new SaleUserDataAuthExample();
        example.createCriteria().andUserIdEqualTo(userId);

        return saleUserDataAuthMapper.selectByExample(example);
    }

    /**
     * 保存用户的数据权限
     * @param userId
     * @param dataAuthStr
     * @return
     */
    public boolean saveUserDataAuth(final long userId, String dataAuthStr) {
        List<SaleUserDataAuth> list = new ArrayList<>();

        String[] arr = dataAuthStr.split(",");
        for (String key : arr) {
            Long groupId = Long.valueOf(key);
            SaleUserDataAuth dataAuth = new SaleUserDataAuth();
            dataAuth.setUserId(userId);
            dataAuth.setDataAuth(groupId);

            list.add(dataAuth);
        }

        this.deleteByUserId(userId); //先删除
        return this.batchInsert(list);
    }

    @Transactional
    public boolean batchInsert(List<SaleUserDataAuth> dataAuthList) {
        return saleUserDataAuthMapper.insertBatch(dataAuthList) > 0;
    }

    @Transactional
    public boolean deleteByUserId(final long userId) {
        SaleUserDataAuthExample example = new SaleUserDataAuthExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return saleUserDataAuthMapper.deleteByExample(example) > 0;
    }
}
