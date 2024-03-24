package io.goorm.etdservice.domain.pages;

import io.goorm.etdservice.domain.games.dto.GameDto;
import io.goorm.etdservice.domain.games.service.GameService;
import io.goorm.etdservice.global.exception.DomainException;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private GameService gameService;


    @GetMapping("/")
    public String homePage() {
        return "home"; // "hello"는 템플릿의 파일명 (hello.html)를 가리킵니다.
    }

    @GetMapping("/games")
    public String gamePage(Model model) {
        model.addAttribute("title","Games");
        return "pages/games/games";
    }

    @GetMapping("/games/palworld")
    public String gamePalworldPage(Model model) {
        model.addAttribute("title","Palworld");
        return "pages/games/palworld";
    }

    @GetMapping("/games/enshrouded")
    public String gameEnshroudedPage(Model model) {
        model.addAttribute("title","Enshrouded");
        return "pages/games/enshrouded";
    }

    @GetMapping("/games/{gameId}")
    public String gameDetailPage(Model model, @PathParam("gameId") Long gameId) throws DomainException {
        GameDto game = gameService.getGame(gameId);
        model.addAttribute("title",game.getName());
        return "pages/games/game-detail";
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
