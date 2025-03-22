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

        if (uri.startsWith("/ws")) {
            return ResponseEntity.notFound().build(); // view resolver 타지 않게 명확히
        }

        return "forward:/index.html";
    }
}
