package org.validator.spring_validation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.validator.spring_validation.validation.ValidationOrder;
import org.validator.spring_validation.vo.CommandVO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class DefaultController {

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> signUpController(@Validated(ValidationOrder.class) @ModelAttribute CommandVO commandVO){
        Map<String, Object> response = new HashMap<>();

        System.out.println(commandVO.toString());

//        // Errors 객체를 직접 다루어 처리하고 싶은 경우 파라미터에 Errors 객체를 주입받아 아래 코드와 함께 사용.
//        Map<String, String> validatorResult = new HashMap<>();
//        for(FieldError error : errors.getFieldErrors()) {
//            String validKeyName = String.format("%sErrorMsg", error.getField());
//            validatorResult.put(validKeyName, error.getDefaultMessage());
//        }

        response.put("message", "Hello World!");
        return ResponseEntity.ok().body(response);
    }

}
