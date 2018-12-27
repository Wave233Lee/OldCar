package com.example.oldcar.exception;


import com.example.oldcar.domain.Result;
import com.example.oldcar.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class HandleExceptions {

	private final static Logger logger = LoggerFactory.getLogger(HandleExceptions.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result<Object> handle(Exception e) {
		if (e instanceof CarException) {
			// 自定义异常
			CarException eduException = (CarException) e;
			return ResultUtil.error(eduException);
		} else if (e instanceof MethodArgumentTypeMismatchException) {
			logger.error("【系统异常】 {}", e.getMessage());
			// 参数类型不匹配
			return ResultUtil.error(new CarException(EnumExceptions.ARGB_MISMATCH_EXCEPTION));
		} else {
			logger.error("【系统异常】 {}", e.getMessage());

			if (e.getMessage().contains("Request method")) {
				// 请求方法不匹配
				return ResultUtil.error(new CarException(EnumExceptions.REQUEST_METHOD));
			} else {
				return ResultUtil.error(new CarException(EnumExceptions.UNKNOWN_ERROR));
			}
		}
	}
}
