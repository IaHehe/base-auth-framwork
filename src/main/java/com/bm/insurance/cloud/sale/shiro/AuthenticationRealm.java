package com.bm.insurance.cloud.sale.shiro;

import com.bm.insurance.cloud.sale.dto.SearchUserGroupDto;
import com.bm.insurance.cloud.sale.enums.RoleEnum;
import com.bm.insurance.cloud.sale.model.SaleMenu;
import com.bm.insurance.cloud.sale.model.SaleOperate;
import com.bm.insurance.cloud.sale.model.SaleRole;
import com.bm.insurance.cloud.sale.model.SaleUser;
import com.bm.insurance.cloud.sale.service.login.LonginService;
import com.bm.insurance.cloud.sale.service.systemmgr.MenuService;
import com.bm.insurance.cloud.sale.service.systemmgr.OperateService;
import com.bm.insurance.cloud.sale.service.systemmgr.UserGroupService;
import com.bm.insurance.cloud.sale.service.systemmgr.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * shiro登陆认证与授权realm
 */
public class AuthenticationRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationRealm.class);

    @Autowired
    private LonginService longinService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private OperateService operateService;


    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername(); // 用户名
        String password = new String(token.getPassword()); // 密码

        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            SaleUser user = longinService.login(userName, password);
            if (user == null) {
                throw new UnknownAccountException();
            }

            SearchUserGroupDto bigUser = userGroupService.getUserRoleGroupByUserId(user.getId());
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    bigUser, bigUser.getPassword(), getName());
            //this.setSession(Constant.CURRENT_USER, bigUser);

            return authenticationInfo;
        } else {
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("授权查询角色与操作权...");
        SearchUserGroupDto user = (SearchUserGroupDto) principals.getPrimaryPrincipal();

        if (user != null && StringUtils.isNotBlank(user.getLoginName())) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            SaleRole role = userRoleService.getRoleByUserId(user.getUserId());
            info.addRoles(Arrays.asList(role.getRoleCode())); //角色

            List<String> permissions = this.getUserPermissionList(user);
            info.addStringPermissions(permissions);//权限

            return info;
        }

        return null;
    }

    /**
     * 用户菜单及按钮功能权限
     *
     * @return
     */
    private List<String> getUserPermissionList(SearchUserGroupDto bigUser) {

        if (bigUser.getRoleId() == RoleEnum.SUPER_ADMIN.getId()) {//超级管理员
            return this.giveSuperAdminAuth();
        }

        List<String> list = new ArrayList<>();

        SaleUser user = new SaleUser();
        user.setId(bigUser.getUserId());
        List<SaleMenu> permissionList = menuService.findMenuByUser(user);

        for (SaleMenu menu : permissionList) {
            String[] opers = menu.getExp3().split(",");
            for (String btnId : opers) {
                if (StringUtils.isBlank(btnId)) {
                    continue;
                }
                String permission = menu.getMenuCode() + ":" + btnId;
                list.add(permission);
            }
        }

        return list;
    }

    /**
     * 默认超级管理员有所有菜单及权限
     * @return
     */
    public List<String> giveSuperAdminAuth(){
        List<String> authList = new ArrayList<>();

        List<SaleMenu> menuList = menuService.findMenus();
        List<SaleOperate> operateList = operateService.findEnableOperateList();

        for(SaleMenu menu : menuList){
            for(SaleOperate operate : operateList){
                StringBuilder permission = new StringBuilder();
                permission.append(menu.getMenuCode()); //菜单
                permission.append(":");
                permission.append(operate.getId()); //按钮

                authList.add(permission.toString());
            }
        }

        return authList;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            logger.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("退出清除缓存中的授权信息....");
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }


}