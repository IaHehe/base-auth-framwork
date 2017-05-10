package com.bm.insurance.cloud.sale.controller.systemmgr;

import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.model.SaleDepartment;
import com.bm.insurance.cloud.sale.service.systemmgr.DepartmentService;
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
 * 部门controller
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentService departmentService;

    /**
     * 部门管理页面
     */
    @RequestMapping(value = "/deptListPage")
    public String groupListPage(Model model) {
        return "dept/deptList";
    }

    /**
     * 部门树
     */
    @RequestMapping(value = "/loadDeptTree")
    @ResponseBody
    public ResponseDto loadDeptTree() {
        List<SaleDepartment> list = departmentService.loadDepartmentTree();
        return super.success(list);
    }

    /**
     * 添加或者修改部门
     * @param flag 1表示添加;2表示更新
     * @param saleDepartment 部门实体
     */
    @RequestMapping(value = "/editDept/{flag}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto editDept(@PathVariable int flag, SaleDepartment saleDepartment) {
        boolean result = departmentService.saveOrUpdateDepartment(saleDepartment, flag);
        return ResponseUtil.success(result);
    }

}
