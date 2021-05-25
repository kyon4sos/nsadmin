package org.nekostudio.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.nekostudio.common.JsonResult;
import org.nekostudio.common.ResultEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author neko
 */
@Component
@Slf4j
public class JwtAccessDenieHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(JsonResult.fail(ResultEnum.NO_PERMISSION));
        log.error("AccessDeniedHandler {}\n",e.getMessage());
        try(PrintWriter writer = httpServletResponse.getWriter()){
            writer.write(String.valueOf(value));
            writer.flush();
        }
    }
}
