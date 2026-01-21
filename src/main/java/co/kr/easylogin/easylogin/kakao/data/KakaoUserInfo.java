package co.kr.easylogin.easylogin.kakao.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class KakaoUserInfo {

    // 회원번호
    private Long id;

    // 서비스에 연결 완료된 시각, UTC*
    private String connected_at;

    // 카카오싱크 간편가입으로 로그인한 시각, UTC*
    private String synched_at;

    //카카오계정 정보
    private KakaoAccount kakao_account;

    @Setter
    private String state;
}
