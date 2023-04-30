package com.study.restapipractice.dto;

import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.validation.ValidationGroups;

import javax.validation.constraints.*;

public class ModifyMemberDto {
    @NotBlank(groups = ValidationGroups.NotBlankGroupId.class) //null,""," " 모두 안됨
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ID는 영어 대소문자와 숫자만 가능합니다.", groups = ValidationGroups.PatternCheckGroupId.class)
    private String id; //id


    @NotBlank(groups = ValidationGroups.NotBlankGroupPw.class)
    private String pw; //pw


    @NotBlank(groups = ValidationGroups.NotBlankGroupName.class)
    private String name; //이름


    @NotBlank(groups = ValidationGroups.NotBlankGroupEmail.class)
    @Email(groups = ValidationGroups.EmailCheckGroup.class)
    private String email; //메일주소


    //    @JsonDeserialize(using = JsonUtil.PhoneNumberDeserializer.class)
    private String hp; //(옵션)폰번호

    /* Spring Boot에서는 RequestBody로 전송된 JSON 데이터의 필드 값을 자동으로 매핑하여 객체로 변환해주는 기능을 제공
     * 이때, 기본적으로 Jackson 라이브러리를 사용하며, Jackson은 문자열을 해당 필드의 타입으로 자동 변환해주는 기능 가짐
     *  이때, JSON 데이터의 필드 값이 null이거나 빈 문자열("")일 경우 Jackson은 해당 필드를 객체의 필드에 null로 설정
     * "role": "1"과 같은 문자열이 RequestBody로 전송된 경우에도 Jackson이 자동으로 문자열을 Integer형으로 변환하여 User 객체의 role 필드에 할당 -> 따라서 에러발생하지 않음
     *
     * * 해당 필드가 Integer형이 아닌 다른 타입으로 지정되어 있거나, 문자열 값을 수동으로 변환해주어야 하는 경우에는
     * @JsonDeserialize 어노테이션 등을 사용하여 Jackson이 해당 값을 원하는 타입으로 변환하도록 설정할 수 있음
     *
     * * 예시 : role 필드에 "", null 값이 들어올 경우 0으로 들어오도록 하기
     * -> role @JsonDeserialize 어노테이션을 사용하여 해당 필드를 Integer 타입으로 변환하고, 동시에 default 값을 0으로 설정
     * @JsonDeserialize(using = JsonUtil.IntegerDeserializer.class)
     * private Integer role; //접근권한(1 관리자, 2 일반인)
     * */


    @NotNull(groups = ValidationGroups.NotNullGroupRole.class) //원래 null만 검증실패여야하는데 null,""," " 모두 검증실패(이유: Jackson의 자동 변환 기능때문이었음)
    @Min(value = 1, message = "접근 권한은 1 또는 2이어야 합니다.", groups = ValidationGroups.MinValueCheckGroupRole.class)
    @Max(value = 2, message = "접근 권한은 1 또는 2이어야 합니다.", groups = ValidationGroups.MaxValueCheckGroupRole.class)
    private Integer role; //접근권한(1 관리자, 2 일반인)

    @NotNull(groups = ValidationGroups.NotNullGroupRole.class)
    @Min(value = 1, message = "접근 권한은 1 또는 2이어야 합니다.", groups = ValidationGroups.MinValueCheckGroupState.class)
    @Max(value = 2, message = "접근 권한은 1 또는 2이어야 합니다.", groups = ValidationGroups.MaxValueCheckGroupState.class)
    private Integer state; //활성화(1 활성화, 2 비활성화)

    private String description; //(옵션)설명

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .id(this.id)
                .pw(this.pw)
                .name(this.name)
                .email(this.email)
                .hp(this.hp)
                .role(this.role)
                .state(this.state)
                .description(this.description)
                .build();
    }
}
