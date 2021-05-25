package org.nekostudio.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.nekostudio.common.JsonResult;
import org.nekostudio.common.ResultEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author neko
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");

        JsonResult res = new JsonResult();
        if (e instanceof InsufficientAuthenticationException) {
            res = JsonResult.fail(ResultEnum.NO_LOGIN);
        }else if(e instanceof BadCredentialsException){
            res = JsonResult.fail(ResultEnum.WRONG_PASSWORD);
        }
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(res);
        try(PrintWriter writer = httpServletResponse.getWriter()){
            writer.write(value);
            writer.flush();
        }

    }
}
