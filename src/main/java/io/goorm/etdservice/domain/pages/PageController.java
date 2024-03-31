package io.goorm.etdservice.domain.pages;

import io.goorm.etdservice.domain.games.dto.GameDto;
import io.goorm.etdservice.domain.games.service.GameService;
import io.goorm.etdservice.global.exception.DomainException;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PageController {

    private final GameService gameService;


    @GetMapping("/")
    public String homePage() {
        return "home"; // "hello"는 템플릿의 파일명 (hello.html)를 가리킵니다.
    }

    @GetMapping("/games")
    public String gamePage(Model model) {
        model.addAttribute("title","Games");
        return "pages/games/games";
    }

    @GetMapping("/games/{gameId}")
    public String gameDetailPage(Model model, @PathVariable("gameId") Long gameId) throws DomainException {
        GameDto game = gameService.getGame(gameId);
        model.addAttribute("title",game.getName());
        return "pages/games/game-detail";
    }

    @GetMapping("/servers/{serverId}")
    public String serverDetailPage(Model model, @PathVariable("serverId") UUID serverId) throws DomainException {
        model.addAttribute("title","Server");
        model.addAttribute("serverId",serverId.toString());
        return "pages/servers/server-detail";
    }


    //TODO Member 기능 완료 시, memberId PathVariable 추가할 것
    //TODO 기존 members API 경로에 api 가 붙지 않아서 Path 겹침 해당 부분 수정할 것
    @GetMapping("/member")
    public String myPage(Model model) {
        model.addAttribute("title","My Page");
        return "pages/members/member";
    }


    @GetMapping("/login")
    String login() {
        return "pages/login";
    }

}
