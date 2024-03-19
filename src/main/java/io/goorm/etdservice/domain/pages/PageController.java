package io.goorm.etdservice.domain.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/")
    public String homePage() {
        return "home"; // "hello"는 템플릿의 파일명 (hello.html)를 가리킵니다.
    }

    @GetMapping("/games")
    public String gamePage() {
        return "pages/games/games";
    }

    @GetMapping("/games/palworld")
    public String gamePalworldPage() {
        return "pages/games/palworld";
    }

    @GetMapping("/games/enshrouded")
    public String gameEnshroudedPage() {
        return "pages/games/enshrouded";
    }


    @GetMapping("/login-page")
    String login() {
        return "login";
    }

    // 로그인 처리 여부
    @GetMapping("/login/login-success")
    public String loginSuccess() {
        return "login-success";
    }

    @GetMapping("/login/login-failure")
    public String loginFailure() {
        return "login-failure";
    }

}
