package com.bm.insurance.cloud.sale.controller;

import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.dto.SearchUserGroupDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleUser;
import com.bm.insurance.cloud.sale.utils.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public abstract class BaseController {

    /**
     * 用户信息
     *
     * @return
     */
    protected SaleUser getUser() {
        SearchUserGroupDto bigUser = this.getUserRoleGroup();

        SaleUser user = new SaleUser();
        user.setId(bigUser.getUserId());
        user.setLoginName(bigUser.getLoginName());
        user.setUserName(bigUser.getUserName());

        return user;
    }

    /**
     * 登陆用户信息，角色，分组
     *
     * @return
     */
    protected SearchUserGroupDto getUserRoleGroup() {
        Subject subject = SecurityUtils.getSubject();
        return (SearchUserGroupDto) subject.getPrincipal();
    }


    /**
     * 成功统一返回Response实体数据
     *
     * @param data
     * @return
     */
    protected ResponseDto success(Object data) {
        return ResponseUtil.success(data);
    }

    /**
     * 异常时统一返回Response实体数据
     *
     * @param statusEnum
     * @return
     */
    protected ResponseDto error(StatusCodeEnum statusEnum) {
        return ResponseUtil.error(statusEnum);
    }
}
