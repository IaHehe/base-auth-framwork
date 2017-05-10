package com.bm.insurance.cloud.sale.service.login;

import com.bm.insurance.cloud.sale.dao.SaleUserMapper;
import com.bm.insurance.cloud.sale.model.SaleUser;
import com.bm.insurance.cloud.sale.model.SaleUserExample;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登陆service
 */
@Service
public class LonginService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleUserMapper saleUserMapper;

    /**
     * 登陆
     *
     * @param userName
     * @param password
     * @return
     */
    public SaleUser login(String userName, String password) {
        SaleUser saleUser = null;

        try {
            SaleUserExample example = new SaleUserExample();
            example.createCriteria().andLoginNameEqualTo(userName.trim())
                    .andPasswordEqualTo(password);

            List<SaleUser> list = saleUserMapper.selectByExample(example);
            if (CollectionUtils.isNotEmpty(list)) {
                saleUser = list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return saleUser;
    }
}
