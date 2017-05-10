package com.bm.insurance.cloud.sale.controller.systemmgr;

import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.model.SaleGroup;
import com.bm.insurance.cloud.sale.service.systemmgr.GroupService;
import com.bm.insurance.cloud.sale.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 组controller
 */
@Controller
@RequestMapping("/group")
public class GroupController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GroupService groupService;

    /**
     * 分组管理页面
     */
    @RequestMapping(value = "/groupListPage")
    public String groupListPage(Model model) {
        return "group/groupList";
    }

    /**
     * 分组树
     * @return
     */
    @RequestMapping(value="/loadgroupTree")
    @ResponseBody
    public ResponseDto loadgroupTree() {
        List<SaleGroup> list = groupService.loadgroupTree();
        return super.success(list);
    }

    /**
     * 编辑分组信息
     *
     * @param saleGroup
     * @return
     */
    @RequestMapping(value = "/editGroup/{flag}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto editSaleGroup(@PathVariable int flag, SaleGroup saleGroup) {
        boolean result = groupService.saveOrUpdateSaleGroup(saleGroup,flag);
        return ResponseUtil.success(result);
    }

    @RequestMapping(value = "/checkExistsUsers/{groupId}")
    @ResponseBody
    public ResponseDto checkExistsUsers(@PathVariable long groupId) {
        boolean result = groupService.checkExistsUsers(groupId);
        return ResponseUtil.success(result);
    }

    @RequestMapping(value = "/removeGroupById/{groupId}")
    @ResponseBody
    public ResponseDto removeGroupById(@PathVariable long groupId) {
        SaleGroup group = new SaleGroup();
        group.setId(groupId);
        group.setStatus((byte) 2);
        boolean result = groupService.updateSaleGroup(group);

        return ResponseUtil.success(result);
    }


}
