package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    // 정규식에서 WebSocket 경로 제외하고, .으로 끝나는 경로도 제외
    @RequestMapping(value = {"/{path:[^\\.]+}", "/**/{path:^(?!ws)(?!.*\\..*).*$}"})
    public String forward() {
        return "forward:/index.html";
    }
}

