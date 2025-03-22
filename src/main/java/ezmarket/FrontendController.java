package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FrontendController {

    @RequestMapping(value = "/**/{path:[^\\.]*}")
    public String redirect(HttpServletRequest request) {
        String uri = request.getRequestURI();

        // WebSocket 요청은 모두 무시 (/ws, /ws/, /ws/abc 등)
        if (uri.startsWith("/ws/") || uri.equals("/ws")) {
            return null; // ViewResolver 타지 않게 null 반환
        }

        return "forward:/index.html";
    }
}
