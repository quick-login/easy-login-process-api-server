package co.kr.easylogin.easylogin.member.domain;

import co.kr.easylogin.easylogin.common.BaseEntity;
import co.kr.easylogin.easylogin.member.value.MemberRole;
import co.kr.easylogin.easylogin.member.value.MemberStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Entity
@Getter
public class Member extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;

    @Column(nullable = false)
    private Long remainCount;

    @Column(nullable = false)
    private Long maxKakaoAppCount;

    @Column(length = 20)
    private String kakaoId;

    @Column(nullable = false)
    private Long cash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status;

    public void useRemainCount() {
        this.remainCount = remainCount - 1;
    }
}
