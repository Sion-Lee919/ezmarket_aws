package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    // 정적 리소스나 API가 아닌 SPA 경로에 대해서만 index.html로 포워딩
    @RequestMapping(value = {
        "/{path:^(?!ws$|ws/|api/|static/|resources/|favicon\\.ico|.*\\..*$).*$}",
        "/**/{path:^(?!ws$|ws/|api/|static/|resources/|favicon\\.ico|.*\\..*$).*$}"
    })
    public String forward() {
        return "forward:/index.html";
    }
}


