package org.validator.spring_validation.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleValidationException(Exception ex){
        Map<String, Object> response = new HashMap<>();

        BindingResult bindingResult = null;
        if(ex instanceof MethodArgumentNotValidException){
             bindingResult = ((MethodArgumentNotValidException)ex).getBindingResult();
        }

        Object target = bindingResult.getTarget();
        List<String> fieldOrder = List.of();

        List<FieldError> fieldErrors = new ArrayList<>(bindingResult.getFieldErrors());

        final List<String> finalFieldOrder = fieldOrder;
        fieldErrors.sort(Comparator.comparingInt(error -> {
            int index = finalFieldOrder.indexOf(error.getField());
            return index != -1 ? index : Integer.MAX_VALUE;
        }));

        FieldError firstError = fieldErrors.stream().findFirst().orElse(null);

        if (firstError != null) {
            return ResponseEntity.ok().body(Map.of("message", firstError.getDefaultMessage()));
        }

        return ResponseEntity.ok().body(Map.of("message", "요청이 유효하지 않습니다."));
    }
}
