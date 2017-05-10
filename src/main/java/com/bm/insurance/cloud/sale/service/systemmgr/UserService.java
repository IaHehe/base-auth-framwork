package com.bm.insurance.cloud.sale.service.systemmgr;

import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.common.Constant;
import com.bm.insurance.cloud.sale.dao.SaleUserMapper;
import com.bm.insurance.cloud.sale.dto.DataGridDto;
import com.bm.insurance.cloud.sale.dto.PageDto;
import com.bm.insurance.cloud.sale.model.SaleUser;
import com.bm.insurance.cloud.sale.model.SaleUserExample;
import com.bm.insurance.cloud.sale.service.BaseService;
import com.bm.insurance.cloud.sale.utils.Md5;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户管理
 * Created by hubo on 2017/4/17.
 */
@Service
public class UserService extends BaseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleUserMapper saleUserMapper;

    /**
     * 用户列表分页查询
     *
     * @param pageDto
     * @return
     */
    public DataGridDto findUserPage(PageDto pageDto, SaleUser saleUser) {
        logger.info("分页:{}搜索条件: {}", JSON.toJSONString(pageDto), JSON.toJSONString(saleUser));

        Page<SaleUser> page = PageHelper.startPage(pageDto.getPage(), pageDto.getRows());
        List<SaleUser> userList = saleUserMapper.selectByExample(this.buildCondition(saleUser));

        return super.getDataGridDto(page, userList);
    }

    /**
     * 组合搜索条件
     *
     * @param saleUser
     * @return
     */
    private SaleUserExample buildCondition(SaleUser saleUser) {
        SaleUserExample example = new SaleUserExample();
        example.setOrderByClause("creat_time DESC"); //按创建时间倒序
        SaleUserExample.Criteria criteria = example.createCriteria();

        if (saleUser.getStatus() != null) {//状态
            criteria.andStatusEqualTo(saleUser.getStatus());
        }
        if (StringUtils.isNotBlank(saleUser.getLoginName())) {//用户名
            criteria.andLoginNameLike(super.like(saleUser.getLoginName()));
        }
        if (StringUtils.isNotBlank(saleUser.getUserName())) {//姓名
            criteria.andUserNameLike(super.like(saleUser.getUserName()));
        }
        if (StringUtils.isNotBlank(saleUser.getEmail())) {//邮箱
            criteria.andEmailLike(super.like(saleUser.getEmail()));
        }
        if (StringUtils.isNotBlank(saleUser.getJobTitle())) {//岗位
            criteria.andJobTitleLike(super.like(saleUser.getJobTitle()));
        }
        if (saleUser.getMobile() != null) { //电话号码
            criteria.andMobileEqualTo(saleUser.getMobile());
        }
        if (saleUser.getDeptId() != null) { //业务部门
            criteria.andDeptIdEqualTo(saleUser.getDeptId());
        }

        return example;
    }

    /**
     * 更新用户
     *
     * @param saleUser
     * @return
     */
    @Transactional
    public boolean updateUser(SaleUser saleUser) {
        saleUser.setUpdateTime(new Date());
        SaleUserExample example = new SaleUserExample();
        example.createCriteria().andIdEqualTo(saleUser.getId());

        return saleUserMapper.updateByExampleSelective(saleUser, example) > 0;
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return
     */
    @Transactional
    public boolean deleteUser(long userId, int status) {
        SaleUser saleUser = new SaleUser();
        saleUser.setId(userId);
        saleUser.setStatus((byte) status);
        saleUser.setUpdateTime(new Date());

        return this.updateUser(saleUser);
    }

    /**
     * 校验用户名是否唯一
     *
     * @param userName 用户名
     * @return true为不存在，false为存在
     */
    public boolean checkUniqueUserName(String userName) {
        SaleUserExample example = new SaleUserExample();
        example.createCriteria().andUserNameEqualTo(userName.trim());
        List<SaleUser> list = saleUserMapper.selectByExample(example);

        return CollectionUtils.isEmpty(list);
    }

    /**
     * 根据用户id查找对象
     *
     * @param userId 用户id
     * @return
     */
    public SaleUser getUserById(long userId) {
        return saleUserMapper.selectByPrimaryKey(userId);
    }


    /**
     * 添加或者更新用户
     *
     * @return
     */
    public boolean saveOrUpdateUser(SaleUser saleUser, SaleUser loginUser) {
        boolean result;
        Date now = new Date();
        saleUser.setPassword(Md5.md5(saleUser.getPassword()));

        if (saleUser.getId() != null) {//更新
            saleUser.setPassword(null);
            saleUser.setUpdateTime(now);
            saleUser.setCreateUserId(loginUser.getId());
            saleUser.setCreateUserName(loginUser.getLoginName());

            result = this.updateUser(saleUser);
        } else {//插入
            saleUser.setCreatTime(now);
            result = saleUserMapper.insertSelective(saleUser) > 0;
        }

        return result;
    }

    /**
     * 重置密码
     *
     * @param userId 用户id
     * @return true成功, false失败
     */
    public boolean resetPwd(final long userId) {
        logger.info("重置密码userId={}", userId);

        SaleUser saleUser = new SaleUser();
        saleUser.setId(userId);
        saleUser.setPassword(Md5.md5(Constant.DEFAULT_PASSWORD));

        return this.updateUser(saleUser);
    }
}
