package org.nekostudio.security.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.nekostudio.common.JsonResult;
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
        httpServletResponse.setContentType("application/json;charset=UTF8");
        JsonResult errResult = new JsonResult().setCode(4001);
        Object exception = httpServletRequest.getAttribute("jwt.exception");
        if (exception != null) {
            errResult = handleJwtException(exception);
        } else {
            errResult.setMsg("请登录").setCode(40001);
        }
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(errResult);
        log.error("AuthenticationEntryPoint {}\n", e.getMessage());
        try (PrintWriter writer = httpServletResponse.getWriter()) {
            writer.write(value);
            writer.flush();
        }

    }

    private JsonResult handleJwtException(Object e) {
        if (e instanceof JWTVerificationException) {
            JWTVerificationException jwtVerificationException = (JWTVerificationException) e;
            String message = jwtVerificationException.getMessage();
            return new JsonResult().setMsg(message).setCode(4002);
        }
        return null;
    }
}
