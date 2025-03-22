package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FrontendController {

    @RequestMapping(value = "/**")
    public String forward(HttpServletRequest request) {
        String uri = request.getRequestURI();

        // ws로 시작하면 React index.html로 포워딩하지 않음
        if (uri.startsWith("/ws")) {
            return "forward:"; 
        }

        return "forward:/index.html";
    }
}



