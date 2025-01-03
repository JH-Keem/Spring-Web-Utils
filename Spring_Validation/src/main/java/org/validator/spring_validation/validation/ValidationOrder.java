package org.validator.spring_validation.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({
        // 이 순서대로 유효성 검증을 실행합니다.
        ValidationGroups.NotBlankGroup.class,
        ValidationGroups.SizeGroup.class,
        ValidationGroups.PatternGroup.class,
})
public interface ValidationOrder {
}
