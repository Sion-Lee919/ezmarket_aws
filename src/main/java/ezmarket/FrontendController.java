package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    // "/ws" 경로는 제외한 나머지 경로를 React로 포워딩
    @RequestMapping(value = {"/{path:^(?!ws$).*$}", "/{path:^(?!ws$).*$}/**"})
    public String forward() {
        return "forward:/index.html";
    }
}




