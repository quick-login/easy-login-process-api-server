package co.kr.easylogin.easylogin.kakao.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoAccount {

    // 사용자 동의 시 프로필 정보(닉네임/프로필 사진) 제공 가능
    // 필요한 동의항목: 프로필 정보(닉네임/프로필 사진)
    private Boolean profile_needs_agreement;

    // 사용자 동의 시 닉네임 제공 가능
    // 필요한 동의항목: 닉네임
    private Boolean profile_nickname_needs_agreement;

    // 사용자 동의 시 프로필 사진 제공 가능
    // 필요한 동의항목: 프로필 사진
    private Boolean profile_image_needs_agreement;

    // 프로필 정보
    // 필요한 동의항목: 프로필 정보(닉네임/프로필 사진), 닉네임, 프로필 사진
    private Profile profile;

    // 사용자 동의 시 카카오계정 이름 제공 가능
    // 필요한 동의항목: 이름
    private Boolean name_needs_agreement;

    //카카오계정 이름
    //필요한 동의항목: 이름
    private String name;

    //사용자 동의 시 카카오계정 대표 이메일 제공 가능
    //필요한 동의항목: 카카오계정(이메일)
    private Boolean email_needs_agreement;

    //이메일 유효 여부
    //true: 유효한 이메일
    //false: 이메일이 다른 카카오계정에 사용돼 만료
    //필요한 동의항목: 카카오계정(이메일)
    private Boolean is_email_valid;

    //이메일 인증 여부
    //true: 인증된 이메일
    //false: 인증되지 않은 이메일
    //필요한 동의항목: 카카오계정(이메일)
    private Boolean is_email_verified;

    //카카오계정 대표 이메일
    //필요한 동의항목: 카카오계정(이메일)
    //주의사항 : https://developers.kakao.com/docs/latest/ko/kakaologin/common#policy-user-info-email
    private String email;

    //사용자 동의 시 연령대 제공 가능
    //필요한 동의항목: 연령대
    private Boolean age_range_needs_agreement;

    //연령대
    //1~9: 1세 이상 10세 미만
    //10~14: 10세 이상 15세 미만
    //15~19: 15세 이상 20세 미만
    //20~29: 20세 이상 30세 미만
    //30~39: 30세 이상 40세 미만
    //40~49: 40세 이상 50세 미만
    //50~59: 50세 이상 60세 미만
    //60~69: 60세 이상 70세 미만
    //70~79: 70세 이상 80세 미만
    //80~89: 80세 이상 90세 미만
    //90~: 90세 이상
    //필요한 동의항목: 연령대
    private String age_range;

    //사용자 동의 시 출생 연도 제공 가능
    //필요한 동의항목: 출생 연도
    private Boolean birthyear_needs_agreement;

    //출생 연도(YYYY 형식)
    //필요한 동의항목: 출생 연도
    private String birthyear;

    //사용자 동의 시 생일 제공 가능
    //필요한 동의항목: 생일
    private Boolean birthday_needs_agreement;

    //생일(MMDD 형식)
    //필요한 동의항목: 생일
    private String birthday;

    //생일 타입
    //SOLAR(양력) 또는 LUNAR(음력)
    //필요한 동의항목: 생일
    private String birthday_type;

    //생일의 윤달 여부
    //필요한 동의항목: 생일
    private Boolean is_leap_month;

    //사용자 동의 시 성별 제공 가능
    //필요한 동의항목: 성별
    private Boolean gender_needs_agreement;

    //성별
    //female: 여성
    //male: 남성
    //필요한 동의항목: 성별
    private String gender;

    //사용자 동의 시 전화번호 제공 가능
    //필요한 동의항목: 카카오계정(전화번호)
    private Boolean phone_number_needs_agreement;

    //카카오계정의 전화번호
    //국내 번호인 경우 +82 00-0000-0000 형식
    //해외 번호인 경우 자릿수, 붙임표(-) 유무나 위치가 다를 수 있음
    //(참고: https://github.com/google/libphonenumber)
    //필요한 동의항목: 카카오계정(전화번호)
    private String phone_number;

    //사용자 동의 시 CI 참고 가능
    //필요한 동의항목: CI(연계정보)
    private Boolean ci_needs_agreement;

    //연계정보
    //필요한 동의항목: CI(연계정보)
    private String ci;

    //CI 발급 시각, UTC*
    //필요한 동의항목: CI(연계정보)
    private LocalDateTime ci_authenticated_at;
}
