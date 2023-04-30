package com.study.restapipractice.validation;

import lombok.Builder;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import static com.study.restapipractice.validation.ValidationGroups.*;


@GroupSequence({
        Default.class,
        NotBlankGroupId.class,
        NotBlankGroupPw.class,
        NotBlankGroupName.class,
        NotBlankGroupEmail.class,
        NotNullGroupRole.class,
        PatternCheckGroupId.class,
        EmailCheckGroup.class,
        MinValueCheckGroupRole.class,
        MaxValueCheckGroupRole.class,
        MinValueCheckGroupState.class,
        MaxValueCheckGroupState.class})
public interface ValidationSequence {
}
