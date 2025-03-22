package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FrontendController {

    @RequestMapping(value = "/**")
    public String forward(HttpServletRequest request) {
        String uri = request.getRequestURI();

        // WebSocket 경로는 제외
        if (uri.startsWith("/ws")) {
            return null; // 더 이상 포워딩 안 함 → WebSocket 핸들러로 빠짐
        }

        return "forward:/index.html";
    }
}


