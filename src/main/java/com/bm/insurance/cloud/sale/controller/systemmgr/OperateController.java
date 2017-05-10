package com.bm.insurance.cloud.sale.controller.systemmgr;


import com.bm.insurance.cloud.sale.controller.BaseController;
import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.model.SaleOperate;
import com.bm.insurance.cloud.sale.service.systemmgr.OperateService;
import com.bm.insurance.cloud.sale.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
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
 * 按钮管理controller
 */
@Controller
@RequestMapping("/operate")
public class OperateController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OperateService operateService;

    /**
     * 按钮操作管理页面
     */
    @RequestMapping(value = "/operateListPage")
    public String index(Model model) {
        return "operate/operateList";
    }

    /**
     * 查询所有按钮操作列表
     * @return
     */
    @RequestMapping(value = "/findOperateList")
    @ResponseBody
    public List<SaleOperate> findOperateList() {
        return operateService.findOperateList();
    }

    /**
     * 根据id获取销管按钮操作信息
     */
    @RequestMapping(value = "/getOperate/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto getSaleOperate(@PathVariable long id) {
        SaleOperate operate = operateService.getOperate(id);
        return ResponseUtil.success(operate);
    }

    /**
     * 插入销管按钮操作信息
     *
     * @param saleOperate
     * @return
     */
    @RequestMapping(value = "/editOperate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto editSaleOperate(SaleOperate saleOperate) {
        boolean result = operateService.saveOrUpdateOperate(saleOperate);
        return ResponseUtil.success(result);
    }

    /**
     * 删除按钮操作
     *
     * @return
     */
    @RequestMapping(value = "/deleteOper/{operateId}")
    @ResponseBody
    public ResponseDto deleteOperate(@PathVariable long operateId) {
        boolean result = operateService.deleteOperate(operateId);
        return super.success(result);
    }

    /**
     * 检验编码是否唯一
     * @param operateCode
     * @return
     */
    @RequestMapping(value = "/checkUniqueCode")
    @ResponseBody
    public ResponseDto checkUniqueCode(String operateCode) {
        if(StringUtils.isBlank(operateCode)){
            return super.error(StatusCodeEnum.NEED_ARGS);
        }

        boolean result = operateService.checkUniqueCode(operateCode);
        return super.success(result);
    }
}
