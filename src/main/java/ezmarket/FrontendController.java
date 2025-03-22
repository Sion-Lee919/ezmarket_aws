package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping(value = {"/{path:^(?!ws$).*$}", "/{path:^(?!ws$).*$}/**"})
    public String forward() {
        return "forward:/index.html";
    }
}

