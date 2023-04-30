package com.study.restapipractice.dto;

import com.study.restapipractice.validation.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    @NotBlank(groups = ValidationGroups.NotBlankGroupId.class) //null,""," " 모두 안됨
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ID는 영어 대소문자와 숫자만 가능합니다.", groups = ValidationGroups.PatternCheckGroupId.class)
    private String id; //id


    @NotBlank(groups = ValidationGroups.NotBlankGroupPw.class)
    private String pw; //pw
}
