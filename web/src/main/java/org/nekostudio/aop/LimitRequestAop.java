package org.nekostudio.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nekostudio.annotation.LimitRequest;
import org.nekostudio.common.JsonResult;
import org.nekostudio.common.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author neko
 */
@Slf4j
@Aspect
@Component
public class LimitRequestAop {

    private final static String KEY_PREFIX = "limit_request";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Pointcut("@annotation(limitRequest)")
    public void limitRequestPointCut(LimitRequest limitRequest) {
    }

    @Around(value = "limitRequestPointCut(limitRequest)", argNames = "joinPoint,limitRequest")
    public Object doAround(ProceedingJoinPoint joinPoint, LimitRequest limitRequest) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            HttpServletRequest request = requestAttributes.getRequest();
            String remoteAddr = request.getRemoteAddr();
            String redisKey = KEY_PREFIX + ":" + className + ":" + methodName + "#" + remoteAddr.replace(":", "-");
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            Integer count = (Integer) valueOperations.get(redisKey);
            if (count != null && count > limitRequest.frequency()) {
                return JsonResult.fail(ResultEnum.TOO_MANY_REQUEST_EXCEPTION);
            }
            if (count == null) {
                valueOperations.set(redisKey, 1, limitRequest.second(), TimeUnit.SECONDS);
            }
            if (count != null && count <= limitRequest.frequency()) {
                valueOperations.set(redisKey, count + 1, limitRequest.second(), TimeUnit.SECONDS);
            }
        }
        return joinPoint.proceed();
    }
}
