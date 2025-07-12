package co.kr.easylogin.easylogin.kakao.controller;

import co.kr.easylogin.easylogin.kakao.service.KakaoAuthService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //인가코드 받아서 카카오 로그인 프로세싱
    @GetMapping("/process/{appId}")
    public RedirectView kakaoLoginProcess(@PathVariable(name = "appId") Long appId,
                                    @RequestParam(name = "code", required = false) String code,
                                    @RequestParam(name = "error", required = false) String error,
                                    @RequestParam(name = "error_description", required = false) String errorDescription,
                                    @RequestParam(name = "state", required = false) String state) { //state 는 아직 지원 X

//        if(error != null) {
//            Map<String, String> result = new HashMap<>();
//            result.put("error", error);
//            result.put("error_description", errorDescription);
//            return new RedirectView()
//        }

        return kakaoAuthService.kakaoAuthorizeProcess(appId, code);
    }
}
