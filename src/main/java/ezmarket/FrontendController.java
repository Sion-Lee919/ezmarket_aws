package ezmarket;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FrontendController {

    @RequestMapping(value = {"/{path:[^\\.]*}", "/**/{path:^(?!ws$).*$}"})
    public Object forward(HttpServletRequest request) throws IOException {
        String uri = request.getRequestURI();

        // WebSocket 경로는 무시
        if (uri.startsWith("/ws")) {
            return null;
        }

        // index.html 존재 확인 후 forward
        if (new ClassPathResource("static/index.html").exists()) {
            return "forward:/index.html";
        } else {
            return new ModelAndView("error/404");
        }
    }
}





