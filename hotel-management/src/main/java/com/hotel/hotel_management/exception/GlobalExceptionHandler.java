package com.hotel.hotel_management.exception;

import com.hotel.hotel_management.dto.request.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String MIN_AGE_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(Exception exception){
        log.error("Exception: ", exception);
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.NO_CATCH.getCode());
        apiResponse.setMess(ErrorCode.NO_CATCH.getMess());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ApiResponse apiResponse = new ApiResponse();
        ErrorCode errorCode = exception.getErrorCode();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMess(errorCode.getMess());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDenied(AccessDeniedException exception){
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getHttpStatus()).body(ApiResponse.builder()
                .code(errorCode.getCode()).mess(errorCode.getMess()).build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation( MethodArgumentNotValidException exception){
        ApiResponse apiResponse = new ApiResponse();
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_TOKEN;

        Map<String, Object> attributes =null;
        try{
            errorCode = ErrorCode.valueOf(enumKey);
            var constrainViolation = exception.getBindingResult().getAllErrors()
                    .get(0).unwrap(ConstraintViolation.class);

            attributes = constrainViolation.getConstraintDescriptor().getAttributes();
            log.info("Validation error attributes: {}", attributes);
        }catch (IllegalArgumentException e){
        }
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMess(Objects.nonNull(attributes) ?
                mapAttribute(errorCode.getMess() , attributes): errorCode.getMess() );
        return ResponseEntity.badRequest().body(apiResponse);
    }

    private String mapAttribute(String mess, Map<String,Object> attributes){
        String minValue =String.valueOf( attributes.get(MIN_AGE_ATTRIBUTE)) ;
        return mess.replace("{"+ MIN_AGE_ATTRIBUTE +"}", minValue);
    }
}
