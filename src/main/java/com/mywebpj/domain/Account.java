package com.mywebpj.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter@Setter
@EqualsAndHashCode(of="id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id@GeneratedValue
    private Long id;

    @Column(unique = true)// 중복된 이메일 사용제한
    private String email;

    @Column(unique = true) // 중복된 닉네임 사용제한
    private String nickname;

    private String password; //로그인시 password

    private boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime joinedAt;

    private String bio;

    private String url;

    private String occupation;

    private String location; // String은 db 에 varchar(255)

    @Lob@Basic(fetch = FetchType.EAGER) // @Lob = 대용량 , eager를 통해 유저 로그인시  바로 쓰기위해!
    private String profileImage;

    private boolean studyCreatedByEmail; // boolean  은 true or false

    private boolean studyCreatedByWeb;

    private LocalDateTime emailCheckTokenGeneratedAt;

    private boolean studyEnrollmentResultByWeb;

    private boolean studyUpdatedByEmail;

    private boolean studyUpdatedByWeb;

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }

    public void completeSignUp() {
        this.setEmailVerified(true);
        this.setJoinedAt(LocalDateTime.now());
    }

    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }

    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
    }
}
