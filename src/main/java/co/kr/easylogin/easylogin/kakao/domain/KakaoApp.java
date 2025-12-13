package co.kr.easylogin.easylogin.kakao.domain;

import co.kr.easylogin.easylogin.common.BaseEntity;
import co.kr.easylogin.easylogin.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoApp extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, unique = true)
    private Long appId;

    // 카카오에서는 최대 45글자인것같음
    @Column(nullable = false, length = 50)
    private String appName;

    // 카카오에서는 32글자 고정인것같은데 확실하진않으니 50자로 해둠
    @Column(nullable = false, length = 50)
    private String restKey;

    // 고객사로 정보 리다이렉트해줄 url
    @Column(nullable = false)
    private String redirectUrl;
}
