package co.kr.easylogin.easylogin.kakao.data;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Profile {
    //닉네임
    //필요한 동의항목: 프로필 정보(닉네임/프로필 사진) 또는 닉네임
    private String nickname;

    //프로필 미리보기 이미지 URL
    //110px * 110px 또는 100px * 100px
    //필요한 동의항목: 프로필 정보(닉네임/프로필 사진) 또는 프로필 사진
    private String thumbnail_image_url;

    //프로필 사진 URL
    //640px * 640px 또는 480px * 480px
    //필요한 동의항목: 프로필 정보(닉네임/프로필 사진) 또는 프로필 사진
    private String profile_image_url;

    //프로필 사진 URL이 기본 프로필 사진 URL인지 여부
    //사용자가 등록한 프로필 사진이 없을 경우, 기본 프로필 사진 제공
    //true: 기본 프로필 사진
    //false: 사용자가 등록한 프로필 사진
    //필요한 동의항목: 프로필 정보(닉네임/프로필 사진) 또는 프로필 사진
    private Boolean is_default_image;

    //닉네임이 기본 닉네임인지 여부
    //사용자가 등록한 닉네임이 운영정책에 부합하지 않는 경우, "닉네임을 등록해주세요"가 기본 닉네임으로 적용됨
    //true: 기본 닉네임
    //false: 사용자가 등록한 닉네임
    //필요한 동의항목: 프로필 정보(닉네임/프로필 사진) 또는 닉네임
    private Boolean is_default_nickname;
}
