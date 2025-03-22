package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    // 정규식으로 .으로 끝나지 않고 ws로 시작하지 않는 모든 경로 매칭
    @RequestMapping(value = { "/{path:^(?!ws$)[^\\.]*}", "/**/{path:^(?!ws).*}" })
    public String forward() {
        return "forward:/index.html";
    }
}
