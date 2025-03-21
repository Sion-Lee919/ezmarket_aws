package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping({ "/", "/{path:^(?!api|static|.*\\..*).*$}", "/**/{subpath:^(?!api|static|.*\\..*).*$}" })
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}
