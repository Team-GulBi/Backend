//package com.gulbi.Backend.global.util.logging;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageCreateRequestDto;
//import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
//import com.gulbi.Backend.domain.user.service.UserService;
//import com.gulbi.Backend.global.util.entity.LogData;
//import com.gulbi.Backend.global.util.entity.LogDataCreateRequestDto;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.*;
//import java.util.stream.Collectors;
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class LoggingAspect {
//    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
//    private Long startTime;
//    private String methodName;
//    private String endPoint;
//    private String requestId;
//    private Long executionTime;
//    private List<Object> args;
//    private final ObjectMapper objectMapper;
//
//    @Pointcut("execution(* com.gulbi.Backend.domain.rental.product.controller.*.*(..))")
//    public void serviceMethods() {
//    }
//
//    @Before("serviceMethods()")
//    public void logMethodStart(JoinPoint joinPoint) {
//        startTime = System.currentTimeMillis();
//        requestId = UUID.randomUUID().toString();
//        args = toList(joinPoint.getArgs());
//    }
//
//    // @AfterReturning은 메소드 실행 후 정상 반환 시 호출됩니다.
//    @AfterReturning("serviceMethods()")
//    public void logMethodReturn(JoinPoint joinPoint) {
//        try {
//            executionTime = calculateExecutionTime();
//            List<String> serializedArgs = serializeList(args);
//            methodName = joinPoint.getSignature().getName();
//            String userId = getAuthenticatedUserId();
//            Long timeStamp = System.currentTimeMillis();
//            String className = joinPoint.getTarget().getClass().getName();
//            String level = "INFO";
//            String httpMethod = getHttpMethod(joinPoint);
//            String message = "";
//            LogData logData = LogData.of(timeStamp, level, requestId, className, methodName, userId, httpMethod,executionTime, message,serializedArgs);
//            logData.log();
//
//        } catch (Exception e) {
//            // 예외가 발생하면 예외를 다시 던지기 전에 로그를 찍어줍니다.
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    // 예외가 발생한 경우 처리
//    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
//    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
//        try {
//            executionTime = calculateExecutionTime();
//            List<String> serializedArgs = serializeList(args);
//            String userId = getAuthenticatedUserId();
//            Long timeStamp = System.currentTimeMillis();
//            String className = joinPoint.getTarget().getClass().getName();
//            String level = "ERROR";
//            String exceptionMessage = exception.getMessage();
//            System.out.println("{"
//                    + "\"timestamp\": \"" + timeStamp + "\","
//                    + "\"level\": \"" + level + "\","
//                    + "\"request_id\": \"" + requestId + "\","
//                    + "\"class_name\": \"" + className + "\","
//                    + "\"method_name\": \"" + methodName + "\","
//                    + "\"user_id\": \"" + userId + "\","
//                    + "\"http_method\": \"" + getHttpMethod(joinPoint) + "\","
//                    + "\"response_time_ms\": \"" + executionTime + "\","
//                    + "\"args\": " + serializedArgs.toString() + ","
//                    + "\"error_message\": \"" + exceptionMessage + "\""
//                    + "}"
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    // HTTP 메소드 정보 추출 예시 (필요에 따라 수정 가능)
//    private String getHttpMethod(JoinPoint joinPoint) {
//        // 실제 HTTP 메소드에 따라 수정
//        return "UNKNOWN"; // 예시로 "UNKNOWN"
//    }
//
//    // 실행 시간을 계산하는 메소드
//    private Long calculateExecutionTime() {
//        return System.currentTimeMillis() - startTime;
//    }
//
//    // ProductImageCollection을 제외한 인수 리스트로 변환
//    private List<Object> toList(Object[] argsArray) {
//        return Arrays.stream(argsArray)
//                .filter(arg -> !(arg instanceof ProductImageCollection)) // ProductImageCollection 제외
//                .collect(Collectors.toList());
//    }
//
//    // FIXME: 이미지 관련 dto는 전부 직렬화를 안하도록 별도의 검증 메서드 분리. 서비스 동작에 지장은 없으나.
//    private List<String> serializeList(List<Object> args) {
//        List<String> serializedArgs = new ArrayList<>();
//        for (Object arg : args) {
//            try {
//                if(arg instanceof ProductImageCreateRequestDto){
//                   continue;
//                }
//                serializedArgs.add(objectMapper.writeValueAsString(arg));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//        return serializedArgs;
//    }
//
//    // 인증된 사용자 ID를 가져오는 메소드
//    private String getAuthenticatedUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return (authentication != null && authentication.isAuthenticated()) ? authentication.getName() : "anonymous";
//    }
//}
