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

        if (uri.startsWith("/ws") || uri.startsWith("/api") || uri.startsWith("/static") || uri.startsWith("/resources")) {
            return ResponseEntity.notFound().build();
        }

        return "forward:/index.html";
    }
}
