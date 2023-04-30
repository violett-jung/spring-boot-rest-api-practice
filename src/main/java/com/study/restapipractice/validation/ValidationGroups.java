package com.study.restapipractice.validation;

public class ValidationGroups {
    public interface NotBlankGroupId {};
    public interface NotBlankGroupPw {};
    public interface NotBlankGroupName {};
    public interface NotBlankGroupEmail {};
    public interface NotNullGroupRole {};
    public interface NotNullGroupState {};
    public interface PatternCheckGroupId {};
    public interface EmailCheckGroup {};
    public interface MinValueCheckGroupRole {};
    public interface MaxValueCheckGroupRole {};
    public interface MinValueCheckGroupState {};
    public interface MaxValueCheckGroupState {};
}
