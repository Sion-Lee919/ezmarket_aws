package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FrontendController {

	@RequestMapping(value = "/**/{path:[^\\.]*}")
    public String redirect(HttpServletRequest request) {
        String uri = request.getRequestURI();

        // WebSocket 요청은 무시하고 포워드하지 않음
        if (uri.startsWith("/ws")) {
            return null; // ViewResolver에도 걸리지 않게
        }

        return "forward:/index.html";
    }
}



