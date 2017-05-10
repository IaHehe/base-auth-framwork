package com.bm.insurance.cloud.sale.service.systemmgr;


import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.common.Constant;
import com.bm.insurance.cloud.sale.dao.SaleMenuMapper;
import com.bm.insurance.cloud.sale.dto.MenuHelpDto;
import com.bm.insurance.cloud.sale.dto.MenuTreeDto;
import com.bm.insurance.cloud.sale.dto.SearchUserGroupDto;
import com.bm.insurance.cloud.sale.dto.TreegridDto;
import com.bm.insurance.cloud.sale.enums.StatusEnum;
import com.bm.insurance.cloud.sale.model.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单管理service
 */
@Service
public class MenuService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleMenuMapper saleMenuMapper;
    @Autowired
    private UserMenuService userMenuService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private GroupMenuService groupMenuService;
    @Autowired
    private RoleMenuService roleMenuService;

    private static String CONTEXT_PATH = "";

    /**
     * 树形菜单
     *
     * @return
     */
    public TreegridDto loadTreeGrid() {
        TreegridDto treegridDto = new TreegridDto();

        List<MenuHelpDto> list = saleMenuMapper.loadTreeGrid();
        treegridDto.setTotal(list.size());
        treegridDto.setRows(list);

        return treegridDto;
    }


    /**
     * 查询用户菜单权限
     *
     * @param contextPath
     * @param user
     * @return
     */
    public List<MenuTreeDto> findUserMenuPermission(String contextPath, SaleUser user) {
        CONTEXT_PATH = contextPath;
        return this.buildMenuTree(this.findMenuByUser(user));
    }


    /**
     * 查询用户的的权限菜单资源
     * salemenu中exp3设置了菜单的所有操作按钮权属性
     *
     * @param user 当前登陆用户
     * @return
     */
    public List<SaleMenu> findMenuByUser(SaleUser user) {
        List<SaleMenu> baseList;

        long userId = user.getId();
        List<SaleUserMenu> menuList = userMenuService.findMyMenus(userId);

        if (CollectionUtils.isEmpty(menuList)) {
            baseList = this.findMenusByStep(userId);
        } else {
            baseList = this.tranformMenu(menuList);
        }

        if (CollectionUtils.isEmpty(baseList)) {
            return null;
        }
        List<Long> menuIdList = this.getMenuIdList(baseList);
        List<SaleMenu> realMenuList = this.findByMenuIdList(menuIdList);

        this.copyOperatesToExp(baseList, realMenuList); //操作按钮权限复制

        List<Long> pIdList = this.getMenuPIdList(realMenuList);
        pIdList.removeAll(menuIdList);
        List<SaleMenu> parentMenuList = this.findByMenuIdList(pIdList);

        if (CollectionUtils.isNotEmpty(parentMenuList))
            realMenuList.addAll(parentMenuList); //父菜单与子菜单并集

        return realMenuList;
    }

    /**
     * <ol>
     * <li>如果用户所在组所属角色有菜单记录，则取</li>
     * <li>如果用户所在组所属角色没有菜单记录，则取角色分配的拥有的菜单</li>
     * </ol>
     *
     * @param userId 用户id
     */
    private List<SaleMenu> findMenusByStep(final long userId) {
        List<SaleMenu> baseList;

        SearchUserGroupDto baseUser = userGroupService.getUserRoleGroupByUserId(userId);
        if (baseUser.getGroupId() == null) {//用户必须被分配到组才有权限
            return null;
        }

        List<SaleGroupMenu> groupMenuList = groupMenuService
                .loadGroupMenuOpers(baseUser.getGroupId(), baseUser.getRoleId());

        if (CollectionUtils.isEmpty(groupMenuList)) {
            List<SaleRoleMenu> roleMenuList = roleMenuService.loadRoleMenuOpers(baseUser.getRoleId());
            baseList = this.tranformRoleMenu(roleMenuList);
        } else {
            baseList = this.tranformGroupMenu(groupMenuList);
        }

        return baseList;
    }

    /**
     * 查询销管角色列表
     */
    public List<MenuTreeDto> findMenuList(String contextPath) {
        CONTEXT_PATH = contextPath;
        List<SaleMenu> baseList = this.findMenus();

        return this.buildMenuTree(baseList);
    }

    public List<MenuTreeDto> buildMenuTree(List<SaleMenu> baseList) {
        if (CollectionUtils.isEmpty(baseList)) {
            return null;
        }

        List<MenuTreeDto> menuTreeDtoList = new ArrayList<>();
        List<MenuTreeDto> parentList = this.collectParentNodes(baseList);

        if (CollectionUtils.isNotEmpty(parentList)) {
            for (MenuTreeDto menu : parentList) {
                List<MenuTreeDto> children = this.collectChildNodes(menu.getId(), baseList);
                menu.setChildren(children);
                menuTreeDtoList.add(menu);
            }
        } else {
            this.transform(baseList);
        }

        return menuTreeDtoList;
    }

    public List<SaleMenu> findMenus() {
        SaleMenuExample example = new SaleMenuExample();
        example.createCriteria().andStatusEqualTo((byte) StatusEnum.EABLE.getCode());
        example.setOrderByClause("sort asc");

        return saleMenuMapper.selectByExample(example);
    }

    public MenuTreeDto setProperty(SaleMenu menu) {
        long menuId = menu.getId();

        MenuTreeDto treeDto = new MenuTreeDto();
        treeDto.setId(menu.getId());
        treeDto.setText(menu.getMenuName());
        treeDto.setIcon(menu.getIcon());
        treeDto.setTargetType(menu.getTargetType());

        String url = CONTEXT_PATH + menu.getUrl();
        url += "?menuId=" + menuId;
        treeDto.setUrl(url);

        return treeDto;
    }

    public List<MenuTreeDto> transform(List<SaleMenu> children) {
        List<MenuTreeDto> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(children)) {
            for (SaleMenu menu : children) {
                resultList.add(setProperty(menu));
            }
        }

        return resultList;
    }

    /**
     * 取出所有的父节点
     *
     * @param baseList
     * @return
     */
    public List<MenuTreeDto> collectParentNodes(List<SaleMenu> baseList) {
        List<SaleMenu> list = (List<SaleMenu>) CollectionUtils.select(baseList, new Predicate<SaleMenu>() {
            @Override
            public boolean evaluate(SaleMenu saleMenu) {
                return saleMenu.getLevels() == 1 || saleMenu.getpId() == null;
            }
        });

        return this.transform(list);
    }

    /**
     * 取出当前父节点下的所有子节点
     *
     * @param id
     * @param baseList
     * @return
     */
    public List<MenuTreeDto> collectChildNodes(final long id, List<SaleMenu> baseList) {
        List<SaleMenu> list = (List<SaleMenu>) CollectionUtils.select(baseList, new Predicate<SaleMenu>() {
            @Override
            public boolean evaluate(SaleMenu saleMenu) {
                if (saleMenu.getpId() == null) {
                    return false;
                }
                return saleMenu.getpId() == id;
            }
        });
        return this.transform(list);
    }

    /**
     * 禁用菜单
     *
     * @param menuId 菜单id
     * @param status 状态
     * @return
     */
    public boolean disableMenu(final long menuId, final int status) {
        SaleMenu menu = new SaleMenu();
        menu.setId(menuId);
        menu.setStatus((byte) status);

        return this.updateMenu(menu);
    }

    /**
     * 插入或者修改菜单
     *
     * @param menu
     * @return
     */
    public boolean saveOrUpdateMenu(SaleMenu menu) {
        logger.info("编辑菜单参数: {}", JSON.toJSONString(menu));

        boolean result;
        String url = StringUtils.isBlank(menu.getUrl()) ? "-" : menu.getUrl();
        menu.setUrl(url);
        Long pId = menu.getpId();

        if (pId == -1) {
            menu.setpId(null);
            menu.setLevels(1);
        }

        if (menu.getId() != null) {//更新
            menu.setLevels(pId == -1 ? 1 : 2);
            result = this.updateMenu(menu);
        } else {
            menu.setLevels(pId > -1 ? 2 : 1);
            saleMenuMapper.insertSelective(menu);
            result = this.updateMenuCode(menu);
        }

        return result;
    }

    /**
     * 更新菜单的编码
     *
     * @param menu
     * @return
     */
    public boolean updateMenuCode(SaleMenu menu) {
        SaleMenu saleMenu = new SaleMenu();
        Long menuId = menu.getId();
        saleMenu.setId(menuId);
        saleMenu.setMenuCode(Constant.MENU_CODE_PREFIX + menuId);

        return this.updateMenu(saleMenu);
    }


    /**
     * 更新菜单
     *
     * @param menu
     * @return
     */
    public boolean updateMenu(SaleMenu menu) {
        menu.setUpdateTime(new Date());

        SaleMenuExample example = new SaleMenuExample();
        example.createCriteria().andIdEqualTo(menu.getId());

        return saleMenuMapper.updateByExampleSelective(menu, example) > 0;
    }

    /**
     * 加载一级下拉菜单树
     *
     * @return
     */
    public List<MenuTreeDto> loadComboTree() {
        List<SaleMenu> baseList = this.findMenus();
        List<MenuTreeDto> treeList = this.collectParentNodes(baseList);

        MenuTreeDto treeDto = new MenuTreeDto();
        treeDto.setId(-1L);
        treeDto.setText("Root节点");
        treeDto.setChildren(treeList);

        return Arrays.asList(treeDto);
    }

    /**
     * 查2级菜单
     *
     * @return
     */
    public List<SaleMenu> findSecondLevelMenuList() {
        SaleMenuExample example = new SaleMenuExample();
        example.setOrderByClause("sort asc");
        example.createCriteria()
                .andStatusEqualTo((byte) StatusEnum.EABLE.getCode());

        return saleMenuMapper.selectByExample(example);

    }

    private List<SaleMenu> findByMenuIdList(List<Long> menuIdList) {
        if (CollectionUtils.isEmpty(menuIdList) && menuIdList.size() > 0) {
            return null;
        }
        SaleMenuExample example = new SaleMenuExample();
        example.setDistinct(true);
        example.createCriteria().andIdIn(menuIdList);

        return saleMenuMapper.selectByExample(example);
    }

    private List<SaleMenu> tranformMenu(List<SaleUserMenu> menuList) {
        List<SaleMenu> resultList = new ArrayList<>();
        for (SaleUserMenu userMenu : menuList) {
            SaleMenu menu = new SaleMenu();
            menu.setId(userMenu.getMenuId());
            menu.setExp3(userMenu.getOperates());

            resultList.add(menu);
        }

        return resultList;
    }

    private List<SaleMenu> tranformGroupMenu(List<SaleGroupMenu> menuList) {
        List<SaleMenu> resultList = new ArrayList<>();
        for (SaleGroupMenu userMenu : menuList) {
            SaleMenu menu = new SaleMenu();
            menu.setId(userMenu.getMenuId());
            menu.setExp3(userMenu.getOperates());

            resultList.add(menu);
        }

        return resultList;
    }

    private List<SaleMenu> tranformRoleMenu(List<SaleRoleMenu> menuList) {
        List<SaleMenu> resultList = new ArrayList<>();
        for (SaleRoleMenu userMenu : menuList) {
            SaleMenu menu = new SaleMenu();
            menu.setId(userMenu.getMenuId());
            menu.setExp3(userMenu.getOperates());

            resultList.add(menu);
        }

        return resultList;
    }

    private List<Long> getMenuIdList(List<SaleMenu> baseList) {
        List<Long> idList = (List<Long>) CollectionUtils.collect(baseList, new Transformer() {
            @Override
            public Long transform(Object obj) {
                SaleMenu saleMenu = (SaleMenu) obj;
                return saleMenu.getId();
            }
        });
        Set<Long> set = new HashSet<>(idList);

        return new ArrayList<>(set);
    }

    private List<Long> getMenuPIdList(List<SaleMenu> baseList) {
        List<Long> idList = (List<Long>) CollectionUtils.collect(baseList, new Transformer() {
            @Override
            public Long transform(Object obj) {
                SaleMenu saleMenu = (SaleMenu) obj;
                return saleMenu.getpId();
            }
        });
        Set<Long> set = new HashSet<>(idList);

        return new ArrayList<>(set);
    }

    private void copyOperatesToExp(List<SaleMenu> baseList, List<SaleMenu> realMenuList) {
        if (CollectionUtils.isNotEmpty(realMenuList)) {
            for (SaleMenu menu : realMenuList) {
                String operBtn = this.getOperateProp(menu.getId(), baseList);
                menu.setExp3(operBtn);
            }
        }
    }

    private String getOperateProp(final long menuId, List<SaleMenu> baseList) {
        List<SaleMenu> list = (List<SaleMenu>) CollectionUtils.select(baseList, new Predicate<SaleMenu>() {
            @Override
            public boolean evaluate(SaleMenu saleMenu) {
                return saleMenu.getId() == menuId;
            }
        });

        return list.get(0).getExp3();
    }

}
