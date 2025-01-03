package org.validator.spring_validation.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.validator.spring_validation.validation.ValidationGroups.*;


import java.util.List;

@Data
public class CommandVO {

    @NotBlank(message = "아이디는 필수정보입니다.", groups= NotBlankGroup.class)
    @Pattern(regexp = "^[a-z]+[a-z0-9]{5,20}", message = "아이디는 5~20자의 영문/숫자로만 구성해주세요.", groups=PatternGroup.class)
    private String username;

    @NotBlank(message = "비밀번호는 필수정보입니다.", groups=NotBlankGroup.class)
    @Pattern(regexp = "^(?=.*\\S+$)(?=.*[\\W_]).{8,16}$", message = "비밀번호는 8~16자, 특수문자를 반드시 포함해야 합니다.", groups=PatternGroup.class)
    private String password;

    @NotBlank(message = "이메일은 필수정보입니다.", groups=NotBlankGroup.class)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "올바른 이메일 형식이 아닙니다.", groups=PatternGroup.class)
    private String email;

    public List<String> getFieldOrder() {
        return List.of(
                "username",
                "password",
                "email"
        );
    }

}
