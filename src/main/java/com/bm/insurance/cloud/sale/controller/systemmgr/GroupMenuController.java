package com.bm.insurance.cloud.sale.controller.systemmgr;

import com.alibaba.fastjson.JSON;
import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleGroupMenu;
import com.bm.insurance.cloud.sale.service.systemmgr.GroupMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 角色组菜单资源权限controller
 */
@Controller
@RequestMapping("/groupMenu")
public class GroupMenuController extends BaseController {

    @Autowired
    private GroupMenuService groupMenuService;

    /**
     * 获取当前组中角色的所有菜单与操作按钮
     * @param groupId 组id
     * @param roleId  组中角色id
     * @return
     */
    @RequestMapping(value = "/loadGroupMenuOpers/{groupId}/{roleId}")
    @ResponseBody
    public ResponseDto loadGroupMenuOpers(@PathVariable long groupId, @PathVariable long roleId) {
        List<SaleGroupMenu> list = groupMenuService.loadGroupMenuOpers(groupId, roleId);
        return super.success(list);
    }

    /**
     * 保存组菜单资源
     * @param groupMenuOpers
     * @return
     */
    @RequestMapping(value = "/saveGroupMenuOperates")
    @ResponseBody
    public ResponseDto saveGroupMenuOperates(String groupMenuOpers){
        if(StringUtils.isBlank(groupMenuOpers)){
            return super.error(StatusCodeEnum.NEED_ARGS);
        }

        List<SaleGroupMenu> list = JSON.parseArray(groupMenuOpers,SaleGroupMenu.class);
        boolean result = groupMenuService.batchInsert(list);

        return super.success(result);
    }

}
