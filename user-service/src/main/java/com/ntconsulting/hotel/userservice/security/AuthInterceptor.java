package com.ntconsulting.hotel.userservice.security;

import com.ntconsulting.hotel.userservice.web.rest.util.error.ApiError;
import com.ntconsulting.hotel.userservice.web.rest.util.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.ParseException;
import java.time.LocalDateTime;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    final static String HEADER_NAME_AUTH = "Authorization";

    private static final String KEY = "123456789";
    private final static Logger LOG = LoggerFactory.getLogger(AuthInterceptor.class);

    public AuthInterceptor() {
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {

            LOG.debug("M=preHandle, requestUrl={}, requestMethod={}", request.getRequestURL(), request.getMethod());

            if (checksIfItHasAnnotationToBypassUrl(handler)) {
                return true;
            }

            verifyValidAuthorizationToken(request);

            return true;
        } catch (Exception e) {
            throw new UnauthorizedException(new ApiError(LocalDateTime.now().toString(), HttpStatus.UNAUTHORIZED, 4001, "Error usuario nao autorizado"));
        }
    }

    private void verifyValidAuthorizationToken(HttpServletRequest request) throws ParseException {
        String token = getBearerToken(request.getHeader(HEADER_NAME_AUTH));

        verifyKey(token);
    }


    private void verifyKey(String token) {
        try {
            if (!token.equals(KEY)) {
                throw new UnauthorizedException(new ApiError(LocalDateTime.now().toString(), HttpStatus.UNAUTHORIZED, 4001, "Error usuario nao autorizado"));
            }
        } catch (Exception e) {
            throw new UnauthorizedException(new ApiError(LocalDateTime.now().toString(), HttpStatus.UNAUTHORIZED, 4001, "Error usuario nao autorizado"));
        }
    }

    private String getBearerToken(String token) {
        return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;
    }

    private boolean checksIfItHasAnnotationToBypassUrl(Object handler) {
        return ((HandlerMethod) handler).getMethod().getAnnotation(WithoutAuthenticating.class) != null;
    }
}
