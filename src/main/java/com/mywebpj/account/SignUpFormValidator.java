package com.mywebpj.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@RequiredArgsConstructor // final 붙은 애들 생성자 만들어준다 // implements 는 인터페이스를 구현
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;
//    @RequiredArgsConstructor
//    public SignUpFormValidator(AccountRepository accountRepository){
//        this.accountRepository = accountRepository;
//    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //TODO email, nickname이 중복되는지 아닌지
        SignUpForm signUpForm = (SignUpForm)target;
        if (accountRepository.existsByEmail(signUpForm.getEmail())){
            errors.rejectValue("email", "invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일 입니다.");
        }

        if (accountRepository.existsByNickname(signUpForm.getNickname())){
            errors.rejectValue("nickname", "invalid_nickname", new Object[]{signUpForm.getNickname()}, "이미 사용중인 닉네임 입니다.");
        }

    }

}
