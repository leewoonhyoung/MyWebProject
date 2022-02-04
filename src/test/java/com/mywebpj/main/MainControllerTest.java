package com.mywebpj.main;

import com.mywebpj.account.AccountRepository;
import com.mywebpj.account.AccountService;
import com.mywebpj.account.SignUpForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;


    @BeforeEach
    void addAccount(){
        //회원 가입
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setNickname("lee");
        signUpForm.setEmail("lee@email.com");
        signUpForm.setPassword("12345678");
        accountService.processNewAccount(signUpForm);

    }
    @AfterEach
    void afterEach(){
        accountRepository.deleteAll();
    }


    @DisplayName("이메일로 로그인 성공")
    @Test
    void login_with_email() throws Exception{
        mockMvc.perform(post("/login")
                .param("username", "lee")
                .param("password", "12345678")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("lee"));
    }


    @DisplayName("닉네임으로 로그인 성공")
    @Test
    void login_with_nickname() throws Exception{
        mockMvc.perform(post("/login")
                .param("nickname", "lee")
                .param("password", "12345678")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("lee"));



    }
}