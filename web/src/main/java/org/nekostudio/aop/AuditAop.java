package org.nekostudio.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.nekostudio.annotation.Audit;
import org.nekostudio.es.repository.Record;
import org.nekostudio.es.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neko
 */
@Aspect
@Component
@Slf4j
public class AuditAop {

    @Autowired
    private RecordRepository recordRepository;

    @Pointcut("@annotation(audit)")
    public void AuditPointCut(Audit audit){};

    @Around(value = "AuditPointCut(audit)",argNames = "joinPoint,audit")
    public Object doAround(ProceedingJoinPoint joinPoint, Audit audit) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name ="";
        if (principal !=null) {
             name = (String) principal;
        }
        Map<String, Object> argObj = getNameAndValue(joinPoint);
        Record record = new Record()
                .setOperation(audit.type())
                .setUsername(name)
                .setArgData(argObj)
                .setUpdateTime(LocalDateTime.now())
                .setDescription(audit.description());
        Record save = recordRepository.save(record);
        return joinPoint.proceed();
    }

    Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }

}
