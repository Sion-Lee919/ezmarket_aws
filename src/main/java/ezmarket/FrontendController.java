package ezmarket;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FrontendController {

    @RequestMapping(value = "/**/{path:[^\\.]*}")
    public Object redirect(HttpServletRequest request) {
        String uri = request.getRequestURI();

        // WebSocket 요청은 스프링이 알아서 처리하게 그냥 둠 (forward, 404 둘 다 안함)
        if (uri.startsWith("/ws")) {
            return null; // 아무 처리도 하지 않음
        }

        // API, 정적 리소스는 무시 (원래 라우팅되도록)
        if (uri.startsWith("/api") || uri.startsWith("/static") || uri.startsWith("/resources")) {
            return ResponseEntity.notFound().build(); // 또는 return null; 도 가능
        }

        return "forward:/index.html";
    }
}

