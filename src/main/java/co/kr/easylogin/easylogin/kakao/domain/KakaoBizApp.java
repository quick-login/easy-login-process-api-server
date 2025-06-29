package co.kr.easylogin.easylogin.kakao.domain;

import co.kr.easylogin.easylogin.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class KakaoBizApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 카카오 비즈앱 ID
    private Long appId;

    // 카카오 비즈앱 이름
    private String appName;

    // 카카오 비즈앱 REST API 키
    // 요청시에는 client_id로 요청
    private String restKey;

    // 최종 결과값 리다이렉트 시켜줄 url
    private String redirectUrl;
}
