package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FrontendController {

    @RequestMapping(value = {
            "/", 
            "/{path:[^\\.]*}", 
            "/**/{path:[^\\.]*}"
    })
    public String forward(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri.startsWith("/ws")) return null; // WebSocket은 제외
        return "forward:/index.html";
    }
}


