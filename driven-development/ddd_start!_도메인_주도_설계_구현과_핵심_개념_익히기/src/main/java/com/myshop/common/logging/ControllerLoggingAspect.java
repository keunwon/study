package com.myshop.common.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class ControllerLoggingAspect {
    private final ObjectMapper mapper;

    @Pointcut("execution(public * com.myshop..*Controller.*(..))")
    public void onRequest() {}

    @Pointcut("execution(public * com.myshop..*Api.*(..))")
    public void onApiRequest() {}

    @Around("onRequest() || onApiRequest()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getServletRequestAttributes().getRequest();
        loggingRequest(request);

        Object proceed = joinPoint.proceed(joinPoint.getArgs());

        HttpServletResponse response = getServletRequestAttributes().getResponse();
        if (response == null) { return proceed; }
        loggingResponse(request, response, proceed);

        return proceed;
    }

    private void loggingRequest(HttpServletRequest request) throws IOException {
        log.info("========== [s] http request ==========");
        log.info("url: {}", request.getServletPath());
        log.info("method: {}", request.getMethod());
        log.info("content-type: {}", request.getContentType());
        log.info("request body: {}", mapper.writeValueAsString(request.getParameterMap()));
        log.info("========== [e] request api ==========");
    }

    private void loggingResponse(HttpServletRequest request, HttpServletResponse response, Object proceed) throws JsonProcessingException {
        log.info("========== [s] http response ===========");
        log.info("url: {}", request.getServletPath());
        log.info("method: {}", request.getMethod());
        log.info("content-type: {}", request.getContentType());
        log.info("status: {}", response.getStatus());
        if (proceed instanceof ResponseEntity<?> entity) {
            log.info("response body: {}", mapper.writeValueAsString(entity.getBody()));
        } else {
            log.info("response: {}", mapper.writeValueAsString(proceed));
        }
        log.info("========== [e] http response ===========");
    }

    private ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }
}
