package co.kr.easylogin.easylogin.kakao.controller;

import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ResultPageController {

    @GetMapping("/kakao/result")
    public String resultPage(@RequestParam(name = "encode") String encodedData, Model model) {
        String decodedJson = new String(Base64.getDecoder().decode(encodedData));

        model.addAttribute("result", decodedJson);

        return "successKakaoResult";
    }
}
