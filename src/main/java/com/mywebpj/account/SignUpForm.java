package com.mywebpj.account;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignUpForm {
    //회원 가입시 받아올 데이터

    @NotBlank // 비어있으면 안된다.
    @Length(min = 3, max = 20) // 길이가 최소 3 최대 20글자
    @Pattern(regexp="^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String nickname;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 8, max = 50)
    private String password;


}
