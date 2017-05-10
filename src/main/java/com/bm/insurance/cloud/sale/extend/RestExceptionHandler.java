package com.bm.insurance.cloud.sale.extend;

import com.bm.insurance.cloud.sale.dto.ResponseDto;
import com.bm.insurance.cloud.sale.enums.StatusCodeEnum;
import com.bm.insurance.cloud.sale.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class RestExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseDto runtimeExceptionHandler(RuntimeException ex) {
        logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.SERVER_RUN_EX);
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseDto nullPointerExceptionHandler(NullPointerException ex) {
        logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.SERVER_NULL_EX);
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public ResponseDto classCastExceptionHandler(ClassCastException ex) {
       logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.PARSE_EX);
    }

    /**
     * IO异常
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ResponseDto iOExceptionHandler(IOException ex) {
       logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.IO_EX);
    }

    /**
     * 未知方法异常
     */
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public ResponseDto noSuchMethodExceptionHandler(NoSuchMethodException ex) {
       logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.UNKNOW_EX);
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public ResponseDto indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
       logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.ARRAY_EX);
    }

    /**
     * 400错误
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseDto requestNotReadable(HttpMessageNotReadableException ex) {
       logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.BAD_REQUEST);
    }

    /**
     * 400错误
     */
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public ResponseDto requestTypeMismatch(TypeMismatchException ex) {
       logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.BAD_REQUEST);
    }

    /**
     * 400错误
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResponseDto requestMissingServletRequest(MissingServletRequestParameterException ex) {
       logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.BAD_REQUEST);
    }

    /**
     * 405错误
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResponseDto request405() {
        return ResponseUtil.error(StatusCodeEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * 406错误
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public ResponseDto request406() {
        return ResponseUtil.error(StatusCodeEnum.NOT_ACCEPTABLE);
    }

    /**
     * 500错误
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public ResponseDto server500(RuntimeException ex) {
       logger.error(ex.getMessage());
        return ResponseUtil.error(StatusCodeEnum.SERVER_ERROR);
    }
}
