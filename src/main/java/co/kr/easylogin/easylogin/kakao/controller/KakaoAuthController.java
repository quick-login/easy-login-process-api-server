package co.kr.easylogin.easylogin.kakao.controller;

import co.kr.easylogin.easylogin.kakao.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/kakao")
@Slf4j
@RequiredArgsConstructor
public class KakaoAuthController {

    private final KakaoAuthService kakaoAuthService;

    // 카카오 로그인창 호출
    @GetMapping("/{appId}")
    public RedirectView kakaoLogin(@PathVariable(name = "appId") Long appId) {
        String kakaoAuthorizeUrl = kakaoAuthService.createKakaoAuthorizeUrl(appId);
        return new RedirectView(kakaoAuthorizeUrl);
    }
}
