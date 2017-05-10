package com.bm.insurance.cloud.sale.utils;

import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;

/**
 * Created by hubo on 2017/4/12.
 */
public class ResponseUtil {

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static ResponseDto success(Object data) {
        ResponseDto response = new ResponseDto();
        response.setCode(StatusCodeEnum.SUCCESS.getCode());
        response.setMsg(StatusCodeEnum.SUCCESS.getDesc());
        response.setSuccess(true);
        response.setData(data);

        return response;
    }

    /**
     * 异常时返回
     *
     * @param statusEnum
     * @return
     */
    public static ResponseDto error(StatusCodeEnum statusEnum) {
        ResponseDto response = new ResponseDto();
        response.setCode(statusEnum.getCode());
        response.setMsg(statusEnum.getDesc());
        response.setSuccess(false);
        response.setData(null);

        return response;
    }

    /**
     * 异常时返回
     *
     * @param code     状态码
     * @param errorMsg 错误信息
     * @return
     */
    public static ResponseDto error(int code, String errorMsg) {
        ResponseDto response = new ResponseDto();
        response.setCode(code);
        response.setMsg(errorMsg);
        response.setSuccess(false);
        response.setData(null);

        return response;
    }
}
