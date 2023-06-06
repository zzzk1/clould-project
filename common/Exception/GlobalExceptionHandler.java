package Exception;

import api.Result;
import api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zzzk1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public Result<ResultCode> handleBizException(BizException bizException) {
        log.warn("业务异常:{}", bizException.getError().getMessage(), bizException);
        return Result.Failed(bizException.getError());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<ResultCode> handleRunTimeException(RuntimeException e) {
        log.warn("运行时异常: {}", e.getMessage(), e);
        return Result.Failed(ResultCode.FORBIDDEN);
    }
}
